package com.easymap.ticket.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.easymap.management.newapi.LogManager;
import com.easymap.ticket.db.ReadProperties;
import com.easymap.ticket.model.User;
import com.easymap.ticket.tools.Constants;
import com.easymap.ticket.tools.testUrlCon;

import sun.misc.BASE64Encoder;
public class ImgQuery extends HttpServlet{
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
			String orgcode="";
			/*address = InetAddress.getLocalHost().getHostAddress();*/
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
					logManager.setLog("440100120005", orgId, orgId, orgcode, new Date(System.currentTimeMillis()), "可视化情报分析","可视化情报分析照片查询接口",  ip, "1", "查询身份证号为"+str+"的照片");
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
		 if(parm!=null)
		 {
		    idcard=(String)parm;
		    if(idcard!=null&&!"".equals(idcard))
		    {
		    	try {
		    		result=testUrlCon.getInstance().getPostUrl(ReadProperties.getimageserverurl()+"/"+"ImgQuery?idcard="+idcard, "");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		 }
		 System.out.println(result);
		 System.out.println(System.currentTimeMillis()-times);
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().print(result);
	}
}
