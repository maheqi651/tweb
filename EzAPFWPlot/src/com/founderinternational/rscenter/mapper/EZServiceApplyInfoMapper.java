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
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.tools.Constants;
 

 
@Repository(value = "eZServiceApplyInfoMapper")
public interface EZServiceApplyInfoMapper {
	
	    @Select(value = "${sql}")
	    List<EZServiceApplyInfo> operateReturnBeans(@Param(value = "sql") String sql);
	    @Insert("insert into  ez_service_apply_info (AID,TABLECODE,THEMECODE) VALUES(#{AID},"
	     +"#{TABLECODE},#{THEMECODE})")  
	    public int insert(EZServiceApplyInfo eZServiceApplyInfo);
}
