package com.anupam.ds.unionfind;

import java.util.List;

public class LazyUnionFindBySize implements UnionFind{
    private int[] parent;
    private int[] sizeOfVertexComponent;
    private int componentCount = 0;

    public LazyUnionFindBySize(int N){
        parent = new int[N];
        sizeOfVertexComponent = new int[N];
        for(int i = 0; i < N; i++){
            parent[i] = i;
            sizeOfVertexComponent[i] = 1;
            componentCount++;
        }
    }

    public LazyUnionFindBySize(List<Integer> verticesList){
        parent = new int[verticesList.size()];
        sizeOfVertexComponent = new int[verticesList.size()];
        for(int vertex: verticesList){
            parent[vertex] = vertex;
            sizeOfVertexComponent[vertex] = 1;
            componentCount++;
        }
    }

    @Override
    public int find(int vertex){
        while(vertex != parent[vertex] ){
            vertex = parent[vertex];
        }
        return vertex;
    }

    @Override
    public boolean isConnected(int p, int q){
        return parent[p] == parent[q];
    }

    @Override
    public int count(){
        return componentCount;
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
