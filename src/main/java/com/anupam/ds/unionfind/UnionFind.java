package com.anupam.ds.unionfind;

import java.util.List;

public abstract class UnionFind {
    protected int[] parent;
    protected int componentCount = 0;

    public UnionFind(int N){
        parent = new int[N];
        for(int i = 0; i < N; i++){
            parent[i] = i;
            componentCount++;
        }
    }

    public UnionFind(List<Integer> verticesList){
        parent = new int[verticesList.size()];
        for(int vertex: verticesList){
            parent[vertex] = vertex;
            componentCount++;
        }
    }

    public abstract int find(int vertex);

    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    public abstract void union(int p, int q);

    public int count(){
        return componentCount;
    }

}
