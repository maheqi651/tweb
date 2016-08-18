package com.easymap.ticket.tools;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static Map<String,String> dicMap=new HashMap<String,String>();
    public static Map<String,String> wbmap=new HashMap<String,String>();
    public static Map<String,String> jdmap=new HashMap<String,String>();
    public static String PRIVATEKEYSTR="";
    public static final String COOKIEID="auth201611114";
    public static String CAS="cas20160125";
    public static String EzManagerUrl="";
    public static String AHTUURL="";
    public static String URLSTR="";
    static{
    	dicMap.put("0201", "同房间");
    	dicMap.put("0202", "同入住同退房");
    	dicMap.put("0203", "同入住");
    	dicMap.put("0204", "同退房");
    	dicMap.put("0301", "同上网同下网");
    	dicMap.put("0302", "同上网");
    	dicMap.put("0303", "同下网");
    	dicMap.put("0304", "同上网时间重叠");
    }
}
