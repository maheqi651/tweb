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
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.tools.Constants;
 

/**
 * 
 * @author  
 * @date  
 */
@Repository(value = "eZServiceMeitemMapper")
public interface EZServiceMeitemMapper {


	  @Select(value = "${sql}")
	  List<EZServiceMditem> operateReturnBeans(@Param(value = "sql") String sql);
		/**
		 * 新增服务
		 * @param  EZServiceMeitemMapper
		 * @return 影响的行数
		 * @throws Exception
		 */
	    @Insert("insert into "+Constants.EZSPATIAL+".ez_service_mditem (MD_CODE,SERVICE_ID,MD_VALUE) VALUES(#{MD_CODE},#{SERVICE_ID},#{MD_VALUE})")  
	    public int insert(EZServiceMditem  ezservicemeitem) throws Exception;
	   
	    @Update("update "+Constants.EZSPATIAL+".ez_service_mditem set MD_CODE=#{MD_CODE},SERVICE_ID=#{SERVICE_ID},"
				 + "MD_VALUE=#{MD_VALUE} "
				+ "  where ID=#{ID}")
		public int update(EZServiceMditem ezservicemeitem);
	    
	    
	    /*@Delete("delete * from "+Constants.EZSPATIAL+".ez_service_mditem where service_id=#{serviceid}")
	    public int delete(String serviceid);*/
	    
	    @Delete(value = "${sql}")
	    public int delete(@Param(value = "sql") String sql);
}
