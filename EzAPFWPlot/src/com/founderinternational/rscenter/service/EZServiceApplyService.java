package com.founderinternational.rscenter.service;
import java.util.List;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZPFunctionService;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceApply;
import com.founderinternational.rscenter.entity.EZServiceApplyInfo;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.ApplyVO;
 
public interface EZServiceApplyService {
	public List<EZServiceApply> findEZServiceApply(String applyuser,String dealuser,  int status,int start,int pagesize);
	public List<EZServiceApply> findEZServiceApplyList(String aid);
	public List<EZServiceApplyInfo> findEZServiceApplyInfo(String aid);
	public int InsertEZServiceApply(EZServiceApply eZServiceApply);
	public int UpdateEZServiceApply(EZServiceApply eZServiceApply);
	public int InsertEZServiceApplyInfo(EZServiceApplyInfo eZServiceApplyInfo);
	public int UpdateEZServiceApplyInfo(EZServiceApplyInfo  eZServiceApplyInfo);
	public int insertEZFunctionService(EZPFunctionService ezpfservice);
	public List<ApplyVO> findApplyList(int status,String user,String dealuser);
	public EZServiceApply findApplyInfoById(String id);
	//public int deleteEZService(String CODE);
}