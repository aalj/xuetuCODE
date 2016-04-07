package com.xuetu.ui;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.ShowDialog;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AddSelfPlanActivity extends FragmentActivity implements OnClickListener {
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
	// 用于存储该页面的全部信息
	SelfStudyPlan selfStudyPlan = new SelfStudyPlan();

	Date startTime = null;
	Date endTime = null;
	DBFindManager dbFindManager = null;
	String[] item = null;
	List<Pattern> list;
	Date date = new Date(System.currentTimeMillis());
	Student student;

	boolean getPateern = false;
	// 标记是点击过学习模式
	boolean pateernMode = false;
	int tempself = 0;
	// 用于返回传值回去
	Intent intent = null;

	private SimpleDateFormat mFormatter = new SimpleDateFormat("MM-dd HH:mm:ss");
	SharedPreferences sp = null;
	/** 用于标记数据是否有更改 */
	boolean temp = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_self_plan);
		ViewUtils.inject(this);
		dbFindManager = new DBFindManager(this);
		// 用于标记是重那个页面跳转过来的
		tempself = getIntent().getIntExtra("tempself", 0);
		student = ((XueTuApplication) getApplication()).getStudent();
		sp = getSharedPreferences("config", Activity.MODE_PRIVATE);
		setOnclick();
		getPateern = sp.getBoolean("savePatternBoolean", true);
		if (getPateern) {// 表示当前本地数据库没有数据
			// 访问网络的到数据
			mySengHttp();
		} else {
			getpattern();

		}

		startTime = date;
		endTime = date;
		tv_startTime_info.setText(mFormatter.format(new Date(date.getTime()+1000*60*2)));
		tv_endTime_info.setText(mFormatter.format(new Date(date.getTime()+1000*60*2)));
		// 数据为空
		// study_info.setText(list.get(0).getPattrenText());

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
				// TODO 需要把数据存到本地数据库

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
		// TODO 需要把数据存到本地数据库

		Log.i("TAG", list.toString());
		item = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			item[i] = list.get(i).getPattrenText();
		}
		
	}

	/*
	 * 设置点击事件
	 */
	private void setOnclick() {
		tiem_start.setOnClickListener(this);
		tiem_end.setOnClickListener(this);
		moshi.setOnClickListener(this);

		titleBar1.setRightLayoutClickListener(this);
		titleBar1.setLeftLayoutClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tiem_start:// 设置开始时间
			temp = true;
			new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(listener)
					.setInitialDate(new Date(date.getTime()+1000*60*2)).setIs24HourTime(true).build().show();
			break;
		case R.id.tiem_end:// 设置结束时间
			temp = true;
			new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(listener2)
					.setInitialDate(startTime).setIs24HourTime(true).build().show();
			break;
		case R.id.moshi:// 设置模式
			temp = true;
			showChangeItemDialog();

			break;
		case R.id.right_layout:
			if (!TextUtils.isEmpty(xuexi_info.getText().toString())) {
				if (startTime.getTime() > System.currentTimeMillis()) {
					if ((endTime.getTime() - startTime.getTime()) < 2 * 60 * 60 * 1000) {
						if (panDuanTimeSize(endTime, startTime)) {

							if (!pateernMode) {// 如果没有选择学习模式的话设置默认的值
								selfStudyPlan.setPattern(list.get(0));
							}

							intent = new Intent();
							selfStudyPlan.setStudent(student);
							Log.i("TAG", "xuesheng duiiang   --- - -- - - -" + selfStudyPlan.getStudent().getStuName());
							selfStudyPlan.setStartTime(startTime);
							selfStudyPlan.setPlanDate(new Date(System.currentTimeMillis()));
							selfStudyPlan.setEndTime(endTime);
							selfStudyPlan.setPlanText(xuexi_info.getText().toString());
							//TODO
							if (study_parrt_info.isChecked()) {
								selfStudyPlan.setPlanReming(1);
							} else {
								selfStudyPlan.setPlanReming(0);
							}
							// dbFindManager.addSelfOne(selfStudyPlan);
							intent.putExtra("self", selfStudyPlan);
							addChangSelf(selfStudyPlan);
							setResult(1011, intent);
							finish();

						} else {

							Toast.makeText(getApplicationContext(), "结束时间应该大于开始时间1分钟", 0).show();
						}
					} else {

						Toast.makeText(getApplicationContext(), "计划不能超过超过两小小时", 0).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "计划开始时间应该大于现在时间", 0).show();

				}
			} else {
				Toast.makeText(getApplicationContext(), "计划描述还没有填写", 0).show();

			}

			break;
		case R.id.left_layout:
			ShowDialog.showDialog(AddSelfPlanActivity.this, temp);
			break;

		default:

			break;
		}

	}

	private void addChangSelf(final SelfStudyPlan selfStudyPlan) {
		HttpUtils httpUtils = new HttpUtils();
		if (httpUtils != null) {
			String url = GetHttp.getHttpLJ() + "InsertSelfServlrt";
			RequestParams patram = new RequestParams();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String json = gson.toJson(selfStudyPlan);
			try {
				patram.addBodyParameter("self", URLEncoder.encode(json, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			httpUtils.send(HttpMethod.POST, url, patram, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {

				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					if (tempself == 1) {
						intent.putExtra("selfa", selfStudyPlan);
						setResult(1011, intent);
					} else {
						finish();
					}

					Toast.makeText(getApplicationContext(), arg0.result, 0).show();

				}
			});
		}

	}

	/**
	 * 比较时间的大小
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean panDuanTimeSize(Date endTime, Date startTime) {
		return (endTime.getTime() - startTime.getTime()) >= 60 * 1000;
	}

	private void showChangeItemDialog() {
		getpattern();
		AlertDialog.Builder builder = new AlertDialog.Builder(AddSelfPlanActivity.this);
		builder.setTitle("设置模式");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 保存当前的页面信心的是否需要提醒
				selfStudyPlan.setPattern(list.get(which));
				study_info.setText(list.get(which).getPattrenText());
				pateernMode = true;
				dialog.dismiss();

			}
		});

		builder.create();
		builder.show();

	}
	//添加开始时间
	private SlideDateTimeListener listener = new SlideDateTimeListener() {

		@Override
		public void onDateTimeSet(Date date) {

			startTime = date;
			Log.i("TAG", mFormatter.format(date));
			tv_startTime_info.setText(mFormatter.format(date));
			Toast.makeText(AddSelfPlanActivity.this, mFormatter.format(date), Toast.LENGTH_SHORT).show();

		}

		// Optional cancel listener
		@Override
		public void onDateTimeCancel() {
			Toast.makeText(AddSelfPlanActivity.this, "放弃修改", Toast.LENGTH_SHORT).show();
		}
	};
	//提娜佳结束时间
	private SlideDateTimeListener listener2 = new SlideDateTimeListener() {

		@Override
		public void onDateTimeSet(Date date) {
			endTime = date;

			tv_endTime_info.setText(mFormatter.format(date));

		}

		// Optional cancel listener
		@Override
		public void onDateTimeCancel() {
			Toast.makeText(AddSelfPlanActivity.this, "放弃修改", Toast.LENGTH_SHORT).show();
		}
	};

}
