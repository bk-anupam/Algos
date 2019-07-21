package com.anupam.graphs;

public interface IConnectedComponents {
    public boolean isConnected(int v1, int v2);
    public int getComponentId(int vertex);
    public int connectComponentCount();
    public void printVertexComponentId();
}
