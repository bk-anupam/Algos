package com.anupam.algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InversionFinds {
	static String fileName = "E:\\Coursera\\Algo-1-Tim\\Assignments\\1\\IntegerArray.txt";
	static int arr[];

	void loadArrayFromFile() {
		FileReader file = null;
		BufferedReader reader = null;
		try {
			file = new FileReader(fileName);
			reader = new BufferedReader(file);
			String currLine;
			ArrayList<Integer> list = new ArrayList<>();
			while ((currLine = reader.readLine()) != null) {
				list.add(Integer.parseInt(currLine));
			}

			arr = new int[list.size()];
			int counter = 0;
			for (int element : list) {
				arr[counter++] = element;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		InversionFinds inverts = new InversionFinds();
		inverts.loadArrayFromFile();
		MergeSortSA mergeSort = new MergeSortSA();
		mergeSort.sortElements(arr);
		System.out.println("Inversions " + mergeSort.inversions + " Number of elements " + arr.length);
	}
}
