/**
 * Question.java
 * com.xuetu.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年2月20日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.entity;

import java.util.Date;

/**
 * ClassName:Question<br/>
 * Function: 问题表的实体类<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月20日		上午12:00:24
 *
 * @see 	 

 */
public class Question {
	private int quesID = 0;
	private Student student = null;
	private String quesText = null;
	private String quesIma = null;
	private Date quesDate = null;
	public Question() {

		// 

	}
	/**
	 * 
	 * getQuesID:(得到问题的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getQuesID() {
		return quesID;
	}
	/**
	 * 
	 * setQuesID:(设置问题ID)<br/>
	
	 *
	 * @param  @param quesID    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setQuesID(int quesID) {
		this.quesID = quesID;
	}
	/**
	 * 
	 * getStudent:(得到是那个学生提的问题)<br/>
	
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
	 * setStudent:(设置是那个学生的提的问题)<br/>
	
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
	 * getQuesText:(得到问题的描述)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getQuesText() {
		return quesText;
	}
	/**
	 * 
	 * setQuesText:(设置问题的描述)<br/>
	
	 *
	 * @param  @param quesText    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setQuesText(String quesText) {
		this.quesText = quesText;
	}
	/**
	 * 
	 * getQuesIma:(得到问题的图片地址)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String getQuesIma() {
		return quesIma;
	}
	/**
	 * 
	 * setQuesIma:(设置问题图片的地址)<br/>
	
	 *
	 * @param  @param quesIma    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setQuesIma(String quesIma) {
		this.quesIma = quesIma;
	}
	/**
	 * 
	 * getQuesDate:(得到提问题的时间)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return Date    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Date getQuesDate() {
		return quesDate;
	}
	/**
	 * 
	 * setQuesDate:(设置提问题的时间)<br/>
	
	 *
	 * @param  @param quesDate    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setQuesDate(Date quesDate) {
		this.quesDate = quesDate;
	}
	public Question(int quesID, Student student, String quesText, String quesIma, Date quesDate) {
		this.quesID = quesID;
		this.student = student;
		this.quesText = quesText;
		this.quesIma = quesIma;
		this.quesDate = quesDate;
	}
	

}

