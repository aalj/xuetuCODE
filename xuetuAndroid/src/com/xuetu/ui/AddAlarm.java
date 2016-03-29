package com.xuetu.ui;

import com.xuetu.R;
import com.xuetu.entity.Alarm;
import com.xuetu.view.TitleBar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
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
	TextView zaoshui_shijianxianshi_tv;
	TextView zaoqi_tishiyu_shezhi_tv;
//	TextView zaoshui_moshitixing;
	TitleBar title;
	String stringExtra;
	Alarm alarm = new Alarm();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.zaoqi_shijian);
		stringExtra = getIntent().getStringExtra("temp");
		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		title = (TitleBar) findViewById(R.id.title_my);

//		zaoshui_moshitixing = (TextView) findViewById(R.id.zaoshui_moshitixing);
		zaoqi_tishiyu_shezhi_tv = (TextView) findViewById(R.id.zaoqi_tishiyu_shezhi_tv);
		zaoshui_shijianxianshi_tv = (TextView) findViewById(R.id.zaoshui_shijianxianshi_tv);
//		zaoshui_moshi_tv = (TextView) findViewById(R.id.zaoshui_moshi_tv);
		timePicker.setIs24HourView(true);
		if ("0".equals(stringExtra)) {// 这是早睡
//			zaoshui_moshi_tv.setText("早睡模式");
			title.setTitle("设置早睡时间");
		} else if ("1".equals(stringExtra)) {// 这是早起
			title.setTitle("设置早起时间");
//			zaoshui_moshi_tv.setText("早起模式");

		}
		title.setRightLayoutClickListener(this);
		title.setLeftLayoutClickListener(this);

	}

	public void onclick(View v) {
		switch (v.getId()) {
//		case R.id.mode:// 模式
//			showChangeItemDialog();
//			Toast.makeText(getApplicationContext(), "1 ", 0).show();
//			break;
		case R.id.congfu:// 重复
			showWeekDialog();
			Toast.makeText(getApplicationContext(), "2", 0).show();
			break;

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

			break;
		case R.id.right_layout:// 确定

			break;

		default:
			break;
		}

	}

	String resule = "";

	/**
	 * 弹窗选择星期几执行
	 */
	private void showWeekDialog() {

		resule = "";
		final boolean[] flags = { false, false, false, false, false, false, false };
		final String[] info = { "周一", "周二", "周三", "周四", "周五", "周六", "周天" };
		AlertDialog show = new AlertDialog.Builder(AddAlarm.this).setTitle("多选框")
				.setMultiChoiceItems(info, flags, new DialogInterface.OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						flags[which] = isChecked;

					}
				}).setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						for (int i = 0; i < flags.length; i++) {
							if (flags[i]) {
								resule += info[i] + "、";
							}
						}
						resule = resule.substring(0, resule.length() - 1);
						zaoshui_shijianxianshi_tv.setText(resule);
						dialog.dismiss();

					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}

	/**
	 * 弹性选择模式
	 */
//	private void showChangeItemDialog() {
//		String[] item = { "普通模式", "温柔模式", "强制模式" };
//		AlertDialog.Builder builder = new AlertDialog.Builder(AddAlarm.this);
//		builder.setTitle("设置模式");
//		builder.setCancelable(false);
//		builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// 保存当前的页面信心的是否需要提醒
//				alarm.setTemp_index(which);// 1、表示普通模式2、表示温柔3、表示强制
//				if(1==which){
////					zaoshui_moshitixing.setText("普通模式");
//				}else if(2==which){
////					zaoshui_moshitixing.setText("温柔模式");
//					
//				}else if (3==which){
////					zaoshui_moshitixing.setText("强制模式");
//					
//				}
//				dialog.dismiss();
//
//			}
//		});
//
//		builder.create();
//		builder.show();
//
//	}

	// 时间控件
	class TimeListener implements OnTimeChangedListener {

		/**
		 * view 当前选中TimePicker控件 hourOfDay 当前控件选中TimePicker 的小时 minute
		 * 当前选中控件TimePicker 的分钟
		 */
		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			alarm.setStartTime(hourOfDay * 60 + minute * 60);
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
