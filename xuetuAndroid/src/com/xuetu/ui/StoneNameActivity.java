package com.xuetu.ui;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.Coupon;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class StoneNameActivity extends Activity implements OnRefreshListener, OnItemClickListener, OnClickListener {
	private static final int REFRESH_COMPLETE = 0X110;
	private SwipeRefreshLayout mSwipeLayout;
	private ListView mListView;
	private ImageView stoneima;
	private TitleBar title_my;
	HttpUtils httpUtlis = new HttpUtils();

	// 用于保存上一次获取的数据
	List<Coupon> olduser = new ArrayList<Coupon>();

	// 用户与存储网络上获取的数据
	List<Coupon> users = new ArrayList<Coupon>();
	Coupon coupon = null;
	private MyBasesadapter<Coupon> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stone_name);
		coupon = (Coupon) getIntent().getSerializableExtra("coupon");

		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
		mSwipeLayout.setOnRefreshListener(this);
		/**
		 * 设置刷新时候的颜色
		 */
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);

		mListView = (ListView) findViewById(R.id.id_listview);
		title_my = (TitleBar) findViewById(R.id.title_my);
		stoneima = (ImageView) findViewById(R.id.stoneima);
		BitmapUtils bit = new BitmapUtils(this);
		bit.display(stoneima, GetHttp.getHttpLJ() + coupon.getStoreName().getStoImg());
		getDate(coupon.getStoreName().getStoID());
		showDengdai();
		mListView.setOnItemClickListener(this);
		title_my.setLeftLayoutClickListener(this);

	}

	private void getDate(int stoneid) {
		String url = GetHttp.getHttpLJ() + "GetCouponServlet";

		RequestParams parterm = new RequestParams();

		parterm.addBodyParameter("reqtemp", stoneid + "");
		httpUtlis.send(HttpMethod.POST, url, parterm, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

				Type type = new TypeToken<List<Coupon>>() {
				}.getType();
				List<Coupon> user = gson.fromJson(arg0.result, type);
				users = gson.fromJson(arg0.result, type);
				if (progressDialog != null)
					progressDialog.dismiss();
				// users.clear();
				users.addAll(user);
				addAdapter();
				mListView.setAdapter(adapter);
				/**
				 * 设置刷新结束之后，圈停止转动
				 */
				mSwipeLayout.setRefreshing(false);

			}

		});

	}

	
	ProgressDialog progressDialog = null;
	private void showDengdai() {
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(StoneNameActivity.this, "", "正在加载...");
			progressDialog.setCancelable(true);
			progressDialog.show();
		} else {

		}
	}
	
	
	
	
	public void addAdapter() {

		adapter = new MyBasesadapter<Coupon>(StoneNameActivity.this, users, R.layout.coupon_item) {

			@Override
			public void convert(ViewHodle viewHolder, Coupon item) {
				// 兑换优惠券需要的积分
				viewHolder.setText(R.id.mornum, item.getCoouRedeemPoints() + "");
				// 优惠券价绍
				viewHolder.setText(R.id.coupon_info, item.getCouName());
				// 店家的积分
				viewHolder.setText(R.id.tv_shoppingname, item.getStoreName().getStoName());
				// 优惠券已经兑换多少
				viewHolder.setText(R.id.tv_coupon_all, item.getShiyongNum() + "");
				// 优惠券的图片
				viewHolder.SetUrlImage(R.id.tv_coupon_ima, GetHttp.getHttpLJ() + item.getCouIma());

			}
		};
	}

	@Override
	public void onRefresh() {
		getDate(coupon.getStoreName().getStoID());

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		intent.setClass(StoneNameActivity.this, CouponInfoActivity.class);
		intent.putExtra("coupon", users.get(position));
		startActivity(intent);
		finish();

	}

	@Override
	public void onClick(View v) {
		finish();

	}
}
