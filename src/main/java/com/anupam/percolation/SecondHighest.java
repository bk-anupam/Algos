package com.anupam.percolation;

public class SecondHighest {
	public static void main(String[] args) {
		int arr[] = { -1, 5, 33, -10, 11, 4, 7, 2, 43, 500, 28, 1, 0, 77, 3, 8, 29, 101, 35, 100, 99 };
		SecondHighest secHigh = new SecondHighest();
		int num = secHigh.getSecondHighest(arr);
		System.out.println("Second Highest " + num);
	}

	int getSecondHighest(int arr[]) {
		int max = arr[0];
		int secondMax = arr[0];
		for (int element : arr) {
			if (element > max) {
				secondMax = max;
				max = element;
			} else if (element > secondMax)
				secondMax = element;
		}
		return secondMax;
	}
}
