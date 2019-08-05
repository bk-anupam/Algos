package com.anupam.ds.unionfind;

public interface UnionFind {
    int find(int vertex);

    boolean isConnected(int p, int q);

    void union(int p, int q);

    int count();
}
