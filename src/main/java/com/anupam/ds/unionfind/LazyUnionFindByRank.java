package com.anupam.ds.unionfind;

import java.util.List;

public class LazyUnionFindByRank extends LazyUnionFind{
    protected int[] vertexRank;

    public LazyUnionFindByRank(int N){
        super(N);
        vertexRank = new int[N];
        for(int i = 0; i < N; i++){
            vertexRank[i] = 0;
        }
    }

    public LazyUnionFindByRank(List<Integer> verticesList){
        super(verticesList);
        vertexRank = new int[verticesList.size()];
        for(int vertex: verticesList){
            vertexRank[vertex] = 0;
        }
    }

    @Override
    public void union(int p, int q){
        int pLeader = find(p);
        int qLeader = find(q);

        if(pLeader == qLeader)
            return;

        if(vertexRank[pLeader] == vertexRank[qLeader]){
            parent[pLeader] = qLeader;
            vertexRank[qLeader] = vertexRank[pLeader] + 1;
        }else if(vertexRank[pLeader] < vertexRank[qLeader]){
            parent[pLeader] = qLeader;
        }else{
            parent[qLeader] = pLeader;
        }

        componentCount--;
    }
}
