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
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.smssdk.EventHandler;
//import cn.smssdk.EventHandler;
//import cn.smssdk.SMSSDK;
import cn.smssdk.SMSSDK;

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
 * 注册界面 缺少验证码 验证码点击事件 按钮验证码倒计时
 * 
 * @author BCL
 *
 */

public class RegisterActivity extends Activity implements OnClickListener {
	EditText et_usertelephone;
	EditText et_checkedNumber;
	EditText et_password;
	TextView huoqvyanzhengma;
	Button btn_register;
	TitleBar titlebar;
	Boolean fromJson_boolean;
	private String phonenum;
	boolean flag = true;
	EventHandler eh = new EventHandler() {

		@Override
		// 主体方法，供回调试用
		public void afterEvent(int event, int result, Object data) {

			Log.i("Msm", "event:" + event + "    result:" + result + "    data:" + data.toString());
			switch (event) {
			// 验证的时候使用
			case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
				if (result == SMSSDK.RESULT_COMPLETE) {
					toast("验证成功");

				} else {
					toast("验证失败");
				}
				break;
			// 得到验证码时候使用
			case SMSSDK.EVENT_GET_VERIFICATION_CODE:
				if (result == SMSSDK.RESULT_COMPLETE) {
					toast("获取验证码成功");
					// 默认的智能验证是开启的,我已经在后台关闭
				} else {
					toast("获取验证码失败");
				}
				break;
			}
		}
	};

	private void toast(final String str) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(RegisterActivity.this, str, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		titlebar = (TitleBar) findViewById(R.id.backtologin);
		titlebar.setLeftLayoutClickListener(this);
		et_usertelephone = (EditText) findViewById(R.id.et_usertelephone);
		et_checkedNumber = (EditText) findViewById(R.id.et_checkedNumber);
		et_password = (EditText) findViewById(R.id.et_password);
		huoqvyanzhengma = (TextView) findViewById(R.id.huoqvyanzhengma);
		btn_register = (Button) findViewById(R.id.btn_register);
		huoqvyanzhengma.setOnClickListener(this);
		btn_register.setOnClickListener(this);

		SMSSDK.initSDK(this, "1065bd80d77ac", "c3a02bb07fc7fa590534ffab2aee1811");
		// 发送短信，也会回调前面的方法
		SMSSDK.registerEventHandler(eh);
		et_checkedNumber.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				autocheck();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.huoqvyanzhengma:
			if (!TextUtils.isEmpty(et_usertelephone.getText().toString().trim())) {
				if (et_usertelephone.getText().toString().trim().length() == 11) {
					phonenum = et_usertelephone.getText().toString().trim();
					SMSSDK.getVerificationCode("86", phonenum);
					et_checkedNumber.requestFocus();
//					huoqvyanzhengma.setVisibility(View.GONE);
				} else {
					Toast.makeText(this, "请输入完整电话号码", Toast.LENGTH_LONG).show();
					et_usertelephone.requestFocus();
				}
			} else {
				Toast.makeText(this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
				et_usertelephone.requestFocus();

			}

			break;
		case R.id.btn_register:
			register();
			break;
		// Toast.makeText(getApplicationContext(), "点击了", 1).show();
		// finish();
		case R.id.text_back:
			finish();
			break;
		case R.id.left_layout:
			finish();
			break;

		default:
			break;
		}

	}

	public void autocheck() {
		if (!TextUtils.isEmpty(et_checkedNumber.getText().toString().trim())) {
			if (et_checkedNumber.getText().toString().trim().length() == 4) {
				String SMS = et_checkedNumber.getText().toString().trim();
				SMSSDK.submitVerificationCode("86", phonenum, SMS);

			} else {
				Toast.makeText(this, "请输入完整验证码", Toast.LENGTH_LONG).show();
				et_checkedNumber.requestFocus();
			}
		} else {
			Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show();
			et_checkedNumber.requestFocus();
		}
	}
	// public void onclick(View v) {
	// switch (v.getId()) {
	// case R.id.btn_register:
	// Toast.makeText(this, "注册", 1).show();
	// String SMS = et_checkedNumber.getText().toString().trim();
	// SMSSDK.submitVerificationCode("86", phonenum, SMS);
	// register();
	//
	// break;
	// case R.id.huoqvyanzhengma:
	// phonenum = et_usertelephone.getText().toString().trim();
	// SMSSDK.getVerificationCode("86", phonenum);
	// Toast.makeText(this, "获取验证码", 1).show();
	// break;
	// /**
	// * 返回login
	// */
	// case R.id.text_back:
	// finish();
	// break;
	//
	// default:
	// break;
	// }
	//
	// }

	// private void Jump() {
	// Intent intent = new Intent();
	// intent.setClass(this, LoginActivity.class);
	// startActivity(intent);
	// finish();
	// }

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
				if (fromJson_boolean == true) {
					toast("注册成功返回登录");
					finish();
				} else {
					toast("该手机号已经注册，重新输入");
				}
			}
		});

	}

	// @Override
	// public void onClick(View v) {
	// phonenum = et_usertelephone.getText().toString().trim();
	// SMSSDK.getVerificationCode("86", phonenum);
	// Toast.makeText(this, "获取验证码", 1).show();
	// }
	@Override
	protected void onDestroy() {
		super.onDestroy();
		SMSSDK.unregisterAllEventHandler();
	}

}
