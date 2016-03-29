package com.xuetu.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyServices extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
		
		
	}

}
