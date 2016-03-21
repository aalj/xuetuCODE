package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.CircleImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CouponInfoActivity extends Activity {
	CircleImageView head;
	CircleImageView head_CircleImageView;
	TextView youhuijuanxingxi;
	TextView youxiaoqixian;
	TextView dianjiadejutixingxi;
	TextView zhekoudejutixingxi;
	TextView shiyongdidianshuoming;
	TextView button_lijishiyong;
	TextView tv_collect_coupon;
	TextView xuyaodejifen;
	ImageView backToDecember;

	Coupon coupon;
	FavoritesCoupons favoritesCoupon;
	HttpUtils httpUtils = new HttpUtils();
	Student student;
	boolean isLogin = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coupon_info);
		// 初始化控件
		coupon = (Coupon) getIntent().getSerializableExtra("coupon");
		Toast.makeText(getApplicationContext(), coupon.getCouName(), 0).show();
		initView();

	}

	private void initView() {
		student = ((XueTuApplication) getApplication()).getStudent();
		if (student.getStuId() > 0) {
			isLogin = true;
		}

		if (isLogin) {
			infavoCouponcheck();
		}

		BitmapUtils bitmapUtils = new BitmapUtils(this);

		head = (CircleImageView) findViewById(R.id.head);
		youhuijuanxingxi = (TextView) findViewById(R.id.youhuijuanxingxi);
		youxiaoqixian = (TextView) findViewById(R.id.youxiaoqixian);
		dianjiadejutixingxi = (TextView) findViewById(R.id.dianjiadejutixingxi);
		zhekoudejutixingxi = (TextView) findViewById(R.id.zhekoudejutixingxi);
		shiyongdidianshuoming = (TextView) findViewById(R.id.shiyongdidianshuoming);
		head_CircleImageView = (CircleImageView) findViewById(R.id.head_CircleImageView);
		tv_collect_coupon = (TextView) findViewById(R.id.tv_collect_coupon);
		xuyaodejifen = (TextView) findViewById(R.id.xuyaodejifen);
		backToDecember = (ImageView) findViewById(R.id.imageView_backToDecember);

		String url = coupon.getStoreName().getStoImg();
		url = GetHttp.getHttpLJ() + url;
		bitmapUtils.display(head, url);
		bitmapUtils.display(head_CircleImageView, url);
		youhuijuanxingxi.setText(coupon.getCouName());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time_format = dateFormat.format(coupon.getConValidity());
		youxiaoqixian.setText(time_format);
		dianjiadejutixingxi.setText(coupon.getStoreName().getStoName());
		zhekoudejutixingxi.setText(coupon.getCouPrice() + "");
		xuyaodejifen.setText(coupon.getCoouRedeemPoints() + "");
		shiyongdidianshuoming.setText(coupon.getStoreName().getStoAddress());

	}

	public void onclick(View v) throws UnsupportedEncodingException {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.head_CircleImageView:
		case R.id.head:
			intent.setClass(CouponInfoActivity.this, StoneNameActivity.class);
			intent.putExtra("coupon", coupon);
			startActivity(intent);
			finish();
			break;
		case R.id.button_lijishiyong:// 兑换走兑换流程
			if (isLogin) {
				intent.setClass(this, ExchangeCouponActivity.class);
				intent.putExtra("exchange", coupon);
				startActivity(intent);
			} else {
				Toast.makeText(getApplicationContext(), "请先登录", 1).show();
			}
			break;
		case R.id.tv_collect_coupon:// 收藏 添加优惠券
			Toast.makeText(getApplicationContext(), "shoucang", 0).show();
			favoritesCoupon = new FavoritesCoupons();
			favoritesCoupon.setCoupon(coupon);

			int stuId = student.getStuId();
			if (stuId > 0) {
				favoritesCoupon.setStudent(student);
				favoritesCoupon.setCreateDate(new Date(System.currentTimeMillis()));

				saveCollectionCoupon();
			} else {
				Toast.makeText(getApplicationContext(), "清先登录", 1).show();
			}
			break;
		case R.id.imageView_backToDecember:
			finish();
			break;
		default:

			break;
		}

	}

	public void infavoCouponcheck() {
		String url = GetHttp.getHttpLJ() + "IssavefavoritesServlet";
		RequestParams pra = new RequestParams();
		pra.addBodyParameter("couponID", coupon.getCouID() + "");
		pra.addBodyParameter("studentid", student.getStuId() + "");
		Log.i("TAG", "yanshenf --------------->" + coupon.getCouID() + "\t" + student.getStuId());
		httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				if ("ok".equals(arg0.result)) {
					tv_collect_coupon.setText("已收藏");
					tv_collect_coupon.setClickable(false);
					tv_collect_coupon.setFocusable(false);
				}
			}
		});
	}

	public void saveCollectionCoupon() throws UnsupportedEncodingException {

		String url = GetHttp.getHttpLJ() + "SaveFavorites";
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(favoritesCoupon);
		RequestParams para = new RequestParams();
		json = URLDecoder.decode(json, "utf-8");
		para.addBodyParameter("facou", json);
		httpUtils.send(HttpMethod.POST, url, para, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(CouponInfoActivity.this, "网络异常", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				if ("ok".equals(arg0.result)) {
					tv_collect_coupon.setText("已收藏");
					tv_collect_coupon.setClickable(false);
					tv_collect_coupon.setFocusable(false);
				}

			}
		});
	}

}