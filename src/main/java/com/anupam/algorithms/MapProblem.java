package com.anupam.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MapProblem {

	public Map<String, Integer> populateMap(){
		Scanner scanner = new Scanner(System.in);
		int noItems = scanner.nextInt();
		Map<String, Integer> phoneBook = new HashMap<>();
		scanner.nextLine();
		for(int i = 0; i<noItems; i++){
			String key = scanner.nextLine();
			String value = scanner.nextLine();
			//scanner.nextLine();
			phoneBook.put(key, Integer.parseInt(value));
		}
		
		while(scanner.hasNext())
		{			
			String queryString = scanner.nextLine();
			if(queryString == "X"){
				break;
			}
			Integer phoneNo = phoneBook.get(queryString);
			System.out.println(phoneNo == null ? "Not found" : phoneNo.toString());
		}
		return phoneBook;
	}
	
}
