package com.anupam.algorithms;

public class SelectionSort {
	static int iterations=0;
	public static void main(String[] args) {
		int[] array={9,2,5,1,7,4,0,3,1,8,19,83,58,22,90};
		System.out.println("Initial Array ");
		for(int val:array){
			System.out.print(val+" ");
		}
		SelectionSort sort=new SelectionSort();
		sort.arraySort(array);
		System.out.println("\nAfter Sorting ");
		for(int val:array){
			System.out.print(val+" ");
		}
		System.out.println("\nArray Length "+array.length+" Iterations "+iterations);
	}
	
	void arraySort(int[] array){
		for(int i=0;i<array.length;i++){
			iterations++;
			int minIndex=findMinIndex(array, i);
			if(minIndex!=i){
				swapVals(array,i,minIndex);
			}
		}
	}
	
	void swapVals(int[] array,int firstInd,int secondInd){
		int firstVal=array[firstInd];
		array[firstInd]=array[secondInd];
		array[secondInd]=firstVal;
	}
	
	int findMinIndex(int[] array,int startIndex){
		int minVal=array[startIndex];
		int minIndex=startIndex;
		for(int j=++startIndex;j<array.length;j++){
			iterations++;
			if(array[j]<minVal){
				minIndex=j;
				minVal=array[j];
			}
		}
		return minIndex;
	}
}
