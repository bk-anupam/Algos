package com.anupam.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HackerRankMedian {
	
	private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1 > o2){
				return -1;
			}else if(o1 == o2){
				return 0;
			}else{
				return 1;
			}
		}
		
	});
	
	public void insert(Integer item){
		if(maxHeap.size() == 0 && minHeap.size() == 0){
			maxHeap.add(item);
			return;
		}
			
		int maxHeapTop = maxHeap.peek() == null ? 0 : maxHeap.peek();
		int minHeapBottom = minHeap.peek() == null ? 0 : minHeap.peek();
		if(item < maxHeapTop){
			maxHeap.add(item);
		}else if(item > minHeapBottom){
			minHeap.add(item);
		}else{
			maxHeap.add(item);
		}
		
		rebalance();
	}
	
	private void rebalance(){
		if(minHeap.size() > maxHeap.size()){
			moveItems(minHeap, maxHeap);
		}else if(minHeap.size() < maxHeap.size()){
			moveItems(maxHeap, minHeap);
		}
	}
	
	private void moveItems(PriorityQueue<Integer> biggerHeap, PriorityQueue<Integer> smallerHeap){
		while(biggerHeap.size() - smallerHeap.size() > 1){
			smallerHeap.add(biggerHeap.poll());
		}
	}
	
	public double findMedian(){
		double median = 0.0;
		if((minHeap.size() + maxHeap.size()) % 2 == 0 || (minHeap.size() + maxHeap.size() == 1)){
			Double item1 = (minHeap.peek() == null) ? 0.0 : minHeap.peek().doubleValue();
			Double item2 = (maxHeap.peek() == null) ? 0.0 : maxHeap.peek().doubleValue();
			median = (item1 + item2) / 2.0;
		}else if(minHeap.size() > maxHeap.size()){
			median = (minHeap.peek() == null) ? 0.0 : minHeap.peek();			
		}else if(maxHeap.size() > minHeap.size()){
			median = (maxHeap.peek() == null) ? 0.0 : maxHeap.peek();
		}
		return median;
	}

}

class MyComparator implements Comparator<Integer>{
    @Override
		public int compare(Integer o1, Integer o2) {
			if(o1 > o2){
				return -1;
			}else if(o1 == o2){
				return 0;
			}else{
				return 1;
			}
		}
}
