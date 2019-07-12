package com.anupam.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

public abstract class Graph {
	protected Map<Integer, List<Integer>> adjList;
	protected int numVertex = 0;
	protected int numEdges = 0;		
	
	public Graph(int V){
		adjList = new HashMap<Integer, List<Integer>>();
		for(int i = 0; i < V ; i++){
			adjList.put(i+1, new ArrayList<Integer>());
		}
	}
	
	/*
	 *  % java Graph tinyG.txt
	 *  13 vertices, 13 edges 
	 *  0: 6 2 1 5 
	 *  1: 0 
	 *  2: 0 
	 *  3: 5 4 
	 *  4: 5 6 3 
	 *  5: 3 4 0 
	 *  6: 0 4 
	 *  7: 8 
	 *  8: 7 
	 *  9: 11 10 12 
	 *  10: 9 
	 *  11: 9 12 
	 *  12: 11 9 
	 */
	public Graph(InputStream in) throws IOException {						
		adjList = parseGraphDataFile(in);
	}
	
	public Graph(Graph pGraph){
		adjList = new HashMap<Integer, List<Integer>>();
		int V = pGraph.getNumVertex();
		for(int i: pGraph.getVertexes()){
			List<Integer> edgeList = new ArrayList<Integer>();
			for(Integer j: pGraph.getNeighbours(i)){
				edgeList.add(j);
			}
			adjList.put(i, edgeList);
		}
	}
	
	public abstract Map<Integer, List<Integer>> parseGraphDataFile(InputStream in) throws IOException;
		
	public abstract int getNumVertex();
	
	public int getNumEdges(){
		return numEdges;
	}
		
	public abstract List<Integer> getVertexes();
	
	public abstract void addEdge(int v, int w);
	
	/*
	 * Adds a vertex to the graph without any edges
	 */
	public void addVertex(int v) {
		adjList.put(v, new ArrayList<Integer>());
		numVertex++;
	}
	
	public abstract List<Integer> getNeighbours(int vertex);	
	
	public abstract List<Integer> getInNeighbours(int vertex);
	
	public int degree(int vertex){
		return getNeighbours(vertex).size();
	}
		
	public int inDegree(int vertex){
		return getInNeighbours(vertex).size();
	}

}
