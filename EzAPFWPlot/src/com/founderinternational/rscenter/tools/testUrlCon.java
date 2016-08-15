package com.founderinternational.rscenter.tools;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;





public class testUrlCon {
   private final static  Object key=new Object();
   private static testUrlCon instance;
	
	
	
	  private testUrlCon(){}
	
	   public static testUrlCon getInstance(){
		
		synchronized (key) {
			if(instance==null)
				instance=new testUrlCon();
			return instance;
		 }
	 }
	   public int getPostUrl(String urls)  {
		  
		   
			
			 try {
				   URL url=new URL(urls);
		           HttpURLConnection con= (HttpURLConnection)url.openConnection();  
				   con.setDoOutput(true);
				  con.setDoInput(true);
				  con.setRequestMethod("POST");
				   return con.getResponseCode();  
			} catch (Exception e) {
				  return 500;
			}
			
           
              
	   }
	   
	   public static void main(String[] args) throws Exception {
		   testUrlCon.getInstance().getPostUrl("http://172.18.70.491:8080/EzAPFWPlot/1");
		 }

}



