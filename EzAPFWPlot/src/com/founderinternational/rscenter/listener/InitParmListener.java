package com.founderinternational.rscenter.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.RSAUtil;
import com.founderinternational.rscenter.totalchart.TotalTimerStart;

public class InitParmListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		String privatepath =arg0.getServletContext().getRealPath("privatekey");
		// sce.getServletContext().getInitParameter("config");
		    System.out.println(privatepath);
		    Constants.PRIVATEKEYSTR=RSAUtil.getKeystoreString(privatepath+"\\privateKey.keystore");
		    System.out.println("..........加载私钥........");
		    System.out.println("..........私钥........"+Constants.PRIVATEKEYSTR);
		    Properties prop = new Properties();   
	        InputStream in = InitParmListener.class.getResourceAsStream("/esbparm.properties");  
	        //System.out.println(in);
	        try {
				prop.load(in);
				//System.out.println(prop.getProperty("HANKTYPE").trim()+"-----------");
				//Constants.HANKTYPE=prop.getProperty("HANKTYPE").trim();
				Constants.FWZXDKWS=prop.getProperty("FWZXDKWS").trim();
				Constants.FWZXDKHTTP=prop.getProperty("FWZXDKHTTP").trim();
				Constants.ESBURL=prop.getProperty("ESBURL").trim();
				Constants.ISHANKSERVICE=prop.getProperty("ISHANKSERVICE").trim();
				//认证连接
				Constants.AHTUURL=prop.getProperty("AHTUURL").trim();
				Constants.URLSTR=prop.getProperty("URLSTR").trim(); 
				Constants.EzManagerUrl=prop.getProperty("ezManagerLocation").trim(); 
			} catch (IOException e) {
				e.printStackTrace();
			}  
	        
	        
	}

}
