/**
 *
 * @author geloin
 * @date 2012-5-5 ÉÏÎç10:26:34
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

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.ServiceVisitInfo;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.totalchart.TotalObject;
 

 
@Repository(value = "serviceVisitInfoMapper")
public interface ServiceVisitInfoMapper {
	   @Select(value = "${sql}")
	   List<ServiceVisitInfo> operateReturnBeans(@Param(value = "sql") String sql);
	   @Select(value = "${sql}")
	   int  operateReturnBeanscount(@Param(value = "sql") String sql);
	   @Select(value = "${sql}")
	   int operateReturnBeansdatacount(@Param(value = "sql") String sql);
	   @Select(value = "${sql}")
	   List<TotalObject> operateReturnTotalObject(@Param(value = "sql") String sql);
}
