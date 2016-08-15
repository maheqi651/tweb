package com.founderinternational.rscenter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.founderinternational.rscenter.entity.FwSqTable;
import com.founderinternational.rscenter.entity.ScoreTable;
import com.founderinternational.rscenter.mapper.ScoreTableMapper;
import com.founderinternational.rscenter.service.ScoreTableService;
@Repository(value = "scoreTableService")
@Transactional
public class ScoreTableServiceImpl implements ScoreTableService {
    @Resource(name="scoreTableMapper")
    private ScoreTableMapper scoreTableMapper;
	@Override
	public int InsertScoreTable(ScoreTable scoreTable) {
		return this.scoreTableMapper.insert(scoreTable);
	}

	@Override
	public List<ScoreTable> findFwSqTableList(String serviceid, int start,
			int end) {
		String sql="";
		sql="SELECT * FROM (SELECT row_.*, ROWNUM rownum_  FROM "
				+"(select * from  scoretable where  ";
		sql+="  SERVICEID='"+serviceid+"' ";
		sql+=" order by id desc) row_ WHERE ROWNUM <"+end+")  WHERE rownum_ >"+start;
		return this.scoreTableMapper.operateReturnBeans(sql);
	}

	
	@Override
	public int getSumByServiceId(String serviceId){
		 String sql="select sum(score) from  scoretable where  serviceid='"+serviceId+"'";
		 System.out.println(sql);
		 return this.scoreTableMapper.operateReturnOneBeans(sql);
	}
	@Override
	public int getcount(String serviceid) {
		 String sql="select count(*) from scoretable where SERVICEID='"+serviceid+"'";
		 System.out.println(sql);
		 return this.scoreTableMapper.operateReturnOneBeans(sql);
	}

}
