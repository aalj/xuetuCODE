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
import com.xuetu.JiFenXiangQingActivity;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.JiFenMingXi;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.MyListView;
import com.xuetu.view.PullToRefreshView;
import com.xuetu.view.PullToRefreshView.OnFooterRefreshListener;
import com.xuetu.view.PullToRefreshView.OnHeaderRefreshListener;
import com.xuetu.view.TitleBar;

import android.app.Activity;
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

public class PointliShiActivity extends Activity implements OnHeaderRefreshListener, OnFooterRefreshListener, OnItemClickListener, OnClickListener {
	MyListView rListView = null;
	SharedPreferences sp = null;
	int stuId = 0;
	PullToRefreshView  main_pull_refresh_view =null;
	List<JiFenMingXi> jiFenMingXiList = new ArrayList<JiFenMingXi>();
	MyBasesadapter<JiFenMingXi> basesadapter;
	TextView textvView;
	Handler handler = new Handler(){
		@Override
		public String getMessageName(Message message) {
			if(message.what==123){
				String jifen = (String )message.obj;
				textvView.setText(jifen);
			}
			return super.getMessageName(message);
		}
	};
	
	//积分中心刷新出现问题
	
	
	TitleBar title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_you_hui_juan);
		  main_pull_refresh_view = (PullToRefreshView) findViewById(R.id.main_pull_refresh_view);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		stuId = ((XueTuApplication) getApplication()).getStudent().getStuId();
		//加载总积分
		jiaZaiShuJu(stuId);
		rListView = (MyListView) findViewById(R.id.listView);
		textvView = (TextView) findViewById(R.id.textView2);
		title = (TitleBar) findViewById(R.id.backtoperson);
		main_pull_refresh_view.setOnHeaderRefreshListener(this);
		main_pull_refresh_view.setOnFooterRefreshListener(this);
		rListView.setOnItemClickListener(this);
//		main_pull_refresh_view.setLastUpdated(new Date().toLocaleString());
		//加载积分历史
		getJifenShuju();
		title.setLeftLayoutClickListener(this);
		
		
		

	}

	
	private void jiaZaiShuJu(int stuid) {
		
	HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "DedaoJIFen";
		RequestParams pra = new RequestParams();
		pra.addBodyParameter("stuid", stuid+ "");
		httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(getApplicationContext(), arg1, 1).show();

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String jiFen =arg0.result;
				textvView.setText(jiFen);
				Log.i("TAG", "-----------的到积分"+jiFen);
				Message message = Message.obtain();
				message.what=123;
				message.obj=jiFen;
				handler.sendMessage(message);
			}
				
				
		});
	}
	
	
	
	public void getJifenShuju() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "JiFenXiangXi";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuid", "" + stuId);
		params.addBodyParameter("weeknum", 1 + "");
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
				
				basesadapter = new MyBasesadapter<JiFenMingXi>(PointliShiActivity.this,
						jiFenMingXiList, R.layout.jifenxiaofei) {

					@Override
					public void convert(ViewHodle viewHolder, JiFenMingXi item) {
						viewHolder.setText(R.id.riqi, DataToTime.dataToh(item.getTime()));
						viewHolder.setText(R.id.info, item.getText());
						if("3".equals(item.getImgUrl())||"2".equals(item.getImgUrl())){
							viewHolder.setText(R.id.jifen, "+ "+item.getUnmpuint());
						}else{
							viewHolder.setText(R.id.jifen, "- "+item.getUnmpuint()+"");
							
						}
						switch (item.getImgUrl()) {
						case "1"://加载问题图片
							viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_home_widget_qa);
							break;
						case "2"://加载答案图片
							viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_item_tishiyu);
							
							break;
						case "3"://加载学习时间图片
							viewHolder.setIma(R.id.imabiaozhi, R.drawable.ic_home_widget_study);
							
							break;
						default://加载网络图片
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

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		Log.i("TAG", "上拉加载");
		
		
	}
	
	public void getJifenShujuTo() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "JiFenXiangXi";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuid", "" + stuId);
		params.addBodyParameter("weeknum", 1 + "");
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
				
				rListView.setAdapter(basesadapter);
				main_pull_refresh_view.onHeaderRefreshComplete();
				
			}
		});
	}
	
	

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		Log.i("TAG", "下拉加载");
		getJifenShujuTo();
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(PointliShiActivity.this,JiFenXiangQingActivity.class);
		intent.putExtra("jifenIten", jiFenMingXiList.get(position));
		startActivity(intent);
		
	}


	@Override
	public void onClick(View v) {
		finish();
		
	}
}
