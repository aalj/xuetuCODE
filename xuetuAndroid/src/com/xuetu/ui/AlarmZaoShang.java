package com.xuetu.ui;

import java.util.ArrayList;
import java.util.List;

import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Alarm;
import com.xuetu.utils.DataToTime;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class AlarmZaoShang extends Activity implements OnClickListener {
	DBFindManager dbManager = null; 
	List<Alarm> queryAlarm = new ArrayList<Alarm>();
	ListView list;
	TitleBar title;
	MyBasesadapter<Alarm> mybaseAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		title = (TitleBar) findViewById(R.id.title_my);
		title.setTitle("早起");
		dbManager= new DBFindManager(this);
		queryAlarm = dbManager.queryAlarm(0);
		list = (ListView) findViewById(R.id.listview);
		setAdapter();
		list.setAdapter(mybaseAdapter);
		title.setLeftLayoutClickListener(this);
		title.setRightLayoutClickListener(this);
		
	}
	
	
	public void setAdapter(){
		mybaseAdapter= new MyBasesadapter<Alarm>(this,queryAlarm,R.layout.alarm_item) {
			
			@Override
			public void convert(ViewHodle viewHolder, Alarm item) {
				viewHolder.setText(R.id.tv_teme, DataToTime.dataToh(item.getStartTime()));
				viewHolder.setText(R.id.tv_text, item.getWeek());
				if(0==item.getTemp_index()){
					
					viewHolder.setClick(R.id.myswitch, true);
				}else{
					viewHolder.setClick(R.id.myswitch, false);
					
				}
			}
		};
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_layout:
			Intent intent = new Intent();
			//TODO
			Toast.makeText(getApplicationContext(), "添加", 0).show();

			break;
		case R.id.left_layout:
			//TODO
			finish();
			Toast.makeText(getApplicationContext(), "返回", 0).show();

			break;

		default:
			break;
		}

	}
	
	

}
