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

public class QueryCxhList extends HttpServlet{
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
		String CC = request.getParameter("cc");
		String CCRQ = request.getParameter("ccrq");
		 
		 
		 
		 
		//select t.cxh from T_TL_CCXX_INFO t  where t.cc='25000C22720B' and  t.ccrq=to_date('2014-05-30','yyyy-mm-dd') group by  t.cxh  order by t.cxh asc  
		 
		String countsql ="select count(*) from T_TL_CCXX_INFO t where 1=1";
		String sql = "select t.cxh from T_TL_CCXX_INFO t where 1=1";
		 
		 
		 
		if(CC!=null&&!"".equals(CC)){
			sql += " and t.CC= '"+CC+"' ";
			countsql+=" and t.CC= '"+CC+"' ";
		}
		 
		if(CCRQ!=null&&!"".equals(CCRQ)){
			sql += " and to_char(t.ccrq,'yyyymmdd') = '"+CCRQ.replace("-", "")+"'";
			countsql+=" and to_char(t.ccrq,'yyyymmdd') = '"+CCRQ.replace("-", "")+"'";
		}
		sql += " group by  t.cxh  order by t.cxh asc";
		System.out.println("execute sql:"+sql);
		ConnectionDB db = new ConnectionDB();
		System.out.println("execute countsql:"+countsql);
		Object[] count = db.executeQuerySingle(countsql, null);
 		int pagecount = ((BigDecimal)count[0]).intValue();
 		int pagenum = ((BigDecimal)count[0]).intValue()%4==0?0:1;
		 pagenum = pagecount/4+pagenum;
    	if(pagenum==0) pagenum=1;
 	    System.out.println(pagecount);
		List<Object[]> objs = db.excuteQuery(sql, null);
		db.closeAll();
		JSONObject res = new JSONObject(); 
		res.put("pagenum", pagenum);
		System.out.println(JSONArray.fromObject(objs).toString());
		res.put("result", JSONArray.fromObject(objs));
		response.getWriter().print(res.toString());
	}
}
