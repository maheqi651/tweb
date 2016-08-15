/**
 *
 * @author geloin
 * @date 2012-5-5 ионГ10:26:34
 */
package com.founderinternational.rscenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.founderinternational.rscenter.entity.EZDataTree;
import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.Menu;
 

/**
 * 
 * @author geloin
 * @date 2012-5-5 ионГ10:26:34
 */
@Repository(value = "eZDataTreeMapper")
public interface EZDataTreeMapper {
	@Select(value = "${sql}")
		List<EZDataTree> operateReturnBeans(@Param(value = "sql") String sql);
		 
}
