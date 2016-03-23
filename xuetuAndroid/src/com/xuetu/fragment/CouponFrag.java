package com.xuetu.fragment;

import java.lang.reflect.Type;
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
import com.xuetu.entity.Coupon;
import com.xuetu.ui.CouponInfoActivity;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.OnRefreshListener;
import com.xuetu.view.RefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class CouponFrag extends Fragment implements OnRefreshListener, OnItemClickListener {
	HttpUtils httpUtlis = new HttpUtils();
	List<Coupon> users = null;
	View view;
	List<Coupon> olduser=new ArrayList<Coupon>();
	// 显示所有商品的列表

	private final int REFRESH_TEMP = 1;
	private final int REFRESH_LIMIT = 2;
	int countpage = 0;
	/** 请求数据的页数 */
	private int pageIndex = 0;
	MyBasesadapter<Coupon> myBaseAdapter = null;
	RefreshListView rListView;
	//表示是否是第一次进入改也页面
	boolean firstInto = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.coupon_frag, null);
//		olduser= ((XueTuApplication) getActivity().getApplication()).getListConpun();
//		olduser=users;
		users= new ArrayList<Coupon>();
		rListView = (RefreshListView) view.findViewById(R.id.refreshlistview);
		getDate(1, REFRESH_TEMP);
		
		myBaseAdapter = new MyBasesadapter<Coupon>(getActivity(), users, R.layout.coupon_item) {
			

			@Override
			public void convert(ViewHodle viewHolder, Coupon item) {
				viewHolder.setText(R.id.tv_coupon_name, item.getCouName());
				viewHolder.setText(R.id.tv_shoppingname, item.getStoreName().getStoName());
				viewHolder.setText(R.id.tv_coupon_all, item.getConNum() + "");
				viewHolder.SetUrlImage(R.id.tv_coupon_ima, GetHttp.getHttpLJ()+item.getStoreName().getStoImg());
			}
		};
		rListView.setAdapter(myBaseAdapter);
		rListView.setOnItemClickListener(this);
		rListView.setOnRefreshListener(this);
		return view;
	}

	private void getDate(final int tempnum, int temp) {
		
		
		
		

		String url = GetHttp.getHttpLJ() + "GetCouponServlet";

		RequestParams parterm = new RequestParams();
		if (temp == REFRESH_TEMP) {
			parterm.addBodyParameter("page", "0");// 查询第1页
		} else {
			countpage++;
			parterm.addBodyParameter("page", countpage + "");// 查询第1页

		}
		parterm.addBodyParameter("num", "10");// 每页显示10条
		parterm.addBodyParameter("reqtemp", "0");// 每页显示10条
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
				
				System.out.println(user.toString());
				if(tempnum==1){
					
					users.removeAll(olduser);
					users.addAll(0,user);
					myBaseAdapter.notifyDataSetChanged();
					rListView.hideHeaderView();
					olduser=user;
				}
				else{
					users.addAll(user);
					myBaseAdapter.notifyDataSetChanged();
					// 控制脚布局隐藏
					rListView.hideFooterView();
				}
				

			}
		});

	}

	@Override
	public void onDownPullRefresh() {
		// 这是下拉刷新出来的数据
		
		getDate(1, REFRESH_TEMP);

	}

	@Override
	public void onLoadingMore() {
		// 这是加载更多出来的数据1
		getDate(2, REFRESH_LIMIT);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		users.get(position-1);
		intent.setClass(getActivity(), CouponInfoActivity.class);
		intent.putExtra("coupon", users.get(position-1));
		startActivity(intent);
		
	}

}
