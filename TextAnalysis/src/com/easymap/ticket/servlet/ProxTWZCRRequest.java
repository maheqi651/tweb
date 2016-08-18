  package com.easymap.ticket.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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

public class ProxTWZCRRequest extends HttpServlet{
	private final int pagesize=20;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			process(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			process(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		 request.setCharacterEncoding("UTF-8");
		  String jsonstr=IOUtils.toString(request.getInputStream());
		  JSONObject jomain=  JSONObject.fromObject(jsonstr);
		// System.out.println(jsonstr);
		// jsonstr=jsonstr.replace("&", "?");
		 System.out.println(jsonstr);
		 
		 JSONObject cph=new JSONObject();
		 cph.put("hbaseInstance","HBase01" );
		 cph.put("hbaseTable", "JZ_SHXX_JTWF");
		 cph.put("qasType", "index.KvQueryBySecondaryIndex");
		 JSONObject parm=new JSONObject();
		 parm.put("indexName", "JTWF_INDEX_HPHM");
		 cph.put("params", parm);
		 Map<String,JSONObject> map=new TreeMap<String,JSONObject>();
		 String result=testUrlCon.getInstance().getPostUrl("StreamQASServlet", jsonstr);
    		JSONObject alist=JSONObject.fromObject(result).getJSONObject("result");
    		//System.out.println("?????---"+result);
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
    	    	    if(temp01.get("HPHM")!=null||!"".equals(temp01.get("HPHM")))
    	    	    {   
    	    	    	String temphphm=(String)temp01.get("HPHM");
                       // System.out.println("kkkkkll"+temphphm);
    	    	    	parm.put("rowkey",temphphm);
    	    	    	cph.put("params", parm);
    	    	    	System.out.println("000000-----------"+cph.toString());
    	    	    	String resultposon=testUrlCon.getInstance().getPostUrl("StreamQASServlet", cph.toString());
    	    	    	System.out.println("--111---"+resultposon);
    	    	    	JSONArray alisttemp=JSONObject.fromObject(resultposon).getJSONArray("result");
    	    	    	if(alisttemp.size()>0){
    	    	    		     for(int i=0;i<alisttemp.size();i++){
    	    	    			 
    	    	    			  JSONObject temp02=alisttemp.getJSONObject(i);
    	    	    			  if(map.get(temp02.get("JSZH"))==null)
    	             	    	    {   
    	             	    	    	temp02.put("count", 1);
    	             	    	    	//String desp=(String)temp01.get("DESP");
    	             	    	    	//desp="1  "+desp;
    	             	    	        //	map.put((String)temp01.get("ID2"), temp01);
    	             	    	    	//map.get(temp01.get("ID2")).put("DESP",desp);
    	             	    	    	map.put((String)temp02.get("JSZH"), temp02);
    	             	    	    }else{
    	             	    	    		int counttemp = map.get(temp02.get("JSZH")).getInt("count");
    	             	    	    		//String desp=(String) map.get(temp01.get("ID2")).get("DESP");
    	             	    	    		counttemp++;
    	             	    	    		//desp=desp+"<br>"+counttemp+"  "+temp01.get("DESP");
    	             	    	    		
    	             	    	    		//map.get(temp01.get("JSZH")).put("DESP",desp);
    	             	    	    	   	map.get(temp02.get("JSZH")).put("count",counttemp);
    	             	    	    }
    	    	    			  
    	    	    			  
    	    	      	    }
    	    	    		
    	    	    	}
    	    	    } 
    	    	}
    	    }
    		}
    		
    		
    		
    	ArrayList<Map.Entry<String,JSONObject>> list= Tools.sortMap(map);
    	List<String> listjson=new ArrayList<String>();
		 
		 //long endtime=99999999999999;
		  
		  
		 
		 for(Map.Entry<String,JSONObject> s:list)
		 {
				 listjson.add(s.getValue().toString());
		 }
		
		JSONObject res = new JSONObject();
		res.put("totalcount", listjson.size());
		JSONArray results =JSONArray.fromObject(listjson);
		 System.out.println("-----111---size-----"+results.toString()); 
	 	res.put("result", results.toString());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(res.toString());
	}
}
