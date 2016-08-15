package com.founderinternational.rscenter.filter;


import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easymap.management.newapi.LogManager;
 


import com.founderinternational.authcenter.bean.User;
import com.founderinternational.authcenter.hessian.ProxyAuthFactory;
import com.founderinternational.rscenter.entity.form.UserVO;
import com.founderinternational.rscenter.tools.AuthUrlCon;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.RSAUtil;
import com.founderinternational.rscenter.tools.testUrlCon;
import com.founderinternational.rscenter.tools.impl.TimeGeneral;

import net.sf.json.JSONObject;
 
 

public class IdentificationFilter implements Filter {
    private final static String ORGID="440100120011";//服务资源中心
   // private static Object keyob=new Object();
    public static String ezManagerLocation;
    public static LogManager logManager;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	static{
		 ezManagerLocation=  ResourceBundle.getBundle("esbparm").getString("ezManagerLocation");
		 logManager = new LogManager(ezManagerLocation);
	}
	public  String setRSAStr(String datastr){
	    try {
			PrivateKey privatekey=RSAUtil.getPrivateKey(Constants.PRIVATEKEYSTR);
			String uuidstr=RSAUtil.encryptbypri(privatekey, datastr);
			uuidstr=URLEncoder.encode(uuidstr);
			return uuidstr;
					 
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
   }
	
	public  void insertLOG(HttpServletRequest request)
	{
		Enumeration<String> plist=request.getParameterNames();
		String str="";
	   
		 while(plist.hasMoreElements())
		 {   String ss=plist.nextElement();
			// System.out.println("parm:"+str);
			 str+=ss+":"+request.getParameter(ss)+",";
		 }
		//String address;
		try {
			String orgId = "admin";
			/*address = InetAddress.getLocalHost().getHostAddress();*/
			 
			String  requeststr=request.getRequestURI();
			requeststr=requeststr.substring(requeststr.lastIndexOf("/"));
			String  ip=request.getRemoteAddr();
			String orgCode="";
			String username="admin";
			if(request.getSession().getAttribute(Constants.USER)!=null)
			{
				UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
				orgCode=uservo.getOrgCode();
				orgId=uservo.getUserid();
				username=uservo.getUsername();
			}
				
				
			try {
			       if(orgId==null)
			       {
			    	   
			       }else{
			    	   // System.out.println(ORGID+" "+username+" "+orgId+" "+""+" "+new Date(System.currentTimeMillis())+" "+"服务资源中心"+" "+"调用服务资源中心"+requeststr+"接口"+" "+  ip+" "+ ""+ findTyle(requeststr)+" "+ str);
						logManager.setLog(ORGID, username,orgId, orgCode, new Date(System.currentTimeMillis()), "服务资源中心","调用服务资源中心"+requeststr+"接口",  ip, ""+ findTyle(requeststr), str);
			       }
				} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  int findTyle(String str){
		//，0：登录；1：查询；2：新增；3：修改；4：删除
		int  type=1;
		str=str.toLowerCase();
		if(str.contains("find")||str.contains("search"))
		{
			type=1;
		}else if(str.contains("add")||str.contains("insert"))
		{
			type=2;
		}else if(str.contains("update")){
			type=3;
		}else if(str.contains("delete")){
			type=4;
		}else{
			type=0;
		}
		return type;
	}
	
	public String  decodeCAS(String datastr,HttpServletRequest request,HttpServletResponse response){
		   String uuid=null;
		   if(datastr!=null)
		   {
			   datastr=URLDecoder.decode(datastr);
			   try {
				uuid=RSAUtil.decrypt(RSAUtil.getPrivateKey(Constants.PRIVATEKEYSTR), datastr);
				if(uuid!=null)
				{//cookie;
					Cookie cookie=new Cookie(Constants.COOKIEID, uuid);
					cookie.setPath("/");
					cookie.setMaxAge(60*60);//一个小时
					response.addCookie(cookie);
					return uuid;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return uuid;
			}
		   }
		   return uuid;
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		    HttpServletRequest request=(HttpServletRequest)arg0;
		    HttpServletResponse response=(HttpServletResponse)arg1;
		    System.out.println(request.getRequestURI()+"--ip--"+request.getRemoteAddr());
		    System.out.println(request.getQueryString()+"--ip2--"+request.getRemoteHost());
		    //System.out.println(request.getQueryString());
		    //System.out.println(request.getRequestURI());
		    String  requeststr=request.getRequestURI();
		    String cas=request.getParameter(Constants.CAS);
		    String casuuid=null;
		    String random="Valite.jsp";
		    if(cas!=null)
		    {
		    	casuuid=decodeCAS(cas, request, response);
		    }
		    if(requeststr.contains(".do"))
		    {
		    	insertLOG(request);//插入日志
		    }
		    boolean flagurl=false;
		    /*  boolean flag=false;
		    if(requeststr.contains(".do"))
		    {
		    	flag=true;
		    }*/
		    if(requeststr.contains("findTotalChartDataForPortal"))
		    {
		    	flagurl=true;
		    }
		    String flag=request.getParameter("flag");
		    //System.out.println("flag:"+flag);
		    String  uuidstr="";
		    if("flase"==flag)
		    {
		    	// System.out.println("---124------");
		    	((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+random+"?urlstr="+Constants.URLSTR);
		    }else{
		    	String uuid=null;
		    	if(casuuid!=null)
		    	{
		    		uuid=casuuid;
		    	    uuid=setRSAStr(casuuid);
		    	}else{
		    		    Cookie[] cookies=request.getCookies();
			    	    System.out.println("cookie:"+(cookies==null));
			    	    if(cookies!=null)
			    	    for(Cookie  co: cookies)
			    	    {
			    	    	System.out.println("cookie:"+co.getName()+"--------"+co.getValue());
			    	    	if(Constants.COOKIEID.equals(co.getName()))
			    	    	{
			    	    		uuidstr=co.getValue();
			    	    		uuid=setRSAStr(co.getValue());
			    	    		System.out.println("加密uuid:"+uuid);
			    	    	}
			    	    }
		    	} 
		    	
		    	User users=ProxyAuthFactory.getInstance().getSessionByuuid(Constants.AHTUURL+"HessianHelloWord", uuidstr);
		    	if((users!=null&&casuuid==null)||flagurl)
			    {      //System.out.println("---1------");
		    		/* if(request.getSession().getAttribute(Constants.USER)==null&&!flagurl)
		    		   {//session 等于空
*/		    			    UserVO uservo=new UserVO();
		    				if(Constants.ADMINTYPE.equals(users.getRoletyle()))
		    		    	{
		    		    		uservo.setRole(Constants.ADMINROLE);
		    		    	}else{
		    		    		uservo.setRole(Constants.COMROLE);
		    		    	}
		    				
		    		    	uservo.setUserid(users.getId());
		    		    	uservo.setUsername(users.getUsername());
		    		    	uservo.setOrgCode(users.getOrgCode());
		    				request.getSession().setAttribute(Constants.USER,uservo);
		    		   //}
			    	   arg2.doFilter(arg0, arg1);
			    }
			    else
			    {
			    	//添加sesstion更新
			    	/* if(request.getSession().getAttribute(Constants.USER)!=null)
			    	 {   //去除session
			    		 request.getSession().removeAttribute(Constants.USER);
			    	 }*/
			    	 
			    	try {
			    	    if(uuid==null)
			    	    {   
			    	    	 System.out.println("uuid==null----------------");
			    	    	((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+random+"?urlstr="+Constants.URLSTR);
			    	    }else{
			    	    	System.out.println("---154------");
			    	    	String jsonstr=AuthUrlCon.getInstance().getPostUrl(Constants.AHTUURL+"GetAuthServlet?uuid="+uuid+"&urlstr="+Constants.URLSTR, "");
						    if(jsonstr!=null)
						    {
						    	JSONObject jsonob=JSONObject.fromObject(jsonstr);
						    	if(jsonob.get("result")!=null)
						    	{
						    		String result=jsonob.getString("result");
						    		System.out.println("result:"+result);
						    		if("true".equals(result))
						    		{
						    		 	if(jsonob.get("data")!=null){
						    				String userstr=jsonob.getString("data");
						    				JSONObject user=JSONObject.fromObject(userstr);
						    				UserVO uservo=new UserVO();
						    				boolean PKILogin=user.getBoolean("PKILogin");
						    				if(PKILogin)
						    				{
						    					if(user.get("phone")==null)
						    					{
						    					      ((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);
						    					}else{
						    						if("".equals(user.getString("phone")))
						    						{
						    						   ((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);	
						    						}
						    					}
						    				}
						    				System.out.println("-----USER--------:"+user);
						    		    	if(Constants.ADMINTYPE.equals(user.getString("roletyle")))
						    		    	{
						    		    		uservo.setRole(Constants.ADMINROLE);
						    		    	}else{
						    		    		uservo.setRole(Constants.COMROLE);
						    		    	}
						    		    	uservo.setUserid(user.getString("id"));
						    		    	uservo.setUsername(user.getString("username"));
						    		    	uservo.setOrgCode(user.getString("orgCode"));
						    				request.getSession().setAttribute(Constants.USER,uservo);
						    				arg2.doFilter(arg0, arg1);
						    			}
						    		}else{
						    			 System.out.println("---126------");
						    			 ((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);	
						    		}
						    	}else{
						    		 System.out.println("---127------");
						    		 ((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+random+"?urlstr="+Constants.URLSTR);
						    	}
						    	
						    	
						    }else{
						    	 System.out.println("---12-8-----");
						    	((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+random+"?urlstr="+Constants.URLSTR);
						    }
			    	    }
						
			    	} catch (Exception e) {
			    		 System.out.println("---12-9-----");
						e.printStackTrace();
					}
			    	 
			    }
		    }
		    
		    
	}
    public static void main(String[] args){
    	
		//String address;
		try {
			String orgId = "1111";
			/*address = InetAddress.getLocalHost().getHostAddress();*/
			 
		/*	440100120011 admin admin  2016-01-16 服务资源中心 调用服务资源中心/EzAPFWPlot/fg/findservice.do接口 10.235.36.2 1 
			null
			440100120011 admin admin  2016-01-16 服务资源中心 调用服务资源中心/EzAPFWPlot/fg/findMassegeNew.do接口接口 10.235.36.2 1 
			/EzAPFWPlot/fg/findMassegeNew.do
			flag:null
			440100120011 admin admin  2016-01-16 服务资源中心 调用服务资源中心/EzAPFWPlot/fg/findMassegeNew.do接口 10.235.36.2 1 
*/
				orgId="admin";
				String requeststr="/EzAPFWPlot/fg/findMassegeNew.do";
				requeststr=requeststr.substring(requeststr.lastIndexOf("/"));
		 System.out.println(requeststr);
			String  ip="10.235.36.2";
			try {
				for(int i=0;i<2;i++)
				{
					System.out.println(ORGID+" "+orgId+" "+orgId+" "+""+" "+new Date(System.currentTimeMillis())+" "+"服务资源中心"+" "+"调用服务资源中心/EzAPFWPlot/fg/findMassegeNew.do接口接口"+" "+  ip+" "+ "1"+" "+ "");
					logManager.setLog(ORGID, orgId, orgId, "440100000000", new Date(System.currentTimeMillis()), "服务资源中心","调用服务资源中心/findMassegeNew.do接口",  ip, "1", "");
				}
				
				} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
