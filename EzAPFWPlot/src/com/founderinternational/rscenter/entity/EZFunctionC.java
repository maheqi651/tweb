package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class EZFunctionC implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private String CODE;
	private String PARENTCODE; 
	private String NAME;
	private String TYPE;
	private String DESCRIPTION;
	private String APPTYPE;
	private String OWNER;
	private String ADDRESS;
	private String PHONE;

	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
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
}
