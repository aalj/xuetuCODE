package com.xuetu.ui;

import com.xuetu.R;
import com.xuetu.entity.Student;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends Baseactivity implements OnClickListener {
	Button button = null;
	SharedPreferences sp = null;
	TextView txt_msgtip;
	TitleBar titlebar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		titlebar = (TitleBar) findViewById(R.id.titlebar_setting);
		button = (Button) findViewById(R.id.zuxiao);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		txt_msgtip = (TextView) findViewById(R.id.txt_msgtip);
		titlebar.setLeftLayoutClickListener(this);
		// txt_msgtip.setOnClickListener(this);
	}

	public void onclick(View v) {

		switch (v.getId()) {
		case R.id.zuxiao:
			Editor edit = sp.edit();
			edit.putString("uasename", null);
			edit.putString("pwd", null);
			((XueTuApplication) getApplication()).setStudent(new Student());
			edit.commit();

			Intent intent = new Intent();
			intent.setClass(SettingActivity.this, LoginActivity.class);
			startActivity(intent);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			finish();

			break;
		case R.id.txt_msgtip:
			String versionNum = getVersionNum();
			// Toast.makeText(this, "当前版本号为" + versionNum, 0).show();

			new AlertDialog.Builder(this).setTitle("版本").setMessage("当前版本号是" + versionNum).setPositiveButton("确定", null)
					.show();
			break;

		default:
			break;
		}

	}

	public String getVersionNum() {
		try {
			PackageManager pm = this.getPackageManager();

			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
			String version = info.versionName;
			return version;

		} catch (NameNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
			return false;
		}
		return false;
	}

}
