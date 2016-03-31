package com.xuetu.ui;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
	Vibrator vibrator;
  @Override
  public void onReceive(final Context context, Intent intent) {
	vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
	    long [] pattern = {100,400,100,400}; // 停止 开启 停止 开启
	    vibrator.vibrate(pattern,2); //重复两次上面的pattern 如果只想震动一次，index设为-1
		if ("alarm1".equals(intent.getAction())) {
			
			
			new AlertDialog.Builder(context).setIcon(android.R.drawable.btn_star)  
			.setTitle("闹钟").setMessage("你该起床了")  .setCancelable(false)
			.setPositiveButton("确定",new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog,int which) {  
					
					vibrator.cancel();
				}  
			})  
			
			.create()	.show();// show很关键  
			
			
			
			
			
			
			Log.i("TAG", "闹钟序号--------》》》》"+"12313213");
			Toast.makeText(context.getApplicationContext(), "niaozhong youle ", 0).show();
		}
		if ("alarm0".equals(intent.getAction())) {
			
			
			new AlertDialog.Builder(context).setIcon(android.R.drawable.btn_star)  
			.setTitle("闹钟").setMessage("你该睡觉了")  .setCancelable(false)
			.setPositiveButton("确定",new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog,int which) {  
					vibrator.cancel();
					
				}  
			})  
			
.create()
			.show();// show很关键  
			
			
			
			
			
			
			Log.i("TAG", "闹钟序号--------》》》》"+"12313213");
			Toast.makeText(context.getApplicationContext(), "niaozhong youle ", 0).show();
		}

  }

}