package com.anupam.graph.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import junit.framework.Assert;

import com.anupam.graphs.BreadthFirstSearch;
import com.anupam.graphs.DepthFirstSearch2;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anupam.graphs.Graph;
import com.anupam.graphs.UndirectedGraph;

public class BFSTests {
	
	static InputStream in = null;
	
	@BeforeClass
	public static void setup() throws FileNotFoundException{
		File file = new File("E:\\tinyG.txt");		
		in = new FileInputStream(file);
	}
	
	@AfterClass
	public static void tearDown() throws IOException{
		if( in != null)
			in.close();
	}

	@Test
	public void connectivityBetweenVerticesUsingBFSTest(){
		try{			
			Graph graph = new UndirectedGraph(in);
			BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);
			Assert.assertEquals(false, bfs.hasPathTo(7));
			List<Integer> pathToFour = (List<Integer>) bfs.pathTo(4);
			Assert.assertEquals(3, pathToFour.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void dfs2Test(){
		try{
			Graph graph = new UndirectedGraph(in);
			DepthFirstSearch2 bfs = new DepthFirstSearch2(graph, 0);
			List<Integer> pathToFour = (List<Integer>) bfs.pathTo(4);
			Assert.assertEquals(3, pathToFour.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
