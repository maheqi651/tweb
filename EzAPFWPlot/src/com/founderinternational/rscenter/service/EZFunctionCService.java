package com.founderinternational.rscenter.service;
import java.util.List;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZFunctionC;
import com.founderinternational.rscenter.entity.Menu;
 
public interface EZFunctionCService {
	List<EZFunctionC> find();
	public List<EZFunctionC> find(String owner);
	public int Insert(EZFunctionC ezfunctionc);
	public int Update(EZFunctionC ezfunctionc);
	public EZFunctionC findByCode(String CODE);
	public void delete(String CODE);
}