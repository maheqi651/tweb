/**
 *
 * @author geloin
 * @date 2012-5-5 上午10:26:34
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
import com.founderinternational.rscenter.entity.EZPFunctionService;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.tools.Constants;
 

 
@Repository(value = "eZPFunctinServiceMapper")
public interface EZPFunctinServiceMapper {


	   @Select(value = "${sql}")
	   List<EZPFunctionService> operateReturnBeans(@Param(value = "sql") String sql);
		/**
		 * 新增应用权限
		 * @param  ezfunction
		 * @return 影响的行数
		 * @throws Exception
		 */
	    @Insert("insert into  ez_p_function_service (ID,FUNCCODE,SERCODE,TABLECODE,THEMECODE"
		 +") VALUES(SEQ_P_FUNC_SER.nextval,"
	     +"#{FUNCTION},#{SERCODE},#{TABLECODE},#{THEMECODE})")  
	    public int insert(EZPFunctionService eZPFunctionService) throws Exception;
}
