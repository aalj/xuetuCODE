package com.xuetu.entity;

import java.io.Serializable;
import java.util.Date;

public class Alarm implements Serializable{
	private int Alarm_id = 0;
	/**时间*/
	private String StartTime = null;
	/**模式*/
	private int temp_index = 0;
	/**提醒时间*/
	private  String week = null;
	/**标识早睡还是早起*/
	int temp = 0;
	private String tishiyu = null;
	public String getTishiyu() {
		return tishiyu;
	}
	public void setTishiyu(String tishiyu) {
		this.tishiyu = tishiyu;
	}
	public Alarm(String startTime, int temp_index, String week, int temp) {
		super();
		StartTime = startTime;
		this.temp_index = temp_index;
		this.week = week;
		this.temp = temp;
	}
	public Alarm(int alarm_id, String startTime, int temp_index, String week, int temp) {
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
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
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
