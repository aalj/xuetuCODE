package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SocializeClientListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.xuetu.R;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Baseactivity implements OnClickListener {
	private UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.login");

	TitleBar titlebar;
	EditText et_usertel;
	EditText et_password;
	SharedPreferences sp = null;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("TAG", "LoginActivity");
		int stuId = ((XueTuApplication) getApplication()).getStudent().getStuId();
		if (stuId > 0) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		setContentView(R.layout.activity_login);

		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		titlebar = (TitleBar) findViewById(R.id.title_back);
		titlebar.setLeftLayoutClickListener(this);
		et_usertel = (EditText) findViewById(R.id.et_tel);
		et_password = (EditText) findViewById(R.id.et_password);
		button = (Button) findViewById(R.id.button_QQ);
		button.setOnClickListener(this);

		configPlatforms();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_layout:
			Toast.makeText(getApplicationContext(), "点击了", 1).show();
			finish();
			break;
		case R.id.button_QQ:
			login(SHARE_MEDIA.QQ);

			break;

		default:
			break;
		}

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
			// Intent intent = new Intent();
			// intent.setClass(getApplicationContext(), PerCerter.class);
			// Bundle bundle = new Bundle();
			// bundle.putSerializable("KEY", student);
			// startActivity(intent);

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

		getLogin(telephone, password);
	}

	public void getLogin(final String telephone, final String password) {
		if (!TextUtils.isEmpty(telephone) && !TextUtils.isEmpty(password)) {
			// 使用框架utils发送数据到服务器，
			HttpUtils httpUtils = new HttpUtils();
			// 10.201.1.5
			String url = GetHttp.getHttpBCL() + "LoginAndroid";

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
					// 从服务器得值 是否是正确
					String result = arg0.result;
					System.out.println(result);
					if (result == null || "null".equals(result)) {
						Toast.makeText(getApplicationContext(), "帐号或密码错误", 1).show();
					} else {
						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
						student = gson.fromJson(result, Student.class);
						Editor edit = sp.edit();
						edit.putString("uasename", telephone);
						edit.putString("pwd", password);
						edit.commit();
						((XueTuApplication) getApplication()).setStudent(student);
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(), MainActivity.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable("KEY", student);
						startActivity(intent);
						finish();
					}
				}
			});

		} else {
			Toast.makeText(getApplicationContext(), "请填写帐号或密码", 1).show();
		}
	}

	private void configPlatforms() {
		Log.i("TAG", " configPlatforms()进入");
		// 添加新浪sso授权
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		// 添加腾讯微博SSO授权
		mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
		addQQQZonePlatform();
	}

	private void addQQQZonePlatform() {
		String appId = "100424468";
		String appKey = "c7394704798a158208a74ab60104f0ba";
		// 添加QQ支持, 并且设置QQ分享内容的target url
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(LoginActivity.this, appId, appKey);
		qqSsoHandler.setTargetUrl("http://www.umeng.com");
		qqSsoHandler.addToSocialSDK();

		// 添加QZone平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(LoginActivity.this, appId, appKey);
		qZoneSsoHandler.addToSocialSDK();
	}

	/**
	 * 授权。如果授权成功，则获取用户信息
	 * 
	 * @param platform
	 */
	private void login(final SHARE_MEDIA platform) {
		mController.doOauthVerify(LoginActivity.this, platform, new UMAuthListener() {

			@Override
			public void onStart(SHARE_MEDIA platform) {
				Toast.makeText(LoginActivity.this, "授权开始", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onError(SocializeException e, SHARE_MEDIA platform) {
				Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete(Bundle value, SHARE_MEDIA platform) {
				// 获取uid
				String uid = value.getString("uid");
				if (!TextUtils.isEmpty(uid)) {
					// uid不为空，获取用户信息
					getUserInfo(platform);
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), MainActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("KEY", student);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(LoginActivity.this, "授权失败...", Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onCancel(SHARE_MEDIA platform) {
				Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 获取用户信息
	 * 
	 * @param platform
	 */
	private void getUserInfo(SHARE_MEDIA platform) {
		mController.getPlatformInfo(LoginActivity.this, platform, new UMDataListener() {

			@Override
			public void onStart() {
				Log.i("TAG", "************开始得到用户信息了喂*******************");

			}

			@Override
			public void onComplete(int status, Map<String, Object> info) {

				addToWeb(info);
				
//				if (status == 200 && info != null) {
//					StringBuilder sb = new StringBuilder();
//					Set<String> keys = info.keySet();
//					for (String key : keys) {
//						sb.append(key + "=" + info.get(key).toString() + "\r\n");
//					}
//					Log.i("TestData", sb.toString());
//				} else {
//					Log.d("TestData", "发生错误：" + status);
//				}
//				Log.i("TAG", "************应该有的*******************");
//
//				if (info != null) {
//					Toast.makeText(LoginActivity.this, info.toString(), Toast.LENGTH_SHORT).show();
//					Log.i("YYY", info.toString());
//				}
			}
		});
	}

	/**
	 * 注销本次登陆
	 * 
	 * @param platform
	 */
	private void logout(final SHARE_MEDIA platform) {
		mController.deleteOauth(LoginActivity.this, platform, new SocializeClientListener() {

			@Override
			public void onStart() {

			}

			@Override
			public void onComplete(int status, SocializeEntity entity) {
				String showText = "解除" + platform.toString() + "平台授权成功";
				if (status != StatusCode.ST_CODE_SUCCESSED) {
					showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
				}
				Toast.makeText(LoginActivity.this, showText, Toast.LENGTH_SHORT).show();
			}
		});
	}

	// 如果有使用任一平台的SSO授权, 则必须在对应的activity中实现onActivityResult方法, 并添加如下代码
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 根据requestCode获取对应的SsoHandler
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(resultCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}

	public void addToWeb(Map<String, Object> info) {
		Log.i("TAG", info + "执行。。。。。。。。");
		String uid = (String) info.get("uid");
		String gender = (String) info.get("gender");
		String image_url = (String) info.get("profile_image_url");
		String name = (String) info.get("screen_name");
		
		Log.i("TAG", uid+"  **********  " +gender+"  执行。。。。。。。。");
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "ReadSQLByUidServlet";
		RequestParams params = new RequestParams();
		try {
			params.addBodyParameter("telephone", URLEncoder.encode(uid, "utf-8"));
			params.addBodyParameter("sex", URLEncoder.encode(gender, "utf-8"));
			params.addBodyParameter("image", URLEncoder.encode(image_url, "utf-8"));
			params.addBodyParameter("name", URLEncoder.encode(name, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("TAG", "失败了？");

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				student = gson.fromJson(arg0.result, Student.class);
				((XueTuApplication) getApplication()).setStudent(student);
				
				Editor edit = sp.edit();
				edit.putBoolean("SANFANG", true);
				edit.putString("uasename", student.getStuPhone());
				edit.putString("pwd", student.getStuPwd());
				edit.commit();
				
			}
		});


	}

}
