/**
 * CollectionQuestion.java
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

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:CollectionQuestion<br/>
 * Function: 收藏问题表的实体<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年2月20日 上午11:31:53
 *
 * @see
 * 
 */
public class CollectionQuestion implements Serializable {
	private int coqoID = 0;
	private Student student = null;
	private Question question = null;
	Date ques_time = null;

	public int getCoqoID() {
		return coqoID;
	}

	public void setCoqoID(int coqoID) {
		this.coqoID = coqoID;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Date getQues_time() {
		return ques_time;
	}

	public void setQues_time(Date ques_time) {
		this.ques_time = ques_time;
	}

	@Override
	public String toString() {
		return "CollectionQuestion [coqoID=" + coqoID + ", student=" + student + ", question=" + question
				+ ", ques_time=" + ques_time + "]";
	}

	public CollectionQuestion(int coqoID, Student student, Question question, Date ques_time) {
		super();
		this.coqoID = coqoID;
		this.student = student;
		this.question = question;
		this.ques_time = ques_time;
	}

	public CollectionQuestion() {
		super();

	}

}
