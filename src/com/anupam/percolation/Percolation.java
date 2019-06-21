package com.anupam.percolation;

import java.util.HashMap;

public class Percolation {
	Node[][] grid;
	int openSites;
	int totalSites;
	WeightedQuickUnionUF wquf;
	HashMap<Integer, String> rootRowColMap = new HashMap<>();

	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		if (n > 0)
			grid = new Node[n][n];
		else
			throw new IllegalArgumentException();
		int counter = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Node node = new Node();
				node.setRoot(counter++);
				rootRowColMap.put(node.getRoot(), i + "," + j);
				grid[i][j] = node;
			}
		}
		totalSites = counter;
		wquf = new WeightedQuickUnionUF(n * n);
	};

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		grid[row][col].setIsOpen(1);
		System.out.println("Opening " + row + " " + col);
		openSites++;

		int root = grid[row][col].getRoot();
		if (col > 0 && grid[row][col - 1].getIsOpen() == 1) {
			int neighbourLeft = grid[row][col - 1].getRoot();
			wquf.union(root, neighbourLeft);
			root=wquf.find(neighbourLeft);					
			grid[row][col - 1].setRoot(root);
		}

		if (col < grid.length - 1 && grid[row][col + 1].getIsOpen() == 1) {
			int neighbourRight = grid[row][col + 1].getRoot();
			wquf.union(root, neighbourRight);
			root=wquf.find(neighbourRight);
			grid[row][col + 1].setRoot(root);
		}

		if (row > 0 && grid[row - 1][col].getIsOpen() == 1) {
			int neighbourTop = grid[row - 1][col].getRoot();
			wquf.union(root, neighbourTop);
			root=wquf.find(neighbourTop);
			grid[row - 1][col].setRoot(root);
		}

		if (row < grid.length - 1 && grid[row + 1][col].getIsOpen() == 1) {
			int neighbourBottom = grid[row + 1][col].getRoot();
			wquf.union(root, neighbourBottom);
			root = wquf.find(neighbourBottom);
			grid[row + 1][col].setRoot(root);
		}
		grid[row][col].setRoot(root);
	};

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (grid[row][col].getIsOpen() == 1)
			return true;
		return false;
	};

	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		int root = grid[row][col].getRoot();
		int rootElement = wquf.find(root);
		if (rootElement < grid.length)
			return true;
		return false;
	};

	// number of open sites
	public int numberOfOpenSites() {
		return openSites;
	};

	// does the system percolate?
	public boolean percolates() {
		boolean percolates = false;
		int n=grid.length-1;
		for(int i=0;i<grid.length;i++){
			if(isFull(n,i)){
				percolates=true;
				break;
			}
		}
		return percolates;
	};

	void startSimulation() {
		while (!percolates()) {
			int siteToOpen = StdRandom.uniform(1, totalSites);
			String rowCol[] = rootRowColMap.get(siteToOpen).split(",");
			int row = Integer.parseInt(rowCol[0]);
			int col = Integer.parseInt(rowCol[1]);
			if (!isOpen(row, col))
				open(row, col);
		}
	}

	// test client (optional)
	public static void main(String[] args) {
		Percolation percolation = new Percolation(2);
		percolation.startSimulation();
	};
}

class Node {
	int isOpen;
	int root;

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}
}