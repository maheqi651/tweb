package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class ScoreTable implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private Integer ID;
	private Integer SCORE;	
	private String USERNAME;			
	private String IP;
	private String ADDTIME;	
	private String CONTENT;			
	private String REPLYCONTENT;			
	private String REPLYTIME;	
	private String REPLYUSER;		
	private String SERVICEID;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getSCORE() {
		return SCORE;
	}
	public void setSCORE(Integer sCORE) {
		SCORE = sCORE;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getADDTIME() {
		return ADDTIME;
	}
	public void setADDTIME(String aDDTIME) {
		ADDTIME = aDDTIME;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getREPLYCONTENT() {
		return REPLYCONTENT;
	}
	public void setREPLYCONTENT(String rEPLYCONTENT) {
		REPLYCONTENT = rEPLYCONTENT;
	}
	public String getREPLYTIME() {
		return REPLYTIME;
	}
	public void setREPLYTIME(String rEPLYTIME) {
		REPLYTIME = rEPLYTIME;
	}
	public String getREPLYUSER() {
		return REPLYUSER;
	}
	public void setREPLYUSER(String rEPLYUSER) {
		REPLYUSER = rEPLYUSER;
	}
	public String getSERVICEID() {
		return SERVICEID;
	}
	public void setSERVICEID(String sERVICEID) {
		SERVICEID = sERVICEID;
	}
	

}
