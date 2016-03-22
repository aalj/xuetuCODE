package com.xuetu.service.inter;

import java.util.List;

import com.xuetu.entity.Coupon;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyCoupon;

/**
 * 
 * @author liang
 *
 */
public interface ShoppingInter {

	public List<Coupon> getCouponlimmit(int page, int num);

	public List<Coupon> getCouponAll(int stoID);

	public boolean saveFavorites(FavoritesCoupons fa);

	public boolean Issavefavorites(int coupID, int studentid);

	public boolean saveMycoupon(MyCoupon mucoupon);

}
