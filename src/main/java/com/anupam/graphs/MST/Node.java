package com.anupam.graphs.MST;

public class Node implements Comparable<Node>{
    private int vertex;
    private int parent;
    // key ( weight of the minimum weight edge with vertex as the destination )
    private double weight;

    public Node(int vertex, int parent, double weight){
        this.vertex = vertex;
        this.parent = parent;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(weight, o.weight);
    }

    public int getVertex() {
        return vertex;
    }

    public int getParent() {
        return parent;
    }

    public double getWeight() {
        return weight;
    }
}
