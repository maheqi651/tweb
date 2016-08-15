package com.founderinternational.rscenter.entity.form;

import java.io.Serializable;
import java.util.Date;

public class ApplyVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String SERVICECODE;
	private String SERVICEID;
	private String NAME; 
	private String INFO;
	private String AID;
 	private String APPLYTIME;
	private String DEALTIME;
	private String APPLYUSER;
	private String DEALUSER;
	private String REPLY;
	private String APPNAME;
	private String DESCRIPTION;
	private String ADDRESS;
	private String PHONE;
	private String FUNCODE;
	private String METHODNAME;
	private String TYPE;
	
	
	
	
	/*
	 * select a.ID AID,a.APPLYTIME,a.DEALTIME,a.APPLYUSER,a.DEALUSER,a.REPLY,b.NAME APPNAME ,b.DESCRIPTION,b.ADDRESS,b.PHONE
	    ,c.SERVICEID,c.NAME,c.INFO from ez_service_apply   a,ez_function_c b,ezspatial.ez_service_info c where
          a.funcode=b.code and a.sercode=c.serviceid 
	 * 
	 * */
	
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getFUNCODE() {
		return FUNCODE;
	}
	public String getMETHODNAME() {
		return METHODNAME;
	}
	public void setMETHODNAME(String mETHODNAME) {
		METHODNAME = mETHODNAME;
	}
	public void setFUNCODE(String fUNCODE) {
		FUNCODE = fUNCODE;
	}
	public String getSERVICECODE() {
		return SERVICECODE;
	}
	public void setSERVICECODE(String sERVICECODE) {
		SERVICECODE = sERVICECODE;
	}
	public String getSERVICEID() {
		return SERVICEID;
	}
	public void setSERVICEID(String sERVICEID) {
		SERVICEID = sERVICEID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getINFO() {
		return INFO;
	}
	public void setINFO(String iNFO) {
		INFO = iNFO;
	}
    public String getAID() {
		return AID;
	}
	public void setAID(String aID) {
		AID = aID;
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
	public String getAPPLYUSER() {
		return APPLYUSER;
	}
	public void setAPPLYUSER(String aPPLYUSER) {
		APPLYUSER = aPPLYUSER;
	}
	
	public String getDEALUSER() {
		return DEALUSER;
	}
	public void setDEALUSER(String dEALUSER) {
		DEALUSER = dEALUSER;
	}
	public String getREPLY() {
		return REPLY;
	}
	public void setREPLY(String rEPLY) {
		REPLY = rEPLY;
	}
	public String getAPPNAME() {
		return APPNAME;
	}
	public void setAPPNAME(String aPPNAME) {
		APPNAME = aPPNAME;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
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
 
}
