package com.hackerrank;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] unsorted = new String[n];
		for (int unsorted_i = 0; unsorted_i < n; unsorted_i++) {
			unsorted[unsorted_i] = in.next();
		}
		in.close();
		Solution solution = new Solution();
//		BigInteger sortedArr[] = solution.sort(unsorted);
		
		solution.stringSort(unsorted);
		for (String element : unsorted) {
			System.out.println(element);
		}
	}

	void stringSort(String[] arr) {
		Arrays.sort(arr, new StringCompare());
	}

	BigInteger[] sort(BigInteger[] array) {
		BigInteger[][] splitArray = { array };
		while (splitArray.length < array.length) {
			splitArray = split(splitArray);
		}

		while (splitArray.length > 1) {
			BigInteger[][] mergedArray = new BigInteger[splitArray.length / 2][];
			int counter = 0, merge = 0;
			while (counter < splitArray.length) {
				mergedArray[merge++] = merge(splitArray[counter++], splitArray[counter++]);
			}
			splitArray = mergedArray;
		}
		return splitArray[0];
	}

	BigInteger[][] split(BigInteger[][] array) {
		BigInteger[][] newArray = new BigInteger[array.length * 2][];
		int counter = 0;
		for (int i = 0; i < array.length; i++) {
			newArray[counter++] = Arrays.copyOfRange(array[i], 0, array[i].length / 2);
			newArray[counter++] = Arrays.copyOfRange(array[i], array[i].length / 2, array[i].length);
		}
		return newArray;
	}

	BigInteger[] merge(BigInteger[] arr1, BigInteger[] arr2) {
		BigInteger mergedArr[] = new BigInteger[arr1.length + arr2.length];
		int counter = 0, i = 0, j = 0;
		for (; i < arr1.length && j < arr2.length;) {
			if (arr1[i].compareTo(arr2[j]) <= 0) {
				mergedArr[counter++] = arr1[i++];
			} else {
				mergedArr[counter++] = arr2[j++];
			}
		}

		while (i < arr1.length) {
			mergedArr[counter++] = arr1[i++];
		}

		while (j < arr2.length) {
			mergedArr[counter++] = arr2[j++];
		}

		return mergedArr;
	}
}

class StringCompare implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		try {
			long l1 = Long.parseLong(o1);
			long l2 = Long.parseLong(o2);
			if (l1 < l2)
				return -1;
			if (l2 < l1)
				return 1;
		} catch (NumberFormatException e) {
			BigInteger b1 = new BigInteger(o1);
			BigInteger b2 = new BigInteger(o2);
			return b1.compareTo(b2);
		}
		return 0;
	}

}
