/**
 * StudentTime.java
 * com.xuetu.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年2月19日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.entity;
import java.util.Date;
/**
 * ClassName:StudentTime<br/>
 * 
 * Function: 学生学习时间表的实体<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月19日		下午11:01:16
 *
 * @see 	 

 */
public class StudyTime {
	private int sttID=0;
	private Date date = null;
	
	private Long time = 0l;
	private Student student = null;
	public StudyTime() {

		// 

	}
	/**
	 * 
	 * getSttID:(的是学习时间的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getSttID() {
		return sttID;
	}
/**
 * 
 * setSttID:(设置学习时间的ID)<br/>

 *
 * @param  @param sttID    设定文件
 * @return void    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public void setSttID(int sttID) {
		this.sttID = sttID;
	}
/**
 * 
 * getDate:(得到记录该条数据的时间)<br/>

 *
 * @param  @return    设定文件
 * @return Date    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public Date getDate() {
		return date;
	}
/**
 * 
 * setDate:(设置该条数据的记录时间)<br/>

 *
 * @param  @param date    设定文件
 * @return void    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public void setDate(Date date) {
		this.date = date;
	}
/**
 * 
 * getTime:(得到该条数据的存储的时间时间长度)<br/>

 *
 * @param  @return    设定文件
 * @return Long    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public Long getTime() {
		return time;
	}
/**
 * 
 * setTime:(设置该条数据的应该记录的时间长度)<br/>

 *
 * @param  @param time    设定文件
 * @return void    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public void setTime(Long time) {
		this.time = time;
	}
/**
 * 
 * getStudent:(得到该条数据是谁产生的)<br/>

 *
 * @param  @return    设定文件
 * @return Student    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public Student getStudent() {
		return student;
	}
/**
 * 
 * setStudent:(设置该条数据是谁产生的)<br/>

 *
 * @param  @param student    设定文件
 * @return void    DOM对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
	public void setStudent(Student student) {
		this.student = student;
	}

	public StudyTime(int sttID, Date date, Long time, Student student) {
		this.sttID = sttID;
		this.date = date;
		this.time = time;
		this.student = student;
	}

}

