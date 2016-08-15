
package com.founderinternational.rscenter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZFunctionC;
import com.founderinternational.rscenter.entity.EZPFunctionService;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceApply;
import com.founderinternational.rscenter.entity.EZServiceApplyInfo;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.EZServiceMditemDEF;
import com.founderinternational.rscenter.entity.EZServiceMditemDEFVO;
import com.founderinternational.rscenter.entity.FwSqTable;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.EZFunctionVO;
import com.founderinternational.rscenter.entity.form.UserVO;
import com.founderinternational.rscenter.service.EZFunctionCService;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.EZServiceApplyService;
import com.founderinternational.rscenter.service.EZServiceService;
import com.founderinternational.rscenter.service.FwSqTableService;
import com.founderinternational.rscenter.service.MassegeInfoService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.PrintGenerator;
import com.founderinternational.rscenter.tools.Tools;
import com.founderinternational.rscenter.tools.impl.TimeGeneral;
import com.founderinternational.rscenter.totalchart.DayPojo;
import com.founderinternational.rscenter.totalchart.ListGeneral;
import com.founderinternational.rscenter.totalchart.TotalObject;
import com.founderinternational.rscenter.totalchart.TotalTask;
import  com.founderinternational.rscenter.entity.form.*;

@Controller
@RequestMapping(value = "fg")
public class TotalChartController {
	@Resource(name = "eZServiceService")
	private EZServiceService eZServiceService;

	@Resource(name = "eZFunctionService")
	private EZFunctionService eZFunctionService;
    private static final String LASTWEEKDATA="lastweekdata";
    private static final String LASTWEEKFUNCTION="lastweekfunction";
    private static final String LASTWEEKSERVICE="lastweekservice";
    private static final String lASTWEEKAPPLYTOTAL="lastweekapplytotal";
    private static final String POORSERVICE="poorservice";
    private static final String HIGHSERVICE="highservice";
    private static final String LASTFUNCTIONTOTALRANK="lastfunctiontotalrank";
    private static Map<String,String> serviceMd2Name=null ;
    private static Map<String,String> funciotnMd2Name=null ;
    private static final Object keyobs=new Object();
	@Resource(name = "totalTask")
	private TotalTask totalTask;
	  
