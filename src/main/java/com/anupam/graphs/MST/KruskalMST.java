package com.anupam.graphs.MST;

import com.anupam.ds.unionfind.LazyUnionFindBySize;
import com.anupam.ds.unionfind.UnionFind;
import com.anupam.graphs.weighted.EdgeWeightedGraph;
import com.anupam.graphs.weighted.WeightedEdge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalMST {
    private boolean[] visited;
    private List<WeightedEdge> mst;
    private PriorityQueue<WeightedEdge> weightedEdgePQ;
    private UnionFind lazyUnionFindBySize;

    public KruskalMST(EdgeWeightedGraph ewg) throws Exception{
        initialize(ewg);
        // The number of edges in a tree of n vertices is n-1 and this is the minimum number
        // of edges a connected graph can have
        while(!weightedEdgePQ.isEmpty() && mst.size() < ewg.getNumVertices()){
            WeightedEdge we = weightedEdgePQ.poll();
            int p = we.either();
            int q = we.other(p);
            // check if including the edge will create a cycle in mst
            if(!lazyUnionFindBySize.isConnected(p, q)) {
                lazyUnionFindBySize.union(p, q);
                mst.add(we);
            }
        }
    }

    private void initialize(EdgeWeightedGraph ewg){
        weightedEdgePQ = new PriorityQueue<>();
        visited = new boolean[ewg.getNumVertices()];
        mst = new ArrayList<>();
        for(WeightedEdge we: ewg.getAllEdges()){
            weightedEdgePQ.add(we);
        }
        lazyUnionFindBySize = new LazyUnionFindBySize(ewg.getVertices());
    }

    public List<WeightedEdge> getMst(){
        return mst;
    }
}
