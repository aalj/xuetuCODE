package com.xuetu.entity;

import java.io.Serializable;
import java.util.Date;

public class Alarm implements Serializable{
	private int Alarm_id = 0;
	/**时间*/
	private int timeHour=0;
	private int timeMin=0;
	/**模式*/
	private int temp_index = 0;
	/**提醒时间*/
	private  String week = null;
	/**标识早睡还是早起*/
	int temp = 0;
	int del_temp = 0;
	String pickedUri;
	public String getPickedUri() {
		return pickedUri;
	}
	public void setPickedUri(String pickedUri) {
		this.pickedUri = pickedUri;
	}
	public int getDel_temp() {
		return del_temp;
	}
	public void setDel_temp(int del_temp) {
		this.del_temp = del_temp;
	}
	private String tishiyu = null;
	public String getTishiyu() {
		return tishiyu;
	}
	public void setTishiyu(String tishiyu) {
		this.tishiyu = tishiyu;
	}
	public int getAlarm_id() {
		return Alarm_id;
	}
	public void setAlarm_id(int alarm_id) {
		Alarm_id = alarm_id;
	}
	public int getTimeHour() {
		return timeHour;
	}
	public void setTimeHour(int timeHour) {
		this.timeHour = timeHour;
	}
	public int getTimeMin() {
		return timeMin;
	}
	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
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
