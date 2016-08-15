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
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.tools.Constants;
 

 
@Repository(value = "eZServiceMapper")
public interface EZServiceMapper {


	   @Select(value = "${sql}")
	   List<EZService> operateReturnBeans(@Param(value = "sql") String sql);
	   @Select(value = "${sql}")
	   int  operateReturnBeanscount(@Param(value = "sql") String sql);
	   @Select(value = "${sql}")
	   List<String>  operateReturnBeansServiceids(@Param(value = "sql") String sql);
		
	   
	   /**
		 * 新增应用
		 * @param  ezfunction
		 * @return 影响的行数
		 * @throws Exception
		 */
	    @Insert("insert into "+Constants.EZSPATIAL+".ez_service_info (SERVICEID,NAME,METHODNAME,TYPE,INFO,"
		 +"PARENTSERVICEID,REGIONTYPE,INTERFACETYPE,SCORE,ACCESSCOUNT) VALUES(#{SERVICEID},"
	     +"#{NAME},#{METHODNAME},#{TYPE},#{INFO},#{PARENTSERVICEID},#{REGIONTYPE},#{INTERFACETYPE},#{SCORE},#{ACCESSCOUNT}"
	     +")")  
	    public int insert(EZService eZService) throws Exception;
	   
	    @Update("update "+Constants.EZSPATIAL+".ez_service_info set  NAME=#{NAME},"
				 + "METHODNAME=#{METHODNAME},SCORE=#{SCORE},ACCESSCOUNT=#{ACCESSCOUNT},TYPE=#{TYPE},INFO=#{INFO},PARENTSERVICEID=#{PARENTSERVICEID}"
				 +"   where ID=#{ID}")
		public int update(EZService eZService);
	    
	    
	    
	    @Update("update "+Constants.EZSPATIAL+".ez_service_info set   "
				 + " SCORE=#{SCORE},ACCESSCOUNT=#{ACCESSCOUNT}  "
				 +"   where ID=#{ID}")
		public int updatefw(EZService eZService);
	    
}
