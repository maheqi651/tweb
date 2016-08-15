package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class EZFunction implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private String CODE;
	private String PARENTCODE; 
	private String NAME;
	private String TYPE;
	private String DESCRIPTION;
	private String APPTYPE;
	private String OWNER;
	private Integer SEQ;
	
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getPARENTCODE() {
		return PARENTCODE;
	}
	public void setPARENTCODE(String pARENTCODE) {
		PARENTCODE = pARENTCODE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getAPPTYPE() {
		return APPTYPE;
	}
	public void setAPPTYPE(String aPPTYPE) {
		APPTYPE = aPPTYPE;
	}
	public String getOWNER() {
		return OWNER;
	}
	public void setOWNER(String oWNER) {
		OWNER = oWNER;
	}
	public Integer getSEQ() {
		return SEQ;
	}
	public void setSEQ(Integer sEQ) {
		SEQ = sEQ;
	}
	
	  
}
