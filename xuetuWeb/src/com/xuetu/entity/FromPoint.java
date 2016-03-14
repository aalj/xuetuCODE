/**
 * FromPoint.java
 * com.xuetu.entity
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月14日 		liang
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.entity;
/**
 * ClassName:FromPoint
 * Function: 积分来源表
 * Reason:	 TODO ADD REASON
 *
 * @author   liang
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月14日		下午6:40:29
 *
 * @see 	 

 */
public class FromPoint {
	
	int fromPointid= 0;
	String fromname = null;
	public FromPoint() {

		// TODO Auto-generated constructor stub

	}
	public int getFromPointid() {
		return fromPointid;
	}
	public void setFromPointid(int fromPointid) {
		this.fromPointid = fromPointid;
	}
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	@Override
	public String toString() {
		return "FromPoint [fromPointid=" + fromPointid + ", fromname=" + fromname + "]";
	}
	public FromPoint(int fromPointid, String fromname) {
		super();
		this.fromPointid = fromPointid;
		this.fromname = fromname;
	}

	 
}

