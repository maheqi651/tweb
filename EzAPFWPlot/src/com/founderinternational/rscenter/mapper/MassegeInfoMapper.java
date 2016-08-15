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
import com.founderinternational.rscenter.entity.EZUser;
import com.founderinternational.rscenter.entity.MassegeInfo;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.tools.Constants;
 
@Repository(value = "massegeInfoMapper")
public interface MassegeInfoMapper {
	   
	   
	   
	   
	   @Select(value = "${sql}")
	   List<MassegeInfo> operateReturnBeans(@Param(value = "sql") String sql);
	   @Select(value = "${sql}")
	   int  operateReturnBeanscount(@Param(value = "sql") String sql);
	   @Select(value = "${sql}")
	   MassegeInfo  operateReturnBeansOne(@Param(value = "sql") String sql);
		 
	    @Insert("insert into massge_info (TITLE,CONTENT,TIMES,STATUS,TYPE,"
		 +"USERNAME) VALUES(#{TITLE},"
	     +"#{CONTENT},#{TIMES},#{STATUS},#{TYPE},#{USERNAME}"
	     +")")  
	    public int insert(MassegeInfo massegeInfo) throws Exception;
	    @Update("update massge_info set STATUS=#{STATUS}  where ID=#{ID}")
		public int updateMassegeInfo(MassegeInfo massegeInfo);
	   
}
