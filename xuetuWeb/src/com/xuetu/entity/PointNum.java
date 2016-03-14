package com.xuetu.entity;

import java.util.Date;

/**
 * 
 * ClassName:PointNum
 * </br>
 * Function: 积分表实体
 * </br>
 * Reason:	 TODO ADD REASON
 *
 * @author   liang
 * @version  
 * @since    Ver 1.1
 * @Date	 2016	2016年3月14日		下午6:38:31
 *
 * @see
 */
public class PointNum {
	int pointNum = 0;
	int pointID = 0;
	FromPoint fromPoint = null;
	public PointNum() {

		// TODO Auto-generated constructor stub

	}
	public int getPointNum() {
		return pointNum;
	}
	public void setPointNum(int pointNum) {
		this.pointNum = pointNum;
	}
	public int getPointID() {
		return pointID;
	}
	public void setPointID(int pointID) {
		this.pointID = pointID;
	}
	public FromPoint getFromPoint() {
		return fromPoint;
	}
	public void setFromPoint(FromPoint fromPoint) {
		this.fromPoint = fromPoint;
	}
	public Date getPointTime() {
		return pointTime;
	}
	public void setPointTime(Date pointTime) {
		this.pointTime = pointTime;
	}
	
	  public PointNum(int pointNum, int pointID, FromPoint fromPoint, Date pointTime) {
		super();
		this.pointNum = pointNum;
		this.pointID = pointID;
		this.fromPoint = fromPoint;
		this.pointTime = pointTime;
	}
	Date pointTime = null;
	
	
	

}
