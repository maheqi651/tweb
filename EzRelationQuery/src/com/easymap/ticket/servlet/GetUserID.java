package com.easymap.ticket.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.easymap.ticket.db.ConnectionDB;
import com.easymap.ticket.db.ReadProperties;
import com.easymap.ticket.model.PageHelper;
import com.easymap.ticket.model.User;

public class GetUserID extends HttpServlet{
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
		System.out.println("coming in,and execute query now...");
		response.setCharacterEncoding("utf-8");
		User user=null;
	    if(request.getSession().getAttribute("user")!=null)
	    {
	    	  user=(User)request.getSession().getAttribute("user");
	    }
		JSONObject res = new JSONObject();
		res.put("result", JSONArray.fromObject(user));
		response.getWriter().print(res.toString());
	}
}
