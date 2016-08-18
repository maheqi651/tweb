package com.easymap.ticket.tools;

import java.awt.image.SampleModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import com.easymap.ticket.servlet.ProxWBRequest;
 
public class Tools {
	

	public static void main(String[] args) {
		 SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		 
		 Date d=new Date();
		 try {
			long t1=f.parse("2015-11-06").getTime();
			long t2=f.parse("1992-02-14").getTime();
			System.out.println(((t1-t2)/(1000*60*60*24)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 System.out.println(365*2+31+5);
	}
	
   public static  String dostr6(String str){
		return str.substring(str.length()-6);
	}
   public static ArrayList<Map.Entry<String,JSONObject>> sortMap(Map map)
   {
	   
	   List<Map.Entry<String,JSONObject>> entries =new ArrayList<Map.Entry<String,JSONObject>>(map.entrySet());
	   Collections.sort(entries,new Comparator<Map.Entry<String,JSONObject>>(){
		@Override
		public int compare(Entry<String, JSONObject> o1,
				Entry<String, JSONObject> o2) 
		{
			return o2.getValue().getInt("count")-o1.getValue().getInt("count");
		}
	   });
	    return (ArrayList<Map.Entry<String,JSONObject>>)entries;
   }
   
   
   

}
