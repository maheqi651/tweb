 
package com.founderinternational.rscenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.founderinternational.rscenter.entity.EZDataTree;
import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZServiceApplyInfo;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.EZFunctionVO;
import com.founderinternational.rscenter.service.EZDataTreeService;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.EZServiceApplyService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.PrintGenerator;
import com.founderinternational.rscenter.tools.Tools;
 

 
@Controller
@RequestMapping(value = "fg")
public class EZDataTreeController {
     public static Object key=new Object();
	 @Resource(name = "eZDataTreeService")
	 private EZDataTreeService eZDataTreeService;
	 
	 @Resource(name = "eZServiceApplyService")
	 private EZServiceApplyService eZServiceApplyService;
	 @ResponseBody
	 @RequestMapping(value="findDataTree")  
	 public Map<String, Object>   findByuser(HttpServletResponse response,HttpServletRequest request,String user) throws Exception {
		 response.setCharacterEncoding("utf-8");
		 Map<String, Object> map = new HashMap<String, Object>();
		 //long times=System.currentTimeMillis();
		 long times=System.currentTimeMillis();
	     JSONArray JList=new JSONArray();
	     if(request.getSession().getAttribute(Constants.TREEFLAG)!=null)
	     {
	    	 JList=(JSONArray)request.getSession().getAttribute(Constants.TREEFLAG);
	     }else{
	    	 
	    	 List<EZDataTree> result = this.eZDataTreeService.find(Tools.CovertToUTF8(user));
			 JSONObject jroot=new JSONObject();
			 jroot.put("id", "0");
			 jroot.put("pId", "");
			 jroot.put("open", "true");
			 jroot.put("name", "数据资源");
			 jroot.put("isParent", "true");
			 JList.add(jroot);
			 for(EZDataTree et:result)
			 {
				 JSONObject jobj=new JSONObject();
				 jobj.put("id",et.getNODE());
				 if("0".equals(et.getLLEVEL())||"1".equals(et.getLLEVEL())||"2".equals(et.getLLEVEL()))
				 {

					 if("".equals(et.getPARENT())||et.getPARENT()==null||"null".equals(et.getPARENT()))
					 {
						 jobj.put("pId","0");
						 jobj.put("open", "true");
					 }else{
						 jobj.put("pId",et.getPARENT());
						 jobj.put("open", "false");
					 }
				 }else{
					 jobj.put("pId",et.getPARENT());
					 jobj.put("open", "false");
				 }
				 if(Constants.ISPARENTS.equals(et.getTABLENAME())||et.getTABLENAME()==null)
				 {
					 jobj.put("isParent", "true");
				 }else{
					 jobj.put("isParent", "false");
					 jobj.put("tablecode", et.getNODE());
					 jobj.put("themecode", et.getTHEMECODE());
				 }
				 jobj.put("name", et.getALIASNAME());
				 JList.add(jobj);
			 }
			 request.getSession().setMaxInactiveInterval(60*1000*20);
			 request.getSession().setAttribute(Constants.TREEFLAG,JList);
	    	 
	     }
	     System.out.println("times:"+(System.currentTimeMillis()-times));
	     map.put("result", JList);
		
		 return  map;
	}
	 
	 @ResponseBody
	 @RequestMapping(value="findByuserandchoose")  
	 public Map<String, Object>   findByuserandchoose(HttpServletResponse response,String user,String aid) throws Exception {
		 response.setCharacterEncoding("utf-8");
		 Map<String,Integer> fastMap=new HashMap<String,Integer>();
		 long times=System.currentTimeMillis();
		 if(aid!=null)
		 {
			 List<EZServiceApplyInfo> ailists=this.eZServiceApplyService.findEZServiceApplyInfo(aid);
			 for(EZServiceApplyInfo ezinfo:ailists)
			 {
				 fastMap.put(ezinfo.getTHEMECODE()+"@"+ezinfo.getTABLECODE(),1);
				// System.out.println(ezinfo.getTHEMECODE()+"@"+ezinfo.getTABLECODE());
			 }
		 }
		 
		 Map<String, Object> map = new HashMap<String, Object>();
	     JSONArray JList=new JSONArray();
	     
	    	 List<EZDataTree> result = this.eZDataTreeService.find(Tools.CovertToUTF8(user));
			 JSONObject jroot=new JSONObject();
			 jroot.put("id", "0");
			 jroot.put("pId", "");
			 jroot.put("open", "true");
			 jroot.put("name", "数据资源");
			 jroot.put("isParent", "true");
			 JList.add(jroot);
			 for(EZDataTree et:result)
			 {
				 JSONObject jobj=new JSONObject();
				 jobj.put("id",et.getNODE());
				 if("0".equals(et.getLLEVEL())||"1".equals(et.getLLEVEL())||"2".equals(et.getLLEVEL()))
				 {

					 if("".equals(et.getPARENT())||et.getPARENT()==null||"null".equals(et.getPARENT()))
					 {
						 jobj.put("pId","0");
						 jobj.put("open", "true");
					 }else{
						 jobj.put("pId",et.getPARENT());
						 jobj.put("open", "false");
					 }
				 }else{
					 jobj.put("pId",et.getPARENT());
					 jobj.put("open", "false");
				 }
				 if(Constants.ISPARENTS.equals(et.getTABLENAME())||et.getTABLENAME()==null)
				 {
					 jobj.put("isParent", "true");
				 }else{
					 jobj.put("isParent", "false");
					 jobj.put("tablecode", et.getNODE());
					 jobj.put("themecode", et.getTHEMECODE());
				 }
				 //System.out.println("Pp------PP:"+et.getTHEMECODE()+"@"+et.getNODE());
				 if(fastMap.get(et.getTHEMECODE()+"@"+et.getNODE())!=null)
				 {
					 
						 jobj.put("open", true);
						 jobj.put("checked", true);
					 
				 }
				 
				 jobj.put("name", et.getALIASNAME());
				 JList.add(jobj);
			 }
			 
	     
	      map.put("result", JList);
		  System.out.println("用时2："+(System.currentTimeMillis()-times));
		 return  map;
	}
	 
	 
}