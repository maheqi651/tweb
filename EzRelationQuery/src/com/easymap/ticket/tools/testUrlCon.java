package com.easymap.ticket.tools;

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

import com.easymap.ticket.db.ReadProperties;




public class testUrlCon {
   private static  Object key=new Object();
   private static testUrlCon instance;
	public static void main(String[] args) throws Exception 
	{
		//testUrlCon.getInstance().getPostUrl("StreamDelServlet", "{\"hbaseInstance\":\"HBase01\",\"hbaseTableName\":\"SJZ_DB_RELATION\",\"delType\":\"rows\",\"params\":{\"fuzzyRow\":\"??????????????????#???#??????????????#07\"}}");
//		URL url=new URL("http://10.25.2.244:7001/EzDBaaSManager/StreamDelServlet");
//          HttpURLConnection con= (HttpURLConnection)url.openConnection();  
//			con.setDoOutput(true);
//			 con.setDoInput(true);
//			 con.setRequestMethod("POST");
//          OutputStream buf=new BufferedOutputStream(con.getOutputStream());
//          OutputStreamWriter out=new OutputStreamWriter(buf,"UTF-8");
//           out.write("{'hbaseInstance':'HBase01','hbaseTableName':'SJZ_DB_RELATION','delType':'rows','params':{'fuzzyRow':'??????????????????#???#??????????????#07'}");
//         // out.write(datastr);
//          out.flush();
//          out.close();
//          InputStream in=con.getInputStream();
//          
//          //in.close();
//          BufferedReader br=new BufferedReader(new InputStreamReader(in,"UTF-8"));
//          String s=null;
//          StringBuffer result=new StringBuffer();
//          while((s=br.readLine())!=null){
//         	 result.append(s);
//          }
//          in.close();
//          System.out.println(result.toString());
		  // System.out.println(result.toString());
		   System.out.println("ци".length());
            // {'hbaseInstance':'HBase01','hbaseTable':'SJZ_DB_RELATION','qasType':'KvQuery','params':{'rowkey':'130121199109142#13011572525#20111230193317#07','isBatch':'true','isFuzzy':'false','pageSize':'3','returnType':'rowkey','maxVersions':'100'}}
	}
	
	  private testUrlCon(){}
	
	   public static testUrlCon getInstance(){
		
		synchronized (key) {
			if(instance==null)
				instance=new testUrlCon();
			return instance;
		 }
	 }
	   public String getPostUrl(String urlstr,String datastr) throws Exception{
		    URL url=new URL(urlstr);
            HttpURLConnection con= (HttpURLConnection)url.openConnection();  
			 con.setDoOutput(true);
			 con.setDoInput(true);
			 con.setRequestMethod("POST");
           OutputStream buf=new BufferedOutputStream(con.getOutputStream());
           OutputStreamWriter out=new OutputStreamWriter(buf,"UTF-8");
           //out.write("{'hbaseInstance':'HBase01','hbaseTable':'SJZ_DB_RELATION','qasType':'KvQuery','params':{'rowkey':'130121199109142#13011572525#20111230193317#07','isBatch':'true','isFuzzy':'false','pageSize':'10','returnType':'rowkey','maxVersions':'100'}}");
           System.out.println("datastr:"+datastr);
           out.write(datastr);
           out.flush();
           out.close(); 
           InputStream in=con.getInputStream();
           //in.close();
           BufferedReader br=new BufferedReader(new InputStreamReader(in,"UTF-8"));
           String s=null;
           StringBuffer result=new StringBuffer();
           while((s=br.readLine())!=null){
          	 result.append(s);
           }
           in.close();
          // System.out.println(result.toString());
		   return result.toString();
	   }

}



