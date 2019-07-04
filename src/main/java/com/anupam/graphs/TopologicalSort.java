package com.anupam.graphs;

import java.util.HashMap;
import java.util.Map;

public class TopologicalSort {

    private DiGraph diGraph;
    private Map<Integer, Integer> vertexTopologicalOrderMap = new HashMap<>();
    private boolean[] visited;
    private int current_label = 0;

    public TopologicalSort(DiGraph diGraph){
        this.diGraph = diGraph;
        visited = new boolean[diGraph.getNumVertex()];
        current_label = diGraph.getNumVertex();
    }

    public Map<Integer, Integer> getTopologicalOrder(){
        for (int vertex: diGraph.getVertexes()){
            if(!visited[vertex])
               dfs(diGraph, vertex);
        }
        return vertexTopologicalOrderMap;
    }

    private void dfs(DiGraph G, int vertex){
        visited[vertex] = true;
        for(int neighbour: G.getNeighbours(vertex)){
            if(!visited[neighbour])
                dfs(G, neighbour);
        }
        vertexTopologicalOrderMap.put(vertex, current_label);
        current_label--;
    }
}
