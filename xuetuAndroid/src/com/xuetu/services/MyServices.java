package com.xuetu.services;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.umeng.socialize.utils.Log;
import com.xuetu.Receive.AlarmBroadcastReceiver;
import com.xuetu.Receive.BootBroadcastReceiver;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyServices extends Service {
	AlarmManager alarmManager;
	PendingIntent pintent;

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(MyServices.this, "nanozhong ", 1).show();
		return super.onStartCommand(intent, flags, startId);

	}


}
