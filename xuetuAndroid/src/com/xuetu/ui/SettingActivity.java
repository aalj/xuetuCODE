package com.xuetu.ui;

import com.xuetu.R;
import com.xuetu.entity.Student;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends Baseactivity {
Button button = null;
SharedPreferences sp = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		button = (Button) findViewById(R.id.zuxiao);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
	}

	
	public void onclick(View v){
		
		switch (v.getId()) {
		case R.id.zuxiao:
			Editor edit = sp.edit();
			edit.putString("uasename", null);
			edit.putString("pwd", null);
			((XueTuApplication)getApplication()).setStudent(new Student());
			edit.commit();
			Intent intent = new Intent();
			intent.setClass(SettingActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
			
			break;

		default:
			break;
		}
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
