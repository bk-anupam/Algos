package com.shikha.algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
	HashMap<Integer,List<Integer>> map;
	HashMap<Integer,Integer> dMap;

	Graph() {
		map = new HashMap<>();
		dMap=new HashMap<>();

		loadGraph();
	}

	void loadGraph() {
		ArrayList<Integer> list=new ArrayList<>();
		list.add(2);
		map.put(1, list);
		dMap.put(1, 0);
		
		list=new ArrayList<>();
		list.add(4);
		list.add(3);
		map.put(2, list);
		dMap.put(2, 0);
		
		list=new ArrayList<>();
		list.add(2);
		map.put(3, list);
		dMap.put(3, 0);
		
		list=new ArrayList<>();
		list.add(2);
		map.put(4, list);
		dMap.put(4, 0);
	}
}
