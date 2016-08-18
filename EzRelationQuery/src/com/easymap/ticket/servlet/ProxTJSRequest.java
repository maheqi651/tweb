  package com.easymap.ticket.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.easymap.ticket.db.ConnectionDB;
import com.easymap.ticket.db.ReadProperties;
import com.easymap.ticket.model.PageHelper;
import com.easymap.ticket.tools.Tools;
import com.easymap.ticket.tools.testUrlCon;

public class ProxTJSRequest extends HttpServlet{
	private final int pagesize=20;
	private static final String JSBH="JSBH";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			process(req, resp);
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			process(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		 request.setCharacterEncoding("UTF-8");
		  String jsonstr=IOUtils.toString(request.getInputStream());
		  System.out.println(jsonstr);
		  JSONObject jomain=  JSONObject.fromObject(jsonstr);
		  Map<String,JSONArray> map=new HashMap<String,JSONArray>();
		  String result=testUrlCon.getInstance().getPostUrl("StreamQASServlet", jsonstr);
    		JSONObject alist=JSONObject.fromObject(result).getJSONObject("result");
    		System.out.println("?????++++++---"+result);
    		if(alist.size()>0){
    	    Iterator<String> keys=alist.keys();
    	    while(keys.hasNext())
    	    {
    	    	String key =keys.next();
    	    	JSONObject temp=(JSONObject)alist.get(key);
    	    	Iterator<String> keystemp=temp.keys();
    	    	while(keystemp.hasNext()){
    	    	 	String tempkey= keystemp.next();
    	    	    JSONObject temp01=(JSONObject)temp.get(tempkey);//
    	    	    if(temp01.get(JSBH)!=null&&!"".equals(temp01.get(JSBH))&&!"无".equals(temp01.get(JSBH)))
    	    	    {   
    	    	    	String tempname= temp01.getString("CSMC")+temp01.getString("JSH")+"室";
    	    	    	JSONArray jarr= map.get(tempname);
    	    	    	if(jarr==null/* jarr为空时间，处理 */)
    	    	    	{
    	    	    		jarr=new JSONArray();
    	    	    		jarr.add(temp01);
    	    	    		map.put(tempname, jarr);
    	    	    	}else 
    	    	    	{
    	    	    		if(!panduan(temp01.getString("ID2"),jarr))
    	    	    		{
    	    	    			 jarr.add(temp01);
    	    	    		}
    	    	    	}
    	    	    }
    	    	}
    	      }
    		}
    	 
            		
    	 
    		
        	//遍历map
        	Iterator iter = map.entrySet().iterator(); 
        	while (iter.hasNext()) {
        	    Map.Entry entry = (Map.Entry) iter.next();
        	    sortJSONArray((JSONArray)entry.getValue());
        	} 
    	 
    	 
    	
     
		
		JSONObject res = new JSONObject();
		res.put("totalcount", map.size());
		
		JSONArray results =JSONArray.fromObject(map);
	     System.out.println("-----111---size-----"+results.toString()); 
	 	res.put("result", results.toString());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(res.toString());
	}
	
	public JSONArray sortJSONArray(JSONArray jsonArr){  
        JSONObject jObject = null;  
     for(int i = 0;i<jsonArr.size();i++){  
         long l = Long.parseLong(jsonArr.getJSONObject(i).get("TIME").toString().substring(0, 11));  
         System.out.println(l);
         for(int j = i+1; j<jsonArr.size();j++){  
                 long nl = Long.parseLong(jsonArr.getJSONObject(j).get("TIME").toString().substring(0, 11));  
                 if(l>nl){  
                     jObject = jsonArr.getJSONObject(j);  
                     jsonArr.set(j, jsonArr.getJSONObject(i));  
                     jsonArr.set(i, jObject);  
                 }  
         }  
     }
     for(int ii = 0;ii<jsonArr.size();ii++){
    	 
    	 System.out.println(Long.parseLong(jsonArr.getJSONObject(ii).get("TIME").toString().substring(0, 11)));
    	 
     }
     return jsonArr;
}  
	
	public boolean panduan(String str,JSONArray jsonArray){
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);
            String temp=jo.getString("ID2");
            if(temp.equals(str))
            {
            	return true;
            }
            }
		return false;
	}
}
