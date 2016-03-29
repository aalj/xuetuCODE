package com.xuetu.Receive;

import com.xuetu.services.MyServices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootBroadcastReceiver extends BroadcastReceiver {
	static final String action_boot = "android.intent.action.BOOT_COMPLETED";

	@Override 
	public void onReceive(Context arg0, Intent arg1) {  // TODO Auto-generated method stub  
		if (arg1.getAction().equals(action_boot)){   
			Toast.makeText(arg0.getApplicationContext(), "开机了", 0).show();
			Intent ootStartIntent=new Intent(arg0,MyServices.class);        
//			Intent ootStartIntent=new Intent();        
			ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);         
			arg0.startService(ootStartIntent);  
			} 
		}
	}
