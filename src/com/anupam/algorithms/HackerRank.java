package com.anupam.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HackerRank {
	
	public static int[][] readTwoDArray(){		
		int[][] twoDInputArray = new int[6][6];
		try(Scanner scn = new Scanner(System.in))
		{
			for(int i = 0; i  < 6; i++){
				String s = scn.nextLine();	
				String[] strArray = s.split(" ");
				for(int j = 0; j < 6; j ++){					
					twoDInputArray[i][j] = Integer.parseInt(strArray[j]);					
				}
			}
		}
		return twoDInputArray;
	}

	public static int twoDArrayProblem(){
		int[][] inputtwoDArray = HackerRank.readTwoDArray();
		// get all possible 3 x 3 2d arrays from the input 2d array
		List<int[][]> listSubArrays = getAllThreeByThreeArrays(inputtwoDArray, 0, 0, 3, 3);
		int[] sumArray = new int[listSubArrays.size()];
		for(int i = 0; i < listSubArrays.size(); i++){			
			int sum = sumTwoDArrayHourGlassElems(listSubArrays.get(i));
			sumArray[i] = sum;
		}		
		Arrays.sort(sumArray);
		//SortingAlgos.insertionSort(sumArray);
		return sumArray[sumArray.length - 1];
	}
	
	private static int sumTwoDArrayHourGlassElems(int[][] inputTwoDArray){
		int sum = 0;
		for(int i = 0; i < inputTwoDArray.length; i++){
			for(int j = 0; j < inputTwoDArray[i].length; j++){
				if(i == 1){
					if( j == 0 || j == 2){
						continue;
					}
				}				
				sum += inputTwoDArray[i][j];
			}
		}
		return sum;
	}
	
	private static List<int[][]> getAllThreeByThreeArrays(int[][] inputTwoDArray, int rowStartIndex, int colStartIndex, int rowCount, int colCount){
		
		List<int[][]> threeByThreeArrayList = new ArrayList<int[][]>();
		while(rowStartIndex < 4)
		{			
			while(colStartIndex < 4){
				int[][] twoDArray = new int[rowCount][colCount];
				for(int i = rowStartIndex, k = 0; i < rowCount + rowStartIndex; i++, k++){							
					for(int j = colStartIndex, l = 0; j < colCount + colStartIndex; j++, l++){
						twoDArray[k][l] = inputTwoDArray[i][j];
					}				
				}	
				threeByThreeArrayList.add(twoDArray);
				colStartIndex++;
			}
			colStartIndex = 0;
			rowStartIndex++;
		}
		
		return threeByThreeArrayList;
	}
}
