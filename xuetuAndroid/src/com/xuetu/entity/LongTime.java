package com.xuetu.entity;

import java.util.Date;

public class LongTime {
	Date myDate;
	long myTime;
	Student student;
	@Override
	public String toString() {
		return "LongTime [myDate=" + myDate + ", myTime=" + myTime + ", student=" + student + "]";
	}
	public Date getMyDate() {
		return myDate;
	}
	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}
	public long getMyTime() {
		return myTime;
	}
	public void setMyTime(long myTime) {
		this.myTime = myTime;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public LongTime() {
		// TODO Auto-generated constructor stub
	}
	public LongTime(Date myDate, long myTime, Student student) {
		super();
		this.myDate = myDate;
		this.myTime = myTime;
		this.student = student;
	}
	
	

}
