package com.xuetu.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.entity.Countdown;
import com.xuetu.utils.DataToTime;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AddCountDownActivity extends Activity implements OnClickListener {
	@ViewInject(R.id.titleEdit)
	EditText titleEdit;
	@ViewInject(R.id.timeEdit)
	TextView timeEdit;
	@ViewInject(R.id.study_parrt_info)
	Switch study_parrt_info;
	@ViewInject(R.id.titleBar1)
	TitleBar title_my;

	Date time = new Date(System.currentTimeMillis());
	int time_a = (int) time.getTime();
	Countdown countdown = new Countdown();
	private static final int NOTIFICATION_FLAG = 1;
	NotificationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_count_down);
		ViewUtils.inject(this);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		//
		countdown.setCodoTime(time);
		// 设置notifacation的唯一性字段
		countdown.setTemp_time(time_a);

		title_my.setLeftLayoutClickListener(this);
		title_my.setRightLayoutClickListener(this);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		timeEdit.setText(format.format(new Date(System.currentTimeMillis())));
	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.title:
			titleEdit.requestFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(titleEdit, InputMethodManager.SHOW_IMPLICIT);
			break;
		case R.id.time:
			onCreateDialog().show();
			break;

		default:
			break;
		}
	}

	final Calendar c = Calendar.getInstance();

	protected Dialog onCreateDialog() {
		Dialog dialog = null;
		dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
				timeEdit.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
				c.set(year, month, dayOfMonth, 0, 0, 0);
				countdown.setCodoTime(c.getTime());
			}
		}, c.get(Calendar.YEAR), // 传入年份
				c.get(Calendar.MONTH), // 传入月份
				c.get(Calendar.DAY_OF_MONTH) // 传入天数
		);

		countdown.setCodoTime(c.getTime());

		return dialog;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_layout:
			String string = titleEdit.getText().toString();
			boolean check = study_parrt_info.isChecked();
			if (check) {
				countdown.setCodo_index(1);
			} else {
				countdown.setCodo_index(0);

			}
			if (!TextUtils.isEmpty(string)) {
				// if(countdown.getCodoTime().getTime()>System.currentTimeMillis()%(1000*60*60*24)*(1000*60*60*24)){
				countdown.setCodoText(string);
				Intent intent = new Intent();

				// TODO 需要判断当前是否需要在通知栏常驻提醒
				if (study_parrt_info.isChecked()) {
					notifa();
				}

				intent.putExtra("countdown", countdown);
				setResult(1011, intent);
				finish();
				// }else{
				//
				// Toast.makeText(getApplicationContext(), "倒计时的时间应该大于今天",
				// 0).show();
				// }

			} else {
				Toast.makeText(getApplicationContext(), "需要输入一个提醒的标题", 0).show();
			}

			break;
		case R.id.left_layout:
			finish();
			break;
		default:
			break;
		}

	}

	public void notifa() {
		PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, new Intent(this, AddCountDownActivity.class),
				0);
		// 通过Notification.Builder来创建通知，注意API Level
		// API11之后才支持
		Notification notify2 = new Notification.Builder(this).setSmallIcon(R.drawable.ic_launcher) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
																									// icon)
				.setTicker("您有一个倒计时" + DataToTime.getDay(c.getTime().getTime()) + "天")// 设置在status
				// bar上显示的提示文字
				.setContentTitle(titleEdit.getText().toString())// 设置在下拉status
				// bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
				.setContentText(timeEdit.getText().toString())// TextView中显示的详细内容
				.setContentIntent(pendingIntent2) // 关联PendingIntent
				.setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
				.getNotification(); // 需要注意build()是在API level
		// 16及之后增加的，在API11中可以使用getNotificatin()来代替
		notify2.flags |= Notification.FLAG_AUTO_CANCEL;
		Log.i("TAG", countdown.getTemp_time()+"------------>>>>>");
		manager.notify(countdown.getTemp_time(), notify2);
	}


}
