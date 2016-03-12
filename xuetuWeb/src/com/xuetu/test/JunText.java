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

import java.util.List;

import org.junit.Test;

import com.xuetu.dao.CouponDao2;
import com.xuetu.dao.FindIml;
import com.xuetu.dao.StoreNameDao2;
import com.xuetu.entity.Coupon;
import com.xuetu.utils.GetHttp;

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
	@Test
	public void myTest(){
		System.out.println(new FindIml().getSelfPlan(1));
		
	}

}

