package com.anupam.graphs;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// Implementation for undirected graph
public class UndirectedGraph extends Graph{	
		
	public UndirectedGraph(InputStream in) throws IOException{
		super(in);
	}
	
	public UndirectedGraph(Graph pGraph){
		super(pGraph);
	}
	
	@Override
	public void addEdge(int v, int w) {
		// Add w to the out neighbours list of v and vice versa
		adjList.get(v).add(w);
		adjList.get(w).add(v);
	}
	
	@Override
	public List<Integer> getNeighbours(int vertex) {
		return adjList.get(vertex);
	}

	@Override
	public List<Integer> getInNeighbours(int vertex) {
		return getNeighbours(vertex);
	}

}
