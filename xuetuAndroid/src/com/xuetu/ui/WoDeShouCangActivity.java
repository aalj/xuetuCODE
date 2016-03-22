package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.Student;
import com.xuetu.fragment.YouHuiJuanFrag;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class WoDeShouCangActivity extends FragmentActivity implements OnClickListener {
	protected static final String TAG = "TAG";
	// ListView listview;
	// List<FavoritesCoupons> datas = new ArrayList<FavoritesCoupons>();
	// Student student;
	// MyBasesadapter<FavoritesCoupons> myadapter;
	TitleBar titlebar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_wo_de_shou_cang);
		// listview = (ListView) findViewById(R.id.listView1);
		titlebar = (TitleBar) findViewById(R.id.backtoperson);
		titlebar.setLeftLayoutClickListener(this);
		YouHuiJuanFrag youHuiJuanFrag = new YouHuiJuanFrag();
		getSupportFragmentManager().beginTransaction().add(R.id.framelayout, youHuiJuanFrag).commit();
		// listview.setOnItemClickListener(this);
		// getCoupon();
	}

	// /**
	// * listview的加载
	// */
	// private void addView() {
	// Log.i("TAG", datas + "");
	// // 设置适配器
	// listview.setAdapter(
	// myadapter = new MyBasesadapter<FavoritesCoupons>(this, datas,
	// R.layout.itemof_mycollection) {
	//
	// @Override
	// public void convert(ViewHodle viewHolder, FavoritesCoupons item) {
	// viewHolder.SetUrlImage(R.id.head,
	// GetHttp.getHttpBCL() + item.getCoupon().getStoreName().getStoImg());
	// viewHolder.setText(R.id.number, item.getCoupon().getCoouRedeemPoints() +
	// "分可以兑换");
	// viewHolder.setText(R.id.youhuijuanxingxi, item.getCoupon().getCouInfo());
	//
	// }
	// });
	//
	// }

	// /***
	// *
	// * 通过学生ID得到个人收藏表
	// *
	// *
	// *
	// *
	// */
	// private void getCoupon() {
	// HttpUtils httpUtils = new HttpUtils();
	// String url = GetHttp.getHttpBCL() +
	// "GetPersonFavoriteCouponsByStuIDServlet";
	// RequestParams params = new RequestParams();
	// try {
	// params.addBodyParameter("stuid",
	// URLEncoder.encode(String.valueOf(student.getStuId()), "utf-8"));
	// Log.i("TAG", student.getStuId() + "**********");
	//
	// httpUtils.send(HttpMethod.POST, url, params, new
	// RequestCallBack<String>() {
	//
	// @Override
	// public void onFailure(HttpException arg0, String arg1) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onSuccess(ResponseInfo<String> arg0) {
	// Log.i(TAG, "onSuccess");
	// String result = arg0.result;
	// Log.i("TAG", result);
	// Type type = new TypeToken<List<FavoritesCoupons>>() {
	// }.getType();
	// Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd
	// HH:mm:ss").create();
	// datas = gson.fromJson(result, type);
	// addView();
	// }
	// });
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// Intent intent = new Intent();
	// /* 跳转到优惠劵信息页面 */
	// intent.setClass(this, YouHuiJuanInfomationActivity.class);
	// Bundle bundle = new Bundle();
	// bundle.putSerializable("MyCoupon", datas.get(position));
	// intent.putExtras(bundle);
	// startActivity(intent);
	//
	// }

	@Override
	public void onClick(View v) {
		finish();
	}

}
