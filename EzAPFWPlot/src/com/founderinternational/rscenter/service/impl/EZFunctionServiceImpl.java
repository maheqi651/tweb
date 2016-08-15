package com.founderinternational.rscenter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.mapper.EZFunctionMapper;
import com.founderinternational.rscenter.mapper.MenuMapper;
import com.founderinternational.rscenter.service.EZFunctionService;
@Repository(value = "eZFunctionService")
@Transactional
public class EZFunctionServiceImpl implements EZFunctionService {
	@Resource(name = "eZFunctionMapper")
	private EZFunctionMapper eZFunctionMapper;
	@Override
	public List<EZFunction> find() {
		String sql = "select * from ez_function";
		return this.eZFunctionMapper.operateReturnBeans(sql);
	}
	@Override
	public List<EZFunction> find(String owner) {
		String sql = "select * from ez_function where owner='"+owner+"'";
		return this.eZFunctionMapper.operateReturnBeans(sql);
	}
	@Override
	public int Insert(EZFunction ezfunction) {
		try {
			return this.eZFunctionMapper.insertEZFuntion(ezfunction);
		} catch (Exception e) {
			e.printStackTrace();
		    return 0;
		}
	}
	@Override
	public int Update(EZFunction ezfunction) {
		return this.eZFunctionMapper.updateEZFuntion(ezfunction);
	}
	@Override
	public EZFunction findByCode(String CODE) {
		return this.eZFunctionMapper.findByCode(CODE);
	}
	@Override
	public void delete(String CODE) {
		 this.eZFunctionMapper.delete(CODE);
	}
	@Override
	public List<EZFunction> findAllFunction() {
		String sql = "select * from ez_function";
		return this.eZFunctionMapper.operateReturnBeans(sql);
	}

}
