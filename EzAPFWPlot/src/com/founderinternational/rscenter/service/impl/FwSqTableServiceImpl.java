package com.founderinternational.rscenter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.founderinternational.rscenter.entity.FwSqTable;
import com.founderinternational.rscenter.mapper.FwSqTableMapper;
import com.founderinternational.rscenter.service.FwSqTableService;
@Repository(value="fwSqTableService")
@Transactional
public class FwSqTableServiceImpl implements FwSqTableService {
	
	@Resource(name="fwSqTableMapper")
	private FwSqTableMapper fwSqTableMapper;
	
	@Override
	public int InsertFwSqTable(FwSqTable fwSqTable) {
		return this.fwSqTableMapper.insert(fwSqTable);
	}

	@Override
	public int UpdateFwSqTable(FwSqTable fwSqTable) {
		return this.fwSqTableMapper.update(fwSqTable);
	}

	@Override
	public List<FwSqTable> findFwSqTableList(int status, String user,
			String dealuser) {
		 String sql="select * from fw_sq_table where 1=1";
		 
		 
		 if(status<=2)
		 {
			 sql+=" and  status="+status+" ";
		 }
		 if(user!=null)
		 {
			 sql+=" and applyuser='"+user+"' ";
		 }
		 if(dealuser!=null)
		 {
			 sql+=" and dealuser='"+dealuser+"' ";
		 }
		 sql+=" order by id desc";
		return this.fwSqTableMapper.operateReturnBeans(sql);
	}

	@Override
	public FwSqTable findFwSqTableById(String id) {
		String sql="select * from fw_sq_table  where id="+id;
		return this.fwSqTableMapper.operateReturnOneBeans(sql);
	}

	@Override
	public int UpateFwSqTableDetail(FwSqTable fwsq) {
		return this.fwSqTableMapper.updateDetail(fwsq);
	}

}
