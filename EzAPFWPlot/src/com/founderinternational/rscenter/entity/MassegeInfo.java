package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class MassegeInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer ID;
	private String TITLE;		
	private String CONTENT;		
	private String TIMES;	
	private Integer STATUS;
	private String TYPE;
	private String USERNAME	;
	
	
	public Integer getID() {
		return ID; 
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getTIMES() {
		return TIMES;
	}
	public void setTIMES(String tIMES) {
		TIMES = tIMES;
	}
	 
	 
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	} 		

	 
   	  
}
