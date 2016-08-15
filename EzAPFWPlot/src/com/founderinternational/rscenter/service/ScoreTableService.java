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
import com.founderinternational.rscenter.entity.ScoreTable;
import com.founderinternational.rscenter.entity.form.ApplyVO;
 
public interface ScoreTableService {
	 
	public int InsertScoreTable(ScoreTable scoreTable);
	public List<ScoreTable> findFwSqTableList(String serviceid,int start,int end);
	public int getcount(String serviceid);
	public int getSumByServiceId(String serviceId);
}