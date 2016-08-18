  package com.easymap.ticket.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;



import com.easymap.ticket.tools.Tools;
import com.easymap.ticket.tools.testUrlCon;

public class CopyOfProxWBRequesttemp extends HttpServlet{
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
	public String dostr6(String str){
		
		
		return str;
	}
	 
	private void process(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		 request.setCharacterEncoding("UTF-8");
		  String jsonstr=IOUtils.toString(request.getInputStream());
		// System.out.println(jsonstr);
		// jsonstr=jsonstr.replace("&", "?");
		 JSONObject jomain=  JSONObject.fromObject(jsonstr);
		 
		 int counts=1;
		 if(jomain.getString("count")!=null&&!"".equals(jomain.getString("count"))){
		    counts=Integer.parseInt(jomain.getString("count"));
		 }
		 String fuzzyRow =((JSONObject)jomain.get("params")).getString("fuzzyRow");
		 String[] condition=fuzzyRow.split("#");
		 String basicstr=condition[0]+"#"+condition[1]+"#"+condition[2]+"#";
	     Map<String,JSONObject> map=new TreeMap<String,JSONObject>();
	   //  System.out.println("---------------"+counts);
	    // String[] strss=new String[6];
	     JSONObject param=jomain.getJSONObject("params");
		 if(counts>0)
		 {//∂‡…Ÿ¥Œ
           	for(int i=0;i<=counts;i++){
           		String cishu=Tools.dostr6("000000"+i);
           		param.put("fuzzyRow",basicstr+cishu+"#"+condition[4]);
           		//System.out.println(cishu+"---------------"+jomain.toString());
           		String result=testUrlCon.getInstance().getPostUrl("StreamQASServlet", jomain.toString());
           		JSONObject alist=JSONObject.fromObject(result).getJSONObject("result");
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
           	    	    if(map.get(temp01.get("ID2"))==null)
           	    	    {   
           	    	    	temp01.put("count", 1);
           	    	    	map.put((String)temp01.get("ID2"), temp01);
           	    	    	
           	    	    }else{
           	    	    		int counttemp = map.get(temp01.get("ID2")).getInt("count");
           	    	    		String desp=(String) map.get(temp01.get("ID2")).get("DESP");
           	    	    		desp=desp+"<br>"+temp01.get("DESP");
           	    	    		counttemp++;
           	    	    		map.get(temp01.get("ID2")).put("DESP",desp);
           	    	    		map.get(temp01.get("ID2")).put("count",counttemp);
           	    	    }
           	    	}
           	    }
           		}
           	}		 
		 }
		 ArrayList<Map.Entry<String,JSONObject>> list= Tools.sortMap(map);
		 List<String> listjson=new ArrayList<String>();
		 BigInteger   starttime=new BigInteger("0");
		 //long endtime=99999999999999;
		 BigInteger   endtime=new BigInteger("99999999999999");
		 System.out.println(jomain.get("starttime"));
		 if(jomain.get("starttime")!=null)
		 {
			  String st=(String)jomain.get("starttime");
			  if(!st.trim().equals(""))
				 {
				  starttime=new BigInteger(st.replace("-", "")+"000000"); 
				 }
		 }
		 if(jomain.get("endtime")!=null)
		 {
			 String ed=(String)jomain.get("endtime");
			 if(!ed.trim().equals(""))
			 {   
				 endtime=new BigInteger(ed.replace("-", "")+"595959"); 
			 }
		 }
		 for(Map.Entry<String,JSONObject> s:list)
		 {
			// System.out.print("-----111---------"+s.getValue().toString()); 
			 BigInteger  b=new BigInteger((String)s.getValue().get("TIME")); 
			 if(b.compareTo(starttime)>=0&&b.compareTo(endtime)<=0)
			 { 
				 listjson.add(s.getValue().toString());
			 }
		 }
		 
		 
		 
		// System.out.println("-----111---size-----"+list.size()); 
		JSONObject res = new JSONObject();
 		 res.put("totalcount", listjson.size());
 		JSONArray results =JSONArray.fromObject(listjson);
 		//System.out.println(results.toString());
	 	res.put("result", results.toString());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(res.toString());
	}
	
	
	
}