	/*
	 * author cloudMa
	 * 图表统计
	 * date  2015-8-13
	 * */
   	@ResponseBody
	@RequestMapping(value="findTotalChartData")  
	public  Map<String, Object>   findTotalChartData(HttpServletResponse response,HttpServletRequest request) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getApplyrank()==null)
		{
			this.totalTask.run();
			Thread.sleep(5000l);
		}
		map.put(this.lASTWEEKAPPLYTOTAL, ListGeneral.getApplyrank());
		map.put(this.LASTWEEKDATA, ListGeneral.getLastweekdatatotal());
		map.put(this.LASTWEEKFUNCTION, ListGeneral.getLastfunctiontotalrank());
		/*map.put(this.LASTWEEKSERVICE, ListGeneral.getLastservicetotalrank());*/
		map.put(this.HIGHSERVICE, ListGeneral.getHighgrade());
		map.put(this.POORSERVICE, ListGeneral.getPoorgrade());
		
		initMAP();
		if(ListGeneral.getLastservicetotalrank()==null)
		{
			this.totalTask.run();
			Thread.sleep(5000l);
		}
		List<TotalObject> totallist=ListGeneral.getLastservicetotalrank();
		List<TotalObject> totallistnew =new ArrayList<TotalObject>();
		int count =0;
		if(totallist!=null)
		{
			for(TotalObject totalob:totallist)
			{
				if(serviceMd2Name.get(totalob.getServiceName())!=null||totallist.size()<=10)
				{
					TotalObject totalobnew=new TotalObject();
					if(serviceMd2Name.get(totalob.getServiceName())!=null)
					    totalobnew.setServiceName(serviceMd2Name.get(totalob.getServiceName()));
					else
						totalobnew.setServiceName(totalob.getServiceName());
					//totalobnew.setServiceName(serviceMd2Name.get(totalob.getServiceName()));
					totalobnew.setServiceCount(totalob.getServiceCount());
					System.out.println(totalobnew.getServiceName());
					totallistnew.add(totalobnew);
					count++;
					if(count>=10)
						break;
				}
			}
		}
		map.put(this.LASTWEEKSERVICE,totallistnew);
		return  map;
	}
	public void initMAP(){
		
		synchronized (keyobs) {
   			if(serviceMd2Name==null)
   			{//处理
   				serviceMd2Name=new HashMap<String,String>();
   				List<EZService> lists=this.eZServiceService.findNoGroup();
   				if(lists!=null)
   				{
   					for(EZService ezs:lists)
   					{
   						serviceMd2Name.put(ezs.getMETHODNAME(),ezs.getNAME());
   					}
   				}
   			}
		}
		
		
		synchronized (keyobs) {
   			if(funciotnMd2Name==null)
   			{//处理
   				funciotnMd2Name=new HashMap<String,String>();
   				List<EZFunction> functionlists=this.eZFunctionService.findAllFunction();
   				if(functionlists!=null)
   				{
   					for(EZFunction ezs:functionlists)
   					{
   						funciotnMd2Name.put(ezs.getCODE(),ezs.getNAME());
   					}
   				}
   			}
		}
	}
   	/*
	 * author cloudMa
	 * 图表统计   laskweekservice
	 * date  2015-8-13
	 * */
   	@ResponseBody
	@RequestMapping(value="lastWeekService")  
	public  Map<String, Object>   lastWeekService(HttpServletResponse response,HttpServletRequest request) throws Exception 
	{
   		initMAP();
   		 
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getLastservicetotalrank()==null)
		{
			this.totalTask.run();
			Thread.sleep(5000l);
		}
		List<TotalObject> totallist=ListGeneral.getLastservicetotalrank();
		List<TotalObject> totallistnew =new ArrayList<TotalObject>();
		int count =0;
		if(totallist!=null)
		{
			for(TotalObject totalob:totallist)
			{
				if(serviceMd2Name.get(totalob.getServiceName())!=null||totallist.size()<=10)
				{
					TotalObject totalobnew=new TotalObject();
					if(serviceMd2Name.get(totalob.getServiceName())!=null)
					    totalobnew.setServiceName(serviceMd2Name.get(totalob.getServiceName()));
					else
						totalobnew.setServiceName(totalob.getServiceName());
					//totalobnew.setServiceName(serviceMd2Name.get(totalob.getServiceName()));
					totalobnew.setServiceCount(totalob.getServiceCount());
					System.out.println(totalobnew.getServiceName());
					totallistnew.add(totalobnew);
					count++;
					if(count>=10)
						break;
				}
			}
		}
		map.put(this.LASTWEEKSERVICE,totallistnew);
		return  map;
	}
   	
   	
   	/*
	 * author cloudMa
	 * 给门户体供图标统计
	 * date  2015-8-13
	 * 
	 * */
   	@ResponseBody
	@RequestMapping(value="findTotalChartDataForPortal")  
	public  Map<String, Object>   findTotalChartDataForPortal(HttpServletResponse response,HttpServletRequest request) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getLastfunctiontotalrank()==null)
		{
			this.totalTask.run();
			Thread.sleep(5000l);
		}
		initMAP();
		//lastfunctiontotalrank
		List<TotalObject> functionlist=ListGeneral.getLastfunctiontotalrank();
		List<TotalObject> functionListCopy=new ArrayList<TotalObject>();
		int countNum=0;
		 
		if(functionlist!=null)
		{
			for(TotalObject to:functionlist)
			{  
				 
				if(countNum>10)
				   break;
				if(funciotnMd2Name.get(to.getFunctionName())!=null)
				{
					countNum++;
					TotalObject totalobnew=new TotalObject();
					totalobnew.setFunctionName(funciotnMd2Name.get(to.getFunctionName()));
					totalobnew.setFunctionCount(to.getFunctionCount());
					functionListCopy.add(totalobnew);
				}
			}
		}
		map.put(this.LASTFUNCTIONTOTALRANK, functionListCopy);
		map.put(this.LASTWEEKDATA, ListGeneral.getLastweekdatatotal());
		/*map.put(this.LASTWEEKSERVICE, ListGeneral.getLastservicetotalrank());*/
		
		
		 
		if(ListGeneral.getLastservicetotalrank()==null)
		{
			this.totalTask.run();
			Thread.sleep(5000l);
		}
		List<TotalObject> totallist=ListGeneral.getLastservicetotalrank();
		List<TotalObject> totallistnew =new ArrayList<TotalObject>();
		int count =0;
		if(totallist!=null)
		{
			for(TotalObject totalob:totallist)
			{
				if(serviceMd2Name.get(totalob.getServiceName())!=null||totallist.size()<=10)
				{
					TotalObject totalobnew=new TotalObject();
					if(serviceMd2Name.get(totalob.getServiceName())!=null)
					    totalobnew.setServiceName(serviceMd2Name.get(totalob.getServiceName()));
					else
						totalobnew.setServiceName(totalob.getServiceName());
					//totalobnew.setServiceName(serviceMd2Name.get(totalob.getServiceName()));
					totalobnew.setServiceCount(totalob.getServiceCount());
					System.out.println(totalobnew.getServiceName());
					totallistnew.add(totalobnew);
					count++;
					if(count>=10)
						break;
				}
			}
		}
		map.put(this.LASTWEEKSERVICE,totallistnew);
		return  map;
	}
   	
   	
	/*
	 * author cloudMa
	 * 服务统计排行
	 * date  2015-8-14
	 * */
   
	@ResponseBody
	@RequestMapping(value="findServiceTotalChart")  
	public  Map<String, Object>   findServiceTotalChart(HttpServletResponse response,HttpServletRequest request) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getLastservicetotalrank()==null)
		{
			this.totalTask.run();
			Thread.sleep(1000l);
		}
	 
		initMAP();
		List<TotalObject> totallist=ListGeneral.getLastservicetotalrank();
		List<TotalObject> totallistnew =new ArrayList<TotalObject>();
		int count =0;
		if(totallist!=null)
		{
			for(TotalObject totalob:totallist)
			{
				if(serviceMd2Name.get(totalob.getServiceName())!=null||totallist.size()<=10)
				{
					TotalObject totalobnew=new TotalObject();
					if(serviceMd2Name.get(totalob.getServiceName())!=null)
					    totalobnew.setServiceName(serviceMd2Name.get(totalob.getServiceName()));
					else
						totalobnew.setServiceName(totalob.getServiceName());
					totalobnew.setServiceCount(totalob.getServiceCount());
					System.out.println(totalobnew.getServiceName());
					totallistnew.add(totalobnew);
					count++;
					if(count>=10)
						break;
				}
			}
		}
		
		
		
		String serviceName[]=null;
		int servicecount[] = null;
		List<DayPojo> listpojo=null;
		if(totallistnew!=null)
		{   servicecount=new int[totallistnew.size()];
		    serviceName=new String[totallistnew.size()];
			listpojo=new ArrayList<DayPojo>();
			for(int i=0;i<totallistnew.size();i++)
			{
				if(totallistnew.get(i).getServiceName()!=null){
					serviceName[i]=totallistnew.get(i).getServiceName();
				    if(totallistnew.get(i).getServiceCount()!=null)
				    	servicecount[i]=totallistnew.get(i).getServiceCount();
				    else
				    	servicecount[i]=0;
				    	 
				    DayPojo dp=new DayPojo();
				    dp.setName(serviceName[i]);
				    dp.setValue(servicecount[i]);
				    listpojo.add(i, dp);
				}
			}
		}
		map.put("serviceName", serviceName);
		map.put("serviceCount", servicecount);
		map.put("lastweekservice", listpojo);
		return  map;
	}
	
	
	
	
	/*
	 * author cloudMa
	 * 应用统计排行
	 * date  2015-8-14
	 * */
	@ResponseBody
	@RequestMapping(value="findFunctionTotalChart")  
	public  Map<String, Object>   findFunctionTotalChart(HttpServletResponse response) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getLastfunctiontotalrank()==null)
		{
			this.totalTask.run();
			Thread.sleep(1000l);
		}
		initMAP();
		List<TotalObject> listto=ListGeneral.getLastfunctiontotalrank();
		String functionName[]=null;
		int functionCount[] = null;
		//List<String> functionNameRecord=new ArrayList<String>();
		//List<Integer> functionCountRecord=new ArrayList<Integer>();
		List<DayPojo> listpojo=null;
		if(listto!=null)
		{   functionCount=new int[listto.size()];
		    functionName=new String[listto.size()];
			listpojo=new ArrayList<DayPojo>();
			int count=0;
			for(int i=0;i<listto.size();i++)
			{    
				if(count>=10)
					break;
				if(funciotnMd2Name.get(listto.get(i).getFunctionName())!=null)
				{   
					count++;
					//functionNameRecord.add(listto.get(i).getFunctionName());
					 
					functionName[count-1]=funciotnMd2Name.get(listto.get(i).getFunctionName());
				    if(listto.get(i).getFunctionCount()!=null)
				    	functionCount[count-1]=listto.get(i).getFunctionCount();
				    else
				    	functionCount[count-1]=0;
				    DayPojo dp=new DayPojo();
				    dp.setName(functionName[count-1]);
				    dp.setValue(functionCount[count-1]);
				    listpojo.add(count-1, dp);
				}
			}
		}
		functionName=new String[listpojo.size()];
		for(int i=0;i<listpojo.size();i++){
			 functionName[i]=listpojo.get(i).getName();
		}
		map.put("functionName", functionName);
		map.put("lastweekfunction", listpojo);
		return  map;
	}
	
	
	/*
	 * author cloudMa
	 * 申请统计排行
	 * date  2015-8-14
	 * */
   
	@ResponseBody
	@RequestMapping(value="findApplyTotalChart")  
	public  Map<String, Object>   findApplyTotalChart(HttpServletResponse response) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getApplyrank()==null)
		{
			this.totalTask.run();
			Thread.sleep(1000l);
		}
		List<TotalObject> listto=ListGeneral.getApplyrank();
		String serviceName[]=null;
		int servicecount[] = null;
		List<DayPojo> listpojo=null;
		if(listto!=null)
		{   servicecount=new int[listto.size()];
		    serviceName=new String[listto.size()];
			listpojo=new ArrayList<DayPojo>();
			for(int i=0;i<listto.size();i++)
			{
				serviceName[i]=listto.get(i).getServiceName();
			    if(listto.get(i).getServiceCount()!=null)
			    	servicecount[i]=listto.get(i).getServiceCount();
			    else
			    	servicecount[i]=0;
			    DayPojo dp=new DayPojo();
			    dp.setName(serviceName[i]);
			    dp.setValue(servicecount[i]);
			    listpojo.add(i, dp);
			}
			
		}
		map.put("serviceName", serviceName);
		map.put("lastweekapplytotal", listpojo);
		return  map;
	}
	
	
	
	/*
	 * author cloudMa
	 * 优质劣质统计排行
	 * date  2015-8-14
	 * */
   
	@ResponseBody
	@RequestMapping(value="findHighPoorTotalChart")  
	public  Map<String, Object>   findHighPoorTotalChart(HttpServletResponse response) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getPoorgrade()==null)
		{
			this.totalTask.PoorAndHighService();
			Thread.sleep(3000l);
		}
		String poorserviceName[] = null;
		int poorserviceCount[]=null;
		String highserviceName[] = null;
		int highserviceCount[]=null;
		List<TotalObject> poorlist=ListGeneral.getPoorgrade();
		List<TotalObject> highlist=ListGeneral.getHighgrade();
		
		
		int count1=0;
		if(poorlist!=null)
		{
			poorserviceCount=new int[poorlist.size()];
			poorserviceName=new String[poorlist.size()];
			for(TotalObject totalob:poorlist)
			{
				if(serviceMd2Name.get(totalob.getServiceName())!=null)
				{
					
					 System.out.println("highserviceName:"+totalob.getServiceName()+"---"+serviceMd2Name.get(totalob.getServiceName()));
					 poorserviceName[count1]=serviceMd2Name.get(totalob.getServiceName());
					 if(totalob.getServiceCount()!=null)
						    poorserviceCount[count1]=totalob.getServiceCount();
					    else
					    	poorserviceCount[count1]=0;
					count1++;
					if(count1>=10)
						break;
				}
			}
		}
		//处理优质服务
		/*if(highlist!=null)
		{
			highserviceCount=new int[highlist.size()];
			highserviceName=new String[highlist.size()];
			for(int i=0;i<highlist.size();i++)
			{
				highserviceName[i]=highlist.get(i).getServiceName();
			    if(highlist.get(i).getServiceCount()!=null)
			    	highserviceCount[i]=highlist.get(i).getServiceCount();
			    else
			    	highserviceCount[i]=0;
			}
		}*/
		
		int count=0;
		 
		if(highlist!=null)
		{
			highserviceCount=new int[highlist.size()];
			highserviceName=new String[highlist.size()];
			for(TotalObject totalob:highlist)
			{
				if(serviceMd2Name.get(totalob.getServiceName())!=null||highlist.size()<=10)
				{
					 
					  if(serviceMd2Name.get(totalob.getServiceName())!=null&&!"".equals(serviceMd2Name.get(totalob.getServiceName())))
					   highserviceName[count]=serviceMd2Name.get(totalob.getServiceName());
					  else
						  highserviceName[count]=totalob.getServiceName();
					 if(totalob.getServiceCount()!=null)
					    	highserviceCount[count]=totalob.getServiceCount();
					    else
					    	highserviceCount[count]=0;
					 
					count++;
					if(count>=10)
						break;
				}
			}
		}
		
		if(count>=10)
		{
			count=10;
		}
		
		if(count1>=10)
		{
			count1=10;
		}
		String poorserviceNamenew[] = null;
		int poorserviceCountnew[]=null;
		String highserviceNamenew[] = null;
		int highserviceCountnew[]=null;
		
		highserviceNamenew=new String[count];
		highserviceCountnew=new int[10];
		for(int i=0;i<count;i++)
		{
			highserviceNamenew[i]=highserviceName[i];
			highserviceCountnew[i]=highserviceCount[i];
		}
		
		  poorserviceNamenew  = new String[count1];
		  poorserviceCountnew =new int[count1];
		  
		  for(int i=0;i<count1;i++)
			{
			  poorserviceNamenew[i]=poorserviceName[i];
			  poorserviceCountnew[i]=poorserviceCount[i];
			}
		map.put("poorserviceName", poorserviceNamenew);
		map.put("poorserviceCount", poorserviceCountnew);
		map.put("highserviceName", highserviceNamenew);
		map.put("highserviceCount", highserviceCountnew);
		return  map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="findLastWeekData")  
	public  Map<String, Object>   findLastWeekData(HttpServletResponse response) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getLastweekdatatotal()==null)
		{
			this.totalTask.run();
			Thread.sleep(1000l);
		}
		List<TotalObject> listto=ListGeneral.getLastweekdatatotal();
		String weekName[]=null;
		int datacount[] = null;
		List<DayPojo> listpojo=null;
		if(listto!=null)
		{   datacount=new int[listto.size()];
			weekName=new String[listto.size()];
			listpojo=new ArrayList<DayPojo>();
			for(int i=0;i<listto.size();i++)
			{
				weekName[i]=listto.get(i).getWeekName();
			    if(listto.get(i).getWeekCount()!=null)
			    	datacount[i]=listto.get(i).getWeekCount();
			    else
			    	 datacount[i]=0;
			    	
			    	 
			    DayPojo dp=new DayPojo();
			    dp.setName(weekName[i]);
			    dp.setValue(datacount[i]);
			    listpojo.add(i, dp);
			    
			}
			
		}
		map.put("weekName", weekName);
		map.put("dataCount", datacount);
		map.put("lastweekdata", listpojo);
		return  map;
	}
	
	
	/*
	 * author cloudMa
	 * 实时统计按照分钟
	 * date  2015-8-13
	 * */
   
	@ResponseBody
	@RequestMapping(value="findRealTimeTotalChartDataInit")  
	public  Map<String, Object>   findRealTimeTotalChartDataInit(HttpServletResponse response) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getRealMuData()==null)
		{
			this.totalTask.runbymu();;
			Thread.sleep(3000l);
		} 
		List<TotalObject> list1=null;
		int data1[]=null;
		if(ListGeneral.getRealMuData()!=null)
		{
			list1=ListGeneral.getRealMuData();
			data1=new int[list1.size()];
			for(int i=0;i<list1.size();i++)
			{
				data1[i]=list1.get(i).getTotalhour();
			}
		}
		
		List<TotalObject> list2=null;
		int data2[]=null;
		if(ListGeneral.getRealMuServiceData()!=null)
		{
			list2=ListGeneral.getRealMuServiceData();
			data2=new int[list2.size()];
			for(int i=0;i<list2.size();i++)
			{
				data2[i]=list2.get(i).getTotalhour();
			}
		}
		map.put("realdatatotal", data1);
		map.put("realservicetotal", data2);
		return  map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="findRealTimeTotalChartDatabys")  
	public  Map<String, Object>   findRealTimeTotalChartDatabys(HttpServletResponse response) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getRealMuData()==null)
		{
			this.totalTask.runbymu();;
			Thread.sleep(3000l);
		} 
		List<TotalObject> list1=null;
		int data1=0;
		if(ListGeneral.getRealMuData()!=null)
		{
			list1=ListGeneral.getRealMuData();
			data1=list1.get(list1.size()-1).getTotalhour();
			 
		}
		
		List<TotalObject> list2=null;
		int data2=0;
		if(ListGeneral.getRealMuServiceData()!=null)
		{
			list2=ListGeneral.getRealMuServiceData();
			data2=list2.get(list2.size()-1).getTotalhour();
		}
		 map.put("realdatatotal", data1);
		map.put("realservicetotal", data2); 
		/*map.put("realdatatotal", new Random().nextInt(100));
		map.put("realservicetotal", new Random().nextInt(100));*/
		return  map;
	}
	
	
	
	/*
	 * author cloudMa
	 * 实时统计按照小时
	 * date  2015-8-13
	 * */
   
	@ResponseBody
	@RequestMapping(value="findRealTimeTotalChartDataByhour")  
	public  Map<String, Object>   findRealTimeTotalChartDataByhour(HttpServletResponse response) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(ListGeneral.getRealHourData()==null)
		{
			this.totalTask.runbyHour();
			Thread.sleep(3000l);
		} 
		int count=0;
		if(ListGeneral.getRealHourData()!=null)
		{
			count=ListGeneral.getRealHourData().getTotalhour();
			//System.out.println(count);
					//ListGeneral.getRealHourData().getTotalhour();
		}
		map.put("realhourtimetotal", count);
		return  map;
	}
	
    
	
	
 


	 

}