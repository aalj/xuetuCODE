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

import com.xuetu.dao.FindIml;
import com.xuetu.dao.JifenDao;
import com.xuetu.dao.LoginDao;
import com.xuetu.dao.ShoppingDao;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;

/**
 * ClassName:JunText<br/>
 * Function: TODO ADD FUNCTION<br/>
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年2月22日 下午6:35:10
 *
 * @see
 * 
 */
public class JunText {

	public void myTest() {
		System.out.println(new FindIml().getSelfPlan(1));

	}

	public void getSchoolById() {
		Date date = new Date(System.currentTimeMillis());
		new FindIml().insertSelfStudyPlan(
				new SelfStudyPlan(date, date, "dsknfl", 1, new Pattern(1, ""), new Student(), date));
	}

	
	
	
	
	
	public void getmycou(){
		JifenDao dao = new JifenDao();
		List<MyCoupon> queryAllCouponById = dao.queryAllCouponById(2);
		System.out.println(queryAllCouponById.size());
	}
	
	


	public void getCouponte() {
		List<Coupon> couponlimmit = new ShoppingDao().queryCouponlimmit(0, 10);
		System.out.println(couponlimmit);

	}

	@Test
	public void getCoupon() {
		JifenDao jifenDao = new JifenDao();
//		System.out.println(jifenDao.getCouponLimitByStuId(3, 1).size());
//		System.out.println(jifenDao.getQuestionLimitByStuId(3, 1).size());
//		System.out.println(jifenDao.getStudyTimeLimitByStuId(3, 1).size());
//		System.out.println(jifenDao.getAnswerLimitByStuId(3, 1).size());
		System.out.println(jifenDao.paixuJifenMingxi(1,1));

	}

}
