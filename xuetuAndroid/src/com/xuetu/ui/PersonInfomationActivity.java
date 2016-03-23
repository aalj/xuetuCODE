package com.xuetu.ui;

import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.R.menu;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
	TextView sex;
	TextView study_gexingqianming;
	TextView text_age;
	TextView text_grade;
	TextView text_school;
	private String SexData[] = { "男", "女" };

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
		study_gexingqianming = (TextView) findViewById(R.id.study_gexingqianming);
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
		text_age = (TextView) findViewById(R.id.text_age);
		text_grade = (TextView) findViewById(R.id.text_grade);
		text_school = (TextView) findViewById(R.id.text_school);
		sex = (TextView) findViewById(R.id.sex);
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutClickListener(this);
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
			Intent intent = new Intent();
			intent.setClass(this, EditNameActivity.class);
			intent.putExtra("key2", 2);
			startActivityForResult(intent, 2);
			break;
		case R.id.gexingqianming:
			Intent intent3 = new Intent();
			intent3.setClass(this, EditNameActivity.class);
			intent3.putExtra("key2", 6);
			startActivityForResult(intent3, 6);
			break;
		case R.id.xingbie:
			showChangeSexDialog();
			break;
		case R.id.nianling:
			Intent intentage = new Intent();
			intentage.setClass(this, ChangeAgeActivity.class);
			intentage.putExtra("key2", 3);
			startActivityForResult(intentage, 3);
			break;
		case R.id.nianji_grade:
			Intent intentgrade = new Intent();
			intentgrade.setClass(this, ChangeAgeActivity.class);
			intentgrade.putExtra("key2", 4);
			startActivityForResult(intentgrade, 4);
			break;

		case R.id.xuexiao:
//			Intent intentschool = new Intent();
//			intentschool.setClass(this, ChangeAgeActivity.class);
//			intentschool.putExtra("key2", 5);
//			startActivityForResult(intentschool, 5);
			break;

		

		default:
			break;
		}

	}
	
	
	
	
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 2 && requestCode == 2) {
			String backResult = data.getStringExtra("ed_name");
			if (backResult != null)
				text_nicheng.setText(backResult);
		}
		if (resultCode == 3 && requestCode == 3) {
			String ageResult = data.getStringExtra("ed_name");
			if (ageResult != null)
				text_age.setText(ageResult);
		}
		if (resultCode == 4 && requestCode == 4) {
			String gradeResult = data.getStringExtra("ed_name");
			if (gradeResult != null)
				text_grade.setText(gradeResult);
		}
		if (resultCode == 5 && requestCode == 5) {
			String schoolResult = data.getStringExtra("ed_name");
			if (schoolResult != null)

				text_school.setText(schoolResult);
		}
		if (resultCode == 6 && requestCode == 6) {
			String schoolResult = data.getStringExtra("ed_name");
			if (schoolResult != null)
				
				study_gexingqianming.setText(schoolResult);
		}

	}

	private void showChangeSexDialog() {

		Dialog dialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT).setTitle("请选择您的性别")
				.setSingleChoiceItems(SexData, 0, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dInterface, int whitch) {
						sex.setText(SexData[whitch]);
						Toast.makeText(getApplication(), "您选择了：" + SexData[whitch], 2).show();
					}
				}).setPositiveButton("确定", null).create();
		dialog.show();
	}
}
