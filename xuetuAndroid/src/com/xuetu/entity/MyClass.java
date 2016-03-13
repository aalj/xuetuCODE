/**
 * Class.java
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

/**
 * ClassName:Class 课程表对应的实体类<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年2月19日 下午10:34:39
 *
 * @see
 * 
 */
public class MyClass {
	private int clsId = 0;
	private String clasName = null;
	private int clsWeek = 0;
	private int clsFew = 0;
	private String clsRoom = null;

	public MyClass() {
	}

	public int getClsId() {
		return clsId;
	}

	public void setClsId(int clsId) {
		this.clsId = clsId;
	}

	public String getClasName() {
		return clasName;
	}

	public void setClasName(String clasName) {
		this.clasName = clasName;
	}

	public int getClsWeek() {
		return clsWeek;
	}

	public void setClsWeek(int clsWeek) {
		this.clsWeek = clsWeek;
	}

	public int getClsFew() {
		return clsFew;
	}

	public void setClsFew(int clsFew) {
		this.clsFew = clsFew;
	}

	public String getClsRoom() {
		return clsRoom;
	}

	public void setClsRoom(String clsRoom) {
		this.clsRoom = clsRoom;
	}

	public MyClass(int clsId, String clasName, int clsWeek, int clsFew, String clsRoom) {
		super();
		this.clsId = clsId;
		this.clasName = clasName;
		this.clsWeek = clsWeek;
		this.clsFew = clsFew;
		this.clsRoom = clsRoom;
	}

	@Override
	public String toString() {
		return "MyClass [clsId=" + clsId + ", clasName=" + clasName + ", clsWeek=" + clsWeek + ", clsFew=" + clsFew
				+ ", clsRoom=" + clsRoom + "]";
	}

}
