/**
 * Shopping.java
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
 * ClassName:Shopping<br/>
 * Function: 商城表的实体<br/>
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年2月19日 下午11:35:01
 *
 * @see
 * 
 */
public class Shopping {
	private int shopID = 0;
	private Coupon coupon = null;
	public int getShopID() {
		return shopID;
	}
	public void setShopID(int shopID) {
		this.shopID = shopID;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public Shopping(int shopID, Coupon coupon) {
		super();
		this.shopID = shopID;
		this.coupon = coupon;
	}

}
