package com.anupam.percolation;

public class SuccessorWithDelete {
	int arr[];

	public SuccessorWithDelete(int n) {
		arr=new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
	}
	
	int delete(int t){
		arr[t]=0;
		return arr[t+1];
	}

	public static void main(String[] args) {
		SuccessorWithDelete sud=new SuccessorWithDelete(10);
		System.out.println("deleting 3 "+sud.delete(3));
		System.out.println("deleting 7 "+sud.delete(7));
	}
}
