package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.utils.GetHttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	EditText et_usertelephone;
	EditText et_checkedNumber;
	EditText et_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		et_usertelephone = (EditText) findViewById(R.id.et_usertelephone);
		et_checkedNumber = (EditText) findViewById(R.id.et_checkedNumber);
		et_password = (EditText) findViewById(R.id.et_password);
	}

	public void onclick(View v) {

	}

	public void register() {
		String password = et_password.getText().toString();
		String checkedNumber = et_checkedNumber.getText().toString();
		String usertelephone = et_usertelephone.getText().toString();
		HttpUtils httpUtils = new HttpUtils();
		String url =GetHttp.getHttpBCL()+"RegisterAndroid";
		RequestParams params = new RequestParams();
		try {
			params.addBodyParameter("usertelephone", URLEncoder.encode(usertelephone, "utf-8"));
			params.addBodyParameter("password", URLEncoder.encode(password, "utf-8"));
			params.addBodyParameter("checkedNumber", URLEncoder.encode(checkedNumber, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

}
