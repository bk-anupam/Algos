package com.anupam.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DiGraph extends Graph{

	private List<Integer> vertexList;

	public DiGraph(InputStream in) throws IOException{
		super(in);
	}

	public DiGraph(Graph graph){
		super(graph);
	}

	public DiGraph(int numVertex){ super(numVertex); }
	
	@Override
	public void addEdge(int v, int w) {
		adjList.get(v).add(w);		
	}

	// This gives the list of out neighbours (vertices to which there are outgoing edges from this vertex)
	@Override
	public List<Integer> getNeighbours(int vertex) {
		if(adjList.containsKey(vertex))
			return adjList.get(vertex);
		else
			return new ArrayList<Integer>();
	}
	
	public List<Integer> getInNeighbours(int vertex){
		List<Integer> inNeighbours = new ArrayList<Integer>();
		for(int v: adjList.keySet()){
			for(int u: adjList.get(v)){
				if( u == vertex)
					inNeighbours.add(v);
			}		
		}
		return inNeighbours;
	}

	public DiGraph reverse(DiGraph inDiGraph){
		DiGraph reversedDiGraph = new DiGraph(inDiGraph.getNumVertex());
		for(int vertex: inDiGraph.getVertexes()){
			for(int neighbour: inDiGraph.adjList.get(vertex)){
				reversedDiGraph.addEdge(neighbour, vertex);
			}
		}
		return reversedDiGraph;
	}

	@Override
	public int getNumVertex() {
		if(vertexList == null){
			vertexList = new ArrayList<>(getDistinctVertexes());
			numVertex = vertexList.size();
		}
		return numVertex;
	}

	@Override
	public List<Integer> getVertexes() {
		if(vertexList == null){
			vertexList = new ArrayList<>(getDistinctVertexes());
		}
		return vertexList;
	}

	private Set<Integer> getDistinctVertexes(){
		Set<Integer> distinctVertexSet = new HashSet<>();
		distinctVertexSet.addAll(adjList.keySet());
		for(int vertex: adjList.keySet()){
			distinctVertexSet.addAll(adjList.get(vertex));
		}
		return distinctVertexSet;
	}

	@Override
	public Map<Integer, List<Integer>> parseGraphDataFile(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		Map<Integer, List<Integer>> adjList = new HashMap<>();
		int count = 0, numVertex = 0, numEdges = 0;
		String row;
		while((row = reader.readLine()) != null ){
			if(count == 0){
				numVertex = Integer.parseInt(row);
			}else if(count == 1){
				numEdges = Integer.parseInt(row);
			}else{
				String[] strArray = row.trim().split(" ");
				int vertex = Integer.parseInt(strArray[0]);
				int neighbour = 0;
				if(!adjList.containsKey(vertex))
					adjList.put(vertex, new ArrayList<>());

				adjList.get(vertex).add(Integer.parseInt(strArray[strArray.length - 1]));
			}
			count++;
		}
		return adjList;
	}
}

