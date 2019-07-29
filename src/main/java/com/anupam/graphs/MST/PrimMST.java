package com.anupam.graphs.MST;

import com.anupam.graphs.weighted.EdgeWeightedGraph;
import com.anupam.graphs.weighted.WeightedEdge;

import java.util.*;

public class PrimMST {
    // X
    private boolean[] visited;
    private Set<Integer> visitedVertices;
    // index is the vertex, value is the weight of minimum weight edge to that vertex from any other vertex
    private double[] distTo;
    // Tree whose entries span the vertices visited so far
    // index is the vertex, value if the minimum weight edge to that vertex from any other vertex
    private WeightedEdge[] edgeTo;
    private EdgeWeightedGraph ewg;
    private PriorityQueue<Node> vertexMinWeightPQ;

    public PrimMST(EdgeWeightedGraph ewg) throws Exception{
        this.ewg = ewg;
        visited = new boolean[ewg.getNumVertices()];
        visitedVertices = new HashSet<>();
        distTo = new double[ewg.getNumVertices()];
        edgeTo = new WeightedEdge[ewg.getNumVertices()];

        initializePQ();
        while(!vertexMinWeightPQ.isEmpty()){
            // Remove the root node from min PQ
            Node node = vertexMinWeightPQ.poll();
            visit(node);
        }
    }

    private void initializePQ(){
        vertexMinWeightPQ = new PriorityQueue<>();
        for(int vertex: ewg.getVertices()){
            Node node;
            if(vertex == 0){
                node = new Node(vertex, -1, 0.0);
            }else {
                node = new Node(vertex, -1, Double.POSITIVE_INFINITY);
            }
            vertexMinWeightPQ.add(node);
            distTo[vertex] = node.getWeight();
            edgeTo[vertex] = new WeightedEdge(node.getVertex(), node.getParent(), node.getWeight());
        }
    }

    private void visit(Node node) throws Exception{
        visitedVertices.add(node.getVertex());
        for(int visitedVertex: visitedVertices){
            for(WeightedEdge edge: ewg.getEdges(visitedVertex)){
                if(isCrossingEdge(edge)){
                    int v = edge.other(visitedVertex);
                    if( edge.weight() < distTo[v]) {
                        distTo[v] = edge.weight();
                        edgeTo[v] = edge;
                        vertexMinWeightPQ.remove(node);
                        vertexMinWeightPQ.add(new Node(v, visitedVertex, edge.weight()));
                    }
                }
            }
        }
    }

    private boolean isCrossingEdge(WeightedEdge we) throws Exception{
        int u = we.either();
        int v = we.other(u);
        if(visitedVertices.contains(u) && visitedVertices.contains(v))
            return false;
        else
            return true;
    }

    public WeightedEdge[] getMst(){
        return edgeTo;
    }
}
