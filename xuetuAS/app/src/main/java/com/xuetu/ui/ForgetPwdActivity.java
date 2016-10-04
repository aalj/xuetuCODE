package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ForgetPwdActivity extends Baseactivity implements OnClickListener {
	/** 账户 */
	EditText et_usertel_forgetpwd;
	/** 验证码 */
	EditText et_checkedNumber;
	TextView huoqvyanzhengma_forgetpwd;
	/** 第一次密码 */
	EditText et_password_new;
	/** 第二次密码 */
	EditText et_password_newagain;
	TitleBar titleBar;
	String phonenum;
	HttpUtils httpUtils = new HttpUtils();

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
				Toast.makeText(ForgetPwdActivity.this, str, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhaohuimima);
		titleBar = (TitleBar) findViewById(R.id.titlebar_forgetpwd);
		et_usertel_forgetpwd = (EditText) findViewById(R.id.et_usertel_forgetpwd);
		et_checkedNumber = (EditText) findViewById(R.id.et_checknum_forget_pwd);
		
		et_password_new = (EditText) findViewById(R.id.et_password_new);
		et_password_newagain = (EditText) findViewById(R.id.et_password_newagain);
		
		huoqvyanzhengma_forgetpwd = (TextView) findViewById(R.id.huoqvyanzhengma_forgetpwd);
		huoqvyanzhengma_forgetpwd.setOnClickListener(this);
		titleBar.setLeftLayoutClickListener(this);

		SMSSDK.initSDK(this, "1065bd80d77ac", "c3a02bb07fc7fa590534ffab2aee1811");
		// 发送短信，也会回调前面的方法
		SMSSDK.registerEventHandler(eh);
		et_checkedNumber.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				autocheck();
			}
		});
	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.btn_finish_forgetpwd:
			String et_password_new_str = et_password_new.getText().toString().trim();
			String et_password_newagain_str = et_password_newagain.getText().toString().trim();
			if (et_password_new_str != null && // 第一个输入的密码不能为空
					et_password_new_str.equals(et_password_newagain_str)) {
				setUserPwd(phonenum, et_password_newagain_str);

			}
			else{
				Toast.makeText(getApplicationContext(), "两次密码不一样哟！", 0).show();
			}

			break;
		case R.id.text_back:
			Jump();
			break;

		default:
			break;
		}
	}

	/** 用于传值到服务器修改密码 */
	private void setUserPwd(String phonenum2, String pwd) {

		try {
			String url = GetHttp.getHttpBCL() + "SetUserPwd";
			RequestParams pra = new RequestParams();

			pra.addBodyParameter("phone", URLEncoder.encode(phonenum2, "utf-8"));
			pra.addBodyParameter("pwd", URLEncoder.encode(pwd, "utf-8"));

			httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Toast.makeText(getApplicationContext(), "检查你的网络，密码修改错误", 0).show();

				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					if ("ok".equals(arg0.result)) {
						finish();
						Toast.makeText(getApplicationContext(), "修改成功", 0).show();
					} else {
						Toast.makeText(getApplicationContext(), "发生未知错误", 0).show();

					}

				}
			});
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	private void Jump() {
		Intent intent = new Intent();
		intent.setClass(this, LoginActivity.class);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_layout:
			finish();
			break;
		case R.id.huoqvyanzhengma_forgetpwd:
			phonenum = et_usertel_forgetpwd.getText().toString().trim();
			// 验证帐号是否存在
			yanzhengzhanghao();

		default:
			break;
		}

	}

	/** 验证站好是存在 */
	private void yanzhengzhanghao() {
		try {
			httpUtils = new HttpUtils();
			String url = GetHttp.getHttpBCL() + "YanZhengZhangHao";
			RequestParams pra = new RequestParams();

			pra.addBodyParameter("phone", URLEncoder.encode(phonenum, "utf-8"));

			httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Toast.makeText(getApplicationContext(), "请检查网络", Toast.LENGTH_LONG).show();

				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					if ("ok".equals(arg0.result)) {
						if (!TextUtils.isEmpty(et_usertel_forgetpwd.getText().toString().trim())) {
							if (et_usertel_forgetpwd.getText().toString().trim().length() == 11) {

								SMSSDK.getVerificationCode("86", phonenum);
								et_checkedNumber.requestFocus();
								// huoqvyanzhengma.setVisibility(View.GONE);
							} else {
								Toast.makeText(getApplicationContext(), "请输入完整电话号码", Toast.LENGTH_LONG).show();
								et_usertel_forgetpwd.requestFocus();
							}
						} else {
							Toast.makeText(getApplicationContext(), "请输入您的电话号码", Toast.LENGTH_LONG).show();
							et_usertel_forgetpwd.requestFocus();

						}
					} else {

						Toast.makeText(getApplicationContext(), "请检查网络", Toast.LENGTH_LONG).show();
					}

				}
			});
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
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

	@Override
	protected void onDestroy() {
		super.onDestroy();
		SMSSDK.unregisterAllEventHandler();
	}
}
