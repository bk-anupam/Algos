package com.anupam.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Comparator;

public class PizzaProblem {

	public static void main(String[] args) {
		// Take two heaps. One the first heap keep the orders sorted on the basis of order time.
		// The second heap will sort the orders on the basis of cooking time - order time. The lower the difference ( both +ve or -ve )
		// the higher the priority of order

		// a. Now take the first order ( with least order time ) and check its cooking time. 
		// b. Take all orders with order time <= cooking time of first order and pop them out and insert them to second heap
		// c. Now schedule all the orders in second heap for cooking adding up their cooking times to a cumulative cooking time variable
		// d. Now take all orders from first heap with order time <= cumulative cooking time and move them to second heap
		// e. Repeat step c and d till all orders in first heap are exhausted.
		
		//Queue<PizzaOrder> orders = getInputOurders();
		PriorityQueue<PizzaOrder> orders = new PriorityQueue<PizzaOrder>((PizzaOrder o1, PizzaOrder o2) -> {
			return ((Integer)o1.orderTime).compareTo((Integer)o2.orderTime);
		});
		
		orders.add(new PizzaOrder(0, 6));
		orders.add(new PizzaOrder(1, 10));
		orders.add(new PizzaOrder(2, 8));
		orders.add(new PizzaOrder(3, 4));
		orders.add(new PizzaOrder(10, 4));		
		
        System.out.print(HackerRankPizzaProblem2(orders));
    }
	
	public static int HackerRankPizzaProblem(Queue<PizzaOrder> orders){
				
		PriorityQueue<PizzaOrder> sortedOrders = new PriorityQueue<PizzaOrder>(new Comparator<PizzaOrder>(){
			@Override
			public int compare(PizzaOrder arg0, PizzaOrder arg1) {
				if(arg0.orderTime == 0){
					return -1;
				}else if( arg1.orderTime == 0){
					return 1;
				}
				
				Integer diff1 = (arg0.cookingTime - arg0.orderTime);
				Integer diff2 = (arg1.cookingTime - arg1.orderTime);
				return diff1.compareTo(diff2);
			}
		});
		
		Queue<PizzaOrder> ordersToCook = new LinkedList<PizzaOrder>();						
		boolean firstTime = true;
		
		while(orders.size() > 0){
			PizzaOrder orderToCook = orders.poll();
			int cumulativeCookingTime = 0;
						
			cumulativeCookingTime += orderToCook.cookingTime;	
			
			// consider the subset of input orders where order time < cumulative cooking time of orders scheduled for cooking
				
			while(orderToCook != null && orderToCook.orderTime <= cumulativeCookingTime){
				sortedOrders.add(orderToCook);
				orderToCook = orders.poll();
				cumulativeCookingTime += orderToCook.cookingTime;	
			}
						
			PizzaOrder order = sortedOrders.poll();			
			
			while(order != null){				
				ordersToCook.add(order);
				order = sortedOrders.poll();
			}						
		}
				
		return averageWaitingTime(ordersToCook);
	}
	
	public static int HackerRankPizzaProblem2(Queue<PizzaOrder> orders){
		int inputOrderSize = orders.size();
		PriorityQueue<PizzaOrder> sortedOrders = new PriorityQueue<PizzaOrder>(new Comparator<PizzaOrder>(){
			@Override
			public int compare(PizzaOrder arg0, PizzaOrder arg1) {
				if(arg0.orderTime == 0){
					return -1;
				}else if( arg1.orderTime == 0){
					return 1;
				}
				
				Integer diff1 = (arg0.cookingTime - arg0.orderTime);
				Integer diff2 = (arg1.cookingTime - arg1.orderTime);
				return diff1.compareTo(diff2);
			}
		});
		
		Queue<PizzaOrder> ordersToCook = new LinkedList<PizzaOrder>();						
		boolean firstTime = true;
		
		while(ordersToCook.size() < inputOrderSize){
			int cumulativeCookingTime = 0;
			if(ordersToCook.size() > 0){
				cumulativeCookingTime = ordersToCook.stream().mapToInt(order -> order.cookingTime).sum();
			}
			
			if(firstTime){
				PizzaOrder orderToCook = orders.poll();
				if(orderToCook != null)
					ordersToCook.add(orderToCook);
				
				// Now take all orders for which order time < cooking time of first order and sort them in order of priority 
				// and move the order with highest priority to cooking queue.
				PizzaOrder order2 = orders.poll();
				while(order2 != null && order2.orderTime <= orderToCook.cookingTime){
					sortedOrders.add(order2);
					order2 = orders.poll();
				}
				
				ordersToCook.add(sortedOrders.poll());
				firstTime = false;
			}else{
				PizzaOrder order3 = orders.poll();
				while(order3 != null && order3.orderTime <= cumulativeCookingTime){
					sortedOrders.add(order3);
					order3 = orders.poll();
				}
				ordersToCook.add(sortedOrders.poll());
			}					
		}
				
		return averageWaitingTime(ordersToCook);
	}
	
	public static Queue<PizzaOrder> getInputOurders(){
		Scanner scn = new Scanner(System.in);
		System.out.println("Length of input array: ");
		int length = scn.nextInt();		
		
		PriorityQueue<PizzaOrder> orders = new PriorityQueue<PizzaOrder>((PizzaOrder o1, PizzaOrder o2) -> {
			return ((Integer)o1.orderTime).compareTo((Integer)o2.orderTime);
		});
		
		for(int i = 0; i < length; i++){
			int orderTime = scn.nextInt();
			int cookingTime = scn.nextInt();
			orders.add(new PizzaOrder(orderTime, cookingTime));					
		}
		
		return orders;
	}
	
	
	private static int averageWaitingTime(Queue<PizzaOrder> orders){
		int waitingTime = 0;
		int cumulativeCookingTime = 0;
		int cumulativeWaitingTime = 0;
		List<Integer> waitingTimes = new ArrayList<Integer>();
		int length = orders.size();
		for(int i = 0; i < length; i++){
			PizzaOrder order = orders.poll();
			cumulativeCookingTime += order.cookingTime;
			waitingTimes.add(cumulativeCookingTime - order.orderTime);
		}				
		
		cumulativeWaitingTime = waitingTimes.stream().mapToInt(waitTime -> waitTime).sum();
		int avgWaitTime = cumulativeWaitingTime / waitingTimes.size();
		return avgWaitTime;
	}
}

class PizzaOrder{
	public int cookingTime;
	public int orderTime;
	
	public PizzaOrder(int pOrderTime, int pCookingTime){
		orderTime = pOrderTime;
		cookingTime = pCookingTime;
	}
		
}
