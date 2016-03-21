package com.xuetu.ui;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.xuetu.entity.Coupon;
import com.xuetu.utils.GetHttp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class StoneNameActivity extends Activity implements OnRefreshListener {
	private static final int REFRESH_COMPLETE = 0X110;
	private SwipeRefreshLayout mSwipeLayout;
	private ListView mListView;
	HttpUtils httpUtlis = new HttpUtils();
	
	//用于保存上一次获取的数据
	List<Coupon> olduser=new ArrayList<Coupon>();
	
	//用户与存储网络上获取的数据
	List<Coupon> users = new ArrayList<Coupon>();
	Coupon coupon = null;
	private MyListViewAdatper mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stone_name);

		  coupon = (Coupon)getIntent().getSerializableExtra("coupon");
		
		
		
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
		mSwipeLayout.setOnRefreshListener(this);
		/**
		 * 设置刷新时候的颜色
		 */
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);

		mListView = (ListView) findViewById(R.id.id_listview);
		ArrayList<String> mData = new ArrayList<String>();

		mAdapter = new MyListViewAdatper(mData);

		mListView.setAdapter(mAdapter);
	}

	private class MyListViewAdatper extends BaseAdapter {
		BitmapUtils bitmapUtils = new BitmapUtils(StoneNameActivity.this);
		LayoutInflater m_inflater = null;
		private ArrayList<String> mData = new ArrayList<String>();

		public MyListViewAdatper(ArrayList<String> Data) {
			m_inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mData = Data;
		}

		@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
			return position > 0 ? 0 : 1;
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mData.get(position);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			if (getItemViewType(position) == 0)//
			{
				ViewHolder holder = null;

				if (convertView == null) {

					view = m_inflater.inflate(R.layout.coupon_item, null);
					holder = new ViewHolder();
					holder.tv_coupon_name = (TextView) view.findViewById(R.id.tv_coupon_name);
					holder.tv_shoppingname = (TextView) view.findViewById(R.id.tv_shoppingname);
					holder.tv_coupon_all = (TextView) view.findViewById(R.id.tv_coupon_all);
					holder.tv_coupon_ima = (ImageView) view.findViewById(R.id.tv_coupon_ima);
					view.setTag(holder);
				} else {
					view = convertView;
					holder = (ViewHolder) view.getTag();
				}

				holder.tv_coupon_name.setText(users.get(position).getCouName());
				;
				holder.tv_shoppingname.setText(users.get(position).getStoreName().getStoName());
				;
				holder.tv_coupon_all.setText(users.get(position).getConNum() + "");
				;
				bitmapUtils.display(holder.tv_coupon_ima,
						GetHttp.getHttpLJ() + users.get(position).getStoreName().getStoImg());
				;
			} else if (getItemViewType(position) == 1)// 如果是顶部viewpager
			{
				ViewPagerHolder holder = null;
				if (convertView == null) {
					view = m_inflater.inflate(R.layout.ima, null);
					holder = new ViewPagerHolder();
					bitmapUtils.display(holder.imaview,
							GetHttp.getHttpLJ() + users.get(position).getStoreName().getStoImg());

					view.setTag(holder);
				} else {
					view = convertView;
					holder = (ViewPagerHolder) view.getTag();

				}
				holder.imaview.setImageResource(R.drawable.ic_launcher);
			}

			return view;
		}

	}

	private void getDate(final int tempnum) {
		String url = GetHttp.getHttpLJ() + "GetCouponServlet";

		RequestParams parterm = new RequestParams();

		parterm.addBodyParameter("reqtemp", "1");// 每页显示10条
		httpUtlis.send(HttpMethod.POST, url, parterm, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

				Type type = new TypeToken<List<Coupon>>() {
				}.getType();
				 users = gson.fromJson(arg0.result, type);
//				users.removeAll(olduser);
//				users.addAll(0,user);
//				olduser=user;
				users.clear();
				users.addAll(users);
				mAdapter.notifyDataSetChanged();
				
				
				
			}

		});

	}

	public static class ViewHolder {
		public TextView tv_coupon_name;
		public TextView tv_shoppingname;
		public TextView tv_coupon_all;
		public ImageView tv_coupon_ima;

	}

	public static class ViewPagerHolder {
		public ImageView imaview;
	}

	@Override
	public void onRefresh() {
		getDate(coupon.getStoreName().getStoID());

	}
}
