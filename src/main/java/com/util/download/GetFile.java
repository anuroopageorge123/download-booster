package com.util.download;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetFile {

    public static void makeGetRequests(String byteRange, URL url, BufferedWriter bw,long chunkSize) {
        try {
            long size=0;
            System.out.println("byteRange:"+byteRange);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Range", byteRange);
            urlConnection.connect();
            System.out.println("Response Code: " + urlConnection.getResponseCode());
            System.out.println("Content-Length: " + urlConnection.getContentLength());
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            InputStream inputStream = urlConnection.getInputStream();

          while ((inputStream.read() != -1) && (size < chunkSize)) {
              String inputLine;
              while ((inputLine = in.readLine()) != null) {
                  // System.out.println(inputLine);
                  bw.write(inputLine);

              }



          }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
