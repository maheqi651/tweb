package com.founderinternational.rscenter.entity;

import java.io.Serializable;

public class EZService  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String SERVICEID;
	private String NAME; 
	private String METHODNAME;
	private String INFO;
	private String TYPE;
	private String PARENTSERVICEID;
	private String TABLECODE;
	private String ORIGIN_SERVICEID;
	private String THEMECODE;
	private String ID;
    private Integer SEQ;
    private EZServiceMditemDEF eZServiceMditemDEF;
    
    private String SCORE;			
    private String REGIONTYPE;		
    private String INTERFACETYPE;			

    private Integer ACCESSCOUNT;
    
    
	public Integer getACCESSCOUNT() {
		return ACCESSCOUNT;
	}
	public void setACCESSCOUNT(Integer aCCESSCOUNT) {
		ACCESSCOUNT = aCCESSCOUNT;
	}
	public String getSCORE() {
		return SCORE;
	}
	public void setSCORE(String sCORE) {
		SCORE = sCORE;
	}
	public String getREGIONTYPE() {
		return REGIONTYPE;
	}
	public void setREGIONTYPE(String rEGIONTYPE) {
		REGIONTYPE = rEGIONTYPE;
	}
	public String getINTERFACETYPE() {
		return INTERFACETYPE;
	}
	public void setINTERFACETYPE(String iNTERFACETYPE) {
		INTERFACETYPE = iNTERFACETYPE;
	}
	public EZServiceMditemDEF geteZServiceMditemDEF() {
		return eZServiceMditemDEF;
	}
	public void seteZServiceMditemDEF(EZServiceMditemDEF eZServiceMditemDEF) {
		this.eZServiceMditemDEF = eZServiceMditemDEF;
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
	public String getMETHODNAME() {
		return METHODNAME;
	}
	public void setMETHODNAME(String mETHODNAME) {
		METHODNAME = mETHODNAME;
	}
	public String getINFO() {
		return INFO;
	}
	public void setINFO(String iNFO) {
		INFO = iNFO;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getPARENTSERVICEID() {
		return PARENTSERVICEID;
	}
	public void setPARENTSERVICEID(String pARENTSERVICEID) {
		PARENTSERVICEID = pARENTSERVICEID;
	}
	public String getTABLECODE() {
		return TABLECODE;
	}
	public void setTABLECODE(String tABLECODE) {
		TABLECODE = tABLECODE;
	}
	public String getORIGIN_SERVICEID() {
		return ORIGIN_SERVICEID;
	}
	public void setORIGIN_SERVICEID(String oRIGIN_SERVICEID) {
		ORIGIN_SERVICEID = oRIGIN_SERVICEID;
	}
	public String getTHEMECODE() {
		return THEMECODE;
	}
	public void setTHEMECODE(String tHEMECODE) {
		THEMECODE = tHEMECODE;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public Integer getSEQ() {
		return SEQ;
	}
	public void setSEQ(Integer sEQ) {
		SEQ = sEQ;
	}
	 
	  
}
