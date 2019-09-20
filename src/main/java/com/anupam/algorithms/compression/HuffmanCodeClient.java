package com.anupam.algorithms.compression;

import java.io.*;

public class HuffmanCodeClient {
    private static String inputFileName = "tinyG.txt";
    private static String inputAsBinaryFileName = "";
    private static String compressedOutFileName = "";
    private static String uncompressedOutFileName = "";

    public static void main(String[] args) throws Exception{
        String inputFileExt = inputFileName.substring(inputFileName.indexOf("."));
        String inputFileNameNoExt = inputFileName.substring(0, inputFileName.indexOf("."));
        inputAsBinaryFileName = inputFileNameNoExt + "_binary.txt";
        compressedOutFileName = inputFileNameNoExt + "_compressed.dat";
        uncompressedOutFileName = inputFileNameNoExt + "_uncompressed" + inputFileExt;
        runHuffmanCompression();
    }

    private static void runHuffmanCompression() throws Exception {
        InputStream inputFileStream = HuffmanCodeClient.class.getClassLoader().getResourceAsStream(inputFileName);
        File compressedOutFile = checkAndCreateFile(compressedOutFileName);
        BinaryOutputStream binaryOutputStream = new BinaryOutputStream(new FileOutputStream(compressedOutFile));
        File uncompressedOutFile = checkAndCreateFile(uncompressedOutFileName);
        try(HuffmanCodeBinary huffmanCode = new HuffmanCodeBinary(inputFileStream, binaryOutputStream)){
            huffmanCode.compress();
        }
        PrintStream expandedOutStream = null;
        try {
            expandedOutStream = new PrintStream(uncompressedOutFile);
            try (HuffmanCodeBinary huffmanCode = new HuffmanCodeBinary(new FileInputStream(compressedOutFile), null)) {
                huffmanCode.expand(expandedOutStream);
            }
        } finally {
            expandedOutStream.close();
        }
    }

    private static File checkAndCreateFile(String fileName){
        File newFile = new File(fileName);
        if(newFile.exists())
            newFile.delete();
        return newFile;
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
