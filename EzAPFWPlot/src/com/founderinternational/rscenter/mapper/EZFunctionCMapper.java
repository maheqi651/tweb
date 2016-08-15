/**
 *
 * @author geloin
 * @date 2012-5-5 ÉÏÎç10:26:34
 */
package com.founderinternational.rscenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZFunctionC;
import com.founderinternational.rscenter.entity.Menu;
 

/**
 * 
 * @author geloin
 * @date 2012-5-5 ÉÏÎç10:26:34
 */
@Repository(value = "eZFunctionCMapper")
public interface EZFunctionCMapper {


	    @Select(value = "${sql}")
		List<EZFunctionC> operateReturnBeans(@Param(value = "sql") String sql);
	    @Insert("insert into ez_function_c (CODE,PARENTCODE,NAME,TYPE,DESCRIPTION,APPTYPE,OWNER,ADDRESS,PHONE) VALUES(#{CODE},#{PARENTCODE},#{NAME},#{TYPE},#{DESCRIPTION},#{APPTYPE},#{OWNER},#{ADDRESS},#{PHONE})")  
	    public int insertEZFuntionC(EZFunctionC ezfunctionC);
	    
	    @Update("update ez_function_c set PARENTCODE=#{PARENTCODE},NAME=#{NAME},"
				 + "TYPE=#{TYPE},DESCRIPTION=#{DESCRIPTION},DESCRIPTION=#{DESCRIPTION},APPTYPE=#{APPTYPE},"
				+ "OWNER=#{OWNER}  where CODE=#{CODE}")
		public int updateEZFuntionC(EZFunctionC ezfunctionc);
	    
	    @Select("select * from ez_function_c where code=#{CODE}")
		public EZFunctionC findByCode(@Param("CODE") String CODE);
	    
	    @Delete("delete from ez_function_c where CODE=#{CODE}")
		public int delete(@Param("CODE") String userid);
	    
}
