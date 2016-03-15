package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.ForgetPwdActivity;
import com.xuetu.R;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
	TitleBar titlebar;
	EditText et_usertel;
	EditText et_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		titlebar = (TitleBar) findViewById(R.id.title_back);
		titlebar.setLeftLayoutClickListener(this);
		et_usertel = (EditText) findViewById(R.id.et_tel);
		et_password = (EditText) findViewById(R.id.et_password);
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "点击了", 1).show();
		finish();

	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.text_register:
			Intent intent1 = new Intent();
			intent1.setClass(getApplicationContext(), RegisterActivity.class);
			startActivity(intent1);
			break;
		case R.id.btn_login:
			login();
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), PerCerter.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("KEY", student);
			startActivity(intent);

			break;

		case R.id.text_forgetpwd:
			Intent intent2 = new Intent();
			intent2.setClass(getApplicationContext(), ForgetPwdActivity.class);
			startActivity(intent2);

			break;

		default:
			break;
		}

	}

	public void register() {

	}

	Student student;

	public void login() {

		String telephone = et_usertel.getText().toString().trim();
		String password = et_password.getText().toString().trim();
		// 使用框架utils发送数据到服务器，
		HttpUtils httpUtils = new HttpUtils();
		// 10.201.1.5
		String url = "http://10.201.1.5:8080/xuetuWeb/LoginAndroid";

		RequestParams params = new RequestParams();
		try {
			params.addBodyParameter("telephone", URLEncoder.encode(telephone, "utf-8"));
			params.addBodyParameter("pwd", URLEncoder.encode(password, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Log.i("TAG", arg1);
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Log.i("TAG", "login");
				Toast.makeText(getApplicationContext(), "成功", 1).show();
				// 从服务器得值 是否是正确
				String result = arg0.result;

				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				student = gson.fromJson(result, Student.class);
				((XueTuApplication) getApplication()).setStudent(student);

			}
		});

	}

}
