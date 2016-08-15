package com.founderinternational.rscenter.tools;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	 public static String PRIVATEKEYSTR="";
	 public static final String COOKIEID="auth201611114";
	 public static String CAS="cas20160125";
	 public static String AHTUURL="";
	 public static String URLSTR="";
	 public static String ISHANKSERVICE="false";//启动自动挂载服务
	 public static String HANKTYPE="http";
	 public static String FWZXDKWS="8099";
	 public static String FWZXDKHTTP="8111";
	 public static String ESBURL="http://10.235.36.3/monitor/FileUploadService?app=VG_PORTAL_02,ESB_4_2_20_36_114";
	 public static String EzManagerUrl="http://10.235.36.7:8080/EzManagerV6";
	 public final static String HANKHTTP="http";
	 public final static String HANKWSDL="wsdl";
	 //public final static String FWZXIPDZ="8099";
	 //public final static String ESBFWDZ="http://172.18.70.153";
     
     public final static String EZSPATIAL="ezspatial";
     public final static String ESBSERVICELOGVISIT="yw6";
     public final static String PARENTCODE="01";
     public final static int pagesize=10;
     public final static int pagesizeindex=12;
     public final static String ADMINROLE="admin";
     public final static String COMROLE="role";
     public final static  int TGSTATUS=1;
     public final static  int TGBMSTATUS=2;
     public final static  int jjSTATUS=-1;
     public final static  int STATUS=0;
     public final static  int ASC=0;
     public final static  int DESC=1;
     public final static  int NOSORT=-1;
     public final static  int MASSAGEFWTG=1;
     public final static  int MASSAGEFWJJ=-1;
     public final static  int MASSAGEAPTG=2;
     public final static  int MASSAGEAPJJ=-2;
     public final static  int MASSAGEDY =3;
     public final static  int MASSAGEPF=4;
     public final static  int MASSAGESTATUSWD=0;
     public final static  int MASSAGESTATUSYD=1;
     public final static  int MASSAGESTATUSSC=-1;
     public final static String USER="user";
     public final static String ISPARENTS="UnKnown";
     public final static String TREEFLAG="TREEFLAG";
     public final static String SMD_BASE_COMPANY="SMD_BASE_COMPANY";
     public final static String SMD_BASE_VERSION="SMD_BASE_VERSION";
     public final static String SMD_BASE_GET="SMD_BASE_GET";
     public final static String SMD_BASE_ACOUNT="SMD_BASE_ACOUNT";
     public final static String SMD_BASE_DZ="SMD_BASE_DZ";
     public final static String SMD_BASE_IMAGEURL="SMD_BASE_IMAGEURL";
     public final static String SMD_BASE_TIME="SMD_BASE_TIME";
     public final static String SMD_BASE_USER="SMD_BASE_USER";
     
     public final static String FWZXDKHM="FWZXDKHM";
     public final static String FWZXBS="FWZXBS";
     public final static String FWSSZXDKHM="FWSSZXDKHM";
     public final static String FWXZQHDM="FWXZQHDM";
     public final static String FWMMDJDM="FWMMDJDM";
     public final static String TGZZDSYFW="TGZZDSYFW";
     public final static String ZXZDSYFW="ZXZDSYFW";
     public final static String FWTZRQSJ="FWTZRQSJ";
     public final static String FWZCRQSJ="FWZCRQSJ";
     public final static String FWZTDM="FWZTDM";
     public final static String FWTGZBS="FWTGZBS";
     
     
     public final static String FWNRBZ="FWNRBZ";
     public final static String LYFSDM="LYFSDM";
     public final static String FWBBH="FWBBH";
     public final static String FWZYXY="FWZYXY";
     public final static String FWZYKFYYLXDM="FWZYKFYYLXDM";
     public final static String GJBZGFBH="GJBZGFBH";
     public final static String SSGAYWFL="SSGAYWFL";
     
     public final static String GLDWDM="GLDWDM";
     public final static String SSYYXTBH="SSYYXTBH";
     public final static String FWZYYSFLDM="FWZYYSFLDM";
     
     
     public final static  String FWZYGSFWBH="FWZYGSFWBH";
     public final static  String GSDQDM="GSDQDM";
     public final static  String GSYYXTBH="GSYYXTBH";
     public final static  String GSGAYWFLDM="GSGAYWFLDM"; 
     
     public final static String ADMINTYPE="0";
     public final static String[] INTERFACETYPE={"Javascript","Flex","WebService"};
     public final static String[] REGIONTYPE={"省厅","成都","阿坝州","巴中","德阳","甘孜州","广安","广元","乐山","凉山州","泸州"};
     public final static Map<String,String> dicParentService=new HashMap<String,String>();
     public static Map<String,String> dicMap=new HashMap<String,String>();
     static{
    	 dicMap.put("Res", "资源目录服务");
    	 dicMap.put("RBSP", "数据访问服务");
    	 dicMap.put("NoSQL", "非结构化服务");
    	 dicMap.put("Analyse", "分析类服务");
    	 dicMap.put("Monitor", "服务监控接口");
    	 dicParentService.put("Res", "ROOT_01");
    	 dicParentService.put("RBSP", "ROOT_02");
    	 dicParentService.put("NoSQL", "ROOT_03");
    	 dicParentService.put("Analyse", "ROOT_04");
    	 dicParentService.put("Monitor", "ROOT_05");
     }
}
