package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class EZServiceApplyInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	private String AID;
	private String TABLECODE; 
	private String THEMECODE;
	public String getAID() {
		return AID;
	}
	public void setAID(String aID) {
		AID = aID;
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
