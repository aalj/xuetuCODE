package com.xuetu.ui;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xuetu.R;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class ChangeAgeActivity extends Activity implements OnClickListener {
	RequestQueue queue;
	TitleBar titlebar;
	EditText edit_age;
	int num;
	ProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_age);
		num = Integer.parseInt(getIntent().getStringExtra("key2"));
		iniView();
		queue = Volley.newRequestQueue(this);

	}
int stuid = 0;
	private void iniView() {
		stuid=((XueTuApplication)getApplication()).getStudent().getStuId();
		titlebar = (TitleBar) findViewById(R.id.titlebar);
		edit_age = (EditText) findViewById(R.id.edit_age);
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutClickListener(this);
		switch (num) {
		case 2:
			titlebar.setTitle("修改昵称");
			break;
		case 3:
			titlebar.setTitle("修改年龄");
			break;
		case 4:
			titlebar.setTitle("修改年级");
			break;
		case 5:
			titlebar.setTitle("修改学校");
			break;
		case 6:
			titlebar.setTitle("修改个性签名");
			break;

		default:
			break;
		}

	}
	
	private void showDengdai() {
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(ChangeAgeActivity.this, "", "正在加载...");
			progressDialog.show();
		}
	}

	public void saveValue(final int temp,final  String stuid,final  String value) {
		String params = GetHttp.getHttpLJ()+"";
		StringRequest request = new StringRequest(Request.Method.POST, params, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				if("ok".equals(response)){
					if (progressDialog != null)
						progressDialog.dismiss();
					setResult(temp, intent);
					finish();
				}
				
				
				
				
				
				

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				

			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map <String,String >map = new HashMap<String, String>();
				map.put("temp", temp+"");
				map.put("usepwd", stuid);
				map.put("value", value);
				return map;
			}
		};

		queue.add(request);

	}

	Intent intent;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_image:
			finish();
			break;
		case R.id.right_image:
			intent = new Intent();
			String ed_age = edit_age.getText().toString();
			switch (num) {
			case 2:
				intent.putExtra("ed_name", ed_age);
				saveValue(2,   stuid+"",ed_age);
//				setResult(2, intent);
				break;
			case 3:
				intent.putExtra("ed_name", ed_age);
				saveValue(3,   stuid+"",ed_age);
//				setResult(3, intent);
				break;
			case 4:
				intent.putExtra("ed_name", ed_age);
				saveValue(4,   stuid+"",ed_age);
//				setResult(4, intent);
				break;
			case 5:
				intent.putExtra("ed_name", ed_age);
				saveValue(5,   stuid+"",ed_age);
//				setResult(5, intent);
				break;
			case 6:
				intent.putExtra("ed_name", ed_age);
				saveValue(6,   stuid+"",ed_age);
				break;

			default:
				break;
			}

			break;

		default:
			break;
		}
		showDengdai();

	}

}
