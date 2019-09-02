package com.anupam.algorithms.compression;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class HuffmanCode {
    // We consider characters in the normal ASCII table
    private Map<Character, String> charBinaryCodeMap = new HashMap<>();
    private InputStream inputStreamToCompress;
    // Number of characters in the standard ASCII table. This is what our input data is assumed to possibly contain
    int R = 128;

    public HuffmanCode(InputStream in){
        this.inputStreamToCompress = in;
    }

    public Node buildTrie(int[] charFrequency){
        PriorityQueue<Node> minFreqCharsPQ = new PriorityQueue<>();
        // https://stackoverflow.com/questions/28790784/java-8-preferred-way-to-count-iterations-of-a-lambda
        AtomicInteger counter = new AtomicInteger(0);
        Arrays.stream(charFrequency)
                // only those characters which appear in input data to be considered while making trie
                .filter(entry -> entry > 0)
                .forEach(entry -> {
                    minFreqCharsPQ.add(new Node((char)counter.get(), entry, null, null));
                    counter.incrementAndGet();
                });

        Node rootNode = null;
        while(minFreqCharsPQ.size() > 1){
            Node smaller = minFreqCharsPQ.poll();
            Node nextSmaller = minFreqCharsPQ.poll();
            // Merge the two to create a new internal node ( that doesn't hold any character symbol )
            // '\0' is the null character which we will use to create an internal node
            rootNode = new Node('\0', smaller.charFrequency + nextSmaller.charFrequency, smaller, nextSmaller);
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
        int[] inputCharFreqCount = getInputCharFreqCount(inputStreamToCompress);
        Node root = buildTrie(inputCharFreqCount);
        // Build code table which maps each of the distinct input characters to its corresponding binary code by traversing the huffman trie
        Map<Character, String> charCodeMap = buildCodeTable(root, new HashMap<>(), "");
    }

    public void expand(){

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
