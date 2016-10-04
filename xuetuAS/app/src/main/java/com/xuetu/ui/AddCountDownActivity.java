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
		Log.i("TAG", time_a+"添加的页面的时间");
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

//				if (study_parrt_info.isChecked()) {
//					notifa();
//				}

				intent.putExtra("countdown", countdown);
				intent.putExtra("numtemp", countdown.getTemp_time());
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

	

}
