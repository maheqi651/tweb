package com.founderinternational.rscenter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.founderinternational.rscenter.entity.EZPFunctionService;
import com.founderinternational.rscenter.entity.EZServiceApply;
import com.founderinternational.rscenter.entity.EZServiceApplyInfo;
import com.founderinternational.rscenter.entity.form.ApplyVO;
import com.founderinternational.rscenter.mapper.EZFunctionMapper;
import com.founderinternational.rscenter.mapper.EZPFunctinServiceMapper;
import com.founderinternational.rscenter.mapper.EZServiceApplyInfoMapper;
import com.founderinternational.rscenter.mapper.EZServiceApplyMapper;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.EZServiceApplyService;
import com.founderinternational.rscenter.tools.Constants;

@Repository(value = "eZServiceApplyService")
@Transactional
public class EZServiceApplyServiceImpl implements EZServiceApplyService {
	
	@Resource(name = "eZServiceApplyInfoMapper")
	private EZServiceApplyInfoMapper eZServiceApplyInfoMapper;
	@Resource(name = "eZServiceApplyMapper")
	private EZServiceApplyMapper eZServiceApplyMapper;
	@Resource(name = "eZPFunctinServiceMapper")
	private EZPFunctinServiceMapper eZPFunctinServiceMapper;
	
	@Override
	public List<EZServiceApply> findEZServiceApply(String applyuser,String dealuser,
			  int status, int start, int end) {
         String sql="";
         sql="SELECT * FROM (SELECT row_.*, ROWNUM rownum_  FROM "
        		    +"(select * from  ez_service_apply where 1=1 ";
        		    if(applyuser!=null&&!"".equals(applyuser))
        		    	sql+=" and applyuser ='"+applyuser+"' ";
        		    if(dealuser!=null&&!"".equals(dealuser))
        		    	sql+=" and dealuser like '"+dealuser+"' ";
        		    sql+=") row_ WHERE ROWNUM <"+end+")  WHERE rownum_ >= "+start;
		return this.eZServiceApplyMapper.operateReturnBeans(sql);
	}

	@Override
	public List<EZServiceApplyInfo> findEZServiceApplyInfo(String aid) { 
		String sql="SELECT * FROM EZ_SERVICE_APPLY_info where aid='"+aid+"'";
		System.out.println(sql);
		return this.eZServiceApplyInfoMapper.operateReturnBeans(sql);
	}

	@Override
	public int InsertEZServiceApply(EZServiceApply eZServiceApply) {
		return this.eZServiceApplyMapper.insert(eZServiceApply);
	}

	@Override
	public int UpdateEZServiceApply(EZServiceApply eZServiceApply) {
		return this.eZServiceApplyMapper.update(eZServiceApply);
	}

	@Override
	public int InsertEZServiceApplyInfo(EZServiceApplyInfo eZServiceApplyInfo) {
		return this.eZServiceApplyInfoMapper.insert(eZServiceApplyInfo);
	}

	@Override
	public int UpdateEZServiceApplyInfo(EZServiceApplyInfo eZServiceApplyInfo) {
		  return 0;
	}

	@Override
	public int insertEZFunctionService(EZPFunctionService ezpfservice) {
		try {
			return this.eZPFunctinServiceMapper.insert(ezpfservice);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<ApplyVO> findApplyList(int status, String user,String dealuser) {
		 String sql="select c.METHODNAME ,c.type,a.funcode funcode,b.CODE SERVICECODE, a.ID AID,to_char(a.APPLYTIME,'yyyy-mm-dd hh24:mi:ss') APPLYTIME,to_char(a.DEALTIME,'yyyy-mm-dd hh24:mi:ss') DEALTIME,a.APPLYUSER,a.DEALUSER,a.REPLY,b.NAME APPNAME ,b.DESCRIPTION,b.ADDRESS,b.PHONE" 
	     +" ,c.SERVICEID,c.NAME,c.INFO from ez_service_apply   a,ez_function_c b,ezspatial.ez_service_info c where " 
          +" a.funcode=b.code and a.sercode=c.serviceid ";
		 if(status<=2)
		 {
			 sql+=" and  a.status="+status+" ";
		 }
		 if(user!=null)
		 {
			 sql+=" and a.applyuser='"+user+"' ";
			 sql+=" order by applytime desc";
		 }else{
			 if(dealuser!=null)
			 {
				 sql+=" and a.dealuser='"+dealuser+"' ";
				 sql+=" order by dealtime desc";
			 }else{
				 sql+=" order by applytime desc";
			 } 
		 }
		 
		
		 System.out.println(sql);
		 return this.eZServiceApplyMapper.findApplyList(sql);
	}

	@Override
	public EZServiceApply findApplyInfoById(String id) {
		String sql="select * from ez_service_apply where id='"+id+"'";
		return this.eZServiceApplyMapper.findOneApply(sql);
	}

	@Override
	public List<EZServiceApply> findEZServiceApplyList(String aid) {
		String sql="select * from ez_service_apply where  id='"+aid+"'";
		return this.eZServiceApplyMapper.operateReturnBeans(sql);
	}

}
