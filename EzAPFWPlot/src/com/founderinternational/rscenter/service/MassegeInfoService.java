package com.founderinternational.rscenter.service;

import java.util.List;

import com.founderinternational.rscenter.entity.MassegeInfo;

public interface MassegeInfoService {
       int addMessageInfo(String user,int type,String title,String content);
       List<MassegeInfo> find(String user,int status);
       MassegeInfo findone(String id);
       List<MassegeInfo> find(String user,int status,int start,int end);
       int getcount(String user,int status);
       int update(MassegeInfo minfo);
}
