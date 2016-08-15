/**
 *
 * @author geloin
 * @date 2012-5-5 ÉÏÎç9:31:52
 */
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

import com.founderinternational.rscenter.entity.EZUser;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.UserVO;
import com.founderinternational.rscenter.mapper.EZUserMapper;
import com.founderinternational.rscenter.service.EZUserService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.testUrlCon;

 
@Controller
@RequestMapping(value = "fg")
public class PostUrlController {
  
	 
    @ResponseBody
	@RequestMapping(value = "testRealUrl")
	public Map<String, Object> testRealUrl(HttpServletResponse response,HttpServletRequest request,String urls) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	int status=404;
	     if(urls==null)
	     {
	    	 
	     }else{
	    	 if("".equals(urls)||"null".equals(urls))
	    	 {
	    		 
	    	 }else{
	    		 status=testUrlCon.getInstance().getPostUrl(urls);
	    	 }
	     }
	    map.put("status", status);
		return map;
	}
     
}