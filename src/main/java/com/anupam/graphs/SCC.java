package com.anupam.graphs;

import java.util.*;

public class SCC implements IConnectedComponents{
    private DiGraph diGraph;
    private SccVertexOrder sccVertexOrder;
    private Map<Integer, Boolean> visitedMap;
    private Map<Integer, Integer> vertexComponentIdMap;
    int componentId = 0;

    public SCC(DiGraph diGraph){
        this.diGraph = diGraph;
        sccVertexOrder = new SccVertexOrder();
        visitedMap = new HashMap<>();
        vertexComponentIdMap = new HashMap<>();
        getSCCs();
    }

    private void getSCCs(){
        // iterate through the vertices starting from vertex with largest order
        for(int vertex: sccVertexOrder.getSccVertexOrdering()){
            if(!visitedMap.containsKey(vertex)) {
                dfs(diGraph, vertex, componentId);
                componentId++;
            }
        }
    }

    private void dfs(DiGraph diGraph, int sourceVertex, int componentId){
        visitedMap.put(sourceVertex, true);
        for(int vertex: diGraph.getNeighbours(sourceVertex)){
            if(!visitedMap.containsKey(vertex))
                dfs(diGraph, vertex, componentId);
        }
        vertexComponentIdMap.put(sourceVertex, componentId);
    }

    @Override
    public boolean isConnected(int v1, int v2) {
        return vertexComponentIdMap.get(v1) == vertexComponentIdMap.get(v2);
    }

    @Override
    public int getComponentId(int vertex) {
        return vertexComponentIdMap.get(vertex);
    }

    @Override
    public int connectComponentCount() {
        return componentId;
    }

    @Override
    public void printVertexComponentId() {
        for(int vertex: diGraph.getVertexes()){
            System.out.println(String.format("Vertex: %s, componentId: %s", vertex, vertexComponentIdMap.get(vertex)));
        }
    }

    private class SccVertexOrder {
        private Map<Integer, Boolean> visitedMap;
        // Key is the vertex order and value is the vertex
        private Map<Integer,Integer> sccVertexOrderMap;
        int counter = 0;

        private SccVertexOrder(){
            visitedMap = new HashMap<>();
            // The map will sort on the basis of key ( order which is an integer value) in descending order
            sccVertexOrderMap = new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });
        }

        private List<Integer> getSccVertexOrdering(){
            // Run a dfs on reverse graph to get the order on vertexes and it is in this order that
            // we will run the second dfs
            for(int vertex: diGraph.getVertexes()){
                if(!visitedMap.containsKey(vertex))
                    reverseDfs(diGraph, vertex);
            }
            return new ArrayList<>(sccVertexOrderMap.values());
        }

        private void reverseDfs(DiGraph diGraph, int sourceVertex){
            visitedMap.put(sourceVertex, true);
            for(int vertex: diGraph.getInNeighbours(sourceVertex)){
                if(!visitedMap.containsKey(vertex))
                    reverseDfs(diGraph, vertex);
            }
            sccVertexOrderMap.put(counter, sourceVertex);
            counter++;
        }
    }
}
