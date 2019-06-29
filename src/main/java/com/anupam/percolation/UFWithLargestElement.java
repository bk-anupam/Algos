package com.anupam.percolation;

import java.util.HashMap;

public class UFWithLargestElement {
	static HashMap<Integer, Integer> map = new HashMap<>();

	int find(int element) {
		int highest = element;
		while (map.get(element) != element) {
			element = map.get(element);
			if (element > highest)
				highest = element;
		}
		return highest;
	}

	void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA < rootB) {
			map.put(rootA, rootB);
		} else if (rootB < rootA) {
			map.put(rootB, rootA);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			map.put(i, i);
		}
		System.out.println("Initial Map "+map);
		UFWithLargestElement uf=new UFWithLargestElement();
		uf.union(2, 6);
		uf.union(3, 9);
		uf.union(1, 4);
		uf.union(9, 2);
		uf.union(4, 3);
		System.out.println("After Union "+map);
	}
}
