package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class FwSqTable  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer ID;
	private String FWNAME;		
	private String INTERFACETYPE;		
	private String FWINFO;	
	private String IMAGEURL;	
	private String FWTYPE;		
	private String FWURL;		
	private String FWHELPURL;	
	private String DEMOURL;		
	private String PUBLISHTYPE;		
	private String FWREGION;		
	private String APPLYTIME;			
	private String DEALTIME;		
	private String REPLY;		
	private Integer STATUS;
	private String DEALUSER;
	private String APPLYUSER;
	private String METHODNAME;
	
	public String getMETHODNAME() {
		return METHODNAME;
	}
	public void setMETHODNAME(String mETHODNAME) {
		METHODNAME = mETHODNAME;
	}
	public String getDEALUSER() {
		return DEALUSER;
	}
	public void setDEALUSER(String dEALUSER) {
		DEALUSER = dEALUSER;
	}
	public String getAPPLYUSER() {
		return APPLYUSER;
	}
	public void setAPPLYUSER(String aPPLYUSER) {
		APPLYUSER = aPPLYUSER;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getFWNAME() {
		return FWNAME;
	}
	public void setFWNAME(String fWNAME) {
		FWNAME = fWNAME;
	}
	public String getINTERFACETYPE() {
		return INTERFACETYPE;
	}
	public void setINTERFACETYPE(String iNTERFACETYPE) {
		INTERFACETYPE = iNTERFACETYPE;
	}
	public String getFWINFO() {
		return FWINFO;
	}
	public void setFWINFO(String fWINFO) {
		FWINFO = fWINFO;
	}
	public String getIMAGEURL() {
		return IMAGEURL;
	}
	public void setIMAGEURL(String iMAGEURL) {
		IMAGEURL = iMAGEURL;
	}
	public String getFWTYPE() {
		return FWTYPE;
	}
	public void setFWTYPE(String fWTYPE) {
		FWTYPE = fWTYPE;
	}
	public String getFWURL() {
		return FWURL;
	}
	public void setFWURL(String fWURL) {
		FWURL = fWURL;
	}
	public String getFWHELPURL() {
		return FWHELPURL;
	}
	public void setFWHELPURL(String fWHELPURL) {
		FWHELPURL = fWHELPURL;
	}
	public String getDEMOURL() {
		return DEMOURL;
	}
	public void setDEMOURL(String dEMOURL) {
		DEMOURL = dEMOURL;
	}
	public String getPUBLISHTYPE() {
		return PUBLISHTYPE;
	}
	public void setPUBLISHTYPE(String pUBLISHTYPE) {
		PUBLISHTYPE = pUBLISHTYPE;
	}
	public String getFWREGION() {
		return FWREGION;
	}
	public void setFWREGION(String fWREGION) {
		FWREGION = fWREGION;
	}
	public String getAPPLYTIME() {
		return APPLYTIME;
	}
	public void setAPPLYTIME(String aPPLYTIME) {
		APPLYTIME = aPPLYTIME;
	}
	public String getDEALTIME() {
		return DEALTIME;
	}
	public void setDEALTIME(String dEALTIME) {
		DEALTIME = dEALTIME;
	}
	public String getREPLY() {
		return REPLY;
	}
	public void setREPLY(String rEPLY) {
		REPLY = rEPLY;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}		



}
