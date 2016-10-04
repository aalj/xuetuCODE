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
import com.xuetu.entity.Student;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class EditSignerActivity extends Baseactivity implements OnClickListener {
	EditText edit_qianming;
	TitleBar titlebar;
	int stuId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_signer);
		edit_qianming = (EditText) findViewById(R.id.edit_qianming);
		titlebar = (TitleBar) findViewById(R.id.titlebar);
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutClickListener(this);
		stuId = ((XueTuApplication) getApplication()).getStudent().getStuId();
		loadData();
	}

	public void loadData() {
		Intent intent = getIntent();
		String qianming = intent.getStringExtra("gexingqianming");
		edit_qianming.setText(qianming);
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
			String ed_name = edit_qianming.getText().toString();
			Student student = ((XueTuApplication) getApplication()).getStudent();
			student.setStuSigner(ed_name);
			intent.putExtra("edit_qianming", ed_name);
			setResult(6, intent);
			finish();
			break;
		default:
			break;
		}

	}

	public void update() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "ChangeQianMingServlet";
		RequestParams params = new RequestParams();
		try {
			params.addBodyParameter("id", URLEncoder.encode(stuId + "", "utf-8"));
			params.addBodyParameter("changeqianming", URLEncoder.encode(edit_qianming.getText().toString(), "utf-8"));
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

}
