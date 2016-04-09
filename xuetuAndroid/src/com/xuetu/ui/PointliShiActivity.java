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
import com.xuetu.view.PullToRefreshLayout;
import com.xuetu.view.PullToRefreshLayout.OnRefreshListener;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class PointliShiActivity extends Activity implements OnItemClickListener, OnClickListener, OnRefreshListener {
	MyListView rListView = null;
	SharedPreferences sp = null;
	// ScrollView scrollView = null;
	int stuId = 0;
	List<JiFenMingXi> jiFenMingXiList = new ArrayList<JiFenMingXi>();
	MyBasesadapter<JiFenMingXi> basesadapter;
	TextView textvView;
	ProgressDialog progressDialog = null;
	protected static final int SUCCESS_GET_DATA = 0;
	protected static final String TAG = "TAG";
	private boolean finish = true;// 是否加载完成
	// 加载积分的初始天数
	private int countPage = 3;
	PullToRefreshLayout pull = null;
	
	private static int LOADMORE  = 0;
	private static int REFRESH =1;
	
	

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

		rListView = (MyListView) findViewById(R.id.list);
		// 下拉刷新的scrollView
		pull = (PullToRefreshLayout) findViewById(R.id.refresh_view);

		textvView = (TextView) findViewById(R.id.textView2);
		title = (TitleBar) findViewById(R.id.backtoperson);
		// 设置ListView适配器
//		setMyAdapter();
		rListView.setOnItemClickListener(this);

		textvView.setText(sp.getString("jifen", "0"));

		// 加载积分历史
		getJifenShuju(REFRESH,countPage, null);
		title.setLeftLayoutClickListener(this);
		pull.setOnRefreshListener(this);
	}

	/**
	 * 设置listView设配器内容
	 */
	public void setMyAdapter() {
		basesadapter = new MyBasesadapter<JiFenMingXi>(PointliShiActivity.this, jiFenMingXiList,
				R.layout.jifenxiaofei) {

			@Override
			public void convert(ViewHodle viewHolder, JiFenMingXi item) {
				viewHolder.setText(R.id.riqi, DataToTime.dataToh(item.getTime()));
				viewHolder.setText(R.id.textView1, DataToTime.getWeekOfDate(item.getTime()));

				switch (item.getImgUrl()) {
				case "1":// 加载问题图片
					viewHolder.setText(R.id.jifen, "提问题- " + item.getUnmpuint() + "积分");
					viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_home_widget_qa);
					break;
				case "2":// 加载答案图片
					viewHolder.setText(R.id.jifen, "回答问题+ " + item.getUnmpuint() + "积分");
					viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_item_tishiyu);

					break;
				case "3":// 加载学习时间图片
					viewHolder.setText(R.id.jifen, "学习+ " + item.getUnmpuint() + "积分");
					viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_home_widget_study);

					break;
				default:// 加载网络图片
					viewHolder.setText(R.id.jifen, "兑换优惠券- " + item.getUnmpuint() + "积分");
					viewHolder.SetUrlImage(R.id.imabiaozhi, GetHttp.getHttpLJ() + item.getImgUrl());

					break;
				}
			}
		};
		
		rListView.setAdapter(basesadapter);
		
		
	}

	/**
	 * 显示等待dialog弹窗
	 */
	private void showDengdai() {
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(PointliShiActivity.this, "", "正在加载...");
			progressDialog.setCancelable(true);
			progressDialog.show();
		} else {

		}
	}

	/**
	 * 网络获取积分总数
	 * 
	 * @param stuid
	 */
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
/**
 * 网络获取全部的积分明细
 * @param countPage
 * @param pullToRefreshLayout
 */
	public void getJifenShuju(final int flag,int Page, final PullToRefreshLayout pullToRefreshLayout) {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "JiFenXiangXi";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuid", "" + stuId);
		if(flag==REFRESH){
			params.addBodyParameter("weeknum", 3 + "");
			
		}else{
			
			params.addBodyParameter("weeknum", Page + "");
		}
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
				Type type = new TypeToken<List<JiFenMingXi>>() {
				}.getType();
				// 获得积分详情数据
				jiFenMingXiList = gson.fromJson(arg0.result, type);
				
				if(flag==REFRESH){
					//下拉刷新顶部的head消失
					if(pullToRefreshLayout!=null){
						
						pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
					}
				}
				else if(flag==LOADMORE){
					//上拉加载底部的foot消失
					pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				}
				
				setMyAdapter();
//				basesadapter.notifyDataSetChanged();
			}
		});
	}

//	public void onclick(View v) {
//		Intent intent = new Intent();
//		intent.setClass(this, MainActivity.class);
//		intent.putExtra("page", 0);
//		intent.putExtra("flag", -1);
//		startActivity(intent);
//	}

	

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

	@Override
	public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
		jiaZaiShuJu(stuId);
		getJifenShuju(REFRESH,3, pullToRefreshLayout);
	}

	@Override
	public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
		countPage++;
		getJifenShuju(LOADMORE,countPage, pullToRefreshLayout);

	}

}
