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

public class ProxTWZCRequest extends HttpServlet{
	private final int pagesize=20;
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
		 Map<String,TreeMap> map=new HashMap<String,TreeMap>();
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
    	    	    if(temp01.get("HPHM")!=null&&!"".equals(temp01.get("HPHM"))&&!"ÎÞ".equals(temp01.get("HPHM")))
    	    	    {   
    	    	    	if(temp01.getString("HPHM").length()>=7){
    	    	    	String temphphm=(String)temp01.get("HPHM");
                       // System.out.println("kkkkkll"+temphphm);
    	    	    	temphphm=temphphm.trim();
    	    	    	parm.put("rowkey",temphphm);
    	    	    	cph.put("params", parm);
    	    	    	//System.out.println("000000-----------"+cph.toString());
    	    	    	String resultposon=testUrlCon.getInstance().getPostUrl("StreamQASServlet", cph.toString());
    	    	    	//System.out.println("--111---"+resultposon);
    	    	    	JSONArray alisttemp=JSONObject.fromObject(resultposon).getJSONArray("result");
    	    	    	if(alisttemp.size()>0){
    	    	    		     for(int i=0;i<alisttemp.size();i++){
    	    	    			 
    	    	    			  JSONObject temp02=alisttemp.getJSONObject(i);//HPZL_MC
    	    	    			 
    	    	    			  if(map.get(temp02.get("HPHM")+"<br>"+temp02.get("HPZL_MC"))==null)
    	             	    	    {   
    	             	    	    	temp02.put("count", 1);
    	             	    	    	//String desp=(String)temp01.get("DESP");
    	             	    	    	//desp="1  "+desp;
    	             	    	        //	map.put((String)temp01.get("ID2"), temp01);
    	             	    	    	//map.get(temp01.get("ID2")).put("DESP",desp);
    	             	    	    	JSONArray jsonarr1=new JSONArray();
             	    	    	    	jsonarr1.add(temp02);
             	    	    	    	temp02.put("temp", jsonarr1);
    	             	    	    	TreeMap maptemp=new TreeMap();
    	             	    	    	maptemp.put((String)temp02.get("JSZH"), temp02);
    	             	    	    	map.put((String)(temp02.get("HPHM")+"<br>"+temp02.get("HPZL_MC")), maptemp);
    	             	    	    }else{
    	             	    	    	TreeMap maptemp01=map.get((String)(temp02.get("HPHM")+"<br>"+temp02.get("HPZL_MC")));
    	             	    	    	    
    	             	    	    	    if(maptemp01.get((String)temp02.get("JSZH"))==null){
    	             	    	    	    	JSONArray jsonarr=new JSONArray();
    	             	    	    	    	jsonarr.add(temp02);
    	             	    	    	    	 
    	             	    	    	    	temp02.put("count", 1);
    	             	    	    	    	temp02.put("temp", jsonarr);
    	             	    	    	    	maptemp01.put((String)temp02.get("JSZH"), temp02);
    	             	    	    	    	
    	             	    	    	    }else
    	             	    	    	    {
    	             	    	    	    	// System.out.println("------clsj11---"+temp02.get("CLSJ"));
    	             	    	    	    	
    	             	    	    	    	 String str01=((JSONObject)maptemp01.get((String)temp02.get("JSZH"))).getString("WFBH");
    	             	    	    	    	 String str02=temp02.getString("WFBH");
    	             	    	    	    	 System.out.println("str01:"+str02+"   str01:"+str01+"  boolean:"+(str01==str02));
    	             	    	    	    	 
    	             	    	    	    		//System.out.println("---------"+temp02.get("WFBH"));
    	             	    	    	    		// System.out.println("------clsj1122---"+temp02.get("CLSJ"));
    	             	    	    	    		// System.out.println("------wfsj---"+temp02.get("WFSJ"));
    	             	    	    	    	JSONObject temp03=(JSONObject)maptemp01.get((String)temp02.get("JSZH"));
    	             	    	    	    	JSONArray jsonarr01=temp03.getJSONArray("temp");
    	             	    	    	    	
    	             	    	    	    	if(panduan(temp02.getString("WFBH"),jsonarr01)){
    	             	    	    	    	jsonarr01.add(temp02);
    	             	    	    	    	temp02.put("temp", jsonarr01);
    	             	    	    	    	int counttemp=temp03.getInt("count");
    	             	    	    	        counttemp++;
    	             	    	    	        temp02.put("count", counttemp);
    	             	    	    	        maptemp01.put((String)temp02.get("JSZH"), temp02);
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
    		}
    	Map<String,List> map2=new HashMap<String,List>();
            		
    	//±éÀúmap
    	Iterator iter = map.entrySet().iterator(); 
    	while (iter.hasNext()) {
    	    Map.Entry entry = (Map.Entry) iter.next();
    	    map2.put((String)entry.getKey(), Tools.sortMap((TreeMap)entry.getValue()));
    	} 
    	
    	//ArrayList<Map.Entry<String,JSONObject>> list= Tools.sortMap(map);
    	//List<String> listjson=new ArrayList<String>();
		 
		 //long endtime=99999999999999;
		  
		  
		 
//		 for(Map.Entry<String,JSONObject> s:list)
//		 {
//				 listjson.add(s.getValue().toString());
//		 }
		
		JSONObject res = new JSONObject();
		res.put("totalcount", map2.size());
		
		JSONArray results =JSONArray.fromObject(map2);
	 // System.out.println("-----111---size-----"+results.toString()); 
	 	res.put("result", results.toString());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(res.toString());
	}
	
	
	
	public boolean panduan(String str,JSONArray jsonArray){
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);
            String temp=jo.getString("WFBH");
            if(temp.equals(str)){
            	//System.out.println(temp+"--------false-----"+str);
            	return false;}
            }
		return true;
	}
}
