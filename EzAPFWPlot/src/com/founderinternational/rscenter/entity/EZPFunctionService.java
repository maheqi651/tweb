package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class EZPFunctionService implements Serializable {
	private static final long serialVersionUID = 1L;
	 
 
	private String ID;
	private String FUNCTION; 
	private String SERCODE;
	private String TABLECODE;
	private String THEMECODE;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFUNCTION() {
		return FUNCTION;
	}
	public void setFUNCTION(String fUNCTION) {
		FUNCTION = fUNCTION;
	}
	public String getSERCODE() {
		return SERCODE;
	}
	public void setSERCODE(String sERCODE) {
		SERCODE = sERCODE;
	}
	public String getTABLECODE() {
		return TABLECODE;
	}
	public void setTABLECODE(String tABLECODE) {
		TABLECODE = tABLECODE;
	}
	public String getTHEMECODE() {
		return THEMECODE;
	}
	public void setTHEMECODE(String tHEMECODE) {
		THEMECODE = tHEMECODE;
	}
	 
	 
	
	 
	  
}
