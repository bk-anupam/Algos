package com.anupam.graphs;

import java.util.Stack;

public class DepthFirstSearch implements IGraphSearch{
	boolean[] visited;
	int[] edgeTo;
	private int count = 0;
	private int sourceVertex;
	private boolean hasCycle;
	
	public DepthFirstSearch(Graph graph, int pSourceVertex){
		visited = new boolean[graph.numVertex];
		edgeTo = new int[graph.numVertex];
		sourceVertex = pSourceVertex;
		search(graph, sourceVertex, sourceVertex);
	}

	@Override
	public void search(Graph graph, int sourceVertex){
		search(graph, sourceVertex, sourceVertex);
	}

	public void search(Graph graph, int vertex, int parent){
		visited[vertex] = true;
		count++;		
		for (int child: graph.getNeighbours(vertex)){
			if(!visited[child]){
				edgeTo[child] = vertex;
				search(graph, child, vertex);
			}else if(vertex != parent){
				hasCycle = true;
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

	public boolean hasPathTo(int vertex){
		return visited[vertex];
	}

	@Override
	public boolean hasCycle() {
		return hasCycle;

	}
}
