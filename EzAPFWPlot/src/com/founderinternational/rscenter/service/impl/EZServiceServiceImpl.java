package com.founderinternational.rscenter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.mapper.EZFunctionMapper;
import com.founderinternational.rscenter.mapper.EZServiceMapper;
import com.founderinternational.rscenter.mapper.EZServiceMeitemMapper;
import com.founderinternational.rscenter.service.EZServiceService;
import com.founderinternational.rscenter.tools.Constants;
@Repository(value = "eZServiceService")
@Transactional
public class EZServiceServiceImpl implements EZServiceService {
	@Resource(name = "eZServiceMeitemMapper")
	private EZServiceMeitemMapper eZServiceMeitemMapper;
	
	@Resource(name = "eZServiceMapper")
	private EZServiceMapper eZServiceMapper;
	@Override
	public List<EZService> findEZService(String regiontype,String interfacetype,String type,String NAME,String DESP,
			int namesort,int scoresort,int accessort,int newsort, int start,int end) {
	    String sql="";
	    sql="SELECT * FROM (SELECT row_.*, ROWNUM rownum_  FROM "
	    +"(select * from "+Constants.EZSPATIAL+".ez_service_info where type!='GROUP' ";
	    if(type!=null&&!"".equals(type))
	    	sql+=" and type='"+type+"' ";
	    if(NAME!=null&&!"".equals(NAME))
	    	{
	    	sql+=" and ( name like '%"+NAME+"%' ";
	    	sql+=" or info like '%"+NAME+"%' )";
	    	}
	    if(regiontype!=null&&!"".equals(regiontype))
	    	sql+=" and regiontype='"+regiontype+"' ";
	    
	    if(interfacetype!=null&&!"".equals(interfacetype))
	    	sql+=" and interfacetype='"+interfacetype+"' ";
	    //if(DESP!=null&&!"".equals(DESP))
	    	 if(namesort!=Constants.NOSORT)
	    	 {
	    		 if(namesort==Constants.ASC)
	    		 {
	    			 sql+=" @order by name asc ";
	    		 }else{
	    			 sql+=" @order by name desc ";
	    		 }
	    	 }
	    	 if(scoresort!=Constants.NOSORT)
	    	 {    
	    		 String temp="";
	    		 if(scoresort==Constants.ASC)
	    		 {  
	    			 temp=" score asc ";
	    		 }else{
	    			 temp=" score desc ";
	    		 }
	    		 if(sql.contains("@order"))
    		     {
	    			 
    		     }else{
    		    	 sql+=" @order by  "+temp;
    		      }
	    	 }
	    	 
	    	 
	    	 if(accessort!=Constants.NOSORT)
	    	 {    
	    		 String temp="";
	    		 if(accessort==Constants.ASC)
	    		 {  
	    			 temp=" accesscount asc ";
	    		 }else{
	    			 temp=" accesscount desc ";
	    		 }
	    		 if(sql.contains("@order"))
    		     {
	    			 
    		     }else{
    		    	 sql+=" @order by  "+temp;
    		      }
	    	 }
	    	 
	    	 if(newsort!=Constants.NOSORT)
	    	 {    
	    		 String temp="";
	    		 if(newsort==Constants.ASC)
	    		 {  
	    			 temp=" id asc ";
	    		 }else{
	    			 temp=" id desc ";
	    		 }
	    		 if(sql.contains("@order"))
    		     {
	    			 
    		     }else{
    		    	 sql+=" @order by  "+temp;
    		      }
	    	 }
	    	 
	    	 sql=sql.replace("@order", "order");
	    sql+=") row_ WHERE ROWNUM <"+end+")  WHERE rownum_ >= "+start;
	    System.out.println("sql----:"+sql);
		return this.eZServiceMapper.operateReturnBeans(sql);
	}

	@Override
	public List<EZServiceMditem> findEZServiceMditem(String SERVICE_ID ) {
		String sql="select * from "+Constants.EZSPATIAL+".Ez_Service_Mditem  where SERVICE_ID='"+SERVICE_ID+"'";
		return this.eZServiceMeitemMapper.operateReturnBeans(sql);
	}

