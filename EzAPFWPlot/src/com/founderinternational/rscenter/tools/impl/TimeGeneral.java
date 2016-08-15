package com.founderinternational.rscenter.tools.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.founderinternational.rscenter.tools.General;
 


public class TimeGeneral  implements General<String> {
	private static TimeGeneral tgr;
	private static final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	@Override
	public  String next() {
	     DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String str = (String) df.format(new Date());
	     return str;
	}
	
	public Date nextStringToDate(String str){
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	public static TimeGeneral getInstance()
	{
		try {
		 if(tgr==null)
			tgr=TimeGeneral.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return tgr;
	}
	
	
	public String next(Date date){
		 DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 if(date!=null)
	     return (String) df.format(date);;
		 return "";
	}
	
	 public String nextWeekOfDate(int week){
		   
		   return weekDays[week];
	 }
	 
	 public int  nextWeekOfcount(Date date){
		 Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0)
	            w = 0;
	        return w;
	 }
	 
	 
	 public String nextWeekOfDate(Date date){
		    return nextWeekOfDate(nextWeekOfcount(date));
	 }
	
	public String next(Date beginDate,int datecount){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd ");
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - datecount);
		try {
			//Date endDate = dft.parse(dft.format(date.getTime()));
			return dft.format(date.getTime());
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	public int nextInterval(Date starttime,Date endtime){
		Calendar date = Calendar.getInstance();
		date.setTime(starttime);
		int day1=date.get(Calendar.DAY_OF_YEAR);
		date.setTime(endtime);
		int day2=date.get(Calendar.DAY_OF_YEAR);
		
		return day2-day1;
	}
	
	public String nextHour(Date beginDate,int datecount){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.HOUR, date.get(Calendar.HOUR) - datecount);
		try {
			//Date endDate = dft.parse(dft.format(date.getTime()));
			return dft.format(date.getTime()).replaceAll("-", "").replace(" ", "").replace(":", "");
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String nextMinu(Date beginDate,int datecount){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.MINUTE, date.get(Calendar.MINUTE) - datecount);
		try {
			//Date endDate = dft.parse(dft.format(date.getTime()));
			return dft.format(date.getTime()).replaceAll("-", "").replace(" ", "").replace(":", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Date nextDate(Date beginDate,int datecount){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dft.parse(next(beginDate,datecount));
			//Date endDate =dft.parse(dft.format(date.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String age[]){
		System.out.println(TimeGeneral.getInstance().nextMinu(new Date(),5));
	}
}
