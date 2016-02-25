/**
 * FavoritesCoupons.java
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
 * ClassName:FavoritesCoupons<br/>
 * Function: 收藏的优惠券表的实体类<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月20日		上午8:51:13
 *
 * @see 	 

 */
public class FavoritesCoupons {
	private 	int facoID = 0;
	private Student student = null;
	private 	Coupon coupon = null;
	public FavoritesCoupons() {

		// 

	}
	/**
	 * 
	 * getFacoID:(得到收藏该张优惠券的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getFacoID() {
		return facoID;
	}
	
	/**
	 * 
	 * setFacoID:(设置收藏该张优惠券的ID)<br/>
	
	 *
	 * @param  @param facoID    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setFacoID(int facoID) {
		this.facoID = facoID;
	}
	/**
	 * 
	 * getStudent:(得到该张优惠券的是有谁收藏的)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return Student    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Student getStudent() {
		return student;
	}
	/**
	 * 
	 * setStudent:(设置该张优惠券是谁收藏的)<br/>
	
	 *
	 * @param  @param student    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	/**
	 * 
	 * getCoupon:(得到收藏的是哪张优惠券)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return Coupon    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Coupon getCoupon() {
		return coupon;
	}
	/**
	 * 
	 * setCoupon:(设置收藏的是那张优惠券)<br/>
	
	 *
	 * @param  @param coupon    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public FavoritesCoupons(int facoID, Student student, Coupon coupon) {
		super();
		this.facoID = facoID;
		this.student = student;
		this.coupon = coupon;
	}
	 
}

