package com.xuetu.fragment;

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
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.GetHttp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class CouponFrag extends Fragment {
	private static final String TAG = "TAG";
	ListView list = null;
	HttpUtils httpUtlis = new HttpUtils();
	List<Coupon> users = null;
	MyBasesadapter<Coupon> adapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView" + "");
		System.out.println("onCreateView" + "");
		View view = inflater.inflate(R.layout.coupon_frag, null);
		Toast.makeText(getActivity(), "woshi ", 0).show();
		list = (ListView) view.findViewById(R.id.list_coupon);

		return view;
	}

	@Override
	public void onAttach(Context context) {
		users = new ArrayList<Coupon>();
		super.onAttach(context);
		users = ((XueTuApplication) getActivity().getApplication()).getListConpun();
		Log.i(TAG, "onAttach" + "");
		System.out.println("onAttach" + "");
		getDate();
		adapter = new MyBasesadapter<Coupon>(getActivity(), users, R.layout.coupon_item) {

			@Override
			public void convert(ViewHodle viewHolder, Coupon item) {
				viewHolder.setText(R.id.tv_coupon_name, item.getCouName());
				viewHolder.setText(R.id.tv_shoppingname, item.getStoreName().getStoName());
				viewHolder.setText(R.id.tv_coupon_all, item.getConNum() + "");
				viewHolder.SetUrlImage(R.id.tv_coupon_ima, item.getStoreName().getStoImg());
			}
		};
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		getDate();
		list.setAdapter(adapter);
		super.onActivityCreated(savedInstanceState);

	}

	private void getDate() {
		String url = GetHttp.getHttpLJ() + "GetCouponServlet";

		RequestParams parterm = new RequestParams();
		parterm.addBodyParameter("page", "");// 查询第几页
		parterm.addBodyParameter("num", "");// 每页显示几行
		httpUtlis.send(HttpMethod.POST, url, parterm, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

				Type type = new TypeToken<List<Coupon>>() {
				}.getType();
				// users = gson.fromJson(arg0.result, type);
				// Log.i(TAG, users.toString());
				// Toast.makeText(getActivity(), users.toString(), 0).show();
				// Log.i("TAG", users.toString());
				adapter.notifyDataSetChanged();

			}
		});

	}
}
