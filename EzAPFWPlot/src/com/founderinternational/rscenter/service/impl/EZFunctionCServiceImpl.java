package com.founderinternational.rscenter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZFunctionC;
import com.founderinternational.rscenter.mapper.EZFunctionCMapper;
import com.founderinternational.rscenter.mapper.EZFunctionMapper;
import com.founderinternational.rscenter.mapper.MenuMapper;
import com.founderinternational.rscenter.service.EZFunctionCService;
import com.founderinternational.rscenter.service.EZFunctionService;
@Repository(value = "eZFunctionCService")
@Transactional
public class EZFunctionCServiceImpl implements EZFunctionCService {
	@Resource(name = "eZFunctionCMapper")
	private EZFunctionCMapper eZFunctionCMapper;
	@Override
	public List<EZFunctionC> find() {
		String sql = "select * from ez_function_c";
		return this.eZFunctionCMapper.operateReturnBeans(sql);
	}
	@Override
	public List<EZFunctionC> find(String owner) {
		String sql = "select * from ez_function_c where owner='"+owner+"'";
		return this.eZFunctionCMapper.operateReturnBeans(sql);
	}
	@Override
	public int Insert(EZFunctionC ezfunctionc) {
		return this.eZFunctionCMapper.insertEZFuntionC(ezfunctionc);
	}
	@Override
	public int Update(EZFunctionC ezfunctionc) {
		return this.eZFunctionCMapper.updateEZFuntionC(ezfunctionc);
	}
	@Override
	public EZFunctionC findByCode(String CODE) {
		return this.eZFunctionCMapper.findByCode(CODE);
	}
	@Override
	public void delete(String CODE) {
		 this.eZFunctionCMapper.delete(CODE);
	}

}
