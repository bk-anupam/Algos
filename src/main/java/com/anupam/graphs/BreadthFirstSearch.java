package com.anupam.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch implements IGraphSearch{
	// An array with the vertices as indices with each entry indicated whether the corresponding vertex has been visited or not
	private boolean[] visited = null;
	// An entry ( vertex being the index ) indicates the vertex having an edge to the index vertex. For a directed edge u -> v v is the index and u is the value 
	private int[] edgeTo = null;
	// No of edges in the shortest path between a source vertex and a given vertex
	private int[] distTo = null;
	private boolean[] color = null;

	Queue<Integer> queue = new LinkedList<Integer>();
	private boolean hasCycle;
	private boolean isBipartite = true;
	
	public BreadthFirstSearch(Graph G, Iterable<Integer> sources){
		edgeTo = new int[G.getNumVertex()];
		distTo = new int[G.getNumVertex()];
		visited = new boolean[G.getNumVertex()];
		
		for(Integer source: sources){
			visited[source] = true;
			queue.offer(source);
		}
		
		while(!queue.isEmpty()){
			Integer current = queue.poll();
			for(int neighbour: G.getNeighbours(current)){
				if(!visited[neighbour]){
					visited[neighbour] = true;
					queue.offer(neighbour);
					edgeTo[neighbour] = current;
				}
			}
		}
	}
	
	public BreadthFirstSearch(Graph G, int sourceVertex){
		search(G, sourceVertex);
	}
	
	public BreadthFirstSearch(){}
	
	@Override
	public boolean hasPathTo(int vertex){
		return visited[vertex];
	}
	
	@Override
	public Iterable<Integer> pathTo(int vertex){
		if(!visited[vertex])
			return null;
		
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for(x = vertex; distTo[x] != 0; x = edgeTo[x]){
			path.push(x);
		}
		path.push(x);
		return path;
	}

	@Override
	public boolean hasCycle() {
		return hasCycle;
	}

	@Override
	public boolean isBipartite(){ return isBipartite; }

	public void search(Graph G, int sourceVertex){
		search(G, sourceVertex, sourceVertex);
	}

	private void search(Graph G, int sourceVertex, int parent) {
		edgeTo = new int[G.numVertex];
		distTo = new int[G.numVertex];
		visited = new boolean[G.numVertex];
		color = new boolean[G.numVertex];

		visited[sourceVertex] = true;
		queue.add(sourceVertex);
		distTo[sourceVertex] = 0;
		
		while(!queue.isEmpty()){
			Integer current = queue.poll();
			for(int neighbour: G.getNeighbours(current)){
				if(!visited[neighbour]){
					visited[neighbour] = true;
					queue.offer(neighbour);
					edgeTo[neighbour] = current;
					distTo[neighbour] = distTo[current] + 1;
					color[neighbour] = !color[current];
				}else {
					if(edgeTo[neighbour] != current)
						hasCycle = true;
					if(color[neighbour] == color[current])
						isBipartite = false;
				}
			}
		}	
		
	}
	
}
