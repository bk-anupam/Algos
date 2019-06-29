package com.anupam.graphs;

public interface IGraphSearch {
	void search(Graph G, int sourceVertex);
	boolean hasPathTo(int vertex);
	Iterable<Integer> pathTo(int vertex);
	boolean hasCycle();
}
