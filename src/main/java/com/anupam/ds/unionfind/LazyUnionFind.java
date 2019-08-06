package com.anupam.ds.unionfind;

import java.util.List;

public abstract class LazyUnionFind extends UnionFind {

    public LazyUnionFind(int N){
        super(N);
    }

    public LazyUnionFind(List<Integer> verticesList){
        super(verticesList);
    }

    @Override
    public int find(int vertex){
        while(vertex != parent[vertex] ){
            vertex = parent[vertex];
        }
        return vertex;
    }
}
