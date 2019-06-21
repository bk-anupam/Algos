package com.anupam.graphs;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;

// Each vertex has a list of neighbours. We need to be able to track what all elements in the neighbours list of a vertex has been popped off the stack
// already. So basically we need to iterate of over the list of neighbours using an iterator
public class DepthFirstSearch2 {
	boolean[] visited;
	Map<Integer, Integer> edgeToMap = new HashMap<Integer, Integer>();
	private int count = 0;
	private int sourceVertex;
	private Map<Integer, ListIterator<Integer>> liMap = null;
	
	public DepthFirstSearch2(Graph graph, int pSourceVertex){
		sourceVertex = pSourceVertex;
		visited = new boolean[graph.getNumVertex()];		
		liMap = new HashMap<Integer, ListIterator<Integer>>();
		for(int vertex: graph.getVertexes()){
			liMap.put(vertex, graph.getNeighbours(vertex).listIterator());
		}
		search(graph, sourceVertex);		
	}
	
	public void search(Graph graph, int sourceVertex){
		Stack<Integer> dfsStack = new Stack<Integer>();
		visited[sourceVertex] = true;
		count++;		
		dfsStack.push(sourceVertex);
		
		while(!dfsStack.isEmpty()){
			int vertex = dfsStack.peek();
			ListIterator<Integer> li = liMap.get(vertex);
			
			if(li != null && li.hasNext()){
				int neighbour = li.next();
				if(!visited[neighbour]){
					visited[neighbour] = true;
					count++;					
					edgeToMap.put(neighbour, vertex);
					dfsStack.push(neighbour);
				}				
			}else{
				dfsStack.pop();
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
		for(int x = vertex; x != sourceVertex; x = edgeToMap.get(vertex)){
			path.push(x);
			vertex = x;
		}
		
		path.push(sourceVertex);
		return path;
	}
	
}
