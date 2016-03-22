/**
 * Subject.java
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

import java.io.Serializable;

/**
 * ClassName:Subject
 * Function: 学科类型
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月6日		上午12:19:59
 *
 * @see 	 

 */
public class Subject implements Serializable{
	
	public int getSubId() {
		return SubId;
	}
	public Subject() {

		// TODO Auto-generated constructor stub

	}
	public Subject(int subId, String name) {
		super();
		SubId = subId;
		this.name = name;
	}
	public void setSubId(int subId) {
		SubId = subId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	int SubId = 0;
			String name= null ;
	
	

}

