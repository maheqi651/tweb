package com.founderinternational.rscenter.tools.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.founderinternational.rscenter.tools.General;

 

public class RandomCountGeneral implements General<String> {
    private static RandomCountGeneral rc;
	@Override
	public String next() {
		StringBuffer str=new StringBuffer();
		str.append(dateToLong());
		 for(int i=0;i<29;i++)
		  {
		    str.append((""+ new Random().nextInt(10)).trim().substring(0, 1));
		  }
		  return str.toString() ;
	}
	private int rand(){
		 Random rm=new Random();
		 return  rm.nextInt(1000);
	}
	public long dateToLong () {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.getTimeInMillis();
    }
	public static RandomCountGeneral getInstance() {
		try {
			if(rc==null)
			  rc=RandomCountGeneral.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return rc;
	}
	public static void main(String args[]){
		
	}

}
