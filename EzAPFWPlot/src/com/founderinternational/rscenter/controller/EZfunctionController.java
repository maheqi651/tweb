 
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
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.EZFunctionVO;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.PrintGenerator;
import com.founderinternational.rscenter.tools.Tools;
 

 
@Controller
@RequestMapping(value = "fg")
public class EZfunctionController {
  
	 @Resource(name = "eZFunctionService")
	 private EZFunctionService eZFunctionService;
	
	 @ResponseBody
	 @RequestMapping(value="searchFunction")  
	 public Map<String, Object>   dataSearch(HttpServletResponse response,String user) throws Exception {
		 response.setCharacterEncoding("utf-8");
		 long times=System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		List<EZFunction> result = this.eZFunctionService.find();
		/*for(EZFunction m: result)
			PrintGenerator.sysoutlog(m.getDESCRIPTION());*/
		map.put("result", result);
		System.out.println("daozhe 用时间："+(System.currentTimeMillis()-times));
		//jsonView
		return  map;
	}
	 
	  /* 
	   * author cloudMa
	   * 根据用户权限查询应用列表
	   * param user
	   * */
	 @ResponseBody
	 @RequestMapping(value="findFunctionByuser")  
	 public Map<String, Object>   findByuser(HttpServletResponse response,String user) throws Exception {
		 response.setCharacterEncoding("utf-8");
		 Map<String, Object> map = new HashMap<String, Object>();
		 List<EZFunction> result = this.eZFunctionService.find(new String(user.getBytes("ISO8859-1"),"utf-8"));
		 map.put("result", result);
		 return  map;
	}
	 
	 /*
	  * author cloudMa
	  * 添加应用 date 
	  * date  2015-06-17
	  * 2015-06-17
	  * */
	 @ResponseBody
	 @RequestMapping(value="addFunction")  
	 public Map<String, Object>   addFunction(HttpServletResponse response,EZFunction formvo) throws Exception {
		 response.setCharacterEncoding("utf-8");
		  Map<String, Object> map = new HashMap<String, Object>();
		  int status=0;
		  if(formvo!=null){
			  formvo.setCODE(Tools.getUUID());
			  formvo.setSEQ(1);
			  status=this.eZFunctionService.Insert(formvo);
		  }
		  map.put("result", status); 
		 //List<EZFunction> result = this.eZFunctionService.find(new String(user.getBytes("ISO8859-1"),"utf-8"));
		// map.put("result", result);
		 return  map;
	}
	 
	 /*
	  * author cloudMa
	  * 删除应用
	  * date 2015-06-7
	  * */
	 @ResponseBody
	 @RequestMapping(value="deleteFunction")  
	 public Map<String, Object>   deleteFunction(HttpServletResponse response,String CODE) throws Exception {
		  response.setCharacterEncoding("utf-8");
		  Map<String, Object> map = new HashMap<String, Object>();
		  int status=0;
		  if(CODE!=null)
		  {
		  this.eZFunctionService.delete(CODE);
		      map.put("result", "success"); 
		  }else{
			  map.put("result", "faild");
		  }
		 return  map;
	}
	 
	 /*
	  * author cloudMa
	  * 修改应用
	  * date 2015-06-17
	  * */
	 
	 @ResponseBody
	 @RequestMapping(value="updateFunction")  
	 public Map<String, Object>   updateFunction(HttpServletResponse response,EZFunction formvo) throws Exception {
		 response.setCharacterEncoding("utf-8");
		  Map<String, Object> map = new HashMap<String, Object>();
		  int status=0;
		  if(formvo!=null){
			  status=this.eZFunctionService.Update(formvo);
		  }
		  map.put("result", status); 
		 //List<EZFunction> result = this.eZFunctionService.find(new String(user.getBytes("ISO8859-1"),"utf-8"));
		 // map.put("result", result);
		 return  map;
	}
	
	 
	 
	 
	 
}