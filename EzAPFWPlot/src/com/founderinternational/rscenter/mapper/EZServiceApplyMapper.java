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
import com.founderinternational.rscenter.entity.EZServiceApply;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.ApplyVO;
import com.founderinternational.rscenter.tools.Constants;
 

 
@Repository(value = "eZServiceApplyMapper")
public interface EZServiceApplyMapper {


	    @Select(value = "${sql}")
	    List<EZServiceApply> operateReturnBeans(@Param(value = "sql") String sql);
		 
	    @Select(value = "${sql}")
	    EZServiceApply findOneApply(@Param(value = "sql") String sql);
	   
	    @Insert("insert into  ez_service_apply (ID,FUNCODE,SERCODE,STATUS,APPLYTIME,"
		 +"  APPLYUSER) VALUES(#{ID},"
	     +"#{FUNCODE},#{SERCODE},#{STATUS},#{APPLYTIME},#{APPLYUSER}"
	     +")")  
	    public int insert(EZServiceApply eZServiceApply);
	    @Update("update ez_service_apply set FUNCODE=#{FUNCODE},SERCODE=#{SERCODE},"
				 + "STATUS=#{STATUS},APPLYTIME=#{APPLYTIME},DEALTIME=#{DEALTIME},"
				+ "APPLYUSER=#{APPLYUSER},DEALUSER=#{DEALUSER},"
				 +"REPLY=#{REPLY}  where ID=#{ID}")
		public int update(EZServiceApply eZServiceApply);
	    @Select(value = "${sql}")
		List<ApplyVO> findApplyList(@Param(value = "sql") String sql);
	    
}
