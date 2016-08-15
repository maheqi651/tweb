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
import com.founderinternational.rscenter.entity.EZServiceApplyInfo;
import com.founderinternational.rscenter.entity.FwSqTable;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.ScoreTable;
import com.founderinternational.rscenter.tools.Constants;
 

 
@Repository(value = "scoreTableMapper")
public interface ScoreTableMapper {
	
	    @Select(value = "${sql}")
	    List<ScoreTable> operateReturnBeans(@Param(value = "sql") String sql);
	    
	    @Select(value = "${sql}")
	    int operateReturnOneBeans(@Param(value = "sql") String sql);
		
	    @Insert("insert into  scoretable (USERNAME,IP,SCORE,ADDTIME,CONTENT,SERVICEID"
	    		+ ") VALUES(#{USERNAME},#{IP},"
	     +"#{SCORE},#{ADDTIME},#{CONTENT},#{SERVICEID}"
	     + ")")  
	    public int insert(ScoreTable scoreTable);
	   
}
