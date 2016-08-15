package com.founderinternational.rscenter.totalchart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.ServiceVisitInfoService;
import com.founderinternational.rscenter.tools.impl.TimeGeneral;
@Component(value="totalTask")
public class TotalTask extends TimerTask {
	private static final int WEEKCOUNT=7;
	private static final int TODAYCOUNT=0;
	private static final int SAXCOUNT=6;
	private static final int FIVECOUNT=5;
	private static final int FOULCOUNT=4;
	private static final int THREECOUNT=3;
	private static final int TWOCOUNT=2;
	private static final int ONECOUNT=1;
	@Resource(name = "serviceVisitInfoService")
	private ServiceVisitInfoService serviceVisitInfoService;
	@Scheduled(cron="0 0 3  * * ?")
	@Override
	public synchronized void run() {
		System.out.println("进入定时器");
		Date date=new Date();
		String start=TimeGeneral.getInstance().next(date,WEEKCOUNT).replaceAll("-", "");
		String end=TimeGeneral.getInstance().next(date,TODAYCOUNT).replaceAll("-", "");
		String start1=TimeGeneral.getInstance().next(date,WEEKCOUNT).replaceAll(" ", "");
		String end2=TimeGeneral.getInstance().next(date,TODAYCOUNT).replaceAll(" ", "");
		
		/* 近一周流量统计 */
    	List<TotalObject> datalist=new ArrayList<TotalObject>(WEEKCOUNT);
		for(int i=WEEKCOUNT;i>0;i--){
			TotalObject temp=new TotalObject();
			System.out.println(serviceVisitInfoService);
			int counts=this.serviceVisitInfoService.getdatatotalbytime(TimeGeneral.getInstance().next(date,i).replaceAll("-", ""), TimeGeneral.getInstance().next(date,i-1).replaceAll("-", ""));
			temp.setWeekName(TimeGeneral.getInstance().nextWeekOfDate(TimeGeneral.getInstance().nextDate(date,i)));
		    temp.setWeekCount(counts);
		    datalist.add(temp);
		}
		ListGeneral.setLastweekdatatotal(datalist);
		//近一周数据应用排行榜
		List<TotalObject> functionRankList=this.serviceVisitInfoService.getFunctionRank(start, end);
		ListGeneral.setLastfunctiontotalrank(functionRankList);
		//近一周服务排行榜
		List<TotalObject> serviceRankList=this.serviceVisitInfoService.getServiceRank(start, end);
		ListGeneral.setLastservicetotalrank(serviceRankList);
		//申请前十的服务
		List<TotalObject> applyServiceRankList=this.serviceVisitInfoService.getApplyRank(start1, end2);
		ListGeneral.setApplyrank(applyServiceRankList);
	}
	
	
	public void PoorAndHighService(){
		        //劣质服务排行
				ExecutorService threadpool=Executors.newFixedThreadPool(2);
				PoorGradeThread poolgrade=new PoorGradeThread();
				threadpool.execute(poolgrade);
				//优质服务排行榜
				HighGradeThread highgrade=new HighGradeThread();
				threadpool.execute(highgrade);
	}
	
	
	//@Scheduled(cron="0 0 2 * * ?")
	@Scheduled(fixedDelay=90000)
	//每十分钟更新一次
	public void runbyHour(){
		Date date=new Date();
		String start=TimeGeneral.getInstance().nextHour(date, 1);
		String end=TimeGeneral.getInstance().nextHour(date, 0);
		//System.out.println("------------------------------------------------------------2016");
		int counts=this.serviceVisitInfoService.getdatatotalbytime(start, end);
		TotalObject to=new TotalObject();
		to.setTotalhour(counts);
		ListGeneral.setRealHourData(to);
	}
	
	//@Scheduled(cron="0 0 1 * * ?")
	@Scheduled(fixedDelay=60000)
	//没两分钟更新
	public void runbymu(){
			Date date=new Date();
			System.out.println("-----------1-------------------------------------------------2016");
			List<TotalObject> minurealdatali=new ArrayList<TotalObject>();
			List<TotalObject> serviceminu=new ArrayList<TotalObject>();
			for(int i=10;i>0;i--)
			{
				String start=TimeGeneral.getInstance().nextMinu(date, i*6);
				String end=TimeGeneral.getInstance().nextMinu(date, (i-1)*6);
				TotalObject to=new TotalObject();
				to.setTotalhour(this.serviceVisitInfoService.getdatatotalbytime(start, end));
				to.setMinuname(end);
				minurealdatali.add(to);
				//System.out.println("start:"+start+" end:"+end);
				TotalObject sto=new TotalObject();
				sto.setTotalhour(this.serviceVisitInfoService.getGradeServiceBytime(start, end));
				sto.setMinuname(end);
				serviceminu.add(to);
				
			}
			ListGeneral.setRealMuServiceData(serviceminu);
			ListGeneral.setRealMuData(minurealdatali); 
	}
	//劣质服务排名
	class PoorGradeThread implements Runnable  {
		public void run() {
			List<TotalObject> poorlist=new ArrayList<TotalObject>();
			List<TotalObject> alllist=null;
			alllist=TotalTask.this.serviceVisitInfoService.getGradeService("all");
			List<TotalObject> errorlist=TotalTask.this.serviceVisitInfoService.getGradeService(null);
			if(alllist!=null)
			{
				for(TotalObject to:alllist)
				{
					if(errorlist!=null)
					{   boolean flag=false;
						for(TotalObject erto:errorlist)
						{
							if(erto.getServiceName()==to.getServiceName())
							{
								to.setWeight((erto.getServiceCount()*1.0)/to.getServiceCount()); 
								flag=true;
								break;
							}
						}
						if(!flag)
						{//不村在
							 to.setWeight((0.1)/to.getServiceCount()); 
						}
					}else{//不村在
						     to.setWeight((0.1)/to.getServiceCount()); 
					}
				}
				Collections.sort(alllist);
				Collections.reverse(alllist);
				/*if(alllist.size()>10)
				{
					ListGeneral.setPoorgrade(alllist.subList(0, 9));
				}else{
					ListGeneral.setPoorgrade(alllist);
				}*/
				ListGeneral.setPoorgrade(alllist);
			}
		}
	} 
	//优质服务排名
	class HighGradeThread implements Runnable  {
		public void run() {
			List<TotalObject> poorlist=new ArrayList<TotalObject>();
			List<TotalObject> alllist=null;
			alllist=TotalTask.this.serviceVisitInfoService.getGradeService("all");
			List<TotalObject> errorlist=TotalTask.this.serviceVisitInfoService.getGradeService(null);
			if(alllist!=null)
			{
				for(TotalObject to:alllist)
				{
					if(errorlist!=null)
					{   boolean flag=false;
						for(TotalObject erto:errorlist)
						{
							if(erto.getServiceName()==to.getServiceName())
							{
								to.setWeight((erto.getServiceCount()*1.0)/to.getServiceCount()); 
								flag=true;
								break;
							}
						}
						if(!flag)
						{//不村在
							 to.setWeight((0.1)/to.getServiceCount()); 
						}
					}else{//不村在
						     to.setWeight((0.1)/to.getServiceCount()); 
					}
				}
				Collections.sort(alllist);
				 
			 /*	if(alllist.size()>10)
				{
					ListGeneral.setHighgrade(alllist.subList(0, 9));
				}else{
					ListGeneral.setHighgrade(alllist);
				}*/
				ListGeneral.setHighgrade(alllist);
			}
		}
	} 
		public static void main(String args[]){
		       
	}

}
