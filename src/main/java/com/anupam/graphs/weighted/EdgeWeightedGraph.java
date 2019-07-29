package com.anupam.graphs.weighted;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EdgeWeightedGraph {
    protected Map<Integer, List<WeightedEdge>> adjList;
    protected int numEdges;
    protected int numVertices;

    public EdgeWeightedGraph(InputStream in) throws Exception {
        adjList = new HashMap<>();
        parseAndLoadGraphFromFile(in);
    }

    protected abstract Map<Integer, List<WeightedEdge>> parseAndLoadGraphFromFile(InputStream in) throws Exception;

    public abstract int getNumEdges();

    public abstract int getNumVertices();

    public abstract List<WeightedEdge> getEdges(int vertex);

    public abstract List<Integer> getVertices();

    public abstract List<WeightedEdge> getAllEdges();
}
