package com.xuetu.entity;

public class GetToddayClass {
	Class_end classend = new Class_end();
	
	private int day_in_week = classend.get_day_of_week();     //现在是星期几
	private int which_class = classend.get_which_chass();		// 现在是第几节课
	
	public int getDay_in_week() {
		return day_in_week;
	}
	public int getWhich_class() {
		return which_class;
	}
	
	
	
	

}
