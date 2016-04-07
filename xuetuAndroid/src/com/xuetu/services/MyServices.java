package com.xuetu.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xuetu.R;
import com.xuetu.entity.Countdown;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.ui.AddCountDownActivity;
import com.xuetu.utils.DataToTime;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

public class MyServices extends Service {
	SelfStudyPlan self;
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	MediaPlayer mp ;
	Vibrator vibrator = null;
	NotificationManager manager;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(MyServices.this, "有计划", 0).show();
		// Toast.makeText(MyServices.this, new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss").format(new Date(System.currentTimeMillis())), 0).show();
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		int intExtra = intent.getIntExtra("flag", -1);
		self = (SelfStudyPlan) intent.getSerializableExtra("self");
		Log.i("TAG", "self计划的时间哈哈哈----------》》》》》》》"+self.getPlanText());
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Log.i("TAG", "查看是否有对象数据"+self.getPlanText() + "\n" + "开始时间" + sim.format(self.getStartTime()) + "\n" + "结束时间："
				+ sim.format(self.getEndTime()));
		switch (intExtra) {
		case 1:// 标准模式
			notifa();
//			setLinSheng();
			setVibrate();
			cancalVibrate();
			break;
		case 2:// 彪悍模式

			break;
		case 3:// 温柔模式
			notifa();
			break;

		default:
			break;
		}

		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 设置铃声
	 */
	public void setLinSheng() {

		try {
			mp = MediaPlayer.create(this, RingtoneManager.getActualDefaultRingtoneUri(this,  
	                RingtoneManager.TYPE_RINGTONE));  
			
//			mp.setDataSource(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
			mp.prepare();
			mp.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置弹出dialog关闭闹钟
	 */
	public void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(self.getPlanText());
		Log.i("TAG", "弹窗内容"+self.getPlanText() + "\n" + "开始时间" + sim.format(self.getStartTime()) + "\n" + "结束时间："
				+ sim.format(self.getEndTime()));
		
		builder.setMessage(self.getPlanText() + "\n" + "开始时间" + sim.format(self.getStartTime()) + "\n" + "结束时间："
				+ sim.format(self.getEndTime()));
		builder.setCancelable(false);
		builder.setPositiveButton("取消闹铃", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mp.stop();
				dialog.dismiss();
			}
		});
		final AlertDialog dialog = builder.create();
		// 在dialog show方法之前添加如下代码，表示该dialog是一个系统的dialog**
		dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
		dialog.show();
	}

	public void setVibrate() {
		vibrator.vibrate(new long[] { 100, 400, 100, 400 }, 2);
	}

	public void cancalVibrate() {
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("取消震动");
		builder.setCancelable(false);
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				vibrator.cancel();
				dialog.dismiss();
			}
		});
		final AlertDialog dialog = builder.create();
		// 在dialog show方法之前添加如下代码，表示该dialog是一个系统的dialog**
		dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
		dialog.show();
	}
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
	public void notifa() {
		Intent intent = new Intent(this, com.xuetu.ui.FindTaskListActivity.class);
		PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent,
				0);
		// 通过Notification.Builder来创建通知，注意API Level
		// API11之后才支持
		Notification notify2 = new Notification.Builder(this).setSmallIcon(R.drawable.ic_launcher) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
																									// icon)
				.setTicker("您有一个计划到时了")// 设置在status
				// bar上显示的提示文字
				.setContentTitle(self.getPlanText())// 设置在下拉status
				// bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
				.setContentText(self.getPlanText() + "\n" + "开始时间" + sim.format(self.getStartTime()) + "\n" + "结束时间："
						+ sim.format(self.getEndTime()))// TextView中显示的详细内容
				.setContentIntent(pendingIntent2) // 关联PendingIntent
				.setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
				.getNotification(); // 需要注意build()是在API level
		// 16及之后增加的，在API11中可以使用getNotificatin()来代替
		notify2.flags |= Notification.FLAG_AUTO_CANCEL;
		manager.notify(1, notify2);
	}

	

}
