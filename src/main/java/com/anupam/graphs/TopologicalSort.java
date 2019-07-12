package com.anupam.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TopologicalSort {
    private DiGraph diGraph;
    private int current_label;
    private Map<Integer, Boolean> visitedMap;
    // Key is the topological order and value is the vertex
    private Map<Integer, Integer> verterTopologicalOrderMap;

    public TopologicalSort(DiGraph diGraph){
        this.diGraph = diGraph;
        current_label = diGraph.getNumVertex();
        verterTopologicalOrderMap = new TreeMap<>();
        visitedMap = new HashMap<>();
    }

    public Map<Integer, Integer> getTopologicalOrder(){
        for(int vertex: diGraph.getVertexes()){
            if(!visitedMap.containsKey(vertex))
                dfs(diGraph, vertex);
        }
        return verterTopologicalOrderMap;
    }

    private void dfs(DiGraph diGraph, int sourceVertex){
        visitedMap.put(sourceVertex, true);
        for(int neighbour: diGraph.getNeighbours(sourceVertex)){
            if(!visitedMap.containsKey(neighbour))
                dfs(diGraph, neighbour);
        }
        verterTopologicalOrderMap.put(current_label, sourceVertex);
        current_label--;
    }


}
