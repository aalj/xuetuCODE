package com.xuetu.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.loc.al;
import com.umeng.socialize.utils.Log;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Alarm;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class AlarmZaoShang extends Activity implements OnClickListener, OnItemLongClickListener, OnItemClickListener{
	DBFindManager dbManager = null;
	List<Alarm> queryAlarm = new ArrayList<Alarm>();
	ListView list;
	TitleBar title;
	MyBasesadapter<Alarm> mybaseAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm);
		title = (TitleBar) findViewById(R.id.title_my);
		title.setTitle("早睡");
		dbManager = new DBFindManager(this);
		queryAlarm = dbManager.queryAlarm(0);
		setAlarm(queryAlarm);
		list = (ListView) findViewById(R.id.listview);
		setAdapter();
		list.setAdapter(mybaseAdapter);
		title.setLeftLayoutClickListener(this);
		title.setRightLayoutClickListener(this);
		list.setOnItemLongClickListener(this);
		list.setOnItemClickListener(this);

	}

	public void setAlarm(List<Alarm> queryAlarm) {
		for (Alarm alarm : queryAlarm) {
			if (alarm.getTemp() == 0) {// 表示提醒
				sendAlarmEveryday1(AlarmZaoShang.this, alarm);
			}
		}

	}

	private void sendAlarmEveryday1(Context context, Alarm alarm) {
		Log.i("TAG", "启动闹钟----------------->>>>>>>>" + alarm.getAlarm_id() + "");
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		calendar.setTimeInMillis(System.currentTimeMillis());
		String[] tt = alarm.getStartTime().split(":");
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tt[0])-1);
		calendar.set(Calendar.MINUTE, Integer.parseInt(tt[1]));
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Intent intent = new Intent(AlarmZaoShang.this, AlarmBroadcastReceiver.class);
		intent.setAction("alarm0");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
				pendingIntent);
	}

	public void setAdapter() {
		mybaseAdapter = new MyBasesadapter<Alarm>(this, queryAlarm, R.layout.alarm_item) {

			@Override
			public void convert(ViewHodle viewHolder, Alarm item) {
				viewHolder.setText(R.id.tv_teme, item.getStartTime());
				viewHolder.setText(R.id.tv_text, item.getWeek());
				if (0 == item.getTemp_index()) {

					viewHolder.setClick(R.id.myswitch, true);
				} else {
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
			intent.setClass(AlarmZaoShang.this, AddAlarm.class);
			intent.putExtra("temp", "0");
			startActivityForResult(intent, 001);
			// TODO
			Toast.makeText(getApplicationContext(), "添加", 0).show();

			break;
		case R.id.left_layout:
			// TODO
			finish();
			Toast.makeText(getApplicationContext(), "返回", 0).show();

			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 001 && resultCode == 0001) {
			Alarm extra = (Alarm) data.getSerializableExtra("alarm");
			queryAlarm.add(extra);
			sendAlarmEveryday1(AlarmZaoShang.this, extra);
			mybaseAdapter.notifyDataSetChanged();
		}
		
		//修改返回的值
		if(resultCode==1113){
			Alarm extra = (Alarm) data.getSerializableExtra("alarm");
			queryAlarm.add(extra);
			sendAlarmEveryday1(AlarmZaoShang.this, extra);
		}
		
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}

	

	/**
	 * 长按删除闹钟
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		dbManager.deleteAlarm(queryAlarm.get(position).getAlarm_id(), queryAlarm.get(position));
		queryAlarm.remove(position);
		mybaseAdapter.notifyDataSetChanged();
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		intent.setClass(AlarmZaoShang.this, AddAlarm.class);
		intent.putExtra("temp", "0");
		intent.putExtra("alarm", queryAlarm.get(position) );
		queryAlarm.remove(queryAlarm.get(position));
		mybaseAdapter.notifyDataSetChanged();
		startActivityForResult(intent, 101);
		
	}


}
