package com.founderinternational.rscenter.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.founderinternational.rscenter.entity.MassegeInfo;
import com.founderinternational.rscenter.mapper.MassegeInfoMapper;
import com.founderinternational.rscenter.service.MassegeInfoService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.impl.TimeGeneral;

@Repository(value = "massegeInfoService")
@Transactional
public class MassegeInfoServiceImpl implements MassegeInfoService {
	
	
    @Resource(name="massegeInfoMapper")
    private MassegeInfoMapper massegeInfoMapper;
	@Override
	public int addMessageInfo(String user, int type,String title,String content) {
		
	    MassegeInfo massegeInfo=new MassegeInfo();
	    massegeInfo.setSTATUS(Constants.MASSAGESTATUSWD);
	    massegeInfo.setTIMES(TimeGeneral.getInstance().next());
	    massegeInfo.setUSERNAME(user);
	    massegeInfo.setTYPE(type+"");
	    massegeInfo.setTITLE(title);
	    massegeInfo.setCONTENT(content);
		try {
			return this.massegeInfoMapper.insert(massegeInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<MassegeInfo> find(String user, int status) {
        String sql="select  *  from massge_info  where username='"+user+"' and status="+status+" order by id desc";
		//System.out.println(sql);
        return this.massegeInfoMapper.operateReturnBeans(sql);
	}

	@Override
	public MassegeInfo findone(String id) {
	    String sql="select * from massge_info  where ID="+id;
		return this.massegeInfoMapper.operateReturnBeansOne(sql);
	}

	@Override
	public List<MassegeInfo> find(String user, int status, int start, int end) {
		
		return null;
	}

	@Override
	public int getcount(String user, int status) {
		String sql="select count(*) from massge_info  where user='"+user+"' and status="+status;
		
		return this.massegeInfoMapper.operateReturnBeanscount(sql);
		 
	}

	@Override
	public int update(MassegeInfo minfo) {
		 return this.massegeInfoMapper.updateMassegeInfo(minfo);
	}

}
