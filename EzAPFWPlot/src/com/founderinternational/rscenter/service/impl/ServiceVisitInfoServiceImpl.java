package com.founderinternational.rscenter.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.founderinternational.rscenter.entity.ServiceVisitInfo;
import com.founderinternational.rscenter.mapper.EZPFunctinServiceMapper;
import com.founderinternational.rscenter.mapper.ServiceVisitInfoMapper;
import com.founderinternational.rscenter.service.ServiceVisitInfoService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.totalchart.TotalObject;
@Repository(value = "serviceVisitInfoService")
@Transactional
public class ServiceVisitInfoServiceImpl implements ServiceVisitInfoService {
	//private static final Object key =new Object();
	private final static String TABLENAME="YW603.SERVICE_VISIT_LOG";
	@Resource(name = "serviceVisitInfoMapper")
	private ServiceVisitInfoMapper serviceVisitInfoMapper;
	public List<ServiceVisitInfo> findFromOracle(String SERVICE_NAME,String APP_NAME, String SENDER_ID,
			String CONSUMER_IP, String REQEUST_DATETIME_start,
			String REQEUST_DATETIME_end, int start, int end){
		String sql="";
	    sql="SELECT * FROM (SELECT row_.*, ROWNUM rownum_  FROM "
	    +"(select * from  "+TABLENAME+" where 1=1 ";
	    if(SENDER_ID!=null&&!"".equals(SENDER_ID))
	    	sql+=" and SENDER_ID='"+SENDER_ID+"' ";
	    if(CONSUMER_IP!=null&&!"".equals(CONSUMER_IP))
	    	sql+=" and CONSUMER_IP='"+CONSUMER_IP+"' ";
	    if(APP_NAME!=null&&!"".equals(APP_NAME))
	    	{
	    	sql+=" and  APP_NAME like '%"+APP_NAME+"%' ";
	    	}
	    if(SERVICE_NAME!=null&&!"".equals(SERVICE_NAME))
    	{
    	sql+=" and  SERVICE_NAME like '%"+SERVICE_NAME+"%' ";
    	}
	    if(REQEUST_DATETIME_start!=null)
	    	sql+=" and REQEUST_DATETIME>='"+REQEUST_DATETIME_start+"' ";
	    if(REQEUST_DATETIME_end!=null)	
	    	sql+=" and REQEUST_DATETIME<='"+REQEUST_DATETIME_end+"3"+"' ";
	    sql+=") row_ WHERE ROWNUM <"+end+")  WHERE rownum_ > "+start;
	    System.out.println(sql);
		return this.serviceVisitInfoMapper.operateReturnBeans(sql);
	}
	
	
	@Override
	public List<ServiceVisitInfo> find(String SERVICE_NAME,String APP_NAME, String SENDER_ID,
			String CONSUMER_IP, String REQEUST_DATETIME_start,
			String REQEUST_DATETIME_end, int start, int end){
		String sql="";
	    sql="select * from   "+TABLENAME+"  where 1=1 ";
	    if(SENDER_ID!=null&&!"".equals(SENDER_ID))
	    	sql+=" and SENDER_ID='"+SENDER_ID+"' ";
	    if(CONSUMER_IP!=null&&!"".equals(CONSUMER_IP))
	    	sql+=" and CONSUMER_IP='"+CONSUMER_IP+"' ";
	    if(APP_NAME!=null&&!"".equals(APP_NAME))
	    	{
	    	sql+=" and  APP_NAME like '%"+APP_NAME+"%' ";
	    	}
	    if(SERVICE_NAME!=null&&!"".equals(SERVICE_NAME))
    	{
    	sql+=" and  SERVICE_NAME like '%"+SERVICE_NAME+"%' ";
    	}
	    if(REQEUST_DATETIME_start!=null)
	    	sql+=" and REQEUST_DATETIME>='"+REQEUST_DATETIME_start.replaceAll("-", "")+"' ";
	    if(REQEUST_DATETIME_end!=null)	
	    	sql+=" and REQEUST_DATETIME<='"+REQEUST_DATETIME_end.replaceAll("-", "")+"' ";
	    sql+=" limit "+(start)+","+(end-start-1);
	    List<ServiceVisitInfo> result=null;
	   
			result=this.serviceVisitInfoMapper.operateReturnBeans(sql);
	    return result;
	}
	@Override
	public int getcount(String SERVICE_NAME,String APP_NAME, String SENDER_ID, String CONSUMER_IP,
			String REQEUST_DATETIME_start,String REQEUST_DATETIME_end) {
		String sql="";
		sql+="select count(1) from   "+TABLENAME+"  where 1=1 ";
			    if(SENDER_ID!=null&&!"".equals(SENDER_ID))
			    	sql+=" and SENDER_ID='"+SENDER_ID+"' ";
			    if(CONSUMER_IP!=null&&!"".equals(CONSUMER_IP))
			    	sql+=" and CONSUMER_IP='"+CONSUMER_IP+"' ";
			    if(APP_NAME!=null&&!"".equals(APP_NAME))
			    	{
			    	sql+=" and   APP_NAME like '%"+APP_NAME+"%' ";
			    	}
			    if(SERVICE_NAME!=null&&!"".equals(SERVICE_NAME))
		    	{
		    	sql+=" and   SERVICE_NAME like '%"+SERVICE_NAME+"%' ";
		    	}
			    if(REQEUST_DATETIME_start!=null)
			    	sql+=" and REQEUST_DATETIME>='"+REQEUST_DATETIME_start.replaceAll("-", "")+"' ";
			    if(REQEUST_DATETIME_end!=null)	
			    	sql+=" and REQEUST_DATETIME<='"+REQEUST_DATETIME_end.replaceAll("-", "")+"3"+"' ";
			    int count=0;
				    	count=this.serviceVisitInfoMapper.operateReturnBeanscount(sql);
			    return count;
	}
	
