package com.anupam.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

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
