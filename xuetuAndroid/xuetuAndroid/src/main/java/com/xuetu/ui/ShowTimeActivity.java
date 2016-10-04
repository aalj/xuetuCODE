package com.xuetu.ui;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.umeng.socialize.utils.Log;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.LongTime;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.OnRefreshListener;
import com.xuetu.view.RefreshListView;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

public class ShowTimeActivity extends Activity implements OnRefreshListener, OnClickListener {
	RefreshListView refreshlistview = null;
	MyBasesadapter myBaseAdapter;
	List<LongTime> times = new ArrayList<LongTime>();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
	TitleBar main_title;
	int i = 1;
	List<LongTime> olduser=new ArrayList<LongTime>();

	Handler hadler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Log.i("TAG", "收到消息");
			myBaseAdapter.notifyDataSetChanged();
			super.handleMessage(msg);
		}
	};

	int stuId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_time);
		refreshlistview = (RefreshListView) findViewById(R.id.refreshlistview);
		refreshlistview.setOnRefreshListener(this);
		stuId = ((XueTuApplication) getApplication()).getStudent().getStuId();
		gettime(0, stuId, i);
		// refreshlistview.setAdapter(myBaseAdapter);
		main_title = (TitleBar) findViewById(R.id.main_title);
		main_title.setLeftLayoutClickListener(this);
		myBaseAdapter = new MyBasesadapter<LongTime>(ShowTimeActivity.this, times, R.layout.showtime) {

			@Override
			public void convert(ViewHodle viewHolder, LongTime item) {
				Log.i("TAG", "学习和市场----------" + item.toString());
				viewHolder.setText(R.id.riqi, dateFormat.format(item.getMyDate()));
				viewHolder.setText(R.id.week, DataToTime.getWeekOfDate(item.getMyDate()));
				long myTime = item.getMyTime();
				long myTime2 = myTime / 60;
				if (myTime2 != 0) {
					viewHolder.setText(R.id.time, myTime / 60 + "分钟" + myTime % 60 + "秒");

				} else {

					viewHolder.setText(R.id.time, +myTime % 60 + "秒");
				}

			}
		};
		refreshlistview.setAdapter(myBaseAdapter);

	}

	/**
	 * 加载学习时间
	 */
	DecimalFormat df = new DecimalFormat("######0.00");

	public void gettime(final int temp, int stuid, int i) {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "GetLongTime";

		RequestParams pra = new RequestParams();
		pra.addBodyParameter("stuID", stuid + "");
		pra.addBodyParameter("week", stuid + "");
		httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Type type = new TypeToken<List<LongTime>>() {
				}.getType();
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

				List<LongTime> time = gson.fromJson(arg0.result, type);
				Log.i("TAG", "学习和市场----------" + time.toString());

				if (temp == 1) {
					times.removeAll(olduser);
					
					times.addAll(0, time);
					refreshlistview.hideHeaderView();
					myBaseAdapter.notifyDataSetChanged();
				} else {
					times.removeAll(times);
					times.addAll(time);
					refreshlistview.hideFooterView();
					myBaseAdapter.notifyDataSetChanged();

				}
				olduser.addAll(time);
			}
		});
	}

	@Override
	public void onDownPullRefresh() {
		gettime(1, stuId, 1);
	}

	@Override
	public void onLoadingMore() {
		i++;
		gettime(0, stuId, i);
	}

	@Override
	public void onClick(View v) {
		finish();

	}

}
