package com.example.fuzzapptest.httpconnect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {
	 public static String getData(String uri){
	        StringBuilder sb = new StringBuilder();
	        BufferedReader bf = null;
	        try {
	            URL url = new URL(uri);
	            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
	            bf = new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));
	            String line;
	            while ((line = bf.readLine()) != null){
	                sb.append(line +'\n');
	            }
	            return sb.toString();
	        } catch (Exception e){
	            e.printStackTrace();
	            return null;
	        }finally {
	            if (bf == null) {
	            } else {
	                try{
	                    bf.close();
	                }catch (Exception e){
	                    e.printStackTrace();

	                }}

	        }


	    }

}
