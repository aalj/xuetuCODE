/**
 * ChckINS.java
 * com.xuetu.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月6日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.entity;

import java.util.Date;

/**
 * ClassName:ChckINS
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月6日		上午12:00:02
 *
 * @see 	 

 */
public class ChckIns {
	
	int chiId = 0;
	Student student = null;
	Date chiTime = null;
	int chiIs = 0;
	public ChckIns() {

		// TODO Auto-generated constructor stub

	}
	public int getChiId() {
		return chiId;
	}
	public void setChiId(int chiId) {
		this.chiId = chiId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Date getChiTime() {
		return chiTime;
	}
	public void setChiTime(Date chiTime) {
		this.chiTime = chiTime;
	}
	public int getChiIs() {
		return chiIs;
	}
	public void setChiIs(int chiIs) {
		this.chiIs = chiIs;
	}
	public ChckIns(int chiId, Student student, Date chiTime, int chiIs) {
		super();
		this.chiId = chiId;
		this.student = student;
		this.chiTime = chiTime;
		this.chiIs = chiIs;
	}
	
	

}