	@Override
	public int InsertEZServiceM(EZServiceMditem ezServiceMditem) {
		try {
			return this.eZServiceMeitemMapper.insert(ezServiceMditem);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int UpdateEZServiceM(EZServiceMditem ezservicemeitem) {
		return this.eZServiceMeitemMapper.update(ezservicemeitem);
	}

	@Override
	public int InsertEZService(EZService eZService) {
		try {
			return this.eZServiceMapper.insert(eZService);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int UpdateEZService(EZService eZService) {
		return this.eZServiceMapper.update(eZService);
	}

	@Override
	public int deleteEZService(String CODE) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EZService> findGroup() {
		String sql="select * from "+Constants.EZSPATIAL+".Ez_Service_Info  where TYPE='GROUP'";
		return this.eZServiceMapper.operateReturnBeans(sql);
	}

	@Override
	public int findEZServiceCount(String regiontype,String interfacetype,String type,String NAME,String DESP) {
		    String sql="";
		    sql+="select count(1) from "+Constants.EZSPATIAL+".ez_service_info where type!='GROUP' ";
		    if(type!=null&&!"".equals(type))
		    	sql+=" and type='"+type+"' ";
		    if(NAME!=null&&!"".equals(NAME))
		    	{
		    	sql+=" and ( name like '%"+NAME+"%' ";
		    	sql+=" or info like '%"+NAME+"%' )";
		    	}
		    
		    if(regiontype!=null&&!"".equals(regiontype))
		    	sql+=" and regiontype='"+regiontype+"' ";
		    
		    if(interfacetype!=null&&!"".equals(interfacetype))
		    	sql+=" and interfacetype='"+interfacetype+"' ";
		    
		    //if(DESP!=null&&!"".equals(DESP))
			return this.eZServiceMapper.operateReturnBeanscount(sql);
	}

	@Override
	public List<EZService> findByServiceid(String Serviceid) {
		String sql="select * from "+Constants.EZSPATIAL+".ez_service_info where ServiceId='"+Serviceid+"' ";
		return this.eZServiceMapper.operateReturnBeans(sql);
	}

	@Override
	public List<EZService> findEZService(String username, String type,
			String NAME, String DESP, int start, int end) {
		/*select * from ezspatial.ez_service_info where serviceid in
		(select service_id from ezspatial.ez_service_mditem where md_code='SMD_BASE_USER' and md_value='admin')*/
		    String sql="";
		    sql="SELECT * FROM (SELECT row_.*, ROWNUM rownum_  FROM "
		    +"(select * from "+Constants.EZSPATIAL+".ez_service_info where type!='GROUP' ";
		    if(type!=null&&!"".equals(type))
		    	sql+=" and type='"+type+"' ";
		    if(NAME!=null&&!"".equals(NAME))
		    	{
		    	sql+=" and ( name like '%"+NAME+"%' ";
		    	sql+=" or info like '%"+NAME+"%' )";
		    	}
		    if(username!=null&&!"".equals(username))
		    {
		    	sql+=" and serviceid in (select service_id from ezspatial.ez_service_mditem where md_code='SMD_BASE_USER' and md_value='"+username+"') ";
		    }
		    //if(DESP!=null&&!"".equals(DESP))
		    	
		    sql+=") row_ WHERE ROWNUM <"+end+")  WHERE rownum_ >= "+start;
			return this.eZServiceMapper.operateReturnBeans(sql);
		 
	}

	@Override
	public int findEZServiceCount(String username, String type, String NAME,
			String DESP) {
		String sql="";
	    sql+="select count(1) from "+Constants.EZSPATIAL+".ez_service_info where type!='GROUP' ";
	    if(type!=null&&!"".equals(type))
	    	sql+=" and type='"+type+"' ";
	    if(NAME!=null&&!"".equals(NAME))
	    	{
	    	sql+=" and ( name like '%"+NAME+"%' ";
	    	sql+=" or info like '%"+NAME+"%' )";
	    	}
	    
	    if(username!=null&&!"".equals(username))
	    {
	    	sql+=" and serviceid in (select service_id from ezspatial.ez_service_mditem where md_code='SMD_BASE_USER' and md_value='"+username+"') ";
	    }
		return this.eZServiceMapper.operateReturnBeanscount(sql);
	}

	@Override
	public List<EZService> findByid(String ID) {
		String sql="select * from "+Constants.EZSPATIAL+".ez_service_info where ID="+ID;
		System.out.println(sql);
		return this.eZServiceMapper.operateReturnBeans(sql);
	}

	@Override
	public int deleteEZServiceM(String serviceid) {
		String sql="delete from "+Constants.EZSPATIAL+".ez_service_mditem where service_id='"+serviceid+"'";
		System.out.println(sql);
		return this.eZServiceMeitemMapper.delete(sql);
		 
	}

	@Override
	public List<String> findByCondition(EZService eZService) {
		/* ezs.setNAME(result.getFWNAME());
		 ezs.setINFO(result.getFWINFO());
		 ezs.setINTERFACETYPE(result.getINTERFACETYPE());
		 ezs.setMETHODNAME(result.getMETHODNAME());
		 ezs.setREGIONTYPE(result.getFWREGION());*/
		String sql="select serviceid from "+Constants.EZSPATIAL+".ez_service_info  ";
		sql+=" where name='"+eZService.getNAME()+"' and info='"+eZService.getINFO()+"'  ";
		sql+=" and INTERFACETYPE='"+eZService.getINTERFACETYPE()+"'  ";
		sql+=" and METHODNAME='"+eZService.getMETHODNAME()+"'  ";
		sql+=" and REGIONTYPE='"+eZService.getREGIONTYPE()+"'  ";
		System.out.println(sql);
		return this.eZServiceMapper.operateReturnBeansServiceids(sql);
	}

	@Override
	public int UpdateEZServicefw(EZService eZService) {
		return this.eZServiceMapper.updatefw(eZService);
	}

	@Override
	public List<EZService> findNoGroup() {
		   String sql="select * from "+Constants.EZSPATIAL+".Ez_Service_Info  where TYPE!='GROUP'";
		   return this.eZServiceMapper.operateReturnBeans(sql);
		 
	}

}
