 
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

import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.PrintGenerator;
 

 
@Controller
@RequestMapping(value = "fg")
public class DataSearchController {
  
	 @Resource(name = "menuService")
	 private MenuService menuService;
	
	 @ResponseBody
	 @RequestMapping(value="dataSearch")  
	public Map<String, Object>   dataSearch(HttpServletResponse response,String user) throws Exception {
		 response.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Menu> result = this.menuService.find();
		for(Menu m: result)
			PrintGenerator.sysoutlog(m.getName());
		map.put("result", result);
		System.out.println("daozhe");
		//jsonView
		return  map;
	}
}