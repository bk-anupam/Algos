package com.anupam.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import com.anupam.algorithms.sorting.SortingAlgos;
import com.anupam.graphs.Graph;
import com.anupam.graphs.UndirectedGraph;

public class Driver {
	
	public static void main(String[] args) throws Exception
	{
		//TestMap();
		//MergeMap();		
		//TestQueue();
		//System.out.println(HackerRank.twoDArrayProblem());
		//HackerRankMedianProblem();
		//PizzaProblem.HackerRankPizzaProblem();
//		Test obj = new Test();
//		obj.test();
//		TestMethod(obj);
//		obj.test();
		//collectionModify();
		quickSortTest();		
		//testGraph();
		String str = "anupam";
		String[] strArray = new String[256];
		char c = str.charAt(0);
		String v = strArray[c];
	}
	
	private static void testGraph() throws IOException{
		File file = new File("E:\\tinyG.txt");		
		InputStream in = new FileInputStream(file);
		Graph graph = new UndirectedGraph(in);				
	}
	
	private static void quickSortTest(){
		Integer[] intArray = new Integer[]{5, 8, 1, 3, 7, 9, 2};
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] counter ={ 0 };
		Arrays.asList(intArray).forEach(x -> map.put(x, counter[0]++));
		SortingAlgos.quickSort(intArray);
		Arrays.asList(intArray).forEach(x -> System.out.print(x.intValue() + " "));
	}

	private static void collectionModify(){
		CopyOnWriteArrayList<Test> list = new CopyOnWriteArrayList<Test>();
		list.add(new Test("field1"));
		list.add(new Test("field2"));
		list.add(new Test("field3"));
		int counter=10;
		
		for(Test element:list){
			list.add(new Test("field "+counter+5));
			element.field="field "+counter++;
			list.remove(element);
		}
		
		list.forEach(x -> System.out.println(x.field));
		
	}
	
		
	private static void twodArrayProblem(){
		int[][] inputtwoDArray = HackerRank.readTwoDArray();
		printArray(inputtwoDArray);
	}
	
	private static void insertionSort(){
		int[] inputArray = getIntArrayFromConsole();
		SortingAlgos.insertionSort(inputArray);
		printArray(inputArray);
	}
	
	private static void TestMap(){
		MapProblem mapProblem = new MapProblem();
		Map<String, Integer> phoneBook = mapProblem.populateMap();
	}
	
	private static void printArray(int[] inputArray){
		Arrays.stream(inputArray).forEach(System.out::print);
	}
	
	private static void printArray(int[][] inputArray){
		Arrays.stream(inputArray).forEach(System.out::print);
	}
	
	private static void MergeMap() {
		Map<String, String> map1 = new HashMap<>();
		map1.put("test", "1");
		map1.put("test1", "1");
		Map<String, String> map2 = new HashMap<>();
		map2.put("test", "1");
		
		map1.forEach((k,v) -> map2.merge(k, v,  (v1, v2) -> v1+v2));
	}

	private static int[] getIntArrayFromConsole(){
		Scanner scn = new Scanner(System.in);
		System.out.println("Length of input array: ");
		int length = scn.nextInt();
		int[] inputArray = new int[length];
		for(int i = 0; i < length; i++){
			inputArray[i] = scn.nextInt();
		}
		return inputArray;
	}
	
	private static void HackerRankMedianProblem(){
		Scanner scn = new Scanner(System.in);
		System.out.println("Length of input array: ");
		int length = scn.nextInt();
		HackerRankMedian hackerRankMedian = new HackerRankMedian();
		List<Double> medianList = new ArrayList<Double>(); 
		for(int i = 0; i < length; i++){
			hackerRankMedian.insert(scn.nextInt());
			medianList.add(hackerRankMedian.findMedian());
		}
		medianList.stream().forEach(System.out::println);
	}
	
	private static void TestQueue() throws Exception{
		QueueUsingArray<Integer> queue = new QueueUsingArray<Integer>(10);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		
		int item1 = queue.dequeue();
		int item2 = queue.dequeue();
		
		queue.enqueue(5);
		
		int item3 = queue.dequeue();
		int item4 = queue.dequeue();
		int item5 = queue.dequeue();
		Integer item6 = queue.dequeue();
	}
}
