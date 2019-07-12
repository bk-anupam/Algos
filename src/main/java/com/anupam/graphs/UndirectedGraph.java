package com.anupam.graphs;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// Implementation for undirected graph
public class UndirectedGraph extends Graph{	

	private List<Integer> vertexList;

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

	@Override
	public int getNumVertex(){
		if(numVertex == 0)
			numVertex = adjList.keySet().size();

		return numVertex;
	}

	@Override
	public List<Integer> getVertexes(){
		if(vertexList == null)
			vertexList = new ArrayList<>(adjList.keySet());

		return vertexList;
	}

	@Override
	public Map<Integer, List<Integer>> parseGraphDataFile(InputStream in) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		//BufferedReader reader = new BufferedReader(new FileReader(file));
		Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
		String str;

		String[] strArray = reader.readLine().split(" ");
		for(int i = 0; i < strArray.length; i++){
			if( i == 0 && StringUtils.isNumeric(strArray[i]))
				numVertex = Integer.parseInt(strArray[i]);
			else if( i > 0 && StringUtils.isNumeric(strArray[i]))
				numEdges = Integer.parseInt(strArray[i]);
		}

		while((str = reader.readLine()) != null){
			StringTokenizer token = new StringTokenizer(str);
			String vertex;
			if(StringUtils.contains(vertex = token.nextToken(), ":"))
				vertex = StringUtils.substringBefore(vertex, ":");

			String nextToken;
			List<Integer> edgeList = new ArrayList<Integer>();
			while(token.hasMoreTokens()){
				if(StringUtils.isNumeric(nextToken = token.nextElement().toString())){
					edgeList.add(Integer.parseInt(nextToken));
				}

			}
			graph.put(Integer.parseInt(vertex), edgeList);
		}
		return graph;
	}
}
