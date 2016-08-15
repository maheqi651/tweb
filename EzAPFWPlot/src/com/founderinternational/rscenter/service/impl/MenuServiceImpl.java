package com.founderinternational.rscenter.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.mapper.MenuMapper;
import com.founderinternational.rscenter.service.MenuService;
 

@Repository(value = "menuService")
@Transactional
public class MenuServiceImpl implements MenuService {
	@Resource(name = "menuMapper")
	private MenuMapper menuMapper;
	@Override
	public List<Menu> find() {
		String sql = "select * from Menu";
		return this.menuMapper.operateReturnBeans(sql);
	}

}