package com.xuetu.ui;

import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;

public class LearingRecordActivity extends Activity {

	CalendarView calendarView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learing_record);
		
		calendarView=(CalendarView) findViewById(R.id.calendar_view);
//		calendarView.ca
	}
	
	
	
	
	
	
}
