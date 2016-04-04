package com.xuetu.fragment;

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
import com.xuetu.ui.CouponInfoActivity;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.ui.YouHuiJuanInfomationActivity;
import com.xuetu.utils.GetHttp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class YouHuiJuanFrag extends Fragment implements OnItemClickListener {
	List<FavoritesCoupons> datas = new ArrayList<FavoritesCoupons>();
	View view = null;
	ListView listview;
	MyBasesadapter<FavoritesCoupons> myadapter;
	Context context;
	Student student;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = getContext();
		student = (((XueTuApplication) getActivity().getApplication())).getStudent();

		view = inflater.inflate(R.layout.listview, null);
		listview = (ListView) view.findViewById(R.id.listview);
		listview.setOnItemClickListener(this);
		// getCoupon();
		return view;
	}

	/**
	 * 通过学生id获得个人收藏优惠劵
	 * 
	 * 
	 */
	private void getCoupon() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "GetPersonFavoriteCouponsByStuIDServlet";
		RequestParams params = new RequestParams();
		try {
			params.addBodyParameter("stuid", URLEncoder.encode(String.valueOf(student.getStuId()), "utf-8"));
			// Log.i("TAG", student.getStuId() + "**********");

			httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// Log.i(TAG, "onSuccess");
					String result = arg0.result;
					Log.i("TAG", result);
					Type type = new TypeToken<List<FavoritesCoupons>>() {
					}.getType();
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					datas = gson.fromJson(result, type);
					addView();
				}
			});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getCoupon();
		Log.i("TAG", "是否刷新");
	}

	/**
	 * listview的加载
	 */
	private void addView() {
		Log.i("TAG", datas + "");
		// 设置适配器
		listview.setAdapter(
				myadapter = new MyBasesadapter<FavoritesCoupons>(context, datas, R.layout.itemof_mycollection) {

					@Override
					public void convert(ViewHodle viewHolder, FavoritesCoupons item) {
						viewHolder.SetUrlImage(R.id.head, GetHttp.getHttpBCL() + item.getCoupon().getCouIma());
						viewHolder.setText(R.id.number, item.getCoupon().getCoouRedeemPoints() + "分可以兑换");
						viewHolder.setText(R.id.youhuijuanxingxi, item.getCoupon().getCouInfo());

					}
				});

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		/* 跳转到优惠劵信息页面 */
		intent.setClass(getActivity(), CouponInfoActivity.class);
		intent.putExtra("coupon", datas.get(position).getCoupon());
		getActivity().startActivity(intent);

	}

}
