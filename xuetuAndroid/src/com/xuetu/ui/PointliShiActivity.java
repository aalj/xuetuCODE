package com.xuetu.ui;

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
import com.umeng.socialize.utils.Log;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.JiFenMingXi;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.MyListView;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class PointliShiActivity extends Activity implements OnItemClickListener, OnClickListener {
	MyListView rListView = null;
	SharedPreferences sp = null;
	ScrollView scrollView = null;
	int stuId = 0;
	List<JiFenMingXi> jiFenMingXiList = new ArrayList<JiFenMingXi>();
	MyBasesadapter<JiFenMingXi> basesadapter;
	TextView textvView;
	ProgressDialog progressDialog = null;
	protected static final int SUCCESS_GET_DATA = 0;
	protected static final String TAG = "TAG";
	private boolean finish = true;// 是否加载完成
	private View footer;
	//加载积分的初始天数
	private int countPage = 3;
	
	
	
	
	Handler handler = new Handler() {
		@Override
		public String getMessageName(Message message) {
			if (message.what == 123) {
				String jifen = (String) message.obj;
				textvView.setText(jifen);
			}
			if (message.what == SUCCESS_GET_DATA) {
				basesadapter.notifyDataSetChanged();
			}
			return super.getMessageName(message);
		}
	};

	// 积分中心刷新出现问题

	TitleBar title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_you_hui_juan);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		stuId = ((XueTuApplication) getApplication()).getStudent().getStuId();

		// 加载总积分
		jiaZaiShuJu(stuId);

		rListView = (MyListView) findViewById(R.id.listView);
		scrollView = (ScrollView) findViewById(R.id.scrollView);
		
		footer = View.inflate(this, R.layout.footer, null);
		
		textvView = (TextView) findViewById(R.id.textView2);
		title = (TitleBar) findViewById(R.id.backtoperson);
		rListView.setOnItemClickListener(this);

		textvView.setText(sp.getString("jifen", "0"));
		// 在增加listview的页脚之前，需要提前设置一次
		rListView.addFooterView(footer);

		// 滚动监听事件
