/**
 * School.java
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
 * ClassName:School 学校表的实体类<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年2月19日 下午10:38:50
 *
 * @see
 * 
 */
public class School {
	/**
	 * 学校的ID
	 */
	private int schId = 0;
	/**
	 * 学校的名字
	 */
	private String schName = null;
	/**
	 * 学校的经度
	 */
	private String schLongitude = null;
	/**
	 * 学校的纬度
	 */
	private String schLatitude = null;
	public School() {

		// 

	}
	public int getSchId() {
		return schId;
	}
	public void setSchId(int schId) {
		this.schId = schId;
	}
	public String getSchName() {
		return schName;
	}
	public void setSchName(String schName) {
		this.schName = schName;
	}
	public String getSchLongitude() {
		return schLongitude;
	}
	public void setSchLongitude(String schLongitude) {
		this.schLongitude = schLongitude;
	}
	public String getSchLatitude() {
		return schLatitude;
	}
	public void setSchLatitude(String schLatitude) {
		this.schLatitude = schLatitude;
	}
	public School(int schId, String schName, String schLongitude, String schLatitude) {
		super();
		this.schId = schId;
		this.schName = schName;
		this.schLongitude = schLongitude;
		this.schLatitude = schLatitude;
	}

}
