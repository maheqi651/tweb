package com.easymap.ticket.listener;

import java.io.InputStream;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.easymap.ticket.db.ReadProperties;
import com.easymap.ticket.tools.Constants;
import com.easymap.ticket.tools.RSAUtil;
import com.easymap.ticket.tools.testUrlCon;

import net.sf.json.JSONObject;

public class Initialization implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String privatepath =sce.getServletContext().getRealPath("privatekey");
		System.out.println(privatepath);
		Constants.PRIVATEKEYSTR=RSAUtil.getKeystoreString(privatepath+"\\privateKey.keystore");
		System.out.println("..........加载私钥........");
		String confpath = sce.getServletContext().getInitParameter("config");
		InputStream confis = sce.getServletContext().getResourceAsStream(confpath);
		try {
			ReadProperties.bundle = new PropertyResourceBundle(confis);
			Constants.EzManagerUrl=ReadProperties.get("EZMANGER");
			Constants.AHTUURL=ReadProperties.get("AHTUURL");
			Constants.URLSTR=ReadProperties.get("URLSTR");
		} catch (Exception e) {
			e.printStackTrace();
		}
	/*	long times=System.currentTimeMillis();
		System.out.println("加载网吧字典开始......");
		dealWBMap();
		
		System.out.println("加载网吧字典结束......，共加载数据"+Constants.wbmap.size()+"条，耗时："+(System.currentTimeMillis()-times)+"ms");
		times=System.currentTimeMillis();
		System.out.println("加载酒店字典开始......");
		dealJDMap();
		System.out.println("加载酒店字典结束......，共加载数据"+Constants.jdmap.size()+"条，耗时："+(System.currentTimeMillis()-times)+"ms");
	*/}
	 public void dealJDMap(){
		    String xmlstr=dealXml("jdtable","HNOHOTEL","HNAME");
		    try {
				String result=testUrlCon.getInstance().getPostUrl(ReadProperties.get("ryxxurl")+"QueryDataServlet", xmlstr);
				resultxml(result,1);
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   /* Set<String> keys=Constants.jdmap.keySet();
	    	  for(String key:keys)
	    	   {
	    		  System.out.println("key:"+key+"    value:"+Constants.jdmap.get(key)); 
	    	   } */
		   
	 } 
     public void dealWBMap(){
    	   String xmlstr=dealXml("wbtable","PUBCODE","PUBNAME");
    	   try {
				String result=testUrlCon.getInstance().getPostUrl(ReadProperties.get("ryxxurl")+"QueryDataServlet", xmlstr);
				resultxml(result,0);
		    } catch (Exception e) {
				e.printStackTrace();
			}
    	   
	 }
     
     public void resultxml(String xmlstr,int type) throws  Exception{
 		if(!"".equals(xmlstr))
 		{
 			Document doc = DocumentHelper.parseText(xmlstr);
 			//System.out.println(xmlstr);
 			Element root=doc.getRootElement();
 			List<Element> items = root.element("Method").element("Items").elements("Item");
 			//System.out.println("==="+items.size());
 			if(items!=null)
 			{
 				for(Element ele:items)
 	 			 {
 	 				  if("Result".equals(ele.element("Name").getTextTrim()))
 	 				  {
 	 					List<Element> Records=ele.element("Value").element("Records").elements("Record");
 	 					if(Records!=null)
 	 					{
 	 						for(Element Record:Records)
 	 						{
 	 							List<Element> datas= Record.elements("Data");
 	 							if(datas!=null)
 	 							{
 	 								if(datas.size()>=2)
 	 								{
 	 									if(type==0)
 	 									{//wb
 	 										Constants.wbmap.put(datas.get(0).getTextTrim(), datas.get(1).getTextTrim());
 	 									}else if(type==1){//jd
 	 										Constants.jdmap.put(datas.get(0).getTextTrim(), datas.get(1).getTextTrim());
 	 									}
 	 									
 	 								}
 	 							}
 	 						}
 	 					}
 	 				  }
 	 			 }
 			}
 			 
 		}
 	}
     

 	public String dealXml(String tablename,String key,String value){
 		String xmlstr="";//
 		xmlstr+="<Request><SenderID>"+ReadProperties.get("appcode")+"</SenderID>";
 		xmlstr+="<Method><Name>QueryData</Name>";
 		xmlstr+="<Security Algorithm='' />";
 		xmlstr+="</Method><Items>";
 		xmlstr+="<Item>";
 		xmlstr+="<Name>DataObjectCode</Name>";
 		xmlstr+="<Value Type='string'>";
 		xmlstr+="<Data>"+ReadProperties.get(tablename)+"</Data>";
 		xmlstr+="</Value>";
 		xmlstr+="</Item>";
		xmlstr+="<Item><Name>RequiredItems</Name>";
 		xmlstr+="<Value Type='arrayof_string'>";
 		xmlstr+="<Data>"+key+"</Data>";//HNOHOTEL
 		xmlstr+="<Data>"+value+"</Data>";//HNAME

 		xmlstr+="</Value> </Item>";
 		xmlstr+="<Item><Name>Condition</Name><Value Type='string'>";
 		xmlstr+="<Data>1=1</Data>";
 		xmlstr+="</Value> </Item>";
 		xmlstr+="<Item><Name>StartPosition</Name>";
 		xmlstr+="<Value Type='long'> <Data>0</Data> </Value> </Item>";
 		xmlstr+="<Item> <Name>MaxResultCount</Name>";
 		xmlstr+="<Value Type='long'> <Data>"+ReadProperties.get("maxcount")+"</Data> </Value>";
 		xmlstr+="</Item>";
 		xmlstr+="<Item>";
 		xmlstr+="<Name>RequestResultCount</Name>";
 		xmlstr+="<Value Type='boolean'>";
 		xmlstr+="<Data>false</Data>";
 		xmlstr+="</Value>";
 		xmlstr+="</Item>";
 		xmlstr+="</Items> </Request>";
 		//System.out.println(xmlstr);
 		return xmlstr;
 	}
}
