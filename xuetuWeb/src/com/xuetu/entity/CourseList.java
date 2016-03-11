/**
 * CourseList.java
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
/**
 * ClassName:CourseList<br/>
 * Function: TODO ADD FUNCTION<br/>
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月20日		上午11:35:08
 *
 * @see 	 

 */
public class CourseList {
	private int coulID = 0;
	private Student student = null;
	private MyClass myclass = null;
	public CourseList() {

		// 

	}
	public int getCoulID() {
		return coulID;
	}
	public void setCoulID(int coulID) {
		this.coulID = coulID;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public MyClass getMyclass() {
		return myclass;
	}
	public void setMyclass(MyClass myclass) {
		this.myclass = myclass;
	}
	public CourseList(int coulID, Student student, MyClass myclass) {
		super();
		this.coulID = coulID;
		this.student = student;
		this.myclass = myclass;
	}

}

