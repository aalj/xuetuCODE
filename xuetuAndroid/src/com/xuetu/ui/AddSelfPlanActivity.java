package com.xuetu.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.gc.materialdesign.views.Switch;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
import com.xuetu.view.TitleBar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
	SelfStudyPlan selfStudyPlan;

	Date startTime = null;
	Date endTime = null;
	DBFindManager dbFindManager = null;
	String[] item = null;
	List<Pattern> list;
	Date date = new Date(System.currentTimeMillis());
	Student student;
	private SimpleDateFormat mFormatter = new SimpleDateFormat("MM-dd HH:mm");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_self_plan);
		selfStudyPlan = new SelfStudyPlan();
		ViewUtils.inject(this);
		dbFindManager = new DBFindManager(this);
		student = ((XueTuApplication)getApplication()).getStudent();
		
		setOnclick();
		getpattern();
		selfStudyPlan.setPattern(list.get(0));
		startTime = date;
		endTime = date;
		tv_startTime_info.setText(mFormatter.format(date));
		tv_endTime_info.setText(mFormatter.format(date));
		study_info.setText(list.get(0).getPattrenText());

	}

	/**
	 * 获得学系
	 */
	private void getpattern() {
		list = dbFindManager.getPattern();
		dbFindManager.addPatter(list);
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

			new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(listener)
					.setInitialDate(new Date()).setIs24HourTime(true).build().show();
			break;
		case R.id.tiem_end:// 设置结束时间

			new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(listener2)
					.setInitialDate(startTime).setIs24HourTime(true).build().show();
			break;
		case R.id.moshi:// 设置模式

			showChangeItemDialog();

			break;
		case R.id.right_layout:
			
			if(panDuanTimeSize(endTime, startTime)){
				
				
			
			Intent intent = new Intent();
			selfStudyPlan.setStudent(student);
			Log.i("TAG", "xuesheng duiiang   --- - -- - - -"+selfStudyPlan.getStudent().getStuName());
			selfStudyPlan.setStartTime(startTime);
			selfStudyPlan.setPlanDate(new Date(System.currentTimeMillis()));
			selfStudyPlan.setEndTime(endTime);
			selfStudyPlan.setPlanText(xuexi_info.getText().toString());
			if (study_parrt_info.isCheck()) {
				selfStudyPlan.setPlanReming(1);
			} else {
				selfStudyPlan.setPlanReming(0);
			}
			// dbFindManager.addSelfOne(selfStudyPlan);
			intent.putExtra("selfa", selfStudyPlan);
			Log.i("TAG", "shixain ===========" + selfStudyPlan.toString());
			setResult(1011, intent);
			finish();
			}else{
				Toast.makeText(getApplicationContext(), "时间不能小于开始时间时间", 0).show();
			}

			break;
		case R.id.left_layout:

			finish();
			break;

		default:

			break;
		}

	}
	
	/**
	 * 比较时间的大小
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean panDuanTimeSize(Date endTime ,Date startTime){
		Log.i("TAG",endTime.getTime()-startTime.getTime()+"------------------<<<<<<<<<<");
		return (endTime.getTime()-startTime.getTime())>60*1000;
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
				dialog.dismiss();

			}
		});

		builder.create();
		builder.show();

	}

	private SlideDateTimeListener listener = new SlideDateTimeListener() {

		@Override
		public void onDateTimeSet(Date date) {

			startTime = date;
			Log.i("TAG", startTime.getTime()+"<<<<<startTime><<<<<<<<<<<<<<<<");
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
	private SlideDateTimeListener listener2 = new SlideDateTimeListener() {

		@Override
		public void onDateTimeSet(Date date) {
			endTime = date;
			
			Log.i("TAG", endTime.getTime()+"<<<<<endTime><<<<<<<<<<<<<<<<");
			tv_endTime_info.setText(mFormatter.format(date));
			
		}

		// Optional cancel listener
		@Override
		public void onDateTimeCancel() {
			Toast.makeText(AddSelfPlanActivity.this, "放弃修改", Toast.LENGTH_SHORT).show();
		}
	};

}
