package com.xuetu;

import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class EditNameActivity extends Activity implements OnClickListener {
	EditText edit_name;
	TitleBar titlebar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_name);
		edit_name = (EditText) findViewById(R.id.edit_name);
		titlebar = (TitleBar) findViewById(R.id.titlebar);
		titlebar.setLeftLayoutClickListener(this);
		titlebar.setRightLayoutClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_layout:
			finish();
			break;

		case R.id.right_layout:
			Intent intent = new Intent();
			String ed_name = edit_name.getText().toString();
			intent.putExtra("ed_name", ed_name);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
		finish();
	}
	// TODO Auto-generated method stub

}
