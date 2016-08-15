package com.founderinternational.rscenter.entity;

import java.io.Serializable;
import java.util.Date;

public class EZServiceApply  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID;
	private String FUNCODE; 
	private String SERCODE;
	private Integer STATUS;
	private Date APPLYTIME;
	private Date DEALTIME;
	private String APPLYUSER;
	private String DEALUSER;
	private String REPLY;

	public String getREPLY() {
		return REPLY;
	}
	public void setREPLY(String rEPLY) {
		REPLY = rEPLY;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFUNCODE() {
		return FUNCODE;
	}
	public void setFUNCODE(String fUNCODE) {
		FUNCODE = fUNCODE;
	}
	public String getSERCODE() {
		return SERCODE;
	}
	public void setSERCODE(String sERCODE) {
		SERCODE = sERCODE;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public Date getAPPLYTIME() {
		return APPLYTIME;
	}
	public void setAPPLYTIME(Date aPPLYTIME) {
		APPLYTIME = aPPLYTIME;
	}
	public Date getDEALTIME() {
		return DEALTIME;
	}
	public void setDEALTIME(Date dEALTIME) {
		DEALTIME = dEALTIME;
	}
	public String getAPPLYUSER() {
		return APPLYUSER;
	}
	public void setAPPLYUSER(String aPPLYUSER) {
		APPLYUSER = aPPLYUSER;
	}
	public String getDEALUSER() {
		return DEALUSER;
	}
	public void setDEALUSER(String dEALUSER) 
	{
		DEALUSER = dEALUSER;
	}
	 

}
