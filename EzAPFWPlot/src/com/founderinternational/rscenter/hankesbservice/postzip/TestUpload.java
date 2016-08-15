/*
 *	Title£ºTestUpload.java<br>
 *  Date: 2015-12-14 ÏÂÎç2:09:18<br>
 * 	Copyright (c) 2015 Founder International Co.,Ltd.<br>
 *
 * 	WebSite: http://www.founder.com<br>
 * 
 */
package com.founderinternational.rscenter.hankesbservice.postzip;
/**
 *  <br>
 * 
 * @author liu_xiaohui
 * @version 1.0
 */
public class TestUpload
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
        
	HttpXmlClient.SubmitPost("http://10.235.36.3/monitor/FileUploadService?app=VG_PORTAL_02,ESB_4_2_20_36_114","C:/Users/founder1/workspace20151216/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/EzAPFWPlot/zip/http/CE20150928.zip");
	
	}

}

