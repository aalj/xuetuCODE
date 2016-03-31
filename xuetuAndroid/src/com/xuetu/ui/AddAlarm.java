package com.xuetu.ui;

import com.gc.materialdesign.views.Switch;
import com.gc.materialdesign.views.Switch.OnCheckListener;
import com.xuetu.R;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Alarm;
import com.xuetu.view.TitleBar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class AddAlarm extends Baseactivity implements OnClickListener {
	TimePicker timePicker;
	TextView zaoshui_moshi_tv;
	// TextView zaoshui_shijianxianshi_tv;
	TextView zaoqi_tishiyu_shezhi_tv;
	// TextView zaoshui_moshitixing;
	TitleBar title;
	String stringExtra;
	Alarm alarm = new Alarm();
	Switch switchmy = null;
	boolean xianglingtixing = false;
	DBFindManager dbFindManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.zaoqi_shijian);
		dbFindManager = new DBFindManager(this);
		stringExtra = getIntent().getStringExtra("temp");
		alarm = (Alarm) getIntent().getSerializableExtra("alarm");
		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		title = (TitleBar) findViewById(R.id.title_my);
		switchmy = (Switch) findViewById(R.id.zaoqi_xiangling_kaiguan_tv);

		switchmy.setClickable(xianglingtixing);
		// zaoshui_moshitixing = (TextView)
		// findViewById(R.id.zaoshui_moshitixing);
		zaoqi_tishiyu_shezhi_tv = (TextView) findViewById(R.id.zaoqi_tishiyu_shezhi_tv);
		// zaoshui_shijianxianshi_tv = (TextView)
		// findViewById(R.id.zaoshui_shijianxianshi_tv);
		// zaoshui_moshi_tv = (TextView) findViewById(R.id.zaoshui_moshi_tv);
		timePicker.setIs24HourView(true);

		if (alarm != null) {
			if (alarm.getTemp_index() == 0) {
				switchmy.setChecked(true);
			} else {
				switchmy.setChecked(false);

			}

			zaoqi_tishiyu_shezhi_tv.setText(alarm.getTishiyu());

		} else {
			alarm = new Alarm();
			alarm.setWeek("全天");
			if ("0".equals(stringExtra)) {// 这是早睡
				alarm.setTemp(0);
				// zaoshui_moshi_tv.setText("早睡模式");
				title.setTitle("设置早睡时间");
			} else if ("1".equals(stringExtra)) {// 这是早起
				title.setTitle("设置早起时间");
				alarm.setTemp(1);
				// zaoshui_moshi_tv.setText("早起模式");

			}
		}
		timePicker.setOnTimeChangedListener(new TimeListener());
		title.setRightLayoutClickListener(this);
		title.setLeftLayoutClickListener(this);
		// alarm.setWeek(zaoshui_shijianxianshi_tv.getText().toString());
	}

	public void onclick(View v) {
		switch (v.getId()) {
		// case R.id.mode:// 模式
		// showChangeItemDialog();
		// Toast.makeText(getApplicationContext(), "1 ", 0).show();
		// break;
		// case R.id.congfu:// 重复
		// showWeekDialog();
		// Toast.makeText(getApplicationContext(), "2", 0).show();
		// break;

		case R.id.tishiyu:// 提示语
			editDialog();
			Toast.makeText(getApplicationContext(), "4 ", 0).show();
			break;
		case R.id.xiangling:// 响铃
			Toast.makeText(getApplicationContext(), "5 ", 0).show();
			break;
		case R.id.lingsheng:// 铃声
			Toast.makeText(getApplicationContext(), "6 ", 0).show();
			break;

		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_layout:// 取消
			finish();
			break;
		case R.id.right_layout:// 确定
			Intent intent = new Intent();
			if (switchmy.isCheck()) {
				alarm.setTemp_index(0);
			} else {
				alarm.setTemp_index(1);
			}
			if (alarm.getAlarm_id() > 0) {

				if ("0".equals(stringExtra)) {
					intent.setClass(AddAlarm.this, AlarmZaoShang.class);
					intent.putExtra("alarm", alarm);
				} else {
					intent.setClass(AddAlarm.this, AlarmZaoqi.class);
					intent.putExtra("alarm", alarm);
				}
				setResult(1113, intent);
				Log.i("TAG", alarm.getStartTime() + "那走红时候ian");
				dbFindManager.updateAlarm(alarm.getAlarm_id(), alarm);
				finish();
			} else {
				// 添加的内容

				if ("0".equals(stringExtra)) {
					intent.setClass(AddAlarm.this, AlarmZaoShang.class);
					intent.putExtra("alarm", alarm);
					Log.i("TAG", alarm.getStartTime() + "那走红时候ian");
					dbFindManager.insertAlarm(alarm);
					setResult(0001, intent);
					finish();
				} else {
					intent.setClass(AddAlarm.this, AlarmZaoqi.class);
					intent.putExtra("alarm", alarm);
					dbFindManager.insertAlarm(alarm);
					setResult(1111, intent);
					finish();
				}
			}
			break;

		default:
			break;
		}

	}

	String resule = "";

	/**
	 * 弹窗选择星期几执行
	 */
	// private void showWeekDialog() {
	//
	// resule = "";
	// final boolean[] flags = { false, false, false, false, false, false, false
	// };
	// final String[] info = { "周一", "周二", "周三", "周四", "周五", "周六", "周天" };
	// AlertDialog show = new AlertDialog.Builder(AddAlarm.this).setTitle("多选框")
	// .setMultiChoiceItems(info, flags, new
	// DialogInterface.OnMultiChoiceClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface dialog, int which, boolean isChecked)
	// {
	// flags[which] = isChecked;
	//
	// }
	// }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// for (int i = 0; i < flags.length; i++) {
	// if (flags[i]) {
	// resule += info[i] + "-";
	// }
	// }
	// resule = resule.substring(0, resule.length() - 1);
	// alarm.setWeek(resule);
	// zaoshui_shijianxianshi_tv.setText(resule);
	// dialog.dismiss();
	//
	// }
	// }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// dialog.dismiss();
	// }
	// }).show();
	// }

	// 时间控件
	class TimeListener implements OnTimeChangedListener {

		/**
		 * view 当前选中TimePicker控件 hourOfDay 当前控件选中TimePicker 的小时 minute
		 * 当前选中控件TimePicker 的分钟
		 */
		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			alarm.setStartTime(hourOfDay + ":" + minute);
			System.out.println("h:" + hourOfDay + " m:" + minute);
		}

	}

	/**
	 * 弹窗出现提示语
	 */
	public void editDialog() {
		Builder builder = new AlertDialog.Builder(AddAlarm.this);
		builder.setTitle("请输入");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		final EditText editText = new EditText(AddAlarm.this);
		builder.setView(editText);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				alarm.setTishiyu(editText.getText().toString());
				zaoqi_tishiyu_shezhi_tv.setText(editText.getText().toString());
				dialog.dismiss();

			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

}
