package com.xuetu.service;

import java.util.List;

import com.xuetu.dao.ShoppingDao;
import com.xuetu.dao.inter.ShoppintDaoInter;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyCoupon;
import com.xuetu.service.inter.ShoppingInter;

public class ShoopingServer implements ShoppingInter {
	ShoppintDaoInter shoppintDaoInter;
	public ShoopingServer(ShoppintDaoInter shoppintDaoInter) {
		this.shoppintDaoInter = shoppintDaoInter;
	}

	@Override
	public List<Coupon> getCouponlimmit(int page, int num) {
		
		return shoppintDaoInter.queryCouponlimmit(page,num);
	}

	@Override
	public List<Coupon> getCouponAll(int stoID) {
		
		return shoppintDaoInter.queryCouponall(stoID);
	}

	@Override
	public boolean saveFavorites(FavoritesCoupons fa) {
		return shoppintDaoInter.insertFavoritesCoupons(fa);
		
	}

	@Override
	public boolean Issavefavorites(int coupID, int studentid) {
		// TODO Auto-generated method stub
		return shoppintDaoInter.queryIssavefavorites(coupID,studentid) ;
	}

	@Override
	public boolean saveMycoupon(MyCoupon mucoupon) {
		
		return shoppintDaoInter.insertMycoupon(mucoupon);
	}

}
