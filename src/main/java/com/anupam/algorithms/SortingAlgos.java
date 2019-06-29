package com.anupam.algorithms;

import java.util.Arrays;

public class SortingAlgos {
	static int comparisons=0;
	public static void insertionSort(int[] inputArray){
		for(int i = 1; i < inputArray.length ; i++){
			int j = i;
			int temp = inputArray[i];
			while(j > 0 && inputArray[j-1] > temp){
				inputArray[j] = inputArray[j-1];
				j--;
			}
			inputArray[j] = temp;
		}
	}
	
	public static <T extends Comparable<T>> void quickSort(T[] inputArray){
		quickSort(inputArray, 0, inputArray.length - 1);
		//Arrays.asList(inputArray).forEach(x -> System.out.print(x + " "));
	}
	
	public static <T extends Comparable<T>> void quickSort(T[] inputArray, int start, int end){
		if(end <= start)
			return;
		int j = partition(inputArray, start, end);
		quickSort(inputArray, start, j - 1);
		quickSort(inputArray, j + 1, end);
	}
	
	private static <T extends Comparable<T>> int partition(T[] array, int start, int end){		
		comparisons=comparisons+(end-start);
		int pivotIndex = start;
		int i = start + 1;
		for(int j = start + 1; j < array.length; j++){
			if(array[j].compareTo(array[pivotIndex]) < 0){
				// Here i if the partition that divides the array into two halves. Elements < i are all < pivot and elements > i are all > pivot
				// swap array[j] with left most element bigger than the pivot i.e. array[i]
				T temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				// Now move i by one position to the right
				i++;
			}
		}
		// Once you have done the linear scan of the array swap the pivot ( index 0 ) with element at index i - 1
		T temp = array[start];
		array[start] = array[i - 1];
		array[i - 1] = temp;
		Arrays.asList(array).forEach(x -> System.out.print(x.toString()));
		System.out.println();
		return i;		
	}	
	
}
