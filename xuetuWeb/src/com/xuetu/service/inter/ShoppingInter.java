package com.xuetu.service.inter;

import java.util.List;

import com.xuetu.entity.Coupon;

/**
 * 
 * @author liang
 *
 */
public interface ShoppingInter {

	public List<Coupon> getCouponlimmit(int page, int num);

	public List<Coupon> getCouponAll();

}
