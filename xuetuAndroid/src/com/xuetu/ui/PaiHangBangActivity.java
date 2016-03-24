package com.xuetu.ui;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.PersonalStudyTimeAll;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class PaiHangBangActivity extends Activity implements OnClickListener {
	ListView listView_paihangbang;
	TitleBar titlebar;
	MyBasesadapter<PersonalStudyTimeAll> myadapter;
	List<PersonalStudyTimeAll> datas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pai_hang_bang);
		listView_paihangbang = (ListView) findViewById(R.id.listView_paihangbang);
		titlebar = (TitleBar) findViewById(R.id.backtoperson_paihangbang);
		titlebar.setLeftLayoutClickListener(this);
		getDate();
	}

	@Override
	public void onClick(View v) {
		finish();
	}

	/**
	 * 数据的获取
	 * 
	 */

	public void getDate() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "PaiHangbangServlet";
		httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new Gson();
				Type type = new TypeToken<List<PersonalStudyTimeAll>>() {
				}.getType();
				datas = gson.fromJson(arg0.result, type);

				loadView();

			}
		});
	}

	/**
	 * 加载listv
	 * 
	 */
	int i = -2;

	public void loadView() {
		i++;
		listView_paihangbang.setAdapter(
				myadapter = new MyBasesadapter<PersonalStudyTimeAll>(this, datas, R.layout.listitem_of_paihangbang) {

					@Override
					public void convert(ViewHodle viewHolder, PersonalStudyTimeAll item) {
						Log.i("TAG", "66666666666" + datas.size());
						Log.i("TAG", "```````````" + i);
						viewHolder.setText(R.id.paihangbangxuhao, i + "");

						viewHolder.SetUrlImage(R.id.head_paihangbang,
								GetHttp.getHttpBCL() + item.getStudent().getStuIma());
						viewHolder.setText(R.id.nicheng_paihangbang, item.getStudent().getStuName());
						viewHolder.setText(R.id.number_paihangbang, item.getTimeAll());
					}
				});

	}

}
