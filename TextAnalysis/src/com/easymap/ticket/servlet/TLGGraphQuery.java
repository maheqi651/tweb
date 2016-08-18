  package com.easymap.ticket.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyleConstants.ColorConstants;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.easymap.ticket.db.ConnectionDB;
import com.easymap.ticket.db.ReadProperties;
import com.easymap.ticket.model.Edges;
import com.easymap.ticket.model.Notes;
import com.easymap.ticket.model.PageHelper;
import com.easymap.ticket.model.PersonRel;
import com.easymap.ticket.tools.Constants;
import com.easymap.ticket.tools.OrientdbUtil;
import com.easymap.ticket.tools.Tools;
import com.easymap.ticket.tools.testUrlCon;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class TLGGraphQuery extends HttpServlet{
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
		//idcard startdate enddate rrtype
		 request.setCharacterEncoding("UTF-8");
		/* String jsonstr=IOUtils.toString(request.getInputStream());
		 System.out.println(jsonstr);*/
		// JSONObject parm=JSONObject.fromObject(jsonstr);
		 String idcard=null;
		 String startdate=null;
		 String enddate=null;
		 String rrtype =null;
		  if(request.getParameter("idcard")!=null)
		  idcard =request.getParameter("idcard");
		  if(request.getParameter("startdate")!=null&&!"".equals(request.getParameter("startdate")))
		  startdate =request.getParameter("startdate").replaceAll("-", "");
	      if(request.getParameter("enddate")!=null&&!"".equals(request.getParameter("enddate")))
		  enddate =request.getParameter("enddate").replaceAll("-", "");
		   
		  if(request.getParameter("rrtype")!=null)
		  rrtype =request.getParameter("rrtype");
		  String[] strtype=null;
		  if(rrtype!=null&&!"".equals(rrtype))
		  {
			  strtype=rrtype.split(",");
		  }
		  
		  long times=System.currentTimeMillis();
		  Map<String,PersonRel> relmap=new HashMap<String,PersonRel>();
		  Map<String,String> flagmap=new HashMap<String,String>();
		  Map<String,Object> resultmap=new HashMap<String,Object>();
		  if(idcard!=null&&!"".equals(idcard))
		  {
			  //String sql="select from (select expand(bothe().bothv().bothe()) from person where id='"+idcard+"') ";
			  String sql="select from (select expand(bothe()) from person where id='"+idcard+"') ";
			  if(rrtype!=null&&!"".equals(rrtype))
			  {
				  if(strtype!=null)
				  {
					   
					  int count=0;
					  for(String str:strtype)
					  {
						  if(!"".equals(str))
						  {
							  if(count==0)
							  {
								  sql+=" where ( type='"+str+"' ";
								  count++;  
							  }else{
								  sql+=" or type='"+str+"' ";
								  count++; 
							  }
							 
						  }
					  }
					  sql+=" )";
				  }
				 
			  }
			  if((startdate!=null&&!"".equals(startdate))||(enddate!=null&&!"".equals(enddate)))
			  {
				 if(rrtype==null||"".equals(rrtype))
				 {
					 sql+="where 1=1 ";
				 }
				 if(startdate!=null&&!"".equals(startdate))
					 sql+=" and (ltime1>'"+startdate+"' or ltime2>'"+startdate+"' ) ";
				 if(enddate!=null&&!"".equals(enddate))
					 sql+=" and (etime1<'"+enddate+"' or etime2<'"+enddate+"' )";
			  }
			  
			  System.out.println(sql);
			  List<ODocument> olist=OrientdbUtil.getinstance().searchRecord(sql);
			
			  if(olist!=null)
			  {
				  for(ODocument o:olist)
				  {   
					    doODum(relmap,o,flagmap,idcard);
				  }
			  }
			  
			  List<Notes> listnotes=new ArrayList<Notes>();//点
			  List<Edges> listedges=new ArrayList<Edges>();//边
			  
			 
			  Set<String> keys=relmap.keySet();
			  Map<String,Integer> ismap=new HashMap<String,Integer>();
			  int count=0;
			  for(String key:keys){
				  PersonRel temp=relmap.get(key);
				  Notes notes1=new Notes();
				  Edges edges=new Edges();
				  if(!ismap.containsKey(temp.getIdcard1()))
				  {
					  notes1.setCount(1);
					  notes1.setIdcard(temp.getIdcard1());
					  notes1.setImage("image/xizhong.png");
					  notes1.setName(temp.getName1());
					  listnotes.add(count, notes1);
					  ismap.put(temp.getIdcard1(), count);
					  edges.setSource(count);//记录多少位
					  count++;
				  }else{
					  Notes notetemp=listnotes.get(ismap.get(temp.getIdcard1()));
					  notetemp.setCount(notetemp.getCount()+1); 
					  edges.setSource(ismap.get(temp.getIdcard1()));
				  }
				  Notes notes2=new Notes();
				  edges.setRelation(temp.getType());
				  if(!ismap.containsKey(temp.getIdcard2()))
				  {//target
					  notes2.setCount(1);
					  notes2.setIdcard(temp.getIdcard2());
					  notes2.setImage("image/chanyou.png");
					  notes2.setName(temp.getName2());
					  listnotes.add(count, notes2);
					  ismap.put(temp.getIdcard2(), count);
					  edges.setTarget(count);//记录多少位
					  count++;
				  }else{
					  Notes notetemp=listnotes.get(ismap.get(temp.getIdcard2()));
					  notetemp.setCount(notetemp.getCount()+1); 
					  edges.setTarget(ismap.get(temp.getIdcard2()));
				  }
				  listedges.add(edges);
			  }
			  Map<String,Integer> edgescheckrepeat=new HashMap<String,Integer>();
			  List<Edges> listedgesnew=new ArrayList<Edges>();
			  if(listedges!=null)
			  {
				  for(int i=0;i<listedges.size();i++)
				  {   
					  Edges edgetemp=listedges.get(i);
					  String keytemp=edgetemp.getSource()+"#"+edgetemp.getTarget();
					  if(edgescheckrepeat.get(keytemp)==null)
					  {
						  edgescheckrepeat.put(keytemp, i);
						  listedgesnew.add(edgetemp);
						  
					  }else{
						  if(!listedges.get(edgescheckrepeat.get(keytemp)).getRelation().contains(edgetemp.getRelation())){
							  listedges.get(edgescheckrepeat.get(keytemp)).setRelation( listedges.get(edgescheckrepeat.get(keytemp)).getRelation()+","+edgetemp.getRelation()); 
						  }
					  }
				  }
			  }
			  resultmap.put("nodes", listnotes);
			  resultmap.put("edges", listedgesnew);
		  }
		 System.out.println(System.currentTimeMillis()-times);
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().print(JSONObject.fromObject(resultmap).toString());
	}
	
	public void doODum(Map<String,PersonRel> relmap,ODocument o, Map<String,String> flagmap,String idcard){
		  String rid=o.field("@RID").toString();
		 
		  if(relmap.get(rid)==null)
		  {
			  PersonRel p=new PersonRel();
			  String key="";
			  if(o.field("type")!=null)
				  p.setType(Constants.dicMap.get(o.field("type").toString())); 
				  ORID RIDout=o.field("out");
				  ODocument  po1=OrientdbUtil.getinstance().searchOneRecord(RIDout);
				  if(po1.field("id")!=null)
					  p.setIdcard1(po1.field("id").toString());
				  if(po1.field("name")!=null)
					  p.setName1(po1.field("name").toString());
				  flagmap.put(key, "1");
				  if(!idcard.equals(po1.field("id").toString()))
				  {
					     key=o.field("nohotel")+"#"+o.field("noroom2")+"#"+po1.field("id").toString()+"#"+o.field("ltime1")+"#"
								  +o.field("etime1");
				  }
			   
			  
				  ORID RIDin=o.field("in");
				  ODocument  po2=OrientdbUtil.getinstance().searchOneRecord(RIDin);
				  if(po2.field("id")!=null)
					  p.setIdcard2(po2.field("id").toString());
				  if(po2.field("name")!=null)
					  p.setName2(po2.field("name").toString());
				  
				  if(!idcard.equals(po2.field("id").toString()))
				  {
					  key=o.field("nohotel")+"#"+o.field("noroom2")+"#"+po2.field("id").toString()+"#"+o.field("ltime2")+"#"
							  +o.field("etime2");
				  }
				  if(flagmap.get(key)==null)
				  {
					  relmap.put(rid, p);
					  flagmap.put(key, o.field("type").toString());
				  }/*else{
					  if(flagmap.get(key).contains(o.field("type").toString()))
					  {}else{
						  flagmap.put(key, flagmap.get(key)+","+o.field("type").toString());
						   
					  }
				  }*/
			  
		  }
	}
}
