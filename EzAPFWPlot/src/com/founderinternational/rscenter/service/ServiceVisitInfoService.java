package com.founderinternational.rscenter.service;
import java.util.Date;
import java.util.List;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZFunctionC;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.ServiceVisitInfo;
import com.founderinternational.rscenter.totalchart.TotalObject;
 
public interface ServiceVisitInfoService {
	List<ServiceVisitInfo> findFromOracle(String SERVICE_NAME,String APP_NAME,String SENDER_ID,String CONSUMER_IP,String REQEUST_DATETIME_start,String REQEUST_DATETIME_end,  int start,int end);
	List<ServiceVisitInfo> find(String SERVICE_NAME,String APP_NAME,String SENDER_ID,String CONSUMER_IP,String REQEUST_DATETIME_start,String REQEUST_DATETIME_end,  int start,int end);
	int getcount(String SERVICE_NAME,String APP_NAME,String SENDER_ID,String CONSUMER_IP,String REQEUST_DATETIME_start,String REQEUST_DATETIME_end);
    Integer getdatatotalbytime(String start,String end);//一周流量统计
    List<TotalObject> getServiceRank(String start,String end);
    List<TotalObject> getFunctionRank(String start,String end);
    List<TotalObject> getApplyRank(String start,String end);//申请前十
    List<TotalObject> getGradeService(String Condtion); //申请前十
    int getGradeServiceBytime(String Condtion,String start,String end);
    public int getGradeServiceBytime(String start, String end);
    public Integer getdatatotalbytimeByFunction(String fucntioncode,String start, String end);
	public int getGradeServiceBytimeByFunction(String fuctioncode,String start, String end);
	
}