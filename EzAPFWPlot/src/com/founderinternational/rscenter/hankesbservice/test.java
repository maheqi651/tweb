package com.founderinternational.rscenter.hankesbservice;

import com.founderinternational.rscenter.tools.Constants;

public class test {

	public static void main(String[] args) {
		testHTTP();
	}
	public static void testHTTP(){
		HankServiceBean hankServiceBean=new HankServiceBean();
		hankServiceBean.setFWurl("http://10.235.36.19:9989/EzAPFWPlot/"); 
		hankServiceBean.setServicename("CE201509281");
		hankServiceBean.setESBPort("8099");
		hankServiceBean.setESBContextPath("FWCX");
		HankESBService hankthread=new HankESBService();
		hankthread.setXmlPath("C:/Users/founder1/workspace20151216/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/EzAPFWPlot"); 
		hankthread.setType(Constants.HANKHTTP);
		hankthread.setHankServiceBean(hankServiceBean);
		new Thread(hankthread).start();
		
	}
    public  static void testWSDL(){
    	HankServiceBean hankServiceBean=new HankServiceBean();
		hankServiceBean.setFWurl("http://172.18.70.173:8080/EZFWRSPlot/fwzybm.html"); 
		hankServiceBean.setServicename("CE201509289");
		hankServiceBean.setESBPort("8019");
		hankServiceBean.setESBContextPath("FWCX9");
		HankESBService hankthread=new HankESBService();
		hankthread.setXmlPath("C:/Users/founder/workspace20151119app/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/EzAPFWPlot"); 
		hankthread.setType(Constants.HANKWSDL);
		hankthread.setHankServiceBean(hankServiceBean);
		new Thread(hankthread).start();
   }
}
