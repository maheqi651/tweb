package com.founderinternational.rscenter.tools;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StrutsSortComparator implements Comparator<String> {
    public static Map<Integer,Integer> numbermap;
    static{
    	   numbermap=new HashMap<Integer,Integer>();
    	   for(int i=0;i<200;i++)
    	   {
    		   numbermap.put(i, new Integer(i));
    	   }
    }
	public static void main(String[] args) {
		String[] a={"ÕÅÈý", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷","¹ù¾§¾§","Ò¦Ã÷", "ÀîËÄ", "ÁõÏè", "ÁõÁù","¹ù¾§¾§","Ò¦Ã÷"};
		Comparator cmp=new StrutsSortComparator();
		long times=System.currentTimeMillis();
		Arrays.sort(a, cmp);
		System.out.println("length:"+a.length);
		System.out.println(System.currentTimeMillis()-times);
		 for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		} 

	}
	/*ÕÅÈý
	ÁõÁù
	ÀîËÄ
	Ò¦Ã÷
	ÁõÏè
	¹ù¾§¾§*/
	@Override
	public int compare(String name1, String name2) {
		
		//System.out.println(CnToStrokeCount.getNumber(name1));
		//return  numbermap.get(CnToStrokeCount.getNumber(name1)).compareTo(numbermap.get(CnToStrokeCount.getNumber(name2)));
		return  Integer.compare(CnToStrokeCount.getNumber(name1), CnToStrokeCount.getNumber(name2));
	}

}
