package com.founderinternational.rscenter.tools;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class Tools {
	        public final static int uidsize=100; 
	        public static Stack<String> uuidstack=new Stack<String>();
			public static String CovertToUTF8(String str){
				try {
					return new String(str.getBytes("ISO8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return str;
				}
			}
			public static String getUUID(){
				if(uuidstack.empty())
				{
					getUUID(uidsize);
					return uuidstack.pop();
				}
				else{
					return  uuidstack.pop();
				}
			}
			
			
			private static String GeneratorUUID(){ 
		        String s = UUID.randomUUID().toString(); 
		        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
		    } 
		    /** 
		     * 获得指定数目的UUID 
		     * @param number int 需要获得的UUID数量 
		     * @return String[] UUID数组 
		     */ 
			private static void getUUID(int number){ 
		        if(number < 1){ 
		            return;
		        } 
		        for(int i=0;i<number;i++){ 
		        	uuidstack.push(GeneratorUUID()); 
		        } 
		        
		    } 
		  /*  public static void main(String[] args){ 
		    	 GeneratorUUID();
		    	long times1 =System.currentTimeMillis();
		    	//getUUID();
		    	getUUID();
		    	System.out.println(System.currentTimeMillis()-times1+"---"+getUUID());
		    	 
		    	long times2 =System.currentTimeMillis();
		    	for(int m=0;m<90;m++){
		    		GeneratorUUID();
		    	}
		    	System.out.println(System.currentTimeMillis()-times2+"---"+getUUID());
		    }*/
		    
		    
		    public static void main(String str[]){
		    	
		    	List<Integer> list=new ArrayList<Integer>();
		    	List<Integer> list2=new ArrayList<Integer>();
		    	for(int i=0;i<5;i++)
		    		{list.add(1);
		    		list2.add(1);
		    		}
		    	list.addAll(list2);
		    	for(Integer ii:list)
		    		System.out.println(ii);
		    		
		    		
		    		
		    		
 		    	
		    	
		    }
}	
