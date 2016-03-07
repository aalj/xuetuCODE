/**
 * Coupon.java
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
 * ClassName:Coupon<br/>
 * Function: 优惠券表的实体类<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月20日		上午8:53:47
 *
 * @see 	 

 */
public class Coupon {
	private int couID = 0;
	private StoreName storeName = null;
	private String couInfo = null;
	private int conNum = 0;
	//表示优惠券到期时间
	private Date conValidity = null;
	private String couName = null;
	private int couPrice = 0;
	private 	int coouRedeemPoints = 0;
	
	
	
	public String getCouName() {
		return couName;
	}
	public void setCouName(String couName) {
		this.couName = couName;
	}
	public int getCouPrice() {
		return couPrice;
	}
	public void setCouPrice(int couPrice) {
		this.couPrice = couPrice;
	}
	
	public Coupon() {

		// 

	}public Coupon(int couID, StoreName storeName, String couInfo, int conNum, Date conValidity, String couName,
		int couPrice, int coouRedeemPoints) {
	super();
	this.couID = couID;
	this.storeName = storeName;
	this.couInfo = couInfo;
	this.conNum = conNum;
	this.conValidity = conValidity;
	this.couName = couName;
	this.couPrice = couPrice;
	this.coouRedeemPoints = coouRedeemPoints;
}

	/**
	 * 
	 * getCouID:(得到优惠券的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getCouID() {
		return couID;
	}
	/**
	 * 
	 * setCouID:(设置优惠券的ID)<br/>
	
	 *
	 * @param  @param couID    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setCouID(int couID) {
		this.couID = couID;
	}
	/**
	 * 
	 * getStoreName:(得到是那个店家发布的优惠券)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return StoreName    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public StoreName getStoreName() {
		return storeName;
	}
	/**
	 * 
	 * setStoreName:(设置该优惠券是由那个店家发布的)<br/>
	
	 *
	 * @param  @param storeName    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	
	public void setStoreName(StoreName storeName) {
		this.storeName = storeName;
	}
	/**
	 * 
	 * getCouInfo:(得到优惠券的描述)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	
	public String getCouInfo() {
		return couInfo;
	}
	/**
	 * 
	 * setCouInfo:(设置优惠券的描述)<br/>
	
	 *
	 * @param  @param couInfo    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setCouInfo(String couInfo) {
		this.couInfo = couInfo;
	}
	/**
	 * 
	 * getConNum:(得到该张优惠券的发布的数量)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getConNum() {
		return conNum;
	}
	/**
	 * 
	 * setConNum:(设置该张优惠券的发布的数量)<br/>
	
	 *
	 * @param  @param conNum    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setConNum(int conNum) {
		this.conNum = conNum;
	}
	/**
	 * 
	 * getConValidity:(得到该张优惠券的有效期)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return Date    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Date getConValidity() {
		return conValidity;
	}
	/**
	 * 
	 * setConValidity:(设置该张优惠券的有效期)<br/>
	
	 *
	 * @param  @param conValidity    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setConValidity(Date conValidity) {
		this.conValidity = conValidity;
	}
	/**
	 * 
	 * getCoouRedeemPoints:(得到该张兑换该张优惠券需要的积分)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getCoouRedeemPoints() {
		return coouRedeemPoints;
	}
	/**
	 * 
	 * setCoouRedeemPoints:(设置兑换该张优惠券需要的积分)<br/>
	
	 *
	 * @param  @param coouRedeemPoints    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setCoouRedeemPoints(int coouRedeemPoints) {
		this.coouRedeemPoints = coouRedeemPoints;
	}
	
	

}

