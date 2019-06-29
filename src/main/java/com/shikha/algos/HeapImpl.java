package com.shikha.algos;

import java.util.ArrayList;

public class HeapImpl {
	ArrayList<Integer> heap;
	int size;
	
	HeapImpl() {
		heap = new ArrayList<>();
		size=0;
	}
	
	void insert(int element){
		heap.add(element);
		size++;
		
		if(element<getKey(size))
			//swap(get)
		;	
			
	}
	
	int getKey(int position){
		return heap.get(position/2);
	}
}
