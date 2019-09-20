package com.anupam.algorithms.compression;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class HuffmanCodeBase implements Closeable{
    // We consider characters in the normal ASCII table
    protected InputStream inputStream;
    protected Node huffmanTrie;
    // Number of characters in the standard ASCII table. This is what our input data is assumed to possibly contain
    int R = 128;
    static final Logger logger = Logger.getLogger(HuffmanCodeBase.class);

    public HuffmanCodeBase(InputStream in){
        this.inputStream = in;
    }

    protected Node buildTrie(int[] charFrequency){
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

    protected Map<Character, String> buildCodeTable(Node node, Map<Character, String> charCodeMap, String code){
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
        logger.info("The code table: ");
        charCodeMap.forEach((k,v) -> logger.info(String.format("%s->%s", k, v)));
        readInputWriteCompressedData(inputStream, charCodeMap);
        logger.info("Compression completed");
    }

    public abstract void readInputWriteCompressedData(InputStream inputStream, Map<Character, String> charCodeMap)
            throws IOException;

    public abstract void expand(PrintStream expandedOutStream) throws IOException;

    protected int[] getInputCharFreqCount(InputStream inputStream) throws Exception{
        // index the decimal value of the character symbol in ASCII table, value is the freq. of that character in the input
        int[] charFrequency = new int[R];
        /*try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String inputLine;
            while((inputLine = reader.readLine()) != null){
                inputLine.codePoints()
                         .forEach(charIntValue -> charFrequency[charIntValue] += 1);
            }
        }*/
        if(inputStream.markSupported()){
            logger.info("Marking the beginning input stream");
            inputStream.mark(0);
        }
        //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int charAsInt;
        while((charAsInt = inputStream.read()) != -1){
            charFrequency[charAsInt] += 1;
        }
        logger.info("Completed calculating the input character frequency count");
        if(inputStream.markSupported()){
            inputStream.reset();
            logger.info("Reset the input stream to beginning");
        }
        return charFrequency;
    }

    class Node implements Comparable<Node>{
        Character charSymbol;
        Node leftChild;
        Node rightChild;
        int charFrequency;

        public Node(Character pChar, int charFrequency, Node leftChild, Node rightChild){
            this.charSymbol = pChar;
            this.charFrequency = charFrequency;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public boolean isLeaf(){
            return leftChild == null && rightChild == null;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.charFrequency, o.charFrequency);
        }
    }
}
