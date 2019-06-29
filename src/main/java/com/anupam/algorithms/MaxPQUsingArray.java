package com.anupam.algorithms;

import java.util.Date;

public class MaxPQUsingArray<T extends Comparable<T>> {

	T[] itemsArray = null;
	int capacity = 0;
	int index = 0;
	
	@SuppressWarnings("unchecked")
	public MaxPQUsingArray(){
		itemsArray = (T[]) new Object[16];		
		capacity = 16;
	}
	
	@SuppressWarnings("unchecked")
	public MaxPQUsingArray(int pCapacity){
		itemsArray = (T[])new Object[pCapacity];
		capacity = pCapacity;
	}
	
	// insert
	
	public void insert(T item) throws Exception{
		if(index == itemsArray.length - 1){
			throw new Exception("No space available for insertion");
		}
		
		itemsArray[index] = item;
		index++;
	}
	
	// Internal array is sorted in ascending order with item of least priority at index 0 and highest at index length - 1
	public void enqueue(T item){
		if(index == itemsArray.length - 1){
			T[] tempItemsArray = (T[])new Object[2*itemsArray.length];
			System.arraycopy(itemsArray, 0, tempItemsArray, 0, itemsArray.length);
			itemsArray = tempItemsArray;
		}
		
		int insertionIndex = 0;
		
		for(int i=0; i<itemsArray.length; i++){
			if(item.compareTo(itemsArray[i]) > 0){
				insertionIndex = i;
			}
		}
		
		for(int j=insertionIndex; j <= index; j++){
			itemsArray[j+1] = itemsArray[j];
		}
		
		index++;
		itemsArray[insertionIndex] = item;
		
	}
	
	// remove max item	
	public T delMax(){		
		int maxIndex = 0;
		for(int i = 1; i < itemsArray.length; i++){
			if(itemsArray[i].compareTo(itemsArray[maxIndex]) > 0){
				maxIndex = i;
			}
		}
		
		T itemToreturn = itemsArray[maxIndex];
		index--;
		itemsArray[maxIndex] = itemsArray[index];
		itemsArray[index] = null;
		return itemToreturn;		
	}
}

class Transaction implements Comparable<Transaction>{
	
	public int amount;
	
	public Date txnDate;
	
	public String customer;

	@Override
	public int compareTo(Transaction o) {
		// TODO Auto-generated method stub
		return ((Integer)amount).compareTo((Integer)o.amount);
	}
	 
	
}