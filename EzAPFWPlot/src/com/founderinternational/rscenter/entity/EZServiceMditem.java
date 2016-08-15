package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class EZServiceMditem  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID;
	private String MD_CODE; 
	private String SERVICE_ID;
	private String MD_VALUE;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMD_CODE() {
		return MD_CODE;
	}
	public void setMD_CODE(String mD_CODE) {
		MD_CODE = mD_CODE;
	}
	public String getSERVICE_ID() {
		return SERVICE_ID;
	}
	public void setSERVICE_ID(String sERVICE_ID) {
		SERVICE_ID = sERVICE_ID;
	}
	public String getMD_VALUE() {
		return MD_VALUE;
	}
	public void setMD_VALUE(String mD_VALUE) {
		MD_VALUE = mD_VALUE;
	}
   	  
}
