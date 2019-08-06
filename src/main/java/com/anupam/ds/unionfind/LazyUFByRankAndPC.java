package com.anupam.ds.unionfind;

import java.util.List;

public class LazyUFByRankAndPC extends LazyUnionFindByRank{

    public LazyUFByRankAndPC(int N){
        super(N);
    }

    public LazyUFByRankAndPC(List<Integer> verticesList){
        super(verticesList);
    }

    @Override
    public int find(int vertex){
        int origVertex = vertex;
        int root;
        // Find the root or leader of the component when find is called first
        while(vertex != parent[vertex] ){
            vertex = parent[vertex];
        }
        root = vertex;
        // Now re-traverse the path from the vertex to the root, setting the parent of each
        // vertex in the path traversed to the root of the component
        while(origVertex != root){
            int temp = parent[origVertex];
            parent[origVertex] = root;
            origVertex = temp;
        }
        return root;
    }

}
