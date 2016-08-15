 
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

import com.founderinternational.rscenter.datasource.DataSourceInstances;
import com.founderinternational.rscenter.datasource.DataSourceSwitch;
import com.founderinternational.rscenter.entity.EZDataTree;
import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceApplyInfo;
import com.founderinternational.rscenter.entity.EZServiceMTODef;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.ServiceVisitInfo;
import com.founderinternational.rscenter.entity.form.EZFunctionVO;
import com.founderinternational.rscenter.service.EZDataTreeService;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.EZServiceApplyService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.service.ServiceVisitInfoService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.PrintGenerator;
import com.founderinternational.rscenter.tools.Tools;
 

 
@Controller
@RequestMapping(value = "fg")
public class ServiceVisitInfoController {
	 @Resource(name = "serviceVisitInfoService")
	 private ServiceVisitInfoService serviceVisitInfoService;
	   /*
		 * author cloudMa
		 * ·þÎñÉó¼Æ
		 * date  2015-07-06
		 * */
		@ResponseBody
		@RequestMapping(value="findserviceVisitInfo")  
		public Map<String, Object>   findservice(HttpServletResponse response,
				String APP_NAME,String SENDER_ID,String SERVICE_NAME,String CONSUMER_IP,String REQEUST_DATETIME_start,String REQEUST_DATETIME_end,String page) throws Exception {
		    long times =System.currentTimeMillis();
			int startpage=1;
			int startcount=0;
			int endcount=0;
			List<ServiceVisitInfo> result=null;
			int pagenum=0;
			int pagecount =this.serviceVisitInfoService.getcount(SERVICE_NAME,APP_NAME, SENDER_ID, CONSUMER_IP, REQEUST_DATETIME_start, REQEUST_DATETIME_end);
	 		pagenum = pagecount%Constants.pagesize==0?0:1;
			pagenum = pagecount/Constants.pagesize+pagenum;
	    	if(pagenum==0) pagenum=1;
			if(page!=null)
				startpage=Integer.parseInt(page);
			startcount=(startpage-1)*Constants.pagesize;
			endcount=startpage*Constants.pagesize+1;
			if(REQEUST_DATETIME_start!=null)
				REQEUST_DATETIME_start=REQEUST_DATETIME_start.replace("-", "");
			if(REQEUST_DATETIME_end!=null)
				REQEUST_DATETIME_end=REQEUST_DATETIME_end.replace("-", "");
			
			result=this.serviceVisitInfoService.findFromOracle(SERVICE_NAME,APP_NAME, SENDER_ID, CONSUMER_IP, REQEUST_DATETIME_start, REQEUST_DATETIME_end, startcount, endcount);
			response.setCharacterEncoding("utf-8");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result",result);
			map.put("pagenum", pagenum);
			map.put("totalcount", pagecount);
			System.out.println(System.currentTimeMillis()-times);
			return  map;
		}
		
	 
	 
	 
}