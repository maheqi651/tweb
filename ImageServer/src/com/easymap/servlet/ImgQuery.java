package com.easymap.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import com.easymap.comm.RequestQuery;
import com.easymap.comm.XmlParser;

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
	private void process(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 Object parm=request.getParameter("idcard");
		 String idcard=null;
		 String result="";
		 long times =System.currentTimeMillis();
		 if(parm!=null)
		 {
		    idcard=(String)parm;
		    if(idcard!=null&&!"".equals(idcard))
		    {
				String xmlstr= RequestQuery.getInstance().requestAdapter("SFZH='"+idcard+"'");
				//System.out.println(xmlstr);
				try {
					result=XmlParser.getInstance().getImg(xmlstr);
				} catch (DocumentException e) {
					 e.printStackTrace();
					 
				}
			}
		 }
		// BASE64Encoder encoder=new BASE64Encoder();
		 System.out.println(System.currentTimeMillis()-times);
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().print(result);
	}
}
