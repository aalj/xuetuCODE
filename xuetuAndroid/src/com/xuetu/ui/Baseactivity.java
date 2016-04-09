package com.xuetu.ui;

import com.xuetu.utils.ActivityColector;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Baseactivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ActivityColector.addActivity(this);
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityColector.removeActivity(this);
	}

}
