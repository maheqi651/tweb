package com.founderinternational.rscenter.hankesbservice;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.founderinternational.rscenter.hankesbservice.postzip.HttpXmlClient;
import com.founderinternational.rscenter.tools.Constants;

public class HttpPostFile {
	 private  static String ESBURL="http://10.235.36.3/monitor/FileUploadService?app=VG_PORTAL_02,ESB_4_2_20_36_114"; 
	 public static void postZIP1(String filePath,String fileName) {
		    ESBURL=Constants.ESBURL;
		    System.out.println(ESBURL);
	        try {
	            URL url=new URL(ESBURL+fileName);
	            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
	            connection.setDoInput(true);
	            connection.setDoOutput(true);
	            connection.setRequestMethod("POST");
	            connection.addRequestProperty("FileName", fileName);
	            connection.setRequestProperty("content-type", "application/x-zip-compressed");
	            BufferedOutputStream  out=new BufferedOutputStream(connection.getOutputStream());
	            //读取文件上传到服务器
	            File file=new File(filePath);
	            FileInputStream fileInputStream=new FileInputStream(file);
	            byte[]bytes=new byte[1024*10];
                int numReadByte=0;
	            while((numReadByte=fileInputStream.read(bytes,0,1024*10))>0)
	            {
	                out.write(bytes, 0, numReadByte);
	            }
	            out.flush();
	            fileInputStream.close();
	            //读取URLConnection的响应
	            InputStream in=connection.getInputStream();
	           // DataInputStream in=new DataInputStream(connection.getInputStream());
	            BufferedReader br=new BufferedReader(new InputStreamReader(in,"UTF-8"));
	            String s=null;
	            StringBuffer result=new StringBuffer();
	            while((s=br.readLine())!=null){
	           	 result.append(s);
	            }
	            in.close();
	            System.out.println(result);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    }
	 
	 
	 public static void postZIP(String filePath,String fileName) {
		    ESBURL=Constants.ESBURL;
		    HttpXmlClient.SubmitPost(ESBURL,filePath);
	    }
}
