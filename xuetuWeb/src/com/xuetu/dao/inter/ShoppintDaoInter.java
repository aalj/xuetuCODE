package com.xuetu.dao.inter;

import java.util.List;

import com.xuetu.entity.Coupon;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyCoupon;

/**
 * 
 * @author liang
 *
 */

public interface ShoppintDaoInter {

	public List<Coupon> queryCouponlimmit(int page, int num);

	public List<Coupon> queryCouponall(int stoID);
	public boolean insertFavoritesCoupons(FavoritesCoupons fa);

	public boolean queryIssavefavorites(int coupID, int studentid);

	public boolean insertMycoupon(MyCoupon mucoupon);
}
