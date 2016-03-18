package com.xuetu.ui;

import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class ChangeSchoolActivity extends Activity implements OnClickListener {

	TitleBar titlebar;
	EditText edit_school;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_school);
		titlebar = (TitleBar) findViewById(R.id.titlebar);
		edit_school = (EditText) findViewById(R.id.edit_school);
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_image:
			finish();
			break;
		case R.id.right_image:
			Intent intent = new Intent();
			String ed_school = edit_school.getText().toString();
			intent.putExtra("ed_school", ed_school);
			setResult(5, intent);
			finish();

			break;

		default:
			break;
		}
	}

}
