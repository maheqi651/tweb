 
package com.founderinternational.rscenter.controller;

import java.util.Date;
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
import com.founderinternational.rscenter.tools.impl.TimeGeneral;
import com.founderinternational.rscenter.totalchart.TotalObject;
import com.founderinternational.rscenter.totalchart.TotalTask;
 

 
@Controller
@RequestMapping(value = "fg")
public class FwControlController {
	 @Resource(name = "serviceVisitInfoService")
	 private ServiceVisitInfoService serviceVisitInfoService;
	   /*
		 * author cloudMa
		 * 服务流量和服务访问统计
		 * date  2015-07-06
		 * */
		@ResponseBody
		@RequestMapping(value="findFWControlService")  
		public Map<String, Object>   findFWControlService(HttpServletResponse response, 
				String REQEUST_DATETIME_start,String REQEUST_DATETIME_end ) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			if(REQEUST_DATETIME_start!=null&&REQEUST_DATETIME_end!=null)
		    {
				System.out.println(REQEUST_DATETIME_start);
				String start=REQEUST_DATETIME_start;
				String end=REQEUST_DATETIME_end;
				System.out.println(start);
				System.out.println(end);
				//访问流量
				Date startdate=TimeGeneral.getInstance().nextStringToDate(start);
				Date enddate=TimeGeneral.getInstance().nextStringToDate(end);
						//
				int counttime=TimeGeneral.getInstance().nextInterval(startdate, enddate);
						//(int)(enddate.getTime()-startdate.getTime())/1000/60/60/24;
				counttime=Math.abs(counttime);
				System.out.println("相差"+counttime+"天");	
				String daytimes[]=new String[counttime];
				int servicecount[]=new int[counttime];
				int datacount[]=new int[counttime];
				for(int i=counttime;i>0;i--)
				{     
					  String starttemp=TimeGeneral.getInstance().next(enddate, i);
					  String endtemp=TimeGeneral.getInstance().next(enddate, i-1);
					  daytimes[counttime-i]=starttemp;
					  System.out.println("starttime"+starttemp+"------endtime"+endtemp);
					  servicecount[counttime-i]=this.serviceVisitInfoService.getGradeServiceBytime(starttemp.replaceAll("-", "").replace(" ", "").replace(":", ""), endtemp.replaceAll("-", "").replace(" ", "").replace(":", ""));
					  datacount[counttime-i]=this.serviceVisitInfoService.getdatatotalbytime(starttemp.replaceAll("-", "").replace(" ", "").replace(":", ""), endtemp.replaceAll("-", "").replace(" ", "").replace(":", ""));
				}
				map.put("datacount", datacount);
				map.put("servicecount",servicecount);
				map.put("daytimes", daytimes);
		    }
			//访问次数
			return  map;
		}
		
		 /*
		 * author cloudMa
		 * 服务成功率统计
		 * date  2015-07-06
		 * */
		@ResponseBody
		@RequestMapping(value="searchSuccessRate")  
		public Map<String, Object>   searchSuccessRate(HttpServletResponse response, 
				String REQEUST_DATETIME_start,String REQEUST_DATETIME_end ) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			if(REQEUST_DATETIME_start!=null&&REQEUST_DATETIME_end!=null)
		    {
				System.out.println(REQEUST_DATETIME_start);
				String start=REQEUST_DATETIME_start;
				String end=REQEUST_DATETIME_end;
				System.out.println(start);
				System.out.println(end);
				//访问流量
				Date startdate=TimeGeneral.getInstance().nextStringToDate(start);
				Date enddate=TimeGeneral.getInstance().nextStringToDate(end);
				int counttime=TimeGeneral.getInstance().nextInterval(startdate, enddate);
						//(int)(enddate.getTime()-startdate.getTime())/1000/60/60/24;
				counttime=Math.abs(counttime);
				System.out.println("相差"+counttime+"天");	
				String daytimes[]=new String[counttime];
				int requestcount[]=new int[counttime];
				Double successrate[]=new Double[counttime];
				for(int i=counttime;i>0;i--)
				{     
					  String starttemp=TimeGeneral.getInstance().next(enddate, i);
					  String endtemp=TimeGeneral.getInstance().next(enddate, i-1);
					  daytimes[counttime-i]=starttemp;
					  System.out.println("starttime"+starttemp+"------endtime"+endtemp);
					  int allrequest= this.serviceVisitInfoService.getGradeServiceBytime("all",starttemp.replaceAll("-", "").replace(" ", "").replace(":", ""), endtemp.replaceAll("-", "").replace(" ", "").replace(":", ""));
					  int errorrequest= this.serviceVisitInfoService.getGradeServiceBytime(null,starttemp.replaceAll("-", "").replace(" ", "").replace(":", ""), endtemp.replaceAll("-", "").replace(" ", "").replace(":", ""));
					  requestcount[counttime-i]=allrequest;
					  if(allrequest==0)
					  {
						  successrate[counttime-i]=0.0;
					  }else{
						  successrate[counttime-i]=(allrequest-errorrequest)*100.0/allrequest;
					  }
				}
				
				map.put("requestcount", requestcount);
				map.put("successrate",successrate);
				map.put("daytimes", daytimes);
		    }
			//访问次数
			return  map;
		}
		 /*
		 * author cloudMa
		 * 服务应用访问统计
		 * date  2015-07-06
		 * */
		@ResponseBody
		@RequestMapping(value="searchFunctionTotal")  
		public Map<String, Object>   searchFunctionTotal(HttpServletResponse response,String functioncode, 
				String REQEUST_DATETIME_start,String REQEUST_DATETIME_end ) throws Exception {
				Map<String, Object> map = new HashMap<String, Object>();
				if(REQEUST_DATETIME_start!=null&&REQEUST_DATETIME_end!=null)
			    {
					System.out.println(REQEUST_DATETIME_start);
					String start=REQEUST_DATETIME_start;
					String end=REQEUST_DATETIME_end;
					System.out.println(start);
					System.out.println(end);
					//访问流量
					Date startdate=TimeGeneral.getInstance().nextStringToDate(start);
					Date enddate=TimeGeneral.getInstance().nextStringToDate(end);
							//
					int counttime=TimeGeneral.getInstance().nextInterval(startdate, enddate);
							//(int)(enddate.getTime()-startdate.getTime())/1000/60/60/24;
					counttime=Math.abs(counttime);
					System.out.println("相差"+counttime+"天");	
					String daytimes[]=new String[counttime];
					int servicecount[]=new int[counttime];
					int datacount[]=new int[counttime];
					for(int i=counttime;i>0;i--)
					{     
						  String starttemp=TimeGeneral.getInstance().next(enddate, i);
						  String endtemp=TimeGeneral.getInstance().next(enddate, i-1);
						  daytimes[counttime-i]=starttemp;
						  System.out.println("starttime"+starttemp+"------endtime"+endtemp);
						  servicecount[counttime-i]=this.serviceVisitInfoService.getGradeServiceBytimeByFunction(functioncode,starttemp.replaceAll("-", "").replace(" ", "").replace(":", ""), endtemp.replaceAll("-", "").replace(" ", "").replace(":", ""));
						  datacount[counttime-i]=this.serviceVisitInfoService.getdatatotalbytimeByFunction(functioncode,starttemp.replaceAll("-", "").replace(" ", "").replace(":", ""), endtemp.replaceAll("-", "").replace(" ", "").replace(":", ""));
					}
					map.put("datacount", datacount);
					map.put("servicecount",servicecount);
					map.put("daytimes", daytimes);
			    }
				//访问次数
				return  map;
		}
		//functioncode
		
	 
	 
}