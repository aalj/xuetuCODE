/**
 * SelfStudyPlan.java
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
 * ClassName:SelfStudyPlan<br/>
 * Function: 自学计划表的实体类<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月19日		下午11:40:59
 *
 * @see 	 

 */
public class SelfStudyPlan {
	private int planID = 0;
	private Date startTime = null;
	private Date endTime = null;
	private String planText = null;
	private int planReming = 0;
	private Pattern pattern = null;
	private Student student = null;
	private Date planDate = null;
	public SelfStudyPlan() {

		// 

	}
	/**
	 * 
	 * getPlanID:(得到自学计划的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getPlanID() {
		return planID;
	}
	/**
	 * 
	 * setPlanID:(设置自学计划的ID)<br/>
	
	 *
	 * @param  @param planID    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setPlanID(int planID) {
		this.planID = planID;
	}
	/**
	 * 
	 * getStartTime:(得到计划开始的时间)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return Date    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 
	 * setStartTime:(设置计划开始时间)<br/>
	
	 *
	 * @param  @param startTime    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 
	 * getEndTime:(得到计划结束的时间)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return Date    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 
	 * setEndTime:(设置计划结束的时间)<br/>
	
	 *
	 * @param  @param endTime    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 
	 * getPlanText:(得到计划的描述)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getPlanText() {
		return planText;
	}
	/**
	 * 
	 * setPlanText:(设置计划开始的描述)<br/>
	
	 *
	 * @param  @param planText    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setPlanText(String planText) {
		this.planText = planText;
	}
	/**
	 * 
	 * getPlanReming:(得到计划是否需要提醒)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getPlanReming() {
		return planReming;
	}
	/**
	 * 
	 * setPlanReming:(设置计划是否提醒)<br/>
	
	 *
	 * @param  @param planReming    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setPlanReming(int planReming) {
		this.planReming = planReming;
	}
	/**
	 * 
	 * getPattern:(得到计划的执行模式)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return Pattern    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Pattern getPattern() {
		return pattern;
	}
	/**
	 * 
	 * setPattern:(设置计划的执行模式)<br/>
	
	 *
	 * @param  @param pattern    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	/**
	 * 
	 * getStudent:(得到计划是那个学生添加的)<br/>
	
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
	 * setStudent:(设置该计划是那个学生添加)<br/>
	
	 *
	 * @param  @param student    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	/**
	 * 
	 * getPlanDate:(得到添加计划的时间)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return Date    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Date getPlanDate() {
		return planDate;
	}
	/**
	 * 
	 * setPlanDate:(设置添加计划的时间)<br/>
	
	 *
	 * @param  @param planDate    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public SelfStudyPlan(int planID, Date startTime, Date endTime, String planText, int planReming, Pattern pattern,
			Student student, Date planDate) {
		this.planID = planID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.planText = planText;
		this.planReming = planReming;
		this.pattern = pattern;
		this.student = student;
		this.planDate = planDate;
	}

}

