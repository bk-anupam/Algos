package com.anupam.algorithms;

public class BinarySearchSA {
	boolean isNumberFound = false;
	int finalIndex=0;
	static int iterations=0;
	public static void main(String[] args) {
		int primes[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };
		int minIndex = 0;
		int maxIndex = primes.length - 1;
		int numberToFind = 1;
		BinarySearchSA search = new BinarySearchSA();
		int index = search.findIndex(minIndex, maxIndex, primes, numberToFind);
		System.out.println("Index Found " + index+" Iterations "+iterations);
	}

	int findIndex(int minIndex, int maxIndex, int[] primes, int numberToFind) {
		iterations++;
		int midIndex = Math.round((minIndex + maxIndex) / 2);
		int numberFound = primes[midIndex];
		if (numberFound == numberToFind){
			isNumberFound = true;
			finalIndex=midIndex;
		}
		else if (numberFound < numberToFind)
			minIndex = midIndex + 1;
		else
			maxIndex = midIndex - 1;
		if(maxIndex<minIndex){
			finalIndex=-1;
			isNumberFound=true;
		}
		if (!isNumberFound)
			findIndex(minIndex, maxIndex, primes, numberToFind);
		return finalIndex;
	}
}
