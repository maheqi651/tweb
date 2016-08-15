
package com.founderinternational.rscenter.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceApply;
import com.founderinternational.rscenter.entity.EZServiceMTODef;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.EZServiceMditemDEF;
import com.founderinternational.rscenter.entity.EZServiceMditemDEFVO;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.EZFunctionVO;
import com.founderinternational.rscenter.entity.form.UserVO;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.EZServiceApplyService;
import com.founderinternational.rscenter.service.EZServiceService;
import com.founderinternational.rscenter.service.FwSqTableService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.PrintGenerator;
import com.founderinternational.rscenter.tools.Tools;
import com.founderinternational.rscenter.tools.impl.TimeGeneral;



@Controller
@RequestMapping(value = "login")
public class EZServiceAuthController {
	 
	@Resource(name = "eZServiceService")
	private EZServiceService eZServiceService;
	@Resource(name = "eZServiceApplyService")
	private EZServiceApplyService eZServiceApplyService;
	
	   @ResponseBody
	   @RequestMapping(value = "authservice")  
	    public Map<String, Object>  authservice(String aid) {  
		    Map<String, Object> map = new HashMap<String, Object>();
	        System.out.println("开始"); 
	        List<EZServiceApply>  list=this.eZServiceApplyService.findEZServiceApplyList(aid);
	        if(list!=null)
	        {
	        	if(list.size()>0)
	        	{  //通过验证
	        	     String serviceid=list.get(0).getSERCODE();
	        	     if(serviceid!=null&&!"".equals(serviceid))
	        	     {
	        	    	 //SMD_BASE_COMPANY
	        	    	 List<EZServiceMditem> listm=this.eZServiceService.findEZServiceMditem(serviceid);
	        	    	 if(listm!=null)
	        	    	 {
	        	    		 for(EZServiceMditem em:listm)
	        	    		 {
	        	    			if(Constants.SMD_BASE_COMPANY.equals(em.getMD_CODE())) //取链接
	        	    			{
	        	    				String result=em.getMD_VALUE();
	        	    				if(result!=null&&!"".equals(result))
	        	    				{
	        	    					int index=result.indexOf("=");
	        	    					if(index>0)
	        	    					result=result.substring(0, index+1);
	        	    					map.put("result",result);
		        	    				return map;
	        	    				}
	        	    				
	        	    			}
	        	    		 }
	        	    	 }
	        	     }
	        	}
	        }
	        map.put("result", "faild");
	        return map;
	    }  
	   public static void main(String[] args)
	   {
		    String result="http://10.235.36.2:8080/EzERecord/?uuid=440112195811010639";
		    int index=result.indexOf("=");
			if(index>0)
			result=result.substring(0, index+1);
			System.out.println(result);
	   }

}