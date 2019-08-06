package com.anupam.ds.unionfind;

import java.util.List;

public class LazyUnionFindBySize extends LazyUnionFind{
    private int[] sizeOfVertexComponent;

    public LazyUnionFindBySize(int N){
        super(N);
        sizeOfVertexComponent = new int[N];
        for(int i = 0; i < N; i++){
            sizeOfVertexComponent[i] = 1;
        }
    }

    public LazyUnionFindBySize(List<Integer> verticesList){
        super(verticesList);
        sizeOfVertexComponent = new int[verticesList.size()];
        for(int vertex: verticesList){
            sizeOfVertexComponent[vertex] = 1;
        }
    }

    @Override
    public void union(int p, int q){
        int pLeader = find(p);
        int qLeader = find(q);

        if(pLeader == qLeader)
            return;

        if(sizeOfVertexComponent[pLeader] < sizeOfVertexComponent[qLeader]){
            parent[pLeader] = qLeader;
            sizeOfVertexComponent[qLeader] += sizeOfVertexComponent[pLeader];
        }else {
            parent[qLeader] = pLeader;
            sizeOfVertexComponent[pLeader] += sizeOfVertexComponent[qLeader];
        }
        componentCount--;
    }
}
