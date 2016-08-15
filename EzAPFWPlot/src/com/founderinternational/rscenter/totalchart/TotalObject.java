package com.founderinternational.rscenter.totalchart;

import java.util.Comparator;

public class TotalObject implements Comparable<TotalObject>{
	private String ServiceName;
	private String FunctionName;
	private Integer ServiceCount;
	private Integer FunctionCount;
	private Integer WeekCount;
	private String WeekName;
	private double weight;
	private String minuname;
	private Integer totalhour;
	 
	public String getMinuname() {
		return minuname;
	}





	public void setMinuname(String minuname) {
		this.minuname = minuname;
	}





	public Integer getTotalhour() {
		return totalhour;
	}





	public void setTotalhour(Integer totalhour) {
		this.totalhour = totalhour;
	}





	public String getServiceName() {
		return ServiceName;
	}





	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}





	public String getFunctionName() {
		return FunctionName;
	}





	public void setFunctionName(String functionName) {
		FunctionName = functionName;
	}





	public Integer getServiceCount() {
		return ServiceCount;
	}





	public void setServiceCount(Integer serviceCount) {
		ServiceCount = serviceCount;
	}





	public Integer getFunctionCount() {
		return FunctionCount;
	}





	public void setFunctionCount(Integer functionCount) {
		FunctionCount = functionCount;
	}





	public Integer getWeekCount() {
		return WeekCount;
	}





	public void setWeekCount(Integer weekCount) {
		WeekCount = weekCount;
	}





	public String getWeekName() {
		return WeekName;
	}





	public void setWeekName(String weekName) {
		WeekName = weekName;
	}





	public double getWeight() {
		return weight;
	}





	public void setWeight(double weight) {
		this.weight = weight;
	}





	@Override
	public int compareTo(TotalObject o2) {
		// TODO Auto-generated method stub
				if(this.getWeight()>o2.getWeight())
				{
					return 1;
				}
				else if(this.getWeight()==o2.getWeight())
				{
					return 0;
				}else
				{
					return -1;
				}
		 
	}
	 
 
}
