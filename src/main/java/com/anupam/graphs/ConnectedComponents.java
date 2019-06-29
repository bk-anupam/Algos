package com.anupam.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class ConnectedComponents {

	// Each vertex in the graph belongs to a CC. This array with the vertex being the index stores which CC the vertex belongs to.
	private int[] id = null;
	// Total number of CC in the graph
	private int count = 0;
	// Whether a vertex has been visited
	private boolean[] visited = null;
	private int size[] = null;	
	
	public ConnectedComponents(Graph G){
		visited = new boolean[G.numVertex];
		id = new int[G.numVertex];
		size = new int[G.numVertex];
		for(int v: G.getVertexes()){
			if(!visited[v]){				
				// The number of connected components is equal to the number of times u run bfs on the graph.
				count = count + 1;
				bfs(G, v, count);
			}
		}	
	}
	
	private void bfs(Graph G, int sourceVertex, int componentId){				
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(sourceVertex);
		while(!queue.isEmpty()){
			Integer vertex = queue.poll();
			for(int v: G.getNeighbours(vertex)){
				if(!visited[v]){
					visited[v] = true;
					// Count here represents the id of the CC. So we will assign this id to each vertex discovered in this run of bfs
					id[v] = componentId;
					size[componentId] = size[componentId] + 1;
					queue.offer(v);
				}
			}			
		}
	}
	
	public boolean connected(int v, int w){
		return id[v] == id[w];
	}
	
	public int count(){
		return count;
	}
	
	public int id(int v){
		return id[v];
	}
	
}
