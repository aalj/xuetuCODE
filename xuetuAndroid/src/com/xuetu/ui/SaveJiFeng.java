package com.xuetu.ui;

import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ListView;

public class SaveJiFeng extends Activity {
	ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_you_hui_juan);
		listview = (ListView) findViewById(R.id.listView);

	}

}
