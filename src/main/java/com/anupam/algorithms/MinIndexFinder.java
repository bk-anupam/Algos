package com.anupam.algorithms;

public class MinIndexFinder {
	public static void main(String[] args) {
		int array[] = {18, 6, 66, 44, 9, 22, 14};
		MinIndexFinder finder = new MinIndexFinder();
		int minIndex = finder.indexOfMin(array, 2);
		System.out.println("Index of Min Number "+minIndex);
	}
	
	int indexOfMin(int[] array, int startIndex){
		int minIndex = startIndex;
		int minValue = array[minIndex];
		for(int i=++minIndex;i<array.length;i++){
			if(array[i]<minValue){
				minIndex=i;
				minValue=array[i];
			}
		}
		return minIndex;
	}
}
