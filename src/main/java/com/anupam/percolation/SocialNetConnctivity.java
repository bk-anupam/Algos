package com.anupam.percolation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SocialNetConnctivity {
	static String fileName = "E:\\SocialNetworkExample.txt";
	HashMap<String, String> members = new HashMap<>();
	HashMap<String, Integer> treeSizes = new HashMap<>();

	boolean connected(String memberA, String memberB) {
		if (findRoot(memberA).equals(findRoot(memberB))) {
			return true;
		}
		return false;
	}

	void union(String memberA, String memberB) {
		String rootA = findRoot(memberA);
		String rootB = findRoot(memberB);
		if (!rootA.equals(rootB)) {
			Integer sizeA = treeSizes.get(rootA);
			Integer sizeB = treeSizes.get(rootB);
			if (sizeA < sizeB) {
				members.put(rootA, rootB);
				treeSizes.put(rootB, treeSizes.get(rootB)+1);
			} else {
				members.put(rootB, rootA);
				treeSizes.put(rootA,treeSizes.get(rootA)+1);
			}
		}
	}

	String findRoot(String member) {
		while (members.get(member) != member) {
			member = members.get(member);
		}
		return member;
	}

	String init() {
		FileReader file = null;
		BufferedReader reader = null;
		String lastTimeStamp = null;
		try {
			file = new FileReader(fileName);
			reader = new BufferedReader(file);
			String currLine;
			while ((currLine = reader.readLine()) != null) {
				String arr[] = currLine.split("\\|");
				String memberA = arr[0];
				String memberB = arr[1];
				String timeConnected = arr[2];
				if(!members.containsKey(memberA)){
					members.put(memberA, memberA);
					treeSizes.put(memberA, 1);
				}
				if(!members.containsKey(memberB)){
					members.put(memberB, memberB);
					treeSizes.put(memberB, 1);
				}
				if(!connected(memberA,memberB)){
					union(memberA,memberB);
					lastTimeStamp=timeConnected;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lastTimeStamp;
	}

	public static void main(String[] args) {
		SocialNetConnctivity connect = new SocialNetConnctivity();
		String timeStamp = connect.init();
		System.out.println("Desired Timestamp "+timeStamp);
	}
}
