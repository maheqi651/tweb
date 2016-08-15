package com.founderinternational.rscenter.service;
import java.util.List;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.Menu;
 
public interface EZFunctionService {
	List<EZFunction> find();
	public List<EZFunction> find(String owner);
	public int Insert(EZFunction ezfunction);
	public int Update(EZFunction ezfunction);
	public EZFunction findByCode(String CODE);
	public void delete(String CODE);
	public List<EZFunction> findAllFunction();
}