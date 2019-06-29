package com.anupam.algorithms;

import java.util.HashMap;

public class UnionFind {
	public static void main(String[] args) {
		HashMap<Integer, Integer> entries = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			entries.put(i, i);
		}
		UnionFind uf = new UnionFind();
		uf.union(1, 2, entries);
		uf.union(2, 3, entries);
		System.out.println(uf.find(1, 3, entries));
		System.out.println(uf.find(8, 9, entries));
		uf.union(9, 3, entries);
		System.out.println(uf.find(8, 9, entries));
		System.out.println(entries);
	}

	Integer findRoot(int i, HashMap<Integer, Integer> entries) {
		Integer root = 0;
		if (entries.get(i).equals(i))
			root = i;
		else {
			root = findRoot(entries.get(i), entries);
		}
		return root;
	}

	void union(int i, int j, HashMap<Integer, Integer> entries) {
		Integer root1 = findRoot(i,entries);
		Integer root2 = findRoot(j,entries);
		if(!root1.equals(root2)){
			entries.replace(root2, root1);
		}
	}

	boolean find(int i, int j, HashMap<Integer, Integer> entries) {
		if (findRoot(i,entries).equals(findRoot(j,entries)))
			return true;
		return false;
	}
}
