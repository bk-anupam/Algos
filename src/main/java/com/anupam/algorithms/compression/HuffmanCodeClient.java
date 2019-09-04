package com.anupam.algorithms.compression;

public class HuffmanCodeClient {

    public static void main(String[] args) throws Exception{

    }

    private static void binaryDump(String[] args) throws Exception{
        int bitsPerLine = 0;
        BinaryDump binaryDump = null;
        String inputFileName = "";
        if (args.length > 0){
            bitsPerLine = Integer.parseInt(args[0]);
        }
        if(args.length > 1){
            inputFileName = args[1];
        }
        if (bitsPerLine > 0)
            binaryDump = new BinaryDump(bitsPerLine);
        else
            binaryDump = new BinaryDump();

        if(inputFileName.isEmpty())
            binaryDump.dumpBinaryToConsole();
        else
            binaryDump.dumpBinaryToFile(inputFileName);
    }
}
