package com.anupam.cryptography;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
 
/**
 * A utility that downloads a file from a URL.
 * @author www.codejava.net
 *
 */
public class Downloader {

	private static final int BUFFER_SIZE = 4096;
	
	/**
     * Downloads a file from a URL
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    public static void downloadFile(String fileURL, String saveDir)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
 
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();
 
            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }
 
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);
 
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = "test.mp4";
             
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            outputStream.close();
            inputStream.close();
 
            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
    
    public static void main(String[] args) {
        String fileURL = "https://d3c33hcgiwev3.cloudfront.net/2.4.c316ae20105b11e5a2fcf756944bc468/full/360p/index.mp4?Expires=1466467200&Signature=bXjtH~51~VQPKkdtfG2Xmcl2VH8Gn9mtsZ5w7GKQn-FOrrEZwFXhZo9Gau4e9Odndh9khFcAGK8FhUdPvmbS8d4ZWyi99gK3s6aA9Cg36c~Gvdp2-lE3GPgQBzS6epJ76LoFUmqb08Z1c0IPP-8by61kMZcAScQ7Y1tnQiZ6eCI_&Key-Pair-Id=APKAJLTNE6QMUY6HBC5A";
        String saveDir = "E://JavaMaterial";
        try {
            Downloader.downloadFile(fileURL, saveDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
