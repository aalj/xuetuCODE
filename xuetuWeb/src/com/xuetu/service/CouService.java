/**
 * CouService.java
 * com.xuetu.service
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年2月20日 		Administrator
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.service;
/**
 * ClassName:CouService
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Administrator
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月20日		下午8:52:00
 *
 * @see 	 

 */

import com.xuetu.dao.CouponDao;
import com.xuetu.entity.Coupon;
public class CouService{
	private CouponDao couponDao = new CouponDao();

	/**添加优惠券(优惠券，店家ID)
	 */
	public void CouponAdd(Coupon coupon,int sto_id){
		couponDao.addCoupon(coupon, sto_id);
	}
	
	
}

