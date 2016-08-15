package com.founderinternational.rscenter.service;
import java.util.List;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.EZUser;
import com.founderinternational.rscenter.entity.Menu;
 
public interface EZUserService {
  	 public EZUser findEZUser(String username,String password);
}