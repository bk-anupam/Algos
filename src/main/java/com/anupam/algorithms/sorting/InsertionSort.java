package com.anupam.algorithms.sorting;

public class InsertionSort {
	public static void main(String[] args) {
		int array[] = { 5,3,1,6,2 };
		InsertionSort sort = new InsertionSort();
		sort.sortElements(array);
		for (int val : array) {
			System.out.println(val + " ");
		}
	}

	void sortElements(int[] array){
		for(int i=0;i<array.length-1; i++){
			insertElement(array, i, array[i+1]);
		}
	}
	
	void insertElement(int[] array, int rightIndex, int value) {
		int index = rightIndex;
		for (; index >= 0 && array[index] > value; index--) {
			array[index + 1] = array[index];
		}
		array[index+1] = value;
	}
}
