package com.xuetu.service;

import java.util.List;

import com.xuetu.dao.ShoppingDao;
import com.xuetu.dao.inter.ShoppintDaoInter;
import com.xuetu.entity.Coupon;
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
	public List<Coupon> getCouponAll() {
		
		return shoppintDaoInter.queryCouponall();
	}

}
