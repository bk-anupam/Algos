package com.anupam.graphs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DiGraph extends Graph{

	public DiGraph(InputStream in) throws IOException{
		super(in);
	}

	public DiGraph(Graph graph){
		super(graph);
	}
	
	@Override
	public void addEdge(int v, int w) {
		adjList.get(v).add(w);		
	}

	// This gives the list of out neighbours (vertices to which there are outgoing edges from this vertex)
	@Override
	public List<Integer> getNeighbours(int vertex) {
		return new ArrayList<Integer>(adjList.get(vertex));
	}
	
	public List<Integer> getInNeighbours(int vertex){
		List<Integer> inNeighbours = new ArrayList<Integer>();
		for(int v: adjList.keySet()){
			for(int u: adjList.get(v)){
				if( u == vertex)
					inNeighbours.add(u);
			}		
		}
		return inNeighbours;
	}
}

