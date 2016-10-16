package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.XueTuApplication;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.widget.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeAgeActivity extends Activity implements OnClickListener {
	EditText edit_age;
	TitleBar titlebar;
	int stuId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_age);
		edit_age = (EditText) findViewById(R.id.edit_age);
		titlebar = (TitleBar) findViewById(R.id.titlebar);
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutClickListener(this);
		stuId = ((XueTuApplication) getApplication()).getStudent().getStuId();
		loadData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_layout:
			finish();
			break;
		case R.id.right_layout:
			update();
			Log.i("TAG", "~~~~~~~~~~~~update执行完");
			Intent intent = new Intent();
			String ed_name = edit_age.getText().toString();
			Student student = ((XueTuApplication) getApplication()).getStudent();
			if (!TextUtils.isEmpty(ed_name)) {
				student.setStuAge(Integer.parseInt(ed_name));
			}
			intent.putExtra("edit_age", ed_name);
			setResult(3, intent);
			finish();
			break;
		default:
			break;
		}

	}

	public void loadData() {
		Intent intent = getIntent();
		String userage = intent.getStringExtra("age");
		edit_age.setText(userage);
	}

	public void update() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "ChangeAgeServlet";
		RequestParams params = new RequestParams();
		try {
			params.addBodyParameter("id", URLEncoder.encode(stuId + "", "utf-8"));
			params.addBodyParameter("changeage", URLEncoder.encode(edit_age.getText().toString(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {

				Gson gson = new Gson();
				Type type = new TypeToken<Boolean>() {
				}.getType();
				Boolean result_back = gson.fromJson(arg0.result, type);
				Log.i("TAG", "-----" + result_back);
				if (result_back == true) {
					Toast.makeText(getApplicationContext(), "修改成功", 1).show();
				} else {
					Toast.makeText(getApplicationContext(), "boom", 1).show();
				}

			}
		});
	}
	// RequestQueue queue;
	// TitleBar titlebar;
	// EditText edit_age;
	// int num;
	// ProgressDialog progressDialog = null;
	//
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_change_age);
	// num = Integer.parseInt(getIntent().getStringExtra("key2"));
	// iniView();
	// queue = Volley.newRequestQueue(this);
	//
	// }
	// int stuid = 0;
	// private void iniView() {
	// stuid=((XueTuApplication)getApplication()).getStudent().getStuId();
	// titlebar = (TitleBar) findViewById(R.id.titlebar);
	// edit_age = (EditText) findViewById(R.id.edit_age);
	// titlebar.setLeftLayoutClickListener(this);
	// titlebar.setRightLayoutClickListener(this);
	// switch (num) {
	// case 2:
	// titlebar.setTitle("修改昵称");
	// break;
	// case 3:
	// titlebar.setTitle("修改年龄");
	// break;
	// case 4:
	// titlebar.setTitle("修改年级");
	// break;
	// case 5:
	// titlebar.setTitle("修改学校");
	// break;
	// case 6:
	// titlebar.setTitle("修改个性签名");
	// break;
	//
	// default:
	// break;
	// }
	//
	// }
	//
	// private void showDengdai() {
	// if (progressDialog == null) {
	// progressDialog = ProgressDialog.show(ChangeAgeActivity.this, "",
	// "正在加载...");
	// progressDialog.show();
	// }
	// }
	//
	// public void saveValue(final int temp,final String stuid,final String
	// value) {
	// String params = GetHttp.getHttpLJ()+"";
	// StringRequest request = new StringRequest(Request.Method.POST, params,
	// new Listener<String>() {
	//
	// @Override
	// public void onResponse(String response) {
	// if("ok".equals(response)){
	// if (progressDialog != null)
	// progressDialog.dismiss();
	// setResult(temp, intent);
	// finish();
	// }
	//
	//
	//
	//
	//
	//
	//
	// }
	// }, new ErrorListener() {
	//
	// @Override
	// public void onErrorResponse(VolleyError error) {
	//
	//
	// }
	// }){
	// @Override
	// protected Map<String, String> getParams() throws AuthFailureError {
	// Map <String,String >map = new HashMap<String, String>();
	// map.put("temp", temp+"");
	// map.put("usepwd", stuid);
	// map.put("value", value);
	// return map;
	// }
	// };
	//
	// queue.add(request);
	//
	// }
	//
	// Intent intent;
	// @Override
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.left_image:
	// finish();
	// break;
	// case R.id.right_image:
	// intent = new Intent();
	// String ed_age = edit_age.getText().toString();
	// switch (num) {
	// case 2:
	// intent.putExtra("ed_name", ed_age);
	// saveValue(2, stuid+"",ed_age);
	//// setResult(2, intent);
	// break;
	// case 3:
	// intent.putExtra("ed_name", ed_age);
	// saveValue(3, stuid+"",ed_age);
	//// setResult(3, intent);
	// break;
	// case 4:
	// intent.putExtra("ed_name", ed_age);
	// saveValue(4, stuid+"",ed_age);
	//// setResult(4, intent);
	// break;
	// case 5:
	// intent.putExtra("ed_name", ed_age);
	// saveValue(5, stuid+"",ed_age);
	//// setResult(5, intent);
	// break;
	// case 6:
	// intent.putExtra("ed_name", ed_age);
	// saveValue(6, stuid+"",ed_age);
	// break;
	//
	// default:
	// break;
	// }
	//
	// break;
	//
	// default:
	// break;
	// }
	// showDengdai();
	//
	// }

}
