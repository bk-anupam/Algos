package com.anupam.algorithms.compression;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class HuffmanCode {
    // We consider characters in the normal ASCII table
    private Map<Character, String> charBinaryCodeMap = new HashMap<>();
    private InputStream inputStream;
    private PrintStream outputStream;
    private Node huffmanTrie;
    // Number of characters in the standard ASCII table. This is what our input data is assumed to possibly contain
    int R = 128;

    public HuffmanCode(InputStream in, PrintStream out){
        this.inputStream = in;
        this.outputStream = out;
    }

    public Node buildTrie(int[] charFrequency){
        PriorityQueue<Node> minFreqCharsPQ = new PriorityQueue<>();
        // https://stackoverflow.com/questions/28790784/java-8-preferred-way-to-count-iterations-of-a-lambda
        /*AtomicInteger counter = new AtomicInteger(0);
        Arrays.stream(charFrequency)
                // only those characters which appear in input data to be considered while making trie
                .filter(entry -> entry > 0)
                .forEach(entry -> {
                    minFreqCharsPQ.add(new Node((char)counter.get(), entry, null, null));
                    counter.incrementAndGet();
                });*/
        for(int i = 0; i < charFrequency.length; i++){
            if(charFrequency[i] > 0)
                minFreqCharsPQ.add(new Node((char)i, charFrequency[i], null, null));
        }
        Node rootNode = null;
        while(minFreqCharsPQ.size() > 1){
            Node smaller = minFreqCharsPQ.poll();
            Node nextSmaller = minFreqCharsPQ.poll();
            // Merge the two to create a new internal node ( that doesn't hold any character symbol )
            // '\0' is the null character which we will use to create an internal node
            rootNode = new Node('\0', smaller.charFrequency + nextSmaller.charFrequency, smaller, nextSmaller);
            minFreqCharsPQ.add(rootNode);
        }
        return rootNode;
    }

    private Map<Character, String> buildCodeTable(Node node, Map<Character, String> charCodeMap, String code){
        if(node.isLeaf()){
            charCodeMap.put(node.charSymbol, code);
        }else {
            buildCodeTable(node.leftChild, charCodeMap, code + "0");
            buildCodeTable(node.rightChild, charCodeMap, code + "1");
        }
        return charCodeMap;
    }

    public void compress() throws Exception{
        int[] inputCharFreqCount = getInputCharFreqCount(inputStream);
        huffmanTrie = buildTrie(inputCharFreqCount);
        // Build code table which maps each of the distinct input characters to its corresponding binary code by traversing the huffman trie
        Map<Character, String> charCodeMap = buildCodeTable(huffmanTrie, new HashMap<>(), "");
        // Read the input stream again character by character
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            int charAsInt;
            // https://www.javamex.com/tutorials/io/character_stream_reader.shtml
            // The read method of a reader returns a character as its int value ( for extended ASCII table where
            // a character is represented by 2 bytes or 16 bits, this means a value ranging from 0 to 65535
            while((charAsInt = reader.read()) != -1){
                outputStream.print(charCodeMap.get((char)charAsInt));
            }
        }
    }

    public void expand() throws Exception{
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            int charAsInt;
            Node currNode = huffmanTrie;
            while((charAsInt = reader.read()) != -1){
                if(currNode.isLeaf())
                    outputStream.print(currNode.charSymbol);
                else if((char)charAsInt == '0')
                    currNode = currNode.leftChild;
                else if((char)charAsInt == '1')
                    currNode = currNode.rightChild;
            }
        }
    }

    private int[] getInputCharFreqCount(InputStream inputStream) throws Exception{
        // index the decimal value of the character symbol in ASCII table, value is the freq. of that character in the input
        int[] charFrequency = new int[R];
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String inputLine;
            while((inputLine = reader.readLine()) != null){
                inputLine.codePoints()
                         .forEach(charIntValue -> charFrequency[charIntValue] += 1);
            }
        }
        return charFrequency;
    }

    private class Node implements Comparable<Node>{
        private Character charSymbol;
        boolean isLeaf = false;
        private Node leftChild;
        private Node rightChild;
        private int charFrequency;

        private Node(Character pChar, int charFrequency, Node leftChild, Node rightChild){
            this.charSymbol = pChar;
            this.charFrequency = charFrequency;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        private boolean isLeaf(){
            return leftChild == null && rightChild == null;
        }

        @Override
        public int compareTo(Node o) {
            return new Integer(this.charFrequency).compareTo(new Integer(o.charFrequency));
        }
    }
}
