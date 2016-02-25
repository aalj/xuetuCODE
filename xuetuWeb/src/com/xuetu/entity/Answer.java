/**
 * Answer.java
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
 * ClassName:Answer<br/>
 * Function: 答案表的实体类<br/>
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年2月20日 上午11:28:50
 *
 * @see
 * 
 */
public class Answer {
	private int ansID = 0;
	private Question question = null;
	private Student student = null;
	private String ansText = null;
	private String ansImg = null;
	private Date ansTime = null;
	public int getAnsID() {
		return ansID;
	}
	public void setAnsID(int ansID) {
		this.ansID = ansID;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getAnsText() {
		return ansText;
	}
	public void setAnsText(String ansText) {
		this.ansText = ansText;
	}
	public String getAnsImg() {
		return ansImg;
	}
	public void setAnsImg(String ansImg) {
		this.ansImg = ansImg;
	}
	public Date getAnsTime() {
		return ansTime;
	}
	public void setAnsTime(Date ansTime) {
		this.ansTime = ansTime;
	}
	public Answer(int ansID, Question question, Student student, String ansText, String ansImg, Date ansTime) {
		super();
		this.ansID = ansID;
		this.question = question;
		this.student = student;
		this.ansText = ansText;
		this.ansImg = ansImg;
		this.ansTime = ansTime;
	}
}
