/**
 * MyCoupon.java
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
 * ClassName:MyCoupon<br/>
 * Function: 我的优惠券表的是实体<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月20日		上午8:49:39
 *
 * @see 	 

 */
public class MyCoupon {
	private int mycouID = 0;
	private UserState userState = null;
	private Coupon coupon = null;
	private Student student = null;
	private Date mycouExchangeTime = null;
	public MyCoupon() {

		// 

	}
	/**
	 * 
	 * getMycouID:(得到我的优惠券的ID)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public int getMycouID() {
		return mycouID;
	}
	/**
	 * 
	 * setMycouID:(设置我的优惠券的ID)<br/>
	
	 *
	 * @param  @param mycouID    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setMycouID(int mycouID) {
		this.mycouID = mycouID;
	}
	/**
	 * 
	 * getUserState:(得到该张优惠券的使用状态)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return UserState    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public UserState getUserState() {
		return userState;
	}
	/**
	 * 
	 * setUserState:(设置该张优惠券的使用状态)<br/>
	
	 *
	 * @param  @param userState    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setUserState(UserState userState) {
		this.userState = userState;
	}
	/**
	 * 
	 * getCoupon:(得到我拥有的是哪张优惠券)<br/>
	
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
	 * setCoupon:(设置饿哦用有的是哪张优惠券)<br/>
	
	 *
	 * @param  @param coupon    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	/**
	 * 
	 * getStudent:(得到这张优惠券是谁拥有的)<br/>
	
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
	 * setStudent:(设置这张优惠券是谁拥有的)<br/>
	
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
	 * getMycouExchangeTime:(得到该张优惠券的兑换的时间)<br/>
	
	 *
	 * @param  @return    设定文件
	 * @return Date    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Date getMycouExchangeTime() {
		return mycouExchangeTime;
	}
	/**
	 * 
	 * setMycouExchangeTime:(设置该张优惠券是什么时候兑换的)<br/>
	
	 *
	 * @param  @param mycouExchangeTime    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void setMycouExchangeTime(Date mycouExchangeTime) {
		this.mycouExchangeTime = mycouExchangeTime;
	}
	/**
	 * 
	 * Creates a new instance of MyCoupon.
	 *
	 * @param mycouID  
	 * @param userState
	 * @param coupon
	 * @param student
	 * @param mycouExchangeTime
	 */
	public MyCoupon(int mycouID, UserState userState, Coupon coupon, Student student, Date mycouExchangeTime) {
		super();
		this.mycouID = mycouID;
		this.userState = userState;
		this.coupon = coupon;
		this.student = student;
		this.mycouExchangeTime = mycouExchangeTime;
	}
}

