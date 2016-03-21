package com.xuetu.dao.inter;

import java.util.List;

import com.xuetu.entity.Coupon;

/**
 * 
 * @author liang
 *
 */

public interface ShoppintDaoInter {

	
	
public List<Coupon> queryCouponlimmit(int page,int num);
	
	public List<Coupon> queryCouponall(int stoID);

	
	
}
