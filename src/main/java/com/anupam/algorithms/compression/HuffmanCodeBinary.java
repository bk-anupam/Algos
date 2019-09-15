package com.anupam.algorithms.compression;

import java.io.*;
import java.util.Map;
import java.util.Stack;

public class HuffmanCodeBinary extends HuffmanCodeBase {
    private BinaryOutputStream binaryOutputStream;

    public HuffmanCodeBinary(InputStream inputStream, BinaryOutputStream binaryOutputStream){
        super(inputStream);
        this.binaryOutputStream = binaryOutputStream;
    }

    @Override
    public void readInputWriteCompressedData(InputStream inputStream, Map<Character, String> charCodeMap)
            throws IOException {
        // read the input stream character by character.
        // find the corresponding encoding of the character from the code table and write to output
        StringBuilder sb = new StringBuilder();
        traverseHuffmanTrie(sb, huffmanTrie);
        String header = sb.toString();
        logger.info("The header string: " + header);
        int headerCharCount = header.toCharArray().length;
        logger.info("Number of characters in header: " + headerCharCount);
        writeHeaderToOutput(header);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int bitCount = 0;
        int charAsInt;
        long inputCharCount = 0;
        while((charAsInt = reader.read()) != -1){
            inputCharCount++;
            String charCode = charCodeMap.get((char)charAsInt);
            for(char inputChar: charCode.toCharArray()){
                if(inputChar == '0')
                    binaryOutputStream.write(false);
                else if(inputChar == '1')
                    binaryOutputStream.write(true);
                bitCount++;
            }
        }
        logger.info("No of characters in uncompressed input file: " + inputCharCount);
        logger.info("Compressed file bit count: " + bitCount);
    }

    private void writeHeaderToOutput(String header) throws IOException{
        header = header + "0";
        for(byte inputByte: header.getBytes()){
            binaryOutputStream.write(inputByte);
        }
    }

    private Node buildHuffmanTrieFromHeader(String header){
        Stack<Node> nodeStack = new Stack<>();
        char[] headerCharArray = header.toCharArray();
        for(int i = 0; i < headerCharArray.length; i++){
            if(headerCharArray[i] == '1'){
                i = i + 1;
                nodeStack.push(new Node(headerCharArray[i], 1, null, null));
            }else if(headerCharArray[i] == '0'){
                if(nodeStack.size() > 1){
                    Node lastNode = nodeStack.pop();
                    Node secondLastNode = nodeStack.pop();
                    // Create a new internal node and push on stack
                    nodeStack.push(new Node('\0', 1, lastNode, secondLastNode));
                }
            }
        }
        logger.info("Completed rebuilding huffman trie from header");
        return nodeStack.pop();
    }

    private void traverseHuffmanTrie(StringBuilder sb, Node node){
        if(node == null)
            return;
        traverseHuffmanTrie(sb, node.leftChild);
        traverseHuffmanTrie(sb, node.rightChild);
        sb.append(visit(node));
    }

    private String visit(Node node){
        if(node.isLeaf())
            return "1" + node.charSymbol;
        // if the node is an internal node
        else
            return "0";
    }

    @Override
    public void close() throws IOException{
        this.binaryOutputStream.close();
        this.inputStream.close();
    }

}
