package com.founderinternational.rscenter.service;
import java.util.List;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZPFunctionService;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceApply;
import com.founderinternational.rscenter.entity.EZServiceApplyInfo;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.FwSqTable;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.ApplyVO;
 
public interface FwSqTableService {
	 
	public int InsertFwSqTable(FwSqTable fwSqTable);
	public int UpdateFwSqTable(FwSqTable fwSqTable);
	public int UpateFwSqTableDetail(FwSqTable fwsq);
	public List<FwSqTable> findFwSqTableList(int status,String user,String dealuser);
	public FwSqTable findFwSqTableById(String id);
	//public int deleteEZService(String CODE);
}