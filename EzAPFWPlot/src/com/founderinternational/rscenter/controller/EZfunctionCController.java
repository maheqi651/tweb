 
package com.founderinternational.rscenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZFunctionC;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.EZFunctionVO;
import com.founderinternational.rscenter.entity.form.UserVO;
import com.founderinternational.rscenter.service.EZFunctionCService;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.PrintGenerator;
import com.founderinternational.rscenter.tools.Tools;
 

 
@Controller
@RequestMapping(value = "fg")
public class EZfunctionCController {
  
	 @Resource(name = "eZFunctionCService")
	 private EZFunctionCService eZFunctionCService;
	 
	  /* 
	   * author cloudMa
	   * 根据用户权限查询应用列表
	   * param user
	   * */
	 @ResponseBody
	 @RequestMapping(value="findFunctionCByuser")  
	 public Map<String, Object>   findByuser(HttpServletResponse response,HttpServletRequest request,String user) throws Exception {
		 response.setCharacterEncoding("utf-8");
		 if(request.getSession().getAttribute(Constants.USER)!=null)
		 {
			 UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
			 String username=uservo.getUsername();
			 if(username!=null)
				 user=username;
		 }
		 Map<String, Object> map = new HashMap<String, Object>();
		 if(user!=null){
			 List<EZFunctionC> result = this.eZFunctionCService.find(user);
			 map.put("result", result);
		 }
		 return  map;
	}
	 /*
	  * author cloudMa
	  * 根据code查询  申请应用信息  date 
	  * date  2015-07-01
	  * */
	 @ResponseBody
	 @RequestMapping(value="findFunctionCByCode")  
	 public Map<String, Object>   findFunctionCByCode(HttpServletResponse response,HttpServletRequest request,String code) throws Exception 
	 {
		 response.setCharacterEncoding("utf-8");
		 Map<String, Object> map = new HashMap<String, Object>();
		 if(code!=null){
			 EZFunctionC ezfunctionc= this.eZFunctionCService.findByCode(code);
			 map.put("result",ezfunctionc);
		 }
		 return  map;
	 }
	 
	 /*
	  * author cloudMa
	  * 添加应用 date 
	  * date  2015-06-17
	  * 2015-06-17
	  * */
	 @ResponseBody
	 @RequestMapping(value="addFunctionC")  
	 public Map<String, Object>   addFunction(HttpServletResponse response,EZFunctionC formvo) throws Exception {
		 response.setCharacterEncoding("utf-8");
		  Map<String, Object> map = new HashMap<String, Object>();
		  int status=0;
		  if(formvo!=null){
			  formvo.setCODE(Tools.getUUID());
			   
			  status=this.eZFunctionCService.Insert(formvo);
		  }
		  map.put("result", status); 
		 //List<EZFunction> result = this.eZFunctionService.find(new String(user.getBytes("ISO8859-1"),"utf-8"));
		// map.put("result", result);
		 return  map;
	}
	 
	  
	
	 
	 
	 
	 
}