package com.founderinternational.rscenter.totalchart;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.springframework.stereotype.Component;
public class TotalTimerStart {
	public void init(){
		   startTask();
	}
	public  void startTask(){
		//new TotalTask().run();//第一次启动
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 3);  // 控制时
		calendar.set(Calendar.MINUTE, 0);       // 控制分
		calendar.set(Calendar.SECOND, 0);       // 控制秒
		Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00
		Timer timer = new Timer();
		timer.schedule(new TotalTask(), time,1000 * 60 * 60 * 24);
		System.out.println("-------启动------");
		//每天3点更新""
	}

}
