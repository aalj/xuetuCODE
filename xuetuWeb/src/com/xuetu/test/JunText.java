/**
 * JunText.java
 * com.xuetu.test
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年2月22日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Data;
import com.xuetu.dao.FindIml;
import com.xuetu.dao.ShoppingDao;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;

/**
 * ClassName:JunText<br/>
 * Function: TODO ADD FUNCTION<br/>
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月22日		下午6:35:10
 *
 * @see 	 

 */
public class JunText {
	
	public void myTest(){
		System.out.println(new FindIml().getSelfPlan(1));
		
	}
	public void getSchoolById(){
		Date date = new Date(System.currentTimeMillis());
		new FindIml().insertSelfStudyPlan
		(new SelfStudyPlan(date, 
				date, "dsknfl", 1, new Pattern(1,""),new Student(), date));
	}
	
	
	
	public void getCouponteq(){
		List<Coupon> queryCouponall = new ShoppingDao().queryCouponall();
		System.out.println(queryCouponall);
		
	}
	@Test
	public void getCouponte(){
		List<Coupon> couponlimmit = new ShoppingDao().queryCouponlimmit(0,10);
		System.out.println(couponlimmit);
		
	}

}

