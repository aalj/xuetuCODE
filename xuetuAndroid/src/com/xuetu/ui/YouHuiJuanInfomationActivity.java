package com.xuetu.ui;

import java.text.SimpleDateFormat;

import com.lidroid.xutils.BitmapUtils;
import com.xuetu.R;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.MyCoupon;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.CircleImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class YouHuiJuanInfomationActivity extends Baseactivity {
	CircleImageView head;
	CircleImageView head_CircleImageView;
	TextView youhuijuanxingxi;
	TextView youxiaoqixian;
	TextView dianjiadejutixingxi;
	TextView zhekoudejutixingxi;
	TextView shiyongdidianshuoming;
	TextView button_lijishiyong;
	ImageView backToDecember;
	MyCoupon mycoupon;
	Coupon coupon;

	/**
	 * 我的优惠劵具体信息
	 * 
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_you_hui_juan_infomation);
		head = (CircleImageView) findViewById(R.id.head);
		youhuijuanxingxi = (TextView) findViewById(R.id.youhuijuanxingxi);
		youxiaoqixian = (TextView) findViewById(R.id.youxiaoqixian);
		dianjiadejutixingxi = (TextView) findViewById(R.id.dianjiadejutixingxi);
		zhekoudejutixingxi = (TextView) findViewById(R.id.zhekoudejutixingxi);
		shiyongdidianshuoming = (TextView) findViewById(R.id.shiyongdidianshuoming);
		head_CircleImageView = (CircleImageView) findViewById(R.id.head_CircleImageView);
		backToDecember = (ImageView) findViewById(R.id.imageView_backToDecember);
		getDatasAndsetDates();
	}
	
	public void getDatasAndsetDates() {
		Intent intent = this.getIntent();
		Bundle extras = intent.getExtras();

		mycoupon = (MyCoupon) extras.getSerializable("MyCoupon");
		coupon = mycoupon.getCoupon();
		BitmapUtils bitmapUtils = new BitmapUtils(this);
		bitmapUtils.display(head, GetHttp.getHttpBCL() + coupon.getStoreName().getStoImg());
		BitmapUtils bitmapUtils1 = new BitmapUtils(this);
		bitmapUtils1.display(head_CircleImageView, GetHttp.getHttpBCL() + coupon.getStoreName().getStoImg());

		youhuijuanxingxi.setText(coupon.getCouInfo());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		String time_format = dateFormat.format(coupon.getConValidity());
		youxiaoqixian.setText(time_format);
		dianjiadejutixingxi.setText(coupon.getStoreName().getStoName());
		zhekoudejutixingxi.setText(coupon.getCouPrice() + "");
		shiyongdidianshuoming.setText("要在" + coupon.getStoreName().getStoAddress()
				+ "吃，如果有来生，要做一棵树， 站成永恒， 没有悲欢的姿势。一半在土里安详， 一半在风里飞扬， 一半洒落阴凉， 一半沐浴阳光。 非常沉默，非常骄傲， 从不依靠，从不寻找。");

	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.imageView_backToDecember:
			Intent intent = new Intent();
			intent.setClass(this, TheCollectionOfYouHuiJuanActivity.class);
			startActivity(intent);
			finish();
			break;
		/** 跳转到店家 **/
		case R.id.head_CircleImageView:
			Intent intent1 = new Intent();
			intent1.setClass(this, StoneNameActivity.class);
			intent1.putExtra("coupon", coupon);
			startActivity(intent1);
			break;
		default:
			break;
		}

	}

}
