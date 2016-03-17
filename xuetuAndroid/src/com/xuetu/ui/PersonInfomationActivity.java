package com.xuetu.ui;

import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.R.menu;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PersonInfomationActivity extends Activity implements OnClickListener {
	TitleBar titlebar;
	RelativeLayout view_user;
	RelativeLayout nicheng;
	RelativeLayout gexingqianming;
	RelativeLayout xingbie;
	RelativeLayout nianling;
	RelativeLayout nianji_grade;
	RelativeLayout xuexiao;
	TextView text_nicheng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_infomation);
		titlebar = (TitleBar) findViewById(R.id.backtoperson);
		titlebar.setLeftLayoutClickListener(this);
		view_user = (RelativeLayout) findViewById(R.id.view_user);
		view_user.setOnClickListener(this);
		nicheng = (RelativeLayout) findViewById(R.id.nicheng);
		nicheng.setOnClickListener(this);
		gexingqianming = (RelativeLayout) findViewById(R.id.gexingqianming);
		gexingqianming.setOnClickListener(this);
		xingbie = (RelativeLayout) findViewById(R.id.xingbie);
		xingbie.setOnClickListener(this);
		nianling = (RelativeLayout) findViewById(R.id.nianling);
		nianling.setOnClickListener(this);
		nianji_grade = (RelativeLayout) findViewById(R.id.nianji_grade);
		nianji_grade.setOnClickListener(this);
		xuexiao = (RelativeLayout) findViewById(R.id.xuexiao);
		xuexiao.setOnClickListener(this);
		text_nicheng = (TextView) findViewById(R.id.text_nicheng);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backtoperson:
			finish();
			break;
		case R.id.view_user:

			break;

		case R.id.nicheng:

			Intent intent = getIntent();
			String changedName = intent.getStringExtra("et_name");
			text_nicheng.setText(changedName);

			break;
		case R.id.gexingqianming:

			break;
		case R.id.xingbie:

			break;
		case R.id.nianling:

			break;
		case R.id.nianji_grade:

			break;
		case R.id.xuexiao:

			break;
		default:
			break;
		}

	}
}
