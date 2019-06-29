package com.anupam.percolation;

public class HighestUnimodal {

	public static void main(String[] args) {
		int arr[] = { 3, 4, 6, 7, 8, 9, 10, 50, 49, 48, 43, 40, 39, 37, 35, 30, 20, 19, 5, 2 };
		HighestUnimodal highest = new HighestUnimodal();
		int max = highest.getHighest(arr);
		System.out.println("Highest Element " + max);
	}

	int getHighest(int arr[]) {
		int minIndex = 0;
		int maxIndex = arr.length - 1;
		int median = arr[(minIndex + maxIndex) / 2];
		int count = 0;
		while (minIndex < maxIndex) {
			count++;
			if (median > arr[(minIndex + maxIndex) / 2 - 1]) {
				minIndex = (minIndex + maxIndex) / 2;
			}
			if (median > arr[(minIndex + maxIndex) / 2 + 1]) {
				maxIndex = (minIndex + maxIndex) / 2;
			}
			median = arr[(minIndex + maxIndex) / 2];
		}
		System.out.println("Array Length " + arr.length + " Iterations " + count);
		return median;
	}
	
}
