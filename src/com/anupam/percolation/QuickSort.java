package com.anupam.percolation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuickSort {
	static int comparisons = 0;

	public static void main(String[] args) {
		// int arr[] = { -1, 2, 15, 8, 10, 16, 17, 3, 6, 11, 9, 19 };
		QuickSort sorting = new QuickSort();
		int arr[] = sorting.readFromFile();
		sorting.sort(arr, 0, arr.length - 1);

		System.out.println("Array after sort ");
		for (int element : arr) {
			System.out.print(element + " ");
		}

		System.out.println("\nElements: " + arr.length + " Comparisons: " + comparisons);
	}

	int[] readFromFile() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File("E:\\Coursera\\Algo-1-Tim\\Quiz\\QuickSort.txt")));
			String currLine;
			ArrayList<String> list = new ArrayList<>();
			while ((currLine = reader.readLine()) != null) {
				list.add(currLine);
			}
			int[] array = new int[list.size()];
			int count = 0;
			for (String element : list) {
				array[count++] = Integer.parseInt(element);
			}
			return array;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	int findMedian(int arr[], int left, int right) {
		int adjust = (right - left) % 2 == 0 ? 1 : 0;
		int middleIndex = ((left + right) / 2) - adjust;
		int middle = arr[middleIndex];
		int leftElement = arr[left];
		int rightElement = arr[right];
		System.out.println("left " + leftElement + " middle " + middle + " right " + rightElement);
		
		if ((leftElement <= middle || rightElement <= middle) && (middle <= rightElement || middle <= leftElement))
			return middleIndex;
		if ((middle <= leftElement || rightElement <= leftElement) && (leftElement <= rightElement || leftElement <= middle))
			return left;
		if ((leftElement <= rightElement || middle <= rightElement) && (rightElement <= middle || rightElement <= leftElement))
			return right;
		return 0;

	}

	int partition(int arr[], int left, int right) {
		comparisons = comparisons + (right - left);
		if (right - left > 1) {
			int median = findMedian(arr, left, right);
			swap(arr, left, median);
		}

		int p = arr[left];
		System.out.println("pivot " + p);
		int i = left + 1, j = left + 1;

		for (; j <= right; j++) {
			if (arr[j] < p) {
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, left, i - 1);

		return i - 1;
	}

	void sort(int arr[], int left, int right) {
		if (left < right) {
			int pivot = partition(arr, left, right);
			sort(arr, left, pivot - 1);
			sort(arr, pivot + 1, right);
		}
	}

	void swap(int arr[], int ind1, int ind2) {
		int temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}
}
