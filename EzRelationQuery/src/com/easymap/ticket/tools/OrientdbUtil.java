package com.easymap.ticket.tools;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import com.orientechnologies.orient.client.remote.OServerAdmin;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
  
public class OrientdbUtil {
	private static String user="admin";
	private static String pass="admin";
	private  ODatabaseDocumentTx oDatabaseDocumentTx;
	 //private static String url="remote:10.235.36.18/peoplegzdb";
	private static String url="remote:10.28.5.235/peopledb";
	//private static String url="remote:localhost/peopledb01";
	private OrientdbUtil(){
		  
       /*   try {
        	  OServerAdmin oServerAdmin =new OServerAdmin("remote:10.235.36.18");
			  oServerAdmin.connect("root", "123456");
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		oDatabaseDocumentTx = ODatabaseDocumentPool.global().acquire(url, user, pass);
	}
	public static OrientdbUtil getinstance(){
		return new OrientdbUtil();
	}
    public static void createDb(){
        try {
            OServerAdmin oServerAdmin =new OServerAdmin("remote:localhost");
            oServerAdmin.connect("root", "123456");
            oServerAdmin.createDatabase("testdb1","document","plocal");
            oServerAdmin.close();
              
        } catch (Exception e) {
            e.printStackTrace();
        }
  
        return;
    }
  
    public static void addRecord(){
          
     /*   oDatabaseDocumentTx.open(user,pass);
        ODocument oDocument = new ODocument("Person");
        oDocument.field( "name", "寮犱笁" );
        oDocument.field( "surname", "寮犲皬涓� );
        oDocument.field( "city", new ODocument("City").field("name","娣卞湷").field("country", "涓浗") );
        oDocument.save();
        oDatabaseDocumentTx.close();*/
        return;
    }
      
    public  List<ODocument>   searchRecord(String sql)
    {
    	open();
    	List<ODocument> listResult = oDatabaseDocumentTx.query(
    			new OSQLSynchQuery<ODocument>(sql));
    	close();
    	return listResult;
    }
    
    public ODocument searchOneRecord(ORID rid)
    {
    	ODocument o=null;
    	open();
    	o=oDatabaseDocumentTx.load(rid);
    	close();
    	return o;
    }
    
    public  void close(){
    	if(!oDatabaseDocumentTx.isClosed()){
    		oDatabaseDocumentTx.close();
    	}
    }  
    public  void open(){
    	if(oDatabaseDocumentTx.isClosed())
    		oDatabaseDocumentTx.open(user,pass);
    }
    public static void main(String[] args) 
    {
        String sql="traverse V.in,E.out from (select expand(bothe()) from person where id='430703199101241523' ) while $depth<6";
         long time=System.currentTimeMillis();
        List<ODocument> olist=OrientdbUtil.getinstance().searchRecord(sql);
        System.out.println(System.currentTimeMillis()-time);
         for(ODocument o:olist)
         {
        	  
        	 try 
        	 {
				System.out.println(o.field("@RID").toString());
			 } catch (Exception e) 
        	 {
				 
				e.printStackTrace();
			 }
         }
    }
          
}