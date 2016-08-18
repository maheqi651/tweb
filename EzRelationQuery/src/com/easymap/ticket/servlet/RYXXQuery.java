package com.easymap.ticket.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.easymap.management.newapi.LogManager;
import com.easymap.ticket.db.ReadProperties;
import com.easymap.ticket.model.User;
import com.easymap.ticket.tools.Constants;
import com.easymap.ticket.tools.testUrlCon;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;
public class RYXXQuery extends HttpServlet{
	
	
	
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}
	
	public  void insertLOG(HttpServletRequest request)
	{
		String ezManagerLocation;
		LogManager logManager;
		ezManagerLocation= Constants.EzManagerUrl;
		logManager = new LogManager(ezManagerLocation);
		Enumeration<String> plist=request.getParameterNames();
		String str=request.getParameter("idcard");
	   
		 
		//String address;
		try {
			String orgId = "nologin";
			/*address = InetAddress.getLocalHost().getHostAddress();*/
			String orgcode="";
			if(request.getSession().getAttribute("user")!=null)
			{
				User user= (User)request.getSession().getAttribute("user");
				orgId=user.getUsername();
			 	orgcode=user.getOrgCode();
			}
			String  requeststr=request.getRequestURI();
			requeststr=requeststr.substring(requeststr.lastIndexOf("/"));
			String  ip=request.getRemoteAddr();
			try {
					logManager.setLog("440100120005", orgId, orgId, orgcode, new Date(System.currentTimeMillis()), "可视化情报分析","可视化情报分析人员信息查询接口",  ip, "1", "查询身份证号为"+str+"的人员基本信息");
				} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		  
		 request.setCharacterEncoding("UTF-8");
		 insertLOG(request);
		 Object parm=request.getParameter("idcard");
		 String idcard=null;
		 String result="";
		 long times =System.currentTimeMillis();
		 String xmlstr="";
		 if(parm!=null)
		 {
		    idcard=(String)parm;
		    	    
		    xmlstr=dealXml(idcard);
		        
		    if(idcard!=null&&!"".equals(idcard))
		    {
		    	try {
		    		result=testUrlCon.getInstance().getPostUrl(ReadProperties.get("ryxxurl")+"G_Query_RYXX", xmlstr);
		    	//System.out.println(result);
		    	} catch (Exception e) {
					e.printStackTrace();
				}
			}
		 }
		 JSONObject res=new JSONObject();
		  try {
			resultxml(result,res);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 System.out.println(System.currentTimeMillis()-times);
		 response.setCharacterEncoding("UTF-8");
		 System.out.println(res.toString());
		 response.getWriter().print(res.toString());
	}
	public void resultxml(String xmlstr,JSONObject res) throws  Exception{
		System.out.println(xmlstr);
		if(!"".equals(xmlstr))
		{
			Document doc = DocumentHelper.parseText(xmlstr);
			System.out.println(xmlstr);
			Element root=doc.getRootElement();
			List<Element> items = root.element("Method").element("Items").elements("Item");
			System.out.println("==="+items.size());
			if(items.size()>=2)
			{
				Element item=items.get(1);
				if(item!=null)
				{
					//List<Element>  listdata
					Element Records=item.element("Value").element("Records");
					if(Records==null)
						return ;
					Element Record=Records.element("Record");
					if(Record==null)
						return ;
					List<Element>  listdata=Record.elements("Data");
					if(listdata!=null)
					{
						res.put("xm", listdata.get(0).getTextTrim());
						res.put("zjhm", listdata.get(2).getTextTrim());
						res.put("xb", listdata.get(1).getTextTrim());
						String jzd="无";
						 
						if(!"null".equals(listdata.get(4).getTextTrim()))
						{
							jzd=listdata.get(4).getTextTrim();
						}
						res.put("csdz", jzd);
						String dhhm="无";
						Element datas=listdata.get(5);
					    if(datas.elements("Data")==null)
					    {
					    	dhhm=datas.getTextTrim();
					    }else{
					    	List<Element> datali=datas.elements("Data");
					    	int count=0;
					        dhhm="";
					    	for(Element da:datali)
					    	{
					    		if(dhhm.contains(da.getTextTrim()))
					    		{}else{
					    			dhhm+=da.getTextTrim()+", ";
						    		count++;
					    		}
					    		
					    		if(count>2)
					    			break;
					    	}
					    	System.out.println(dhhm);
					    	if(dhhm.contains(","))
					    	dhhm=dhhm.substring(0, dhhm.lastIndexOf(","));
					    	else
					    		 dhhm="无";
					    }
					    res.put("dhhm", dhhm);
					}
				}
			}
		}
	}
	
	public String dealXml(String idcard){
		String xmlstr="";//
		xmlstr+="<Request><SenderID>"+ReadProperties.get("appcode")+"</SenderID>";
		xmlstr+="<Method><Name>G_Query_RYXX</Name>";
		xmlstr+="<Security Algorithm='' />";
				xmlstr+="</Method><Items><Item><Name>RequiredItems</Name>";
				xmlstr+="<Value Type='arrayof_string'>";
				xmlstr+="<Data>XM</Data><Data Trans='true'>XBDM</Data>";
				xmlstr+=" <Data>ZJHM</Data><Data>CSDGJHDQDM</Data>";
				xmlstr+="<Data Trans='true'>JGSSXDM</Data><Data>LXDH</Data>";
				xmlstr+="</Value> </Item>";
				xmlstr+="<Item><Name>Condition</Name><Value Type='string'>";
				xmlstr+="<Data>ZJHM='"+idcard+"'</Data>";
						xmlstr+="</Value> </Item>";
						xmlstr+="<Item><Name>StartPosition</Name>";
								xmlstr+="<Value Type='long'> <Data>0</Data> </Value> </Item>";
				xmlstr+="<Item> <Name>MaxResultCount</Name>";
						xmlstr+="<Value Type='long'> <Data>1</Data> </Value>";
								xmlstr+="</Item>";
								xmlstr+="</Items> </Request>";
								System.out.println(xmlstr);
		return xmlstr;
	}
}
