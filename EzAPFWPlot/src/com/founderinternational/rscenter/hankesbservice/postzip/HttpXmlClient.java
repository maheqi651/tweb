/*
 *	Title：HttpXmlClient.java<br>
 *  Date: 2015-9-26 下午3:12:08<br>
 * 	Copyright (c) 2015 Founder International Co.,Ltd.<br>
 *
 * 	WebSite: http://www.founder.com<br>
 * 
 */
package com.founderinternational.rscenter.hankesbservice.postzip;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;   
  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus; 
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient; 
import org.apache.http.client.methods.HttpPost;   
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;  
import org.apache.log4j.Logger;  

  
public class HttpXmlClient {  
    private static Logger log = Logger.getLogger(HttpXmlClient.class);  
      
	public static String post(String url, String params) throws UnsupportedEncodingException
	{
		HttpClient httpclient = new DefaultHttpClient();
		String body = null;
		 log.info("create httppost:" + url); 
		HttpPost httpost = new HttpPost(url);
		StringEntity str = new StringEntity(params, "UTF-8");
		str.setContentType("application/json");
		httpost.setEntity(str);
		HttpResponse res = null;
		try
		{
			res = httpclient.execute(httpost);
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		{
			HttpEntity entity = res.getEntity();
			String charset = EntityUtils.getContentCharSet(entity);
			try
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
				StringBuilder xmlBuf = new StringBuilder();
				String line = null;
				while ((line = in.readLine()) != null)
				{
					xmlBuf.append(line);
				}
				 body = xmlBuf.toString();
			}
			catch (IllegalStateException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		httpclient.getConnectionManager().shutdown();

		return body;
	}
	 public static void SubmitPost(String url,String filepath){  
         
	        HttpClient httpclient = new DefaultHttpClient();  
	          
	        try {  
	      
	            HttpPost httppost = new HttpPost(url);  
	              
	            FileBody bin = new FileBody(new File(filepath));  
	  
	            MultipartEntity reqEntity = new MultipartEntity();  
	              
	            reqEntity.addPart("file1", bin);//file1为请求后台的File upload;属性      
//	             reqEntity.addPart("file2", bin2);//file2为请求后台的File upload;属性  
//	             reqEntity.addPart("filename1", comment);//filename1为请求后台的普通参数;属性     
	            httppost.setEntity(reqEntity);  
	              
	            HttpResponse response = httpclient.execute(httppost);  
	              
	                  
	            int statusCode = response.getStatusLine().getStatusCode();  
	              
	                  
	            if(statusCode == HttpStatus.SC_OK){  
	                      
	                System.out.println("服务器正常响应.....");  
	                  
	                HttpEntity resEntity = response.getEntity();  
	                  
	                  
	                System.out.println(EntityUtils.toString(resEntity));//httpclient自带的工具类读取返回数据  
	                  
	                  
	                  
	                System.out.println(resEntity.getContent());     
	  
	                EntityUtils.consume(resEntity);  
	            }  
	                  
	            } catch (ParseException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            } finally {  
	                try {   
	                    httpclient.getConnectionManager().shutdown();   
	                } catch (Exception ignore) {  
	                      
	                }  
	            }  
	        }  
   
}  

