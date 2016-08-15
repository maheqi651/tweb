/**
 *
 * @author geloin
 * @date 2012-5-5 上午10:26:34
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
import com.founderinternational.rscenter.entity.Menu;
 

/**
 * 
 * @author geloin
 * @date 2012-5-5 上午10:26:34
 */
@Repository(value = "eZFunctionMapper")
public interface EZFunctionMapper {
	@Select(value = "${sql}")
	@Results(value = { @Result(id = true, property = "CODE", column = "CODE"),
			@Result(property = "PARENTCODE", column = "PARENTCODE"),
			@Result(property = "NAME", column = "NAME"),
			@Result(property = "TYPE", column = "TYPE"),
			@Result(property = "DESCRIPTION", column = "DESCRIPTION") ,
			@Result(property = "APPTYPE", column = "APPTYPE"),
			@Result(property = "OWNER", column = "OWNER"),
			@Result(property = "SEQ", column = "SEQ")})
		List<EZFunction> operateReturnBeans(@Param(value = "sql") String sql);
		/**
		 * 新增应用
		 * @param  ezfunction
		 * @return 影响的行数
		 * @throws Exception
		 */
	    @Insert("insert into ez_function (CODE,PARENTCODE,NAME,TYPE,DESCRIPTION,APPTYPE,OWNER,SEQ) VALUES(#{CODE},#{PARENTCODE},#{NAME},#{TYPE},#{DESCRIPTION},#{APPTYPE},#{OWNER},#{SEQ})")  
	    public int insertEZFuntion(EZFunction ezfunction) throws Exception;
	    @Update("update ez_function set PARENTCODE=#{PARENTCODE},NAME=#{NAME},"
				 + "TYPE=#{TYPE},DESCRIPTION=#{DESCRIPTION},DESCRIPTION=#{DESCRIPTION},APPTYPE=#{APPTYPE},"
				+ "OWNER=#{OWNER},SEQ=#{SEQ}  where CODE=#{CODE}")
		public int updateEZFuntion(EZFunction ezfunction);
	    
	    @Select("select * from ez_function where code=#{CODE}")
		public EZFunction findByCode(@Param("CODE") String CODE);
	    
	    @Delete("delete from ez_function where CODE=#{CODE}")
		public int delete(@Param("CODE") String userid);
	    
}
