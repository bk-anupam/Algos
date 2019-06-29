package com.anupam.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        int[] inputArray = getIntArrayFromConsole();
        int j = partition(inputArray, 0, inputArray.length - 1);
        Arrays.asList(inputArray).forEach(x -> System.out.print(x + " "));
    }
    
    private static int[] getIntArrayFromConsole(){
		Scanner scn = new Scanner(System.in);		
		int length = scn.nextInt();
		int[] inputArray = new int[length];
		for(int i = 0; i < length; i++){
			inputArray[i] = scn.nextInt();
		}
		return inputArray;
	}
    
    private static int partition(int[] array, int start, int end){
		int pivotIndex = start;
		int i = start + 1;
		for(int j = start + 1; j < array.length; j++){
			if(array[j] < array[pivotIndex] ){
				// Here i if the partition that divides the array into two halves. Elements < i are all < pivot and elements > i are all > pivot
				// swap array[j] with left most element bigger than the pivot i.e. array[i]
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				// Now move i by one position to the right
				i++;
			}
		}
		// Once you have done the linear scan of the array swap the pivot ( index 0 ) with element at index i - 1
		int temp = array[start];
		array[start] = array[i - 1];
		array[i - 1] = temp;
		return i;		
	}	
}
