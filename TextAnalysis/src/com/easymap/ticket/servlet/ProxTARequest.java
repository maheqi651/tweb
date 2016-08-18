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

public class ProxTARequest extends HttpServlet{
	private final int pagesize=20;
	private static final String AJBH=""; 
	private static final String AJBH_MC=""; 
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
		  System.out.println(jsonstr);
        // jsonstr=jsonstr.replace("&", "?");
		 //System.out.println(jsonstr);
		 
		 JSONObject cph=new JSONObject();
		 cph.put("hbaseInstance","HBase01" );
		 cph.put("hbaseTable", jomain.getString("hbaseTable"));//"JZ_SHXX_JTWF3"
		 cph.put("qasType", "index.KvQueryBySecondaryIndex");
		 JSONObject parm=new JSONObject();
		 parm.put("indexName", jomain.getString("indexName"));//"INDEX_JTWF_HPHM"
		 cph.put("params", parm);
		 Map<String,JSONArray> map=new HashMap<String,JSONArray>();
		 String result=testUrlCon.getInstance().getPostUrl("StreamQASServlet", jsonstr);
    		JSONObject alist=JSONObject.fromObject(result).getJSONObject("result");
    		System.out.println("?????---"+result);
    		if(alist.size()>0){
    	    Iterator<String> keys=alist.keys();
    	   // strss[i]=basicstr+cishu+"#"+condition[4];
    	    while(keys.hasNext())
    	    {
    	    	String key =keys.next();
    	    	JSONObject temp=(JSONObject)alist.get(key);
    	    	 //System.out.println(temp.toString());
    	    	Iterator<String> keystemp=temp.keys();
    	    	while(keystemp.hasNext()){
    	    	 	String tempkey= keystemp.next();
    	    	    JSONObject temp01=(JSONObject)temp.get(tempkey);
    	    	    //案件编号
    	    	    if(temp01.get(AJBH)!=null&&!"".equals(temp01.get(AJBH)/*预判断案件号存在不*/))
    	    	    {   
    	    	    	if(temp01.getString(AJBH).length()>=1){
    	    	    	String temphphm=(String)temp01.get(AJBH);
                       // System.out.println("kkkkkll"+temphphm);
    	    	    	temphphm=temphphm.trim();//根据案件编号查询
    	    	    	parm.put("rowkey",temphphm);
    	    	    	cph.put("params", parm);
    	    	    	String resultposon=testUrlCon.getInstance().getPostUrl("StreamQASServlet", /*根据案件编号查询*/ cph.toString());
    	    	    	JSONArray alisttemp=JSONObject.fromObject(resultposon).getJSONArray("result");
    	    	    	if(alisttemp.size()>0){
    	    	    		     for(int i=0;i<alisttemp.size();i++){
    	    	    			 
    	    	    			  JSONObject temp02=alisttemp.getJSONObject(i);//HPZL_MC
    	    	    			 
    	    	    			  if(map.get("案件编号："+temp02.get(AJBH)+"<br>案件名称："+temp02.get(AJBH_MC))==null)
    	             	    	    {   
    	             	    	    	 
    	             	    	    	JSONArray jsonarr1=new JSONArray();
             	    	    	    	jsonarr1.add(temp02);
    	             	    	    	map.put((String)("案件编号："+temp02.get(AJBH)+"<br>案件名称："+temp02.get(AJBH_MC)), jsonarr1);
    	             	    	    }else{
    	             	    	    	JSONArray maptemp01=map.get((String)("案件编号："+temp02.get(AJBH)+"<br>案件名称："+temp02.get(AJBH_MC)));
    	             	    	    	    
    	             	    	    	    if(/*判断存在不*/!panduan((String)temp02.get(AJBH),maptemp01)){//不存在
    	             	    	    	    	maptemp01.add(temp02);
    	             	    	    	    }else
    	             	    	    	    {   //存在就不添加
    	             	    	    	    	//not do 
    	             	    	    	    }
    	             	    	    	    	//}
    	             	    	    	    }
    	             	    	    }
    	    	      	    }
    	    	    	}
    	    	    }//
    	    	}
    	    	}
    	    }
    		JSONObject res = new JSONObject();
    		res.put("totalcount", map.size());
    		
    		JSONArray results =JSONArray.fromObject(map);
    	   // System.out.println("-----111---size-----"+results.toString()); 
    	 	res.put("result", results.toString());
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().print(res.toString());
 }
    	 
    	 
		
	
	
	
	
	public boolean panduan(String str,JSONArray jsonArray){
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);
            String temp=jo.getString(AJBH);
            if(temp.equals(str)){
            	//System.out.println(temp+"--------false-----"+str);
            	return true;}
            }
		return false;
	}
}
