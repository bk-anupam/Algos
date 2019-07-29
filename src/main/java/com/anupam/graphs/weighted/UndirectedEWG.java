package com.anupam.graphs.weighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class UndirectedEWG extends EdgeWeightedGraph {
    private Set<WeightedEdge> distinctEdgeSet;

    public UndirectedEWG(InputStream in) throws Exception{
        super(in);
    }

    @Override
    protected Map<Integer, List<WeightedEdge>> parseAndLoadGraphFromFile(InputStream in) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String row = null;
        int counter = 0;
        while((row = reader.readLine()) != null){
            if(counter == 0)
                numVertices = Integer.parseInt(row);
            else if(counter == 1)
                numEdges = Integer.parseInt(row);
            else{
                String[] strArray = row.split(" ");
                WeightedEdge we = new WeightedEdge(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]), Double.parseDouble(strArray[2]));
                addEdge(we);
            }
            counter++;
        }
        return adjList;
    }

    private void addEdge(WeightedEdge we) throws Exception{
        int either = we.either();
        int other = we.other(either);
        if(!adjList.containsKey(either))
            adjList.put(either, new ArrayList<>());
        if(!adjList.containsKey(other))
            adjList.put(other, new ArrayList<>());

        adjList.get(either).add(we);
        adjList.get(other).add(we);
        numEdges++;
    }

    @Override
    public int getNumEdges() {
        if(numEdges == 0){
            numEdges = getAllEdges().size();
        }
        return numEdges;
    }

    @Override
    public int getNumVertices() {
        if(numVertices == 0)
            numVertices = adjList.keySet().size();

        return numVertices;
    }

    @Override
    public List<WeightedEdge> getEdges(int vertex) {
        if(adjList.containsKey(vertex))
            return adjList.get(vertex);
        else
            return new ArrayList<>();
    }

    @Override
    public List<Integer> getVertices() {
        return new ArrayList(adjList.keySet());
    }

    @Override
    public List<WeightedEdge> getAllEdges() {
        if(distinctEdgeSet == null) {
            distinctEdgeSet = new HashSet<>();
            for(int vertex: getVertices()){
                distinctEdgeSet.addAll(adjList.get(vertex));
            }
        }
        return new ArrayList<>(distinctEdgeSet);
    }
}
