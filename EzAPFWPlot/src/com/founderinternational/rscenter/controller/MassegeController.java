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
import com.founderinternational.rscenter.entity.MassegeInfo;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.UserVO;
import com.founderinternational.rscenter.mapper.EZUserMapper;
import com.founderinternational.rscenter.service.EZUserService;
import com.founderinternational.rscenter.service.MassegeInfoService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.Constants;

 
@Controller
@RequestMapping(value = "fg")
public class MassegeController {
  
	@Resource(name = "massegeInfoService")
	private MassegeInfoService massegeInfoService;
    @ResponseBody
	@RequestMapping(value = "findMassegeNew")
	public Map<String, Object> findMassegeNew(HttpServletResponse response,HttpServletRequest request) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
	    if(request.getSession().getAttribute(Constants.USER)!=null)
	    {
	    	UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
	    	List<MassegeInfo> lists=this.massegeInfoService.find(uservo.getUsername(), Constants.MASSAGESTATUSWD);
	    	if(lists!=null)
	    	{    map.put("count", lists.size());
	    		if(lists.size()>10)
	    			lists=lists.subList(0, 10);
	    		for(MassegeInfo mi:lists)
	    		{
	    			 String name=mi.getTITLE();
	    			 if(name!=null)
	    			 {
	    				 if(name.length()>18)
	    				 {
	    					 mi.setTITLE(name.substring(0,18)+"...");
	    				 }
	    			 }
	    		}
	    	}else{
	    		map.put("count",0);
	    	}
	    	 
	        map.put("result", lists);
	    } 
		return map;
	}
    @ResponseBody
	@RequestMapping(value = "findMassegeBystatus")
	public Map<String, Object> findMassegeBystatus(HttpServletResponse response,HttpServletRequest request,Integer status) throws Exception {
    	System.out.println(status);
    	Map<String, Object> map = new HashMap<String, Object>();
	    if(request.getSession().getAttribute(Constants.USER)!=null)
	    {
	    	UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
	    	List<MassegeInfo> lists=null;
	    	if(status>=0)
	    		lists=this.massegeInfoService.find(uservo.getUsername(), status);
	        map.put("data", lists);
	        //System.out.println("----"+lists.size());
	    } 
		return map;
	}
    
    @ResponseBody
   	@RequestMapping(value = "DeleteMassegeInfoById")
   	public Map<String, Object> DeleteMassegeInfoById(HttpServletResponse response,HttpServletRequest request,Integer ids) throws Exception {
        
       	Map<String, Object> map = new HashMap<String, Object>();
   	    if(request.getSession().getAttribute(Constants.USER)!=null)
   	    {
   	    	UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
   	    	 MassegeInfo  lists=null;
   	    	if(ids>=0)
   	    		lists=this.massegeInfoService.findone(ids+"");
   	    	if(lists!=null)
   	    	{
   	    		lists.setSTATUS(Constants.MASSAGESTATUSSC);
   	    		map.put("result", this.massegeInfoService.update(lists)); 
   	    	}
   	        map.put("result", lists);
   	        //System.out.println("----"+lists.size());
   	    } 
   		return map;
   	}
    
    
    @ResponseBody
   	@RequestMapping(value = "findMassegeInfoById")
   	public Map<String, Object> findMassegeInfoById(HttpServletResponse response,HttpServletRequest request,Integer ids) throws Exception {
        
       	Map<String, Object> map = new HashMap<String, Object>();
   	    if(request.getSession().getAttribute(Constants.USER)!=null)
   	    {
   	    	UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
   	    	 MassegeInfo  lists=null;
   	    	if(ids>=0)
   	    		lists=this.massegeInfoService.findone(ids+"");
   	    	if(lists!=null)
   	    	{
   	    		lists.setSTATUS(Constants.MASSAGESTATUSYD);
   	    		this.massegeInfoService.update(lists);
   	    	}
   	        map.put("result", lists);
   	        //System.out.println("----"+lists.size());
   	    } 
   		return map;
   	}
}