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

public class ChangeGradeActivity extends Activity implements OnClickListener{
	TitleBar titlebar;
	EditText edit_grade;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_grade);
		titlebar = (TitleBar) findViewById(R.id.titlebar);
		edit_grade = (EditText) findViewById(R.id.edit_grade);
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
			String ed_grade = edit_grade.getText().toString();
			intent.putExtra("ed_grade", ed_grade);
			setResult(4, intent);
			finish();

			break;

		default:
			break;
		} 
		
	}

}
