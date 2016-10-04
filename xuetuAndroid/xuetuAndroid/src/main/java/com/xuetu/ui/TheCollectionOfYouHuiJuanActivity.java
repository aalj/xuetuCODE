package com.xuetu.ui;

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
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * 
 * 优惠劵管理 不是收藏
 * 
 * @author BCL
 *
 */
public class TheCollectionOfYouHuiJuanActivity extends Baseactivity implements OnItemClickListener,OnClickListener{
	ListView listview;
	List<MyCoupon> datas = new ArrayList<MyCoupon>();
	MyBasesadapter<MyCoupon> myadapter;
	Student student;
	TitleBar titlebar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		student = ((XueTuApplication) getApplication()).getStudent();
		setContentView(R.layout.activity_the_collection_of_you_hui_juan);
		listview = (ListView) findViewById(R.id.listView);
		listview.setOnItemClickListener(this);
		titlebar = (TitleBar) findViewById(R.id.backtoperson);
		titlebar.setLeftLayoutClickListener(this);
		getCoupon();

	}
	@Override
	public void onClick(View v) {
		finish();

	}
	/**
	 * listview的加载
	 */
	private void addView() {
		Log.i("TAG", datas + "");
		// 设置适配器
		listview.setAdapter(myadapter = new MyBasesadapter<MyCoupon>(this, 
				datas, R.layout.youhuijuan) {

			@Override
			public void convert(ViewHodle viewHolder, MyCoupon mycoupon) {
				viewHolder.setIayoutBgColor(R.id.layout, R.drawable.cornro_myshoucang);
				
				viewHolder.setText(R.id.youhuijuanxingxi, mycoupon.getCoupon().getCouInfo());
				viewHolder.setText(R.id.number, mycoupon.getCoupon().getCouPrice() + "折");
				viewHolder.SetUrlImage(R.id.head,
						GetHttp.getHttpBCL() + mycoupon.getCoupon().getCouIma());
				viewHolder.setText(R.id.youhuijuanshiyongqingkuang, mycoupon.getUserState().getUstaName());
				if ((mycoupon.getUserState().getUstaID() == 1)) {
					viewHolder.setIayoutBgColor(R.id.layout, R.drawable.cornro_myshoucang);
					
				}
				else if((mycoupon.getUserState().getUstaID() == 2)){
					viewHolder.setIayoutBgColor(R.id.layout, R.drawable.cornor_layout);
					
				}

			}
		});

	}

	/**
	 * 通过学生ID得到收藏优惠劵ID 查询优惠劵表格得到个人优惠劵
	 * 
	 */

	public void getCoupon() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "TheCollectionOfYouHuiJuanServlet";
		RequestParams params = new RequestParams();
		try {

			params.addBodyParameter("stuid", URLEncoder.encode(String.valueOf(student.getStuId()), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Log.i("TAG", student.getStuId() + " ");
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				Log.i("TAG", result);
				Type type = new TypeToken<List<MyCoupon>>() {
				}.getType();
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				datas = gson.fromJson(result, type);
				addView();
			}

		});

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		intent.setClass(this, YouHuiJuanInfomationActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("MyCoupon", datas.get(position));
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
