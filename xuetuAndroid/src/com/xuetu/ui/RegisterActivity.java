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
import com.xuetu.utils.GetHttp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//┏┓　　　┏┓  
//┏┛┻━━━┛┻┓  
//┃　　　　　　　┃ 　
//┃　　　━　　　┃  
//┃　┳┛　┗┳　┃  
//┃　　　　　　　┃ 
//┃　　　┻　　　┃  
//┃　　　　　　　┃  
//┗━┓　　　┏━┛  
//┃　　　┃  神兽保佑　　　　　　　　  
//┃　　　┃  代码无BUG！  
//┃　　　┗━━━┓  
//┃　　　　　　　┣┓  
//┃　　　　　　　┏┛  
//┗┓┓┏━┳┓┏┛  
// ┃┫┫　┃┫┫  
// ┗┻┛　┗┻┛ 
/***
 * 注册界面    缺少验证码 验证码点击事件  按钮验证码倒计时
 * @author BCL
 *
 */

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

	Boolean fromJson_boolean;

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			if (fromJson_boolean == true) {
				Toast.makeText(this, "注册成功返回登录", 1).show();
				Jump();

			} else {
				Toast.makeText(this, "该手机号已经注册，重新输入", 1).show();
			}
			break;
		case R.id.text_back:
			Jump();
			break;

		default:
			break;
		}

	}

	private void Jump() {
		Intent intent = new Intent();
		intent.setClass(this, LoginActivity.class);
		startActivity(intent);
	}

	public void register() {
		String password = et_password.getText().toString();
		String checkedNumber = et_checkedNumber.getText().toString();
		String usertelephone = et_usertelephone.getText().toString();
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "RegisterAndroid";
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
				Gson gson = new Gson();
				Type type = new TypeToken<Boolean>() {
				}.getType();
				fromJson_boolean = gson.fromJson(arg0.result, type);
			}
		});

	}

}
