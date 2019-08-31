package com.anupam.algorithms.compression;

import java.io.*;
import java.util.function.Function;

// https://stackoverflow.com/questions/12310017/how-to-convert-a-byte-to-its-binary-string-representation

public class BinaryDump {
    private static int bitsPerLine = 8;
    private static String inputFileName = "";
    private static String binaryOutputFileName = "BinaryOutput.txt";
    private static long bitCount = 0;

    public static void main(String[] args) throws Exception{
        if (args.length > 0){
            bitsPerLine = Integer.parseInt(args[0]);
        }
        if(args.length > 1){
            inputFileName = args[1];
        }
        if(inputFileName.isEmpty())
            dumpBinaryToConsole();
        else
            dumpBinaryToFile(inputFileName);
    }

    public static void dumpBinaryToConsole() throws Exception{
        try(BufferedInputStream inputStream = new BufferedInputStream(System.in)){
            System.out.println("Enter some text input: ");
            System.out.println("The binary representation of input is: ");
            readByteWriteBit(inputStream, System.out, BinaryDump::convertByteToBinaryString);
        }
    }

    public static void dumpBinaryToFile(String fileName) throws Exception{
        InputStream in = BinaryDump.class.getClassLoader().getResourceAsStream(fileName);
        File binaryOutputFile = new File(binaryOutputFileName);
        if(binaryOutputFile.exists())
            binaryOutputFile.delete();
        try(PrintStream outputStream = new PrintStream(new FileOutputStream(binaryOutputFile));
            BufferedInputStream inputStream = new BufferedInputStream(in)){
            readByteWriteBit(inputStream, outputStream, BinaryDump::getByteAsBinaryString);
        }
    }

    private static void readByteWriteBit(InputStream inputStream, PrintStream outputStream,
                                         Function<Byte, String> conversionFunc) throws Exception{
        int byteRead;
        int counter = 1;
        while((byteRead = inputStream.read()) != -1){
            String byteAsString = conversionFunc.apply((byte)byteRead);
            outputStream.println(byteAsString);
            if(counter % (bitsPerLine/8) == 0)
                outputStream.println("\n");
            counter++;
            bitCount += 8;
        }
        outputStream.println(String.format("\nTotal %s bits", bitCount));
    }

    private static String convertByteToBinaryString(byte inputByte){
        StringBuilder sb = new StringBuilder();
        for(int i = 7; i >= 0 ; --i){
            sb.append(inputByte >>> i & 1);
        }
        return sb.toString();
    }

    private static String getByteAsBinaryString(byte inputByte) {
        return String.format("%8s", Integer.toBinaryString(inputByte & 0xFF)).replace(' ', '0');
    }
}
