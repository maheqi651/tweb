package com.founderinternational.rscenter.service;
import java.util.List;

import com.founderinternational.rscenter.entity.EZDataTree;
import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.Menu;
 
public interface EZDataTreeService {
	List< EZDataTree> find(String user);
}