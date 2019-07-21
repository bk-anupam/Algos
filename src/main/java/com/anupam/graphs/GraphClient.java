package com.anupam.graphs;

import java.io.*;
import java.util.Map;

public class GraphClient {

    public static void main(String[] args) throws IOException {
        String inputFileName = args[0];
        boolean isDirected = false;
        if(!inputFileName.equalsIgnoreCase("tinyG") && !inputFileName.equalsIgnoreCase("tinyDG") && !inputFileName.equalsIgnoreCase("tinyDAG"))
            throw new IOException("Invalid input file name");

        if(inputFileName.equalsIgnoreCase("tinyDG") || inputFileName.equalsIgnoreCase("tinyDAG"))
            isDirected = true;

        inputFileName = inputFileName + ".txt";
        Graph graph = loadGraphFromFile(inputFileName, isDirected);
        if(!isDirected)
            unDirectedGraphsRoutines(inputFileName);
        else
            runDirectedGraphRoutines((DiGraph)graph);
    }

    private static void runDirectedGraphRoutines(DiGraph diGraph){
        runTopologicalSort(diGraph);
        System.out.println("####################################");
        getSCCs(diGraph);
    }

    private static void runTopologicalSort(DiGraph diGraph) {
        TopologicalSort topologicalSort = new TopologicalSort(diGraph);
        Map<Integer, Integer> topologicalOrder = topologicalSort.getTopologicalOrder();
        for(Map.Entry entry: topologicalOrder.entrySet()){
            System.out.println(String.format("vertex: %s, topological order: %s", entry.getValue(), entry.getKey()));
        }
    }

    private static void getSCCs(DiGraph diGraph){
        SCC scc = new SCC(diGraph);
        scc.printVertexComponentId();
    }

    private static Graph loadGraphFromFile(String inputFileName, boolean isDirected) throws IOException{
        InputStream graphStream = GraphClient.class.getClassLoader().getResourceAsStream(inputFileName);
        if(isDirected)
            return new DiGraph(graphStream);
        else
            return new UndirectedGraph(graphStream);
    }

    private static void unDirectedGraphsRoutines(String strGraphFileLoc) throws IOException {
        File graphInputFile = new File(strGraphFileLoc);
        InputStream graphInputStream = GraphClient.class.getClassLoader().getResourceAsStream("tinyG.txt");
        //InputStream graphInputStream = new FileInputStream(graphInputFile);
        Graph graph = new UndirectedGraph(graphInputStream);
        IGraphSearch dfsSearch = new DepthFirstSearch(graph, 0);
        IGraphSearch bfsSearch = new BreadthFirstSearch(graph, 0);
        boolean hasCycle = dfsSearch.hasCycle();
        boolean hasCycle2 = bfsSearch.hasCycle();
        System.out.println("hasCycle: " + hasCycle);
        System.out.println("isBipartite using dfs: " + dfsSearch.isBipartite());
        System.out.println("hasCycle2: " + hasCycle2);
        System.out.println("isBipartite using bfs: " + bfsSearch.isBipartite());
    }
}
