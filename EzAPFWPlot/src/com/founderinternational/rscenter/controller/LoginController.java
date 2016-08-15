/**
 *
 * @author geloin
 * @date 2012-5-5 上午9:31:52
 */
package com.founderinternational.rscenter.controller;

import java.net.URLEncoder;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easymap.management.entity.Function;
import com.easymap.management.newapi.FunctionManager;
import com.founderinternational.rscenter.entity.EZUser;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.UserVO;
import com.founderinternational.rscenter.mapper.EZUserMapper;
import com.founderinternational.rscenter.service.EZUserService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.AuthUrlCon;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.RSAUtil;

 
@Controller
@RequestMapping(value = "login")
public class LoginController {
  
	@Resource(name = "eZUserService")
	private EZUserService eZUserService;
    @ResponseBody
	@RequestMapping(value = "to_login")
	public Map<String, Object> toLogin(HttpServletResponse response,HttpServletRequest request,String username,String password) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
	    EZUser ezuser=this.eZUserService.findEZUser(username, password);
	    if(ezuser!=null)
	    {
	    	FunctionManager fm=new FunctionManager(Constants.EzManagerUrl);
	        List<Function> listf=fm.getFunctionByUserId(username);
	        boolean flag=true;
	     /*   for(Function f:listf)
	        {
	        	if("车辆卡口分析".equals(f.getName()))
	        	{
	        		System.out.println(f.getName());
	        		flag=true;
	        		break;
	        	}
	        }*/
	        if(flag)
	        {
	        	UserVO uservo=new UserVO();
		    	if(Constants.ADMINTYPE.equals(ezuser.getUSERTYPE()))
		    	{
		    		uservo.setRole(Constants.ADMINROLE);
		    	}else{
		    		uservo.setRole(Constants.COMROLE);
		    	}
		    	System.out.println("username:"+ezuser.getUSERNAME());
		    	uservo.setUsername(ezuser.getUSERNAME());
		    	uservo.setOrgCode(ezuser.getZZJGDM()); 
		    	request.getSession().setAttribute(Constants.USER, uservo);	
		    	map.put("result", "success");
	        }else
	        {
	        	map.put("result", "faild");
	        }
	    }else
	    {
	    	    map.put("result", "faild");
	    }
		return map;
	}
    
    @ResponseBody
  	@RequestMapping(value = "to_loginp")
  	public Map<String, Object> to_loginp(HttpServletResponse response,HttpServletRequest request,String pusername,String password) throws Exception {
      	Map<String, Object> map = new HashMap<String, Object>();
  	    EZUser ezuser=this.eZUserService.findEZUser(pusername, password);
  	    if(ezuser!=null)
  	    {
  	    	UserVO uservo=new UserVO();
  	    	if(Constants.ADMINTYPE.equals(ezuser.getUSERTYPE()))
  	    	{
  	    		uservo.setRole(Constants.ADMINROLE);
  	    	}else{
  	    		uservo.setRole(Constants.COMROLE);
  	    	}
  	    	System.out.println("username:"+ezuser.getUSERNAME());
  	    	uservo.setUsername(ezuser.getUSERNAME());
  	    	request.getSession().setAttribute(Constants.USER, uservo);
  	    	map.put("result", "success");
  	    }else{
  	    	map.put("result", "faild");
  	    }
  		return map;
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
    @ResponseBody
  	@RequestMapping(value = "to_logout")
  	public Map<String, Object> to_logout(HttpServletResponse response,HttpServletRequest request,String pusername,String password) throws Exception {
      	Map<String, Object> map = new HashMap<String, Object>();
  	     if(request.getSession().getAttribute(Constants.USER)!=null)
  	     {
  	    	request.getSession().removeAttribute(Constants.USER);
  	    	 //清楚COOKIE
  	    	   Cookie[] cookies=request.getCookies();
	    	   // String uuid=null;
	    	    if(cookies!=null)
	    	    for(Cookie  co: cookies)
	    	    {
	    	    	if(Constants.COOKIEID.equals(co.getName()))
	    	    	{
	    	    		String casuuid= setRSAStr(co.getValue());
	    	    		Cookie cookie=new Cookie(co.getName(),null);
	    	    		cookie.setMaxAge(0);
	    	    		cookie.setPath("/");
	    	    		System.out.println("清空cookie");
	    	    		response.addCookie(cookie);
	    	    		if(casuuid!=null)//去除远端cookie
	    	    		{
	    	    			 AuthUrlCon.getInstance().getPostUrl(Constants.AHTUURL+"LgoutServlet?"+Constants.CAS+"="+casuuid, "");
	    	    	         System.out.println("去除远端cookie...");
	    	    		}
	    	    		}
	    	    }
  	     }
  	    map.put("result", "success");
  		return map;
  	}
    
    
    @ResponseBody
	@RequestMapping(value = "findrole")
	public Map<String, Object> findrole(HttpServletResponse response,HttpServletRequest request) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
	   
	    if(request.getSession().getAttribute(Constants.USER)!=null)
	    {
	    	UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
	    	map.put("result", uservo.getRole());
	    }else{
	    	map.put("result", "error");
	    }
		return map;
	}
    
    
    @ResponseBody
   	@RequestMapping(value = "findusername")
   	public Map<String, Object> findusername(HttpServletResponse response,HttpServletRequest request) throws Exception {
       	Map<String, Object> map = new HashMap<String, Object>();
   	   
   	    if(request.getSession().getAttribute(Constants.USER)!=null)
   	    {
   	    	UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
   	    	map.put("result", uservo.getUsername());
   	    }else{
   	    	map.put("result", "error");
   	    }
   		return map;
   	}
    
    
     
}