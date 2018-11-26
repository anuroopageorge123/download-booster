package com.util.download;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloader {
    public static void main(String args[]) {

        /*initialising variables*/
        String sourceUrl=args[0];
        URL url=null;
        try {
            url = new URL(sourceUrl);
        }catch(MalformedURLException mue) {
            mue.printStackTrace();
        }

        String outputPath=args[1];
        BufferedWriter bw=null;
        try {
             bw = new BufferedWriter(new FileWriter(new File(outputPath)));
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }


        int maxNumberofRequests=4; /*File is downloaded in 4 chunks (4 requests made to the server)*/
        long chunkSize=1000000; /*Chunk size set to 1MB*/
        long start=0;
        long end=999999;
        long size = 0;



        for(int i=0;i<maxNumberofRequests;i++) {

            String byteRange = "bytes=" + start + "-" + end;
            GetFile.makeGetRequests(byteRange,url,bw,chunkSize);
            start=end+1;
            end=start+chunkSize-1;

        }


    }




}
