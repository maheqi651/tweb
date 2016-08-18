package com.easymap.ticket.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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
import com.easymap.ticket.tools.testUrlCon;

public class DelProxRequest extends HttpServlet{
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
		// System.out.println(jsonstr);
		// jsonstr=jsonstr.replace("&", "?");
		 System.out.println(jsonstr);
		 String result=testUrlCon.getInstance().getPostUrl("StreamDelServlet", jsonstr);
		JSONObject res = new JSONObject();
 		 
		res.put("result", result);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(res.toString());
	}
}
