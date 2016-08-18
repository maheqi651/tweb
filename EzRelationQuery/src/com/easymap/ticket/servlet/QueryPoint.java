package com.easymap.ticket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.easymap.ticket.db.ConnectionDB;

public class QueryPoint extends HttpServlet{
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
		response.setCharacterEncoding("utf-8");
		String sql1 = "select distinct fz from T_TL_CCXX_INFO t";
		String sql2 = "select distinct dz from T_TL_CCXX_INFO t";
		System.out.println(sql2+"--------------------"+sql1);
		ConnectionDB db = new ConnectionDB();
		List<Object[]> objs1 = db.excuteQuery(sql1, null);
		List<Object[]> objs2 = db.excuteQuery(sql2, null);
		
		db.closeAll();
		JSONObject res = new JSONObject();
//		res.put("pagenum", 12);
		res.put("objs1", JSONArray.fromObject(objs1));
		res.put("objs2", JSONArray.fromObject(objs2));
		response.getWriter().print(res.toString());
	}
}
