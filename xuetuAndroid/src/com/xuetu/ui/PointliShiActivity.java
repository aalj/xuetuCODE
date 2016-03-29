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
import com.xuetu.R;
import com.xuetu.R.layout;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.JiFenMingXi;
import com.xuetu.entity.Pattern;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.MyListView;
import com.xuetu.view.RefreshListView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class PointliShiActivity extends Activity {
	MyListView rListView = null;
	SharedPreferences sp = null;
	int stuId = 0;
	List<JiFenMingXi> jiFenMingXiList = new ArrayList<JiFenMingXi>();
	MyBasesadapter<JiFenMingXi> basesadapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_you_hui_juan);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		stuId = ((XueTuApplication) getApplication()).getStudent().getStuId();
		rListView = (MyListView) findViewById(R.id.listView);
		getJifenShuju();
		

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
							viewHolder.setText(R.id.jifen, "- "+item.getUnmpuint());
						}else{
							viewHolder.setText(R.id.jifen, "+ "+item.getUnmpuint()+"");
							
						}
						switch (item.getImgUrl()) {
						case "1"://加载问题图片
							
							break;
						case "2"://加载答案图片
							
							break;
						case "3"://加载学习时间图片
							
							break;
						default://加载网络图片
							viewHolder.SetUrlImage(R.id.imabiaozhi, GetHttp.getHttpLJ() + item.getImgUrl());
							
							break;
						}
					}
				};
				rListView.setAdapter(basesadapter);
//				basesadapter.notifyDataSetChanged();
			}
		});
	}

	public void onclick(View v) {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		intent.putExtra("page", 0);
		startActivity(intent);
	}
}
