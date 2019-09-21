package com.anupam.algorithms.compression;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class BinaryOutputStream implements Closeable {
    OutputStream bufferedOutputStream;
    // Buffer to hold upto 8 bits or 1 byte before writing to output stream
    private int buffer = 0;
    private int remainingBits = 0;
    private byte[] byteBuffer = new byte[16];

    public BinaryOutputStream(OutputStream outputStream){
        bufferedOutputStream = new BufferedOutputStream(outputStream);
    }

    public void write(boolean inputBit) throws IOException{
        // add the new bit to buffer by doing bit left shift by 1
        buffer = buffer << 1;
        // if the input bit is 1, set the newly added bit to 1 by doing a bitwise or with 1
        if(inputBit)
            buffer = buffer | 1;
        remainingBits++;
        if(remainingBits == 8)
            clearBuffer();
    }

    public void write(byte inputByte) throws IOException{
        bufferedOutputStream.write(inputByte);
    }

    public void write(int inputInt) throws IOException{
        /*for(int i = 1; i < 5; i++){
            int x = i;
            int eightbitsfromright = (inputInt >>> (32 - x*8)) & 0xff;
            write((byte)eightbitsfromright);
        }*/
        int firsteightbitsfromright = (inputInt >>> 24) & 0xff;
        int secondeightbitsfromright = (inputInt >>> 16) & 0xff;
        int thirdeightbitsfromright = (inputInt >>> 8) & 0xff;
        int lasteightbitsfromright = inputInt & 0xff;
        write((byte)firsteightbitsfromright);
        write((byte)secondeightbitsfromright);
        write((byte)thirdeightbitsfromright);
        write((byte)lasteightbitsfromright);
    }

    private void clearBuffer() throws IOException {
        if(remainingBits == 0)
            return;
        buffer <<= 8 - remainingBits;
        bufferedOutputStream.write(buffer);
        remainingBits = 0;
        buffer = 0;
    }

    @Override
    public void close() throws IOException{
        this.bufferedOutputStream.close();
    }
}