//		rListView.setOnScrollListener();
		// main_pull_refresh_view.setLastUpdated(new Date().toLocaleString());
		// 加载积分历史
		getJifenShuju(countPage);
		title.setLeftLayoutClickListener(this);

		scrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_UP) {
					View view = ((ScrollView) v).getChildAt(0);
					Log.e("getMeasuredHeight()------->", view.getMeasuredHeight() + "");
					Log.e("getScrollY()------->", v.getScrollY() + "");
					Log.e("getHeight()------->", v.getHeight() + "");
					// v.getHeight()可看見的控件高度
					// v.getScrollY()在y軸方向的偏移量
					// 整個控件的高度（包括不可見的如ScrollView）
					if (view.getMeasuredHeight() <= v.getScrollY() + v.getHeight()) {
						countPage++;
						Log.i("TAG", "kankna刚加载是否有执行------>>>"+countPage);
						getJifenShujuTo(countPage);
						showDengdai();
					}
				}

				return false;
			}
		});
	}

	
	
	private void showDengdai() {
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(PointliShiActivity.this, "", "正在加载...");
			progressDialog.setCancelable(true);
			progressDialog.show();
		} else {

		}
	}
	
	
	private void jiaZaiShuJu(int stuid) {

		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "DedaoJIFen";
		RequestParams pra = new RequestParams();
		pra.addBodyParameter("stuid", stuid + "");
		httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(getApplicationContext(), arg1, 1).show();

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String jiFen = arg0.result;
				sp.edit().putString("jifen", jiFen).commit();

				textvView.setText(jiFen);
				Log.i("TAG", "-----------的到积分" + jiFen);
				Message message = Message.obtain();
				message.what = 123;
				message.obj = jiFen;
				handler.sendMessage(message);
			}

		});
	}

	public void getJifenShuju(int countPage) {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "JiFenXiangXi";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuid", "" + stuId);
		params.addBodyParameter("weeknum", countPage + "");
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type type = new TypeToken<List<JiFenMingXi>>() {
				}.getType();
				// 获得积分详情数据
				jiFenMingXiList = gson.fromJson(arg0.result, type);
				// 然后再次将页脚删除掉
				rListView.removeFooterView(footer);
				basesadapter = new MyBasesadapter<JiFenMingXi>(PointliShiActivity.this, jiFenMingXiList,
						R.layout.jifenxiaofei) {

					@Override
					public void convert(ViewHodle viewHolder, JiFenMingXi item) {
						viewHolder.setText(R.id.riqi, DataToTime.dataToh(item.getTime()));
						// viewHolder.setText(R.id.info, item.getText());
						viewHolder.setText(R.id.textView1, DataToTime.getWeekOfDate(item.getTime()));

						if ("3".equals(item.getImgUrl()) || "2".equals(item.getImgUrl())) {
							viewHolder.setText(R.id.jifen, "+ " + item.getUnmpuint());
						} else {
							viewHolder.setText(R.id.jifen, "- " + item.getUnmpuint() + "");

						}
						switch (item.getImgUrl()) {
						case "1":// 加载问题图片
							viewHolder.setText(R.id.jifen, "提问题花费+ " + item.getUnmpuint() + "积分");
							viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_home_widget_qa);
							break;
						case "2":// 加载答案图片
							viewHolder.setText(R.id.jifen, "回答问题得到+ " + item.getUnmpuint() + "积分");
							viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_item_tishiyu);

							break;
						case "3":// 加载学习时间图片
							viewHolder.setText(R.id.jifen, "学习得到+ " + item.getUnmpuint() + "积分");
							viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_home_widget_study);

							break;
						default:// 加载网络图片
							viewHolder.setText(R.id.jifen, "兑换优惠券花费+ " + item.getUnmpuint() + "积分");
							viewHolder.SetUrlImage(R.id.imabiaozhi, GetHttp.getHttpLJ() + item.getImgUrl());

							break;
						}
					}
				};
				rListView.setAdapter(basesadapter);
			}
		});
	}

	public void onclick(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		intent.putExtra("page", 0);
		startActivity(intent);
	}

	public void getJifenShujuTo(int i) {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "JiFenXiangXi";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuid", "" + stuId);
		params.addBodyParameter("weeknum", i + "");
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Log.i("TAG", "访问网络是否执行加载是否执行");
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type type = new TypeToken<List<JiFenMingXi>>() {
				}.getType();
//				jiFenMingXiList.clear();
				// 获得积分详情数据
				jiFenMingXiList = gson.fromJson(arg0.result, type);
				if (progressDialog != null)
				progressDialog.dismiss();
				Log.i(TAG, "				jiFenMingXiList+"				+jiFenMingXiList.size());
				basesadapter = new MyBasesadapter<JiFenMingXi>(PointliShiActivity.this, jiFenMingXiList,
						R.layout.jifenxiaofei) {

					@Override
					public void convert(ViewHodle viewHolder, JiFenMingXi item) {
						viewHolder.setText(R.id.riqi, DataToTime.dataToh(item.getTime()));
						// viewHolder.setText(R.id.info, item.getText());
						viewHolder.setText(R.id.textView1, DataToTime.getWeekOfDate(item.getTime()));

						if ("3".equals(item.getImgUrl()) || "2".equals(item.getImgUrl())) {
							viewHolder.setText(R.id.jifen, "+ " + item.getUnmpuint());
						} else {
							viewHolder.setText(R.id.jifen, "- " + item.getUnmpuint() + "");

						}
						switch (item.getImgUrl()) {
						case "1":// 加载问题图片
							viewHolder.setText(R.id.jifen, "提问题花费+ " + item.getUnmpuint() + "积分");
							viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_home_widget_qa);
							break;
						case "2":// 加载答案图片
							viewHolder.setText(R.id.jifen, "回答问题得到+ " + item.getUnmpuint() + "积分");
							viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_item_tishiyu);

							break;
						case "3":// 加载学习时间图片
							viewHolder.setText(R.id.jifen, "学习得到+ " + item.getUnmpuint() + "积分");
							viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_home_widget_study);

							break;
						default:// 加载网络图片
							viewHolder.setText(R.id.jifen, "兑换优惠券花费+ " + item.getUnmpuint() + "积分");
							viewHolder.SetUrlImage(R.id.imabiaozhi, GetHttp.getHttpLJ() + item.getImgUrl());

							break;
						}
					}
				};
				
				
//				// 然后再次将页脚删除掉
//				rListView.removeFooterView(footer);
//				Message message = Message.obtain();
//				message.what = SUCCESS_GET_DATA;
//
//				handler.sendMessage(message);

				 rListView.setAdapter(basesadapter);
//				 basesadapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(PointliShiActivity.this, JiFenXiangQingActivity.class);
		intent.putExtra("jifenIten", jiFenMingXiList.get(position));
		startActivity(intent);

	}

	@Override
	public void onClick(View v) {
		finish();

	}




}