	@Override
	public Integer getdatatotalbytime(String start, String end) {
		 int count=0;
		 String sql="select  nvl(sum(RESPONSE_RESULT_COUNT),0)   from  "+TABLENAME+"  where reqeust_datetime>='"+start+"' and reqeust_datetime<='"+end+"'";
		// System.out.println(sql);
		    	count=this.serviceVisitInfoMapper.operateReturnBeansdatacount(sql);
		return count;
	}
	@Override
	public List<TotalObject> getServiceRank(String start, String end) {
		List<TotalObject>  result=null;
		 String sql="";
		sql+="select * from ( select service_name ServiceName,count(service_name) ServiceCount from  "+TABLENAME+"   where reqeust_datetime>='"+start+"' and reqeust_datetime<='"+end+"' group "
				+" by service_name  )  b order by b.ServiceCount desc  ";
	    System.out.println(sql);
		result=this.serviceVisitInfoMapper.operateReturnTotalObject(sql);
		return result;
	}
	
	
/*	@Override
	public List<TotalObject> getServiceRank(String start, String end) {
		List<TotalObject>  result=null;
		 String sql="";
	    sql="SELECT * FROM (SELECT row_.*, ROWNUM rownum_  FROM ( ";
	    		 
		sql+="select * from ( select service_name ServiceName,count(service_name) ServiceCount from  "+TABLENAME+"   where reqeust_datetime>='"+start+"' and reqeust_datetime<='"+end+"' group "
				+" by service_name  )  b order by b.ServiceCount desc  ";
	    sql+=") row_ WHERE ROWNUM <"+11+")  WHERE rownum_ > "+0;
			result=this.serviceVisitInfoMapper.operateReturnTotalObject(sql);
		return result;
	}*/
	@Override
	public List<TotalObject> getFunctionRank(String start, String end) {
		List<TotalObject>  result=null;
		/*String sql="SELECT * FROM (SELECT row_.*, ROWNUM rownum_  FROM ( ";
		sql+="select * from ( select SENDER_ID FunctionName, nvl(count(SENDER_ID),0)    as FunctionCount from  "+TABLENAME+"  where reqeust_datetime>='"+start+"' and reqeust_datetime<='"+end+"'  group by " 
				+" SENDER_ID  )   b order by b.FunctionCount desc  ";
		sql+=") row_ WHERE ROWNUM <"+11+")  WHERE rownum_ > "+0;*/
		String sql="select * from ( select SENDER_ID FunctionName, nvl(count(SENDER_ID),0)    as FunctionCount from  "+TABLENAME+"  where reqeust_datetime>='"+start+"' and reqeust_datetime<='"+end+"'  group by " 
				+" SENDER_ID  )   b order by b.FunctionCount desc  ";
		System.out.println(sql);
			result=this.serviceVisitInfoMapper.operateReturnTotalObject(sql);
		return result;
	}


