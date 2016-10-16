package com.xuetu.ui;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.base.Baseactivity;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.ShowDialog;
import com.xuetu.widget.TitleBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class FindTaskItemActivity extends Baseactivity implements OnClickListener {
	@ViewInject(R.id.tiem_start)
	RelativeLayout tiem_start;
	@ViewInject(R.id.tiem_end)
	RelativeLayout tiem_end;
	@ViewInject(R.id.moshi)
	RelativeLayout moshi;
	@ViewInject(R.id.tv_startTime_info)
	TextView tv_startTime_info;
	@ViewInject(R.id.tv_endTime_info)
	TextView tv_endTime_info;
	@ViewInject(R.id.study_info)
	TextView study_info;// 执行模式
	@ViewInject(R.id.study_parrt_info)
	Switch study_parrt_info;
	@ViewInject(R.id.xuexi_info)
	EditText xuexi_info;
	@ViewInject(R.id.titleBar1)
	TitleBar titleBar1;
	@ViewInject(R.id.button1)
	Button button1;
	// 用于存储该页面的全部信息
	SelfStudyPlan selfStudyPlan;
	SharedPreferences sp = null;
	DBFindManager dbFindManager = null;

	Date startTime = null;
	Date endTime = null;
	boolean getPateern = false;
	// 标记是点击过学习模式
	boolean pateernMode = false;

	List<Pattern> list;
	String[] item = null;
	boolean temp=false;

	// DBFindManager dbFindManager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_task_item_edit);
		ViewUtils.inject(this);
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		// dbFindManager = new DBFindManager(this);
		initView();
		// mySengHttp();
		setOnclick();

	}

	/*
	 * 设置点击事件
	 */
	private void setOnclick() {
		tiem_start.setOnClickListener(this);
		tiem_end.setOnClickListener(this);
		moshi.setOnClickListener(this);
		button1.setOnClickListener(this);
		titleBar1.setRightLayoutClickListener(this);
		titleBar1.setLeftLayoutClickListener(this);
	}

	/*
	 * 初始化页面
	 */
	private void initView() {
		dbFindManager = new DBFindManager(this);
		selfStudyPlan = (SelfStudyPlan) getIntent().getSerializableExtra("plans");
		endTime = selfStudyPlan.getEndTime();
		startTime = selfStudyPlan.getStartTime();
		tv_startTime_info.setText(DataToTime.dataToT(selfStudyPlan.getStartTime()));
		tv_endTime_info.setText(DataToTime.dataToT(selfStudyPlan.getEndTime()));
		study_info.setText(selfStudyPlan.getPattern().getPattrenText());
		boolean temp = false;
		if (selfStudyPlan.getPlanReming() == 1) {
			temp = true;
		}
		
		
		getPateern = sp.getBoolean("savePatternBoolean", true);
		if (getPateern) {// 表示当前本地数据库没有数据
			// 访问网络的到数据
			mySengHttp();
		} else {
			getpattern();

		}
		

		study_parrt_info.setChecked(temp);
		xuexi_info.setText(selfStudyPlan.getPlanText());

	}

	private SimpleDateFormat mFormatter = new SimpleDateFormat("MM-dd HH:mm");

	private SlideDateTimeListener listener = new SlideDateTimeListener() {

		@Override
		public void onDateTimeSet(Date date) {

			startTime = date;
			Log.i("TAG", mFormatter.format(date));
			tv_startTime_info.setText(mFormatter.format(date));
			Toast.makeText(FindTaskItemActivity.this, mFormatter.format(date), Toast.LENGTH_SHORT).show();

		}

		// Optional cancel listener
		@Override
		public void onDateTimeCancel() {
			Toast.makeText(FindTaskItemActivity.this, "放弃修改", Toast.LENGTH_SHORT).show();
		}
	};
	private SlideDateTimeListener listener2 = new SlideDateTimeListener() {

		@Override
		public void onDateTimeSet(Date date) {
			endTime = date;
			tv_endTime_info.setText(mFormatter.format(date));
			Toast.makeText(FindTaskItemActivity.this, mFormatter.format(date), Toast.LENGTH_SHORT).show();

		}

		// Optional cancel listener
		@Override
		public void onDateTimeCancel() {
			Toast.makeText(FindTaskItemActivity.this, "放弃修改", Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tiem_start:// 设置开始时间
			temp=true;
			new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(listener).setInitialDate(startTime)
					// .setMinDate(minDate)
					// .setMaxDate(maxDate)
					.setIs24HourTime(true)
					// .setTheme(SlideDateTimePicker.HOLO_DARK)
					// .setIndicatorColor(Color.parseColor("#990000"))
					.build().show();
			break;
		case R.id.tiem_end:// 设置结束时间
			temp=true;
			new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(listener2).setInitialDate(endTime)
					// .setMinDate(minDate)
					// .setMaxDate(maxDate)
					.setIs24HourTime(true)
					// .setTheme(SlideDateTimePicker.HOLO_DARK)
					// .setIndicatorColor(Color.parseColor("#990000"))
					.build().show();
			break;
		case R.id.moshi:// 设置模式
			temp=true;
			showChangeItemDialog();

			break;
		case R.id.right_layout:

			if (startTime.getTime() >= System.currentTimeMillis()) {
				if (!TextUtils.isEmpty(xuexi_info.getText().toString())) {
					if (panDuanTimeSize(endTime, startTime)) {

						Intent intent = new Intent();
						// 设置开始时间
						selfStudyPlan.setStartTime(startTime);
						// 这是结束时间
						selfStudyPlan.setEndTime(endTime);
						// 设置计划信息
						selfStudyPlan.setPlanText(xuexi_info.getText().toString());
						// 设置是否需要提醒
						if (study_parrt_info.isChecked()) {
							selfStudyPlan.setPlanReming(1);
						} else {
							selfStudyPlan.setPlanReming(0);
						}
						intent.putExtra("name1", selfStudyPlan);
						// 设置执行模式在选择的时候已经确定

						setResult(1010, intent);
						finish();

						// setResult(1011, intent);

					} else {

						Toast.makeText(getApplicationContext(), "结束时间应该大于开始时间2分钟", 0).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "计划描述还没有填写", 0).show();

				}
			} else {
				Toast.makeText(getApplicationContext(), "您的计划不能在过去开始", 0).show();
				
			}

			break;
		case R.id.left_layout:

			Intent intent2 = new Intent();
			setResult(1001, intent2);
			
			ShowDialog.showDialog(FindTaskItemActivity.this, temp);

			break;
		case R.id.button1:// 删除按钮
			Intent intent3 = new Intent();
			deleSelf(selfStudyPlan.getPlanID());
			// intent3.putExtra("name1", selfStudyPlan);
			// 设置执行模式在选择的时候已经确定

			setResult(1012, intent3);
			finish();

			break;

		default:

			break;
		}

	}

	public boolean panDuanTimeSize(Date endTime, Date startTime) {
		Log.i("TAG", endTime.getTime() - startTime.getTime() + "------------------<<<<<<<<<<");
		return (endTime.getTime() - startTime.getTime()) >= 60 * 1000;
	}

	private void deleSelf(int planID) {

		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "DeleteSelfServlet";
		RequestParams pra = new RequestParams();
		pra.addBodyParameter("planID", planID + "");
		httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(getApplicationContext(), "请检查网络", 0).show();

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				if ("1".equals(arg0.result)) {
					Toast.makeText(getApplicationContext(), "请检查网络", 0).show();
				}
				Toast.makeText(getApplicationContext(), "删除成功", 0).show();

			}
		});

	}

	private void showChangeItemDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(FindTaskItemActivity.this);
		builder.setTitle("设置模式");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setSingleChoiceItems(item, selfStudyPlan.getPattern().getPatternID() - 1,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						selfStudyPlan.setPattern(list.get(which));
						study_info.setText(list.get(which).getPattrenText());
						dialog.dismiss();

					}
				});

		builder.create();
		builder.show();

	}
	
	
	// 获取学习类型的模式
		private void mySengHttp() {
			Log.i("TAG", "获取学习类型");
			HttpUtils httpUtils = new HttpUtils();
			String url = GetHttp.getHttpLJ() + "GetPatterServlet";
			httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {

				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					Gson gson = new Gson();
					Type type = new TypeToken<List<Pattern>>() {
					}.getType();
					list = gson.fromJson(arg0.result, type);
					study_info.setText(list.get(0).getPattrenText());
					item = new String[list.size()-1];
					for (int i = 0; i < list.size()-1; i++) {
						item[i] = list.get(i).getPattrenText();
					}
					Editor edit = sp.edit();

					dbFindManager.addPatter(list);
					edit.putBoolean("savePatternBoolean", false);
					edit.commit();

				}
			});

		}

		/**
		 * 获得学系
		 */
		private void getpattern() {
			list = dbFindManager.getPattern();
			study_info.setText(list.get(0).getPattrenText());
			// dbFindManager.addPatter(list);

			Log.i("TAG", list.toString());
			item = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				item[i] = list.get(i).getPattrenText();
			}
			
		}
	
	
	

}
