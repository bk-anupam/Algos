package com.anupam.algorithms;

import java.util.Iterator;

public class QueueUsingArray<T> implements Iterable<T>{

	public T[] itemsArray = null;
	private int headIndex = 0;
	private int tailIndex = 0;
	private int noOfElements = 0;
	
	public QueueUsingArray(int initialCapacity){
		itemsArray = (T[])new Object[initialCapacity];
	}
	
	// Add item to the tail
	public void enqueue(T item) throws Exception{
		if(tailIndex > itemsArray.length - 1){
			throw new Exception("Can't add more items to the queue as the capacity has been exceeded");
		}
		
		itemsArray[tailIndex] = item;
		noOfElements++;
		tailIndex++;
	}
	
	public T dequeue(){
		T itemToReturn = null;
				
		if(headIndex <= tailIndex){
			itemToReturn = itemsArray[headIndex];
			itemsArray[headIndex] = null;
			headIndex++;
			noOfElements--;
		}
		else{
			headIndex = tailIndex = 0;
			noOfElements = 0;
		}
				
		return itemToReturn;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
