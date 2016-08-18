package com.easymap.comm;

import com.dragonsoft.adapter.service.QueryAdapterSend;

public class RequestQuery {
    public  static RequestQuery getInstance(){
        	return new RequestQuery();
    }
	public  String requestAdapter(String condition) 
	{
		String strReturns = "";
		try {
			QueryAdapterSend adapter = new QueryAdapterSend();
						strReturns = adapter.sendQuery("Query", condition, "440105197007150046", "ÌÆÏþºì", "130103196401240322");//440182199410170612
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strReturns;
	}
	public static void main(String[] args) 
	{
		System.out.println("++++++++++++++++++++++++++++++message:" + new RequestQuery().requestAdapter("SFZH='440882199007101623'"));//130103196401240322
	}
}
