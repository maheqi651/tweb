package com.easymap.ticket.listener;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

 






import com.easymap.ticket.tools.Constants;
import com.easymap.ticket.tools.RSAUtil;
import com.easymap.ticket.tools.testUrlCon;
import com.founderinternational.authcenter.bean.User;
import com.founderinternational.authcenter.hessian.ProxyAuthFactory;
 

public class IdentificationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

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
	
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		    HttpServletRequest request=(HttpServletRequest)arg0;
		    HttpServletResponse response=(HttpServletResponse)arg1;
		    System.out.println(request.getQueryString());
		    System.out.println(request.getRequestURI());
		    String  requeststr=request.getRequestURI();
		    String cas=request.getParameter(Constants.CAS);
		    String casuuid=null;
		    if(cas!=null)
		    {
		    	casuuid=decodeCAS(cas, request, response);
		    }
		     
		    String uuid=null;
		    String uuidstr="";
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
	    	if(users!=null&&casuuid==null)
			    {       
	    		       System.out.println("--------------------------hessian-------------------");
	    		       if(request.getSession().getAttribute("user")==null){
	    		    	    com.easymap.ticket.model.User usersvo=new com.easymap.ticket.model.User();
		    				usersvo.setUsername(users.getUsername());
		    				usersvo.setUserId(users.getId());
		    				usersvo.setOrgCode(users.getOrgCode());
		    				request.getSession().setAttribute("user",usersvo);
	    		       }
			    	   arg2.doFilter(arg0, arg1);
			    }
			    else
			    {
			    	try {
			    	    if(uuid==null)
			    	    {   
			    	    	((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);
			    	    }else{
 
			    	    	 String jsonstr=testUrlCon.getInstance().getPostUrl(Constants.AHTUURL+"GetAuthServlet?uuid="+uuid+"&urlstr="+Constants.URLSTR, "");
						    if(jsonstr!=null)
						    {
						    	JSONObject jsonob=JSONObject.fromObject(jsonstr);
						    	if(jsonob.get("result")!=null)
						    	{
						    		String result=jsonob.getString("result");
						    		if("true".equals(result))
						    		{
						    			if(jsonob.get("data")!=null){
						    				String userstr=jsonob.getString("data");
						    				JSONObject user=JSONObject.fromObject(userstr);
						    				String username=user.getString("username");
						    				com.easymap.ticket.model.User uservo=new com.easymap.ticket.model.User();
						    				uservo.setUsername(username);
						    				uservo.setOrgCode(user.getString("orgCode"));
						    				request.getSession().setAttribute("user",uservo);
						    				arg2.doFilter(arg0, arg1);
						    			}
						    			
						    		}else{
						    			 System.out.println("---126------");
						    		 ((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);
						    		}
						    	}else{
						    		 System.out.println("---127------");
						    		 ((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);
						    	}
						    	
						    	
						    }else{
						    	 System.out.println("---12-8-----");
						    	((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);
						    }
			    	    }
						
			    	} catch (Exception e) {
			    		 System.out.println("---12-9-----");
						e.printStackTrace();
					}
			    }
	}
	 
	/*public void doFilter2(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		    HttpServletRequest request=(HttpServletRequest)arg0;
		    if(request.getSession().getAttribute("user")!=null)
		    { 
		    	   arg2.doFilter(arg0, arg1);
		    }
		    else
		    {
		    	try {
		    	    Cookie[] cookies=request.getCookies();
		    	    String uuid=null;
		    	    if(cookies!=null)
		    	    for(Cookie  co: cookies)
		    	    {
		    	    	if(Constants.COOKIEID.equals(co.getName()))
		    	    	{
		    	    		uuid=setRSAStr(co.getValue());
		    	    		System.out.println("加密uuid:"+uuid);
		    	    	}
		    	    }
		    	    if(uuid==null)
		    	    {
		    	    	((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);
		    	    }else{
		    	    	JSONObject uuidjson=new JSONObject();
		    	    	uuidjson.put("uuid", uuid);
		    	    	System.out.println("json uuid:"+uuidjson.toString());
		    	    	String jsonstr=testUrlCon.getInstance().getPostUrl(Constants.AHTUURL+"GetAuthServlet?uuid="+uuid+"&urlstr="+Constants.URLSTR, "");
					    if(jsonstr!=null)
					    {
					    	JSONObject jsonob=JSONObject.fromObject(jsonstr);
					    	if(jsonob.get("result")!=null)
					    	{
					    		String result=jsonob.getString("result");
					    		if("true".equals(result))
					    		{
					    			if(jsonob.get("data")!=null){
					    				String userstr=jsonob.getString("data");
					    				JSONObject user=JSONObject.fromObject(userstr);
					    				User users=new  User();
					    				String username=user.getString("username");
					    				users.setOrgCode(user.getString("orgCode"));
					    				users.setUsername(username);
					    				request.getSession().setAttribute("user",users);
					    				arg2.doFilter(arg0, arg1);
					    			}
					    		}else{
					    		 ((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);
					    		}
					    	}else{
					    		 ((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);
					    	}
					    	
					    	
					    }else{
					    	((HttpServletResponse)arg1).sendRedirect(Constants.AHTUURL+"random?urlstr="+Constants.URLSTR);
					    }
		    	    }
					
		    	} catch (Exception e) {
					e.printStackTrace();
				}
		    	 
		    }
		    
	}*/

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
