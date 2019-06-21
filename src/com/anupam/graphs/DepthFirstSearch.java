package com.anupam.graphs;

import java.util.Stack;

public class DepthFirstSearch {
	boolean[] visited;
	int[] edgeTo;
	private int count = 0;
	private int sourceVertex;
	
	public DepthFirstSearch(Graph graph, int pSourceVertex){
		sourceVertex = pSourceVertex;
		search(graph, sourceVertex);
	}
	
	public void search(Graph graph, int sourceVertex){
		visited[sourceVertex] = true;
		count++;		
		for (int vertex: graph.getNeighbours(sourceVertex)){
			if(!visited[vertex]){
				edgeTo[vertex] = sourceVertex;
				search(graph, vertex);
			}
		}
	}
	
	public boolean visited(int vertex){
		return visited[vertex];	
	}
	
	public int countVisitedNodes(){
		return count;
	}
	
	public Iterable<Integer> pathTo(int vertex){
		if(!visited(vertex))
			return null;
		
		Stack<Integer> path = new Stack<Integer>(); 
		for(int x= vertex; x != sourceVertex; x=edgeTo[vertex]){
			path.push(x);
		}
		path.push(sourceVertex);
		return path;
	}
	
}
