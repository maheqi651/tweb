package com.founderinternational.rscenter.service;
import java.util.List;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.Menu;
 
public interface EZServiceService {
	public List<EZService> findEZService(String regiontype,String interfacetype,String type,String NAME,String DESP,
			int namesort,int scoresort,int accessort,int newsort,int start,int pagesize);
	public List<EZService> findEZService(String username,String type,String NAME,String DESP,int start,int pagesize);
	public int findEZServiceCount(String username,String type,String NAME,String DESP);
	public int findEZServiceCount(String regiontype,String interfacetype,String type,String NAME,String DESP);
	public List<EZServiceMditem> findEZServiceMditem(String SERVICE_ID);
	public List<EZService> findByServiceid(String Serviceid);
	public List<EZService> findByid(String ID);
	public int InsertEZServiceM(EZServiceMditem ezfunction);
	public int deleteEZServiceM(String serviceid);
	public int UpdateEZServiceM(EZServiceMditem ezfunction);
	public int InsertEZService(EZService eZService);
	public int UpdateEZService(EZService  eZService);
	public int UpdateEZServicefw(EZService  eZService);
	public int deleteEZService(String CODE);
	public List<EZService> findGroup();
	public List<EZService> findNoGroup();
	public List<String> findByCondition(EZService  eZService);
}