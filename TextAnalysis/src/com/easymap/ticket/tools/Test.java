package com.easymap.ticket.tools;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
          JSONObject ob=new JSONObject();
          ob.put("delType", "rows");
        
          ob.put("hbaseTableName", "THDB_DHHM02");
          //startRow
          ob.put("hbaseInstance","HBase01");
          JSONObject parm=new JSONObject();
          
          parm.put("startRow", "");
          parm.put("stopRow", "~");
          parm.put("qualifierConditions","TABLE_NAME:S_GZZCLS_NEW:S_GZZCLS_NEW");
          ob.put("params", parm);
          System.out.println(ob.toString());
	}

}
