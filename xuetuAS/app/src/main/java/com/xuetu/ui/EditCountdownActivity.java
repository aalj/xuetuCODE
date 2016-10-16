package com.xuetu.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Countdown;
import com.xuetu.widget.TitleBar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EditCountdownActivity extends Activity implements OnClickListener {
	@ViewInject(R.id.titleEdit)
	EditText titleEdit;
	@ViewInject(R.id.timeEdit)
	TextView timeEdit;
	@ViewInject(R.id.study_parrt_info)
	Switch study_parrt_info;
	@ViewInject(R.id.titleBar1)
	TitleBar title_my;
	Countdown countdown;
	// NotificationManager manager;

	int temp_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_countdown);
		ViewUtils.inject(this);
		// manager = (NotificationManager)
		// getSystemService(Context.NOTIFICATION_SERVICE);

		countdown = (Countdown) getIntent().getSerializableExtra("countdown");
		temp_time = countdown.getTemp_time();
		countdown.setTemp_time(temp_time);
		title_my.setLeftLayoutClickListener(this);
		title_my.setRightLayoutClickListener(this);
		titleEdit.setText(countdown.getCodoText());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		study_parrt_info = (Switch) findViewById(R.id.study_parrt_info);
		timeEdit.setText(dateFormat.format(countdown.getCodoTime()));
		// manager.cancel(countdown.getTemp_time());
		if (countdown.getCodo_index() == 0) {

			study_parrt_info.setChecked(false);
			;
		} else if (countdown.getCodo_index() == 1) {
			study_parrt_info.setChecked(true);
		}
		// study_parrt_info.setOnCheckedChangeListener(this);

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
		case R.id.button1:
			DBFindManager db = new DBFindManager(EditCountdownActivity.this);
			db.deleteCountdown(countdown.getCodoID());
			Intent intent = new Intent();
			intent.setClass(EditCountdownActivity.this, DaoJiShiActivity.class);
			intent.putExtra("countdown", countdown);
			Log.i("TAG", "countdown.getCodoID()---------->>>>" + countdown.getCodoID());
			setResult(10, intent);
			finish();
			break;

		default:
			break;
		}
	}

	protected Dialog onCreateDialog() {
		Dialog dialog = null;
		final Calendar c = Calendar.getInstance();
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

	SimpleDateFormat sim = new SimpleDateFormat("dd");
	SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sim3 = new SimpleDateFormat("MM");

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
				// if (Integer.parseInt(sim.format(countdown.getCodoTime())) >=
				// Integer
				// .parseInt(sim.format(new Date(System.currentTimeMillis()))))
				// {
				Log.i("TAG",
						"System.currentTimeMillis()/24*60*60*1000" + System.currentTimeMillis() / 24 * 60 * 60 * 1000);
				if (countdown.getCodoTime().getTime() >= System.currentTimeMillis() || sim2
						.format(countdown.getCodoTime()).equals(sim2.format(new Date(System.currentTimeMillis())))) {

					countdown.setCodoText(string);
					Intent intent = new Intent();

					intent.putExtra("countdown", countdown);
					setResult(1012, intent);
					finish();
				} else {

					Toast.makeText(getApplicationContext(), "计时时间应该大于现在的时间", 0).show();
				}

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
