package com.xuetu.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.entity.SelfStudyPlan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class FindTaskItemActivity extends Activity {
	@ViewInject(R.id.tv_startTime)
	TextView tv_startTime_info;
	@ViewInject(R.id.tv_endTime_info)
	TextView tv_endTime_info;
	@ViewInject(R.id.study_info)
	TextView study_info;//执行模式
	@ViewInject(R.id.study_parrt_info)
	TextView study_parrt_info;
	@ViewInject(R.id.xuexi_info)
	EditText xuexi_info;
	//用于存储该页面的全部信息
	SelfStudyPlan selfStudyPlan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_task_item_edit);
		ViewUtils.inject(this);
		initView();
		
		
		
		
		
	}

	private void initView() {
		selfStudyPlan = (SelfStudyPlan) getIntent().getSerializableExtra("plans");
		tv_startTime_info.setText(selfStudyPlan.getStartTime()+"");
		tv_endTime_info.setText(selfStudyPlan.getEndTime()+"");
		study_info.setText(selfStudyPlan.getPattern().getPattrenText());
		boolean temp = false;
		if(selfStudyPlan.getPlanReming()==1){
			  temp = true;
		}
//		study_parrt_info.setClickable(temp);
		xuexi_info.setText(selfStudyPlan.getPlanText());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_task_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
