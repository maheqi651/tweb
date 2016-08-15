package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class ServiceVisitInfo implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private String REQUEST_ID;		
	private String MSG_ID;			
	private String APP_NAME;			
	private Integer MESSAGE_TYPE;		
	private String REQUEST_CONTENT;			
	private String RESPONSE_CONTENT;			
	private String REQEUST_DATETIME;			
	private String RESPONSE_TIME;			
	private String SERVICE_NAME;			
	private Integer SERVICE_VERSION;		
	private String PRODUCER_IP;			
	private String CONSUMER_IP;		
	private String SENDER_ID;		
	private Integer RESPONSE_RESULT_COUNT;			
	private String REQUEST_SERIAL;
	public String getREQUEST_ID() {
		return REQUEST_ID;
	}
	public void setREQUEST_ID(String rEQUEST_ID) {
		REQUEST_ID = rEQUEST_ID;
	}
	public String getMSG_ID() {
		return MSG_ID;
	}
	public void setMSG_ID(String mSG_ID) {
		MSG_ID = mSG_ID;
	}
	public String getAPP_NAME() {
		return APP_NAME;
	}
	public void setAPP_NAME(String aPP_NAME) {
		APP_NAME = aPP_NAME;
	}
	public Integer getMESSAGE_TYPE() {
		return MESSAGE_TYPE;
	}
	public void setMESSAGE_TYPE(Integer mESSAGE_TYPE) {
		MESSAGE_TYPE = mESSAGE_TYPE;
	}
	public String getREQUEST_CONTENT() {
		return REQUEST_CONTENT;
	}
	public void setREQUEST_CONTENT(String rEQUEST_CONTENT) {
		REQUEST_CONTENT = rEQUEST_CONTENT;
	}
	public String getRESPONSE_CONTENT() {
		return RESPONSE_CONTENT;
	}
	public void setRESPONSE_CONTENT(String rESPONSE_CONTENT) {
		RESPONSE_CONTENT = rESPONSE_CONTENT;
	}
	public String getREQEUST_DATETIME() {
		return REQEUST_DATETIME;
	}
	public void setREQEUST_DATETIME(String rEQEUST_DATETIME) {
		REQEUST_DATETIME = rEQEUST_DATETIME;
	}
	public String getRESPONSE_TIME() {
		return RESPONSE_TIME;
	}
	public void setRESPONSE_TIME(String rESPONSE_TIME) {
		RESPONSE_TIME = rESPONSE_TIME;
	}
	public String getSERVICE_NAME() {
		return SERVICE_NAME;
	}
	public void setSERVICE_NAME(String sERVICE_NAME) {
		SERVICE_NAME = sERVICE_NAME;
	}
	public Integer getSERVICE_VERSION() {
		return SERVICE_VERSION;
	}
	public void setSERVICE_VERSION(Integer sERVICE_VERSION) {
		SERVICE_VERSION = sERVICE_VERSION;
	}
	public String getPRODUCER_IP() {
		return PRODUCER_IP;
	}
	public void setPRODUCER_IP(String pRODUCER_IP) {
		PRODUCER_IP = pRODUCER_IP;
	}
	public String getCONSUMER_IP() {
		return CONSUMER_IP;
	}
	public void setCONSUMER_IP(String cONSUMER_IP) {
		CONSUMER_IP = cONSUMER_IP;
	}
	public String getSENDER_ID() {
		return SENDER_ID;
	}
	public void setSENDER_ID(String sENDER_ID) {
		SENDER_ID = sENDER_ID;
	}
	public Integer getRESPONSE_RESULT_COUNT() {
		return RESPONSE_RESULT_COUNT;
	}
	public void setRESPONSE_RESULT_COUNT(Integer rESPONSE_RESULT_COUNT) {
		RESPONSE_RESULT_COUNT = rESPONSE_RESULT_COUNT;
	}
	public String getREQUEST_SERIAL() {
		return REQUEST_SERIAL;
	}
	public void setREQUEST_SERIAL(String rEQUEST_SERIAL) {
		REQUEST_SERIAL = rEQUEST_SERIAL;
	}			

	  
}
