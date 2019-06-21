package com.shikha.algos;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstSearch {
	Graph g = new Graph();
	Queue queue=new LinkedBlockingQueue();
	public static void main(String[] args) {
		Integer startNode=1;
		BreadthFirstSearch search=new BreadthFirstSearch();
		search.exploreGraph(startNode);
	}

	void exploreGraph(Integer startNode) {
		List<Integer> neighbours=g.map.get(startNode);
		
	}
}
