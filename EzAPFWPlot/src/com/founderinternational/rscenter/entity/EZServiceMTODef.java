package com.founderinternational.rscenter.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founderinternational.rscenter.tools.Constants;

public class EZServiceMTODef {
        public static EZServiceMditemDEF MTOdef(List<EZServiceMditem> list){
        	if(list!=null)
        		if(list.size()>0)
        		{
        			EZServiceMditemDEF def=new EZServiceMditemDEF();
        			for(EZServiceMditem em:list)
        			{
        			   if(Constants.SMD_BASE_ACOUNT.equals(em.getMD_CODE()) )
        			   {//访问次数
        				   def.setAcount(em.getMD_VALUE());
        			   }else if(Constants.SMD_BASE_COMPANY.equals(em.getMD_CODE()))
        			   {//访问链接
        				   
        				   def.setCompany(em.getMD_VALUE());
        			   }else if(Constants.SMD_BASE_DZ.equals(em.getMD_CODE()))
        			   {//点赞
        				   
        				   def.setDz(em.getMD_VALUE());
        			   }else if(Constants.SMD_BASE_GET.equals(em.getMD_CODE()))
        			   {//访问方式
        				   
        				   def.setGET(em.getMD_VALUE());
        			   }else if(Constants.SMD_BASE_VERSION.equals(em.getMD_CODE()))
        			   {//版本
        				   
        				   def.setVersion(em.getMD_VALUE());
        			   }else if(Constants.SMD_BASE_IMAGEURL.equals(em.getMD_CODE()))
        			   {//图片
        				   def.setImageurl(em.getMD_VALUE());
        			   }else if(Constants.SMD_BASE_TIME.equals(em.getMD_CODE()))
        			   {//时间
        				   def.setTime(em.getMD_VALUE());
        			   }else if(Constants.SMD_BASE_USER.equals(em.getMD_CODE()))
        			   {//用户
        				   def.setUser(em.getMD_VALUE());
        			   }
        			}
        			return def;
        		}
        	return null;
        }
}
