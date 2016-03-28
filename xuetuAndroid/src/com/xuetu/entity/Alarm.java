package com.xuetu.entity;

import java.util.Date;

public class Alarm {
	private int Alarm_id = 0;
	private Date StartTime = null;
	private int temp_index = 0;
	private  String week = null;
	int temp = 0;
	public Alarm(Date startTime, int temp_index, String week, int temp) {
		super();
		StartTime = startTime;
		this.temp_index = temp_index;
		this.week = week;
		this.temp = temp;
	}
	public Alarm(int alarm_id, Date startTime, int temp_index, String week, int temp) {
		super();
		Alarm_id = alarm_id;
		StartTime = startTime;
		this.temp_index = temp_index;
		this.week = week;
		this.temp = temp;
	}
	public int getAlarm_id() {
		return Alarm_id;
	}
	public void setAlarm_id(int alarm_id) {
		Alarm_id = alarm_id;
	}
	public Date getStartTime() {
		return StartTime;
	}
	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}
	public int getTemp_index() {
		return temp_index;
	}
	public void setTemp_index(int temp_index) {
		this.temp_index = temp_index;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public Alarm() {
		// TODO Auto-generated constructor stub
	}

}
