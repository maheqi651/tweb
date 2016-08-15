package com.founderinternational.rscenter.entity;

import java.io.Serializable;
import java.util.Map;

public class EZServiceMditemDEF  implements Serializable {
	private static final long serialVersionUID = 1L;
	private  String  Company;
	private  String Version; 
	private  String GET;
	private  String imageurl;
	private  String acount;
	private  String dz;
	private String user;
	private String time;
	 
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getGET() {
		return GET;
	}
	public void setGET(String gET) {
		GET = gET;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getAcount() {
		return acount;
	}
	public void setAcount(String acount) {
		this.acount = acount;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	
	 
   	  
}
