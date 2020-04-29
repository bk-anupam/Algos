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
        while(visitedVertices.size() != this.ewg.getNumVertices()){
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
        int visitedVertex = node.getVertex();
        //for(int visitedVertex: visitedVertices){
            for(WeightedEdge edge: ewg.getEdges(visitedVertex)){
                if(isCrossingEdge(edge)){
                    int v = edge.other(visitedVertex);
                    if( edge.weight() < distTo[v]) {
                        distTo[v] = edge.weight();
                        edgeTo[v] = edge;
                        // Since the edge the from visitedVertex (in X) to v (in V-X) where X is the set of visited vertices
                        // and V is the set of all vertices, being a crossing edge and its weight is less than the
                        // weight of the current edge (distTo[v]) that connects v to the MST, hence we will update
                        // the minPQ and mark visitedVertex as the parent of v and edge.weight as the weight of v.
                        // This is done by doing a remove and an add since PQ doesn't provide and update function
                        // Both remove and add have a time complexity of O(logn)
                        vertexMinWeightPQ.remove(node);
                        vertexMinWeightPQ.add(new Node(v, visitedVertex, edge.weight()));
                    }
                 // if the edge is not a crossing edge, surely both the vertices of the edge must have been visited
                 // already and the edge has been identified as part of MST (being a greedy algorithm this consideration
                 // is irrevocable ) and hence this edge can be removed from the min PQ.
                }else{
                    vertexMinWeightPQ.remove(node);
                }
            }
        //}
    }

    // By cut property of a graph, if one of edge vertices is in the visited set and the other not, the edge
    // is a crossing edge.
    private boolean isCrossingEdge(WeightedEdge we) throws Exception{
        int u = we.either();
        int v = we.other(u);
        if(visitedVertices.contains(u) && !visitedVertices.contains(v))
            return true;
        else if(visitedVertices.contains(v) && !visitedVertices.contains(u))
            return true;
        else
            return false;
    }

    public WeightedEdge[] getMst(){
        return edgeTo;
    }
}
