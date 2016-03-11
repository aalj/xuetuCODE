/**
 * Class.java
 * com.xuetu.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月1日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.entity;
/**
 * ClassName:Class
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月1日		下午11:59:55
 *
 * @see 	 

 */
public class Class {
	int id ;
	String clsName;
	int week;
	int fewLessons;
	public Class() {

		// TODO Auto-generated constructor stub

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClsName() {
		return clsName;
	}
	public void setClsName(String clsName) {
		this.clsName = clsName;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getFewLessons() {
		return fewLessons;
	}
	public void setFewLessons(int fewLessons) {
		this.fewLessons = fewLessons;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public Class(int id, String clsName, int week, int fewLessons, String classroom) {
		super();
		this.id = id;
		this.clsName = clsName;
		this.week = week;
		this.fewLessons = fewLessons;
		this.classroom = classroom;
	}
	String classroom;
	
	
	
	

}

