package com.anupam.algorithms;

import java.util.Arrays;

public class MergeSortSA {
	static long inversions=0;
	public static void main(String[] args) {
		int[] array = {54044,14108,79294,29649,25260,60660,2995,53777,49689,9083,16122,90436,4615,40660,25675,58943,92904,9900,95588};
		MergeSortSA mergeSort = new MergeSortSA();
		int sorted[] = mergeSort.sortElements(array);
		System.out.println("Number of elements " + array.length+" Inversions "+inversions);
		for (int element : sorted) {
			System.out.print(element + " ");
		}
	}

	int[] sortElements(int[] initialArray) {
		int[][] splitArrays = { initialArray };
		while (initialArray.length > splitArrays.length) {
			splitArrays = split(splitArrays);
		}

		while (splitArrays.length > 1) {
			int[][] mergedArray = new int[splitArrays.length / 2][];
			int count = 0;
			for (int i = 0; i < splitArrays.length;) {
				mergedArray[count++] = merge(splitArrays[i++], splitArrays[i++]);
			}
			splitArrays = mergedArray;
		}
		return splitArrays[0];
	}

	int[][] split(int[][] array) {
		int[][] splitArrays = new int[array.length * 2][];
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			splitArrays[count++] = Arrays.copyOfRange(array[i], 0, array[i].length / 2);
			splitArrays[count++] = Arrays.copyOfRange(array[i], array[i].length / 2, array[i].length);
		}
		return splitArrays;
	}

	int[] merge(int arr1[], int arr2[]) {
		int finalArr[] = new int[arr1.length + arr2.length];
		int count = 0, i = 0, j = 0;

		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] <= arr2[j]) {
				finalArr[count++] = arr1[i++];
			} else {
				finalArr[count++] = arr2[j++];
				inversions=inversions+(arr1.length-i);
			}
		}

		while (i < arr1.length) {
			finalArr[count++] = arr1[i++];
		}
		while (j < arr2.length) {
			finalArr[count++] = arr2[j++];
		}

		return finalArr;
	}
}
