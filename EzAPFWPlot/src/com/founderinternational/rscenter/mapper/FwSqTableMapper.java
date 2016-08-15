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
import com.founderinternational.rscenter.tools.Constants;
 

 
@Repository(value = "fwSqTableMapper")
public interface FwSqTableMapper {
	
	    @Select(value = "${sql}")
	    List<FwSqTable> operateReturnBeans(@Param(value = "sql") String sql);
	    
	    
	    @Select(value = "${sql}")
	    FwSqTable operateReturnOneBeans(@Param(value = "sql") String sql);
	    
		 	
		 
		
	    @Insert("insert into  fw_sq_table (METHODNAME,FWNAME,INTERFACETYPE,FWINFO,FWTYPE,IMAGEURL,FWURL,FWHELPURL,DEMOURL,PUBLISHTYPE,"
	    		+ "FWREGION,APPLYTIME,STATUS,APPLYUSER) VALUES(#{METHODNAME},#{FWNAME},"
	     +"#{INTERFACETYPE},#{FWINFO},#{FWTYPE},#{IMAGEURL},#{FWURL},#{FWHELPURL},"
	     + "#{DEMOURL},#{PUBLISHTYPE},#{FWREGION},#{APPLYTIME},#{STATUS},#{APPLYUSER})")  
	    public int insert(FwSqTable fwSqTable);
	    
	    
	    @Update("update fw_sq_table set  "
				 + "STATUS=#{STATUS} ,DEALTIME=#{DEALTIME},"
				+ " DEALUSER=#{DEALUSER},"
				 +"REPLY=#{REPLY}  where ID=#{ID}")
	    public int update(FwSqTable fwSqTable);
	    
	    
	    @Update("update fw_sq_table set  "
				 + "METHODNAME=#{METHODNAME} ,FWNAME=#{FWNAME},"
				 + " INTERFACETYPE=#{INTERFACETYPE},"
				 + "FWINFO=#{FWINFO} ,FWTYPE=#{FWTYPE},"
				 + "IMAGEURL=#{IMAGEURL} ,FWURL=#{FWURL},"
				 + "FWHELPURL=#{FWHELPURL} ,DEMOURL=#{DEMOURL},"
				 + " PUBLISHTYPE=#{PUBLISHTYPE},"
				 + "FWREGION=#{FWREGION} ,APPLYTIME=#{APPLYTIME},"
				 + "STATUS=#{STATUS} ,APPLYUSER=#{APPLYUSER} "
				 +"  where ID=#{ID}")
	    public int updateDetail(FwSqTable fwSqTable);
}
