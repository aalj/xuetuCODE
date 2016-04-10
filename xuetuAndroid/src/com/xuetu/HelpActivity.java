package com.xuetu;

import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class HelpActivity extends Activity implements OnClickListener {
	TitleBar titleBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		titleBar = (TitleBar) findViewById(R.id.main_title);
		titleBar.setLeftLayoutClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		finish();
		
	}

	
}
