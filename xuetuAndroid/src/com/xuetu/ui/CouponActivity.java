package com.xuetu.ui;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.entity.Coupon;
import com.xuetu.utils.GetHttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * 显示优惠券列表
 * 
 * @author liang
 *
 */
public class CouponActivity extends Activity {
	@ViewInject(R.id.list_coupon)
	ListView list_coupon;

	HttpUtils httpUtlis = new HttpUtils();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupon);
		ViewUtils.inject(this);
		getDate();

	}

	private void getDate() {
		String url=GetHttp.getHttpLJ()+"";
		
		RequestParams parterm = new RequestParams();
		parterm.addBodyParameter("page","");//查询第几页
		parterm.addBodyParameter("num","");//每页显示几行
		httpUtlis.send(HttpMethod.POST, url,parterm  ,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

				Type type = new TypeToken<List<Coupon>>() {
				}.getType();
				List<Coupon> users = gson.fromJson(arg0.result, type);
				Log.i("TAG", users.toString());
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coupon, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
