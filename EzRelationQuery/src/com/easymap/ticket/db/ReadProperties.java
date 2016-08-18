package com.easymap.ticket.db;

import java.util.ResourceBundle;

public class ReadProperties {
	public static ResourceBundle bundle;

	public static final String get(String key) {
		return bundle.getString(key);
	}

	public static final String getDriver() {
		return get("driverClassName");
	}

	public static final String getUrl() {
		return get("jdbc_url");
	}

	public static final String getUsername() {
		return get("jdbc_username");
	}

	public static final String getPassword() {
		return get("jdbc_password");
	}
	public static final String getEzbigdateurl(){
		return get("ezbigdateurl");
	}
	public static final String getimageserverurl(){
		return get("imageserverurl");
	}
}