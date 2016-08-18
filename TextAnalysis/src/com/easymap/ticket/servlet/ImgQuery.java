package com.easymap.ticket.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.easymap.ticket.db.ReadProperties;
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
