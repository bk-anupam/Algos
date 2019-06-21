package com.anupam.algorithms;

public class MergeSort {

	public static void main(String[] args){
		int[] A = new int[]{3,8};
		int[] B = new int[]{1,4};
		
		int[] R = merge(A, B);				
	}
	
	public static int[] merge(int[] A, int[] B){
		
		int i = 0;
		int j = 0;
		int k = 0;
		int[] R = new int[A.length + B.length];
		
		while(i < A.length && j < B.length){
			
			if(A[i] < B[j]){
				R[k] = A[i];
				k++;
				i++;
				continue;
			}
			
			if(B[j] < A[i]){
				R[k] = B[j];
				k++;
				j++;
				continue;
			}
		}
		
		// B is exhausted and A has elements remaining
		if(i < A.length && j == B.length){
			while(i < A.length){
				R[k] = A[i];
				k++;
				i++;
			}
		}
		
		if(j < B.length && i == A.length){
			while(j < B.length){
				R[k] = B[j];
				k++;
				j++;
			}
		}
		
		return R;
	}
	
}
