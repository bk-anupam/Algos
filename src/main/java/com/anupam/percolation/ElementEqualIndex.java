package com.anupam.percolation;

public class ElementEqualIndex {
	public static void main(String[] args) {
		int arr[] = { -1, 0, 1, 2, 3, 5, 7, 10, 11 };
		ElementEqualIndex equalIndex = new ElementEqualIndex();
		int index = equalIndex.getIndex(arr);
		if (index > -1)
			System.out.println("Desired Index " + index + " Value at index " + arr[index]);
		else
			System.out.println("No index with equal element found ");
	}

	int getIndex(int arr[]) {
		int minIndex = 0;
		int maxIndex = arr.length - 1;
		int eqIndex = -1;
		while (minIndex <= maxIndex) {
			int median = arr[(minIndex + maxIndex) / 2];
			int index = (minIndex + maxIndex) / 2;
			if (median == index) {
				eqIndex = index;
				break;
			}
			if (median < index)
				minIndex = index + 1;
			if (median > index)
				maxIndex = index - 1;
		}
		return eqIndex;
	}
}