	@Override
	public List<TotalObject> getApplyRank(String start, String end) {
		List<TotalObject>  result=null;
		String sql="select count(a.sercode) ServiceCount,b.name ServiceName  from ez_service_apply a ,ezspatial.ez_service_info b " 
				+" where a.sercode =b.serviceid and  to_char(a.applytime,'yyyy-mm-dd')>='"+start+"' and  to_char(a.applytime,'yyyy-mm-dd')<='"+end+"' group by a.sercode,b.name";
	    System.out.println(sql);
			result=this.serviceVisitInfoMapper.operateReturnTotalObject(sql);
		return result;
	}


	@Override
	public List<TotalObject> getGradeService(String Condtion) {
		List<TotalObject>  result=null;
		String sql="select service_name ServiceName,count(service_name) ServiceCount from  "+TABLENAME+"  ";
		if(Condtion==null)
		{
			sql+=" where request_content is null";
		}
		sql+=" group by service_name ";
		System.out.println(sql);
			result=this.serviceVisitInfoMapper.operateReturnTotalObject(sql);
		return result;
	}
	 
	
	@Override
	public int getGradeServiceBytime(String Condtion,String start,String end) {
		int  result=0;
		String sql="select  count(service_name) ServiceCount from  "+TABLENAME+"  where 1=1";
		if(Condtion==null)
		{
			sql+=" and  request_content is null";
		}
		sql+=" and reqeust_datetime>='"+start+"' and reqeust_datetime<='"+end+"'"; 
		//System.out.println(sql);
			result=this.serviceVisitInfoMapper.operateReturnBeanscount(sql);
		return result;
	}
	
	
	@Override
	public int getGradeServiceBytime(String start, String end) {
		int  result=0;
		String sql="select count(service_name) ServiceCount from  "+TABLENAME+"  where reqeust_datetime>='"+start+"' and reqeust_datetime<='"+end+"' ";
		 System.out.println(sql);
			result=this.serviceVisitInfoMapper.operateReturnBeanscount(sql);
		return result;
	}
	
	@Override
	public Integer getdatatotalbytimeByFunction(String fuctioncode,String start, String end) {
		 int count=0;
		 String sql="select  nvl(sum(RESPONSE_RESULT_COUNT),0)   from  "+TABLENAME+"  where reqeust_datetime>='"+start+"' and reqeust_datetime<='"+end+"'";
		 if("all".equals(fuctioncode))
			{
				
			}else{
				sql+=" and app_name='"+fuctioncode+"'";
			}
		 System.out.println(sql);
		    	count=this.serviceVisitInfoMapper.operateReturnBeansdatacount(sql);
		return count;
	}
	@Override
	public int getGradeServiceBytimeByFunction(String fuctioncode,String start, String end) {
		int  result=0;
		String sql="select count(service_name) ServiceCount from  "+TABLENAME+"  where reqeust_datetime>='"+start+"' and reqeust_datetime<='"+end+"' ";
		if("all".equals(fuctioncode))
		 
		{
			
		}else{
			sql+=" and app_name='"+fuctioncode+"'";
		}
		System.out.println(sql);
			result=this.serviceVisitInfoMapper.operateReturnBeanscount(sql);
		return result;
	}
	
}
