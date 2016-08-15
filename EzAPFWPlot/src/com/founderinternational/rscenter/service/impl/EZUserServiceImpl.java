package com.founderinternational.rscenter.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.founderinternational.rscenter.entity.EZUser;
import com.founderinternational.rscenter.mapper.EZServiceMeitemMapper;
import com.founderinternational.rscenter.mapper.EZUserMapper;
import com.founderinternational.rscenter.service.EZUserService;
@Repository(value = "eZUserService")
@Transactional
public class EZUserServiceImpl implements EZUserService {
	
	@Resource(name = "eZUserMapper")
	private EZUserMapper eZUserMapper;
	@Override
	public EZUser findEZUser(String username, String password) {
		String sql="select * from ez_user t where userid='"+username+"' and password='"+password+"'";
		System.out.println(sql);
		return this.eZUserMapper.operateReturnBeans(sql);
	}
}
