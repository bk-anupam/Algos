package com.anupam.algorithms.compression;

import java.io.*;
import java.util.function.Function;

// https://stackoverflow.com/questions/12310017/how-to-convert-a-byte-to-its-binary-string-representation

public class BinaryDump {
    private int bitsPerLine = 8;
    private String binaryOutputFileName = "BinaryOutput.txt";
    private long bitCount = 0;

   public BinaryDump(){}

   public BinaryDump(int bitsPerLine){
       this.bitsPerLine = bitsPerLine;
   }

    public void dumpBinaryToConsole() throws Exception{
        try(BufferedInputStream inputStream = new BufferedInputStream(System.in)){
            System.out.println("Enter some text input: ");
            //System.out.println("The binary representation of input is: ");
            readByteWriteBit(inputStream, System.out, BinaryDump::convertByteToBinaryString);
        }
    }

    public void dumpBinaryToFile(String fileName) throws Exception{
        InputStream in = BinaryDump.class.getClassLoader().getResourceAsStream(fileName);
        File binaryOutputFile = new File(binaryOutputFileName);
        if(binaryOutputFile.exists())
            binaryOutputFile.delete();
        try(PrintStream outputStream = new PrintStream(new FileOutputStream(binaryOutputFile));
            BufferedInputStream inputStream = new BufferedInputStream(in)){
            readByteWriteBit(inputStream, outputStream, BinaryDump::getByteAsBinaryString);
        }
    }

    private void readByteWriteBit(InputStream inputStream, PrintStream outputStream,
                                         Function<Byte, String> conversionFunc) throws Exception{
        int byteRead;
        int counter = 1;
        while((byteRead = inputStream.read()) != -1){
            String byteAsString = conversionFunc.apply((byte)byteRead);
            if(counter % (bitsPerLine/8) == 0) {
                outputStream.print(byteAsString);
                outputStream.print("\n");
            }else{
                outputStream.print(byteAsString + " ");
            }
            counter++;
            bitCount += 8;
        }
        outputStream.println(String.format("Total %s bits", bitCount));
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
