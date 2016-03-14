/**
 * family.java
 * com.snail.dao
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年1月27日 		asus
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.snail.dao;
/**
 * ClassName:Family
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   asus
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年1月27日		下午11:05:45
 *
 * @see 	 

 */
public class Family {
	private int fm_id;
	private String userName;
	private String realName;
	private String pwd;
	private int permiss;
	
	
	public int getPermiss() {
		return permiss;
	}
	public void setPermiss(int permiss) {
		this.permiss = permiss;
	}
	public int getFm_Id() {
		return fm_id;
	}
	public void setFm_Id(int id) {
		this.fm_id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}

