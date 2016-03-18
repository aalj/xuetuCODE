package com.xuetu.ui;

import java.util.List;

import com.xuetu.R;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.Student;
import com.xuetu.utils.CourseService;
import com.xuetu.utils.DBManager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 
 * @author BCL 课程
 * 
 * 
 */
public class CourseActivity extends Activity {
	SharedPreferences preferences = null;

	CourseService service;
	RelativeLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Student student = ((XueTuApplication) getApplication()).getStudent();
		setContentView(R.layout.activity_course);
		preferences = getSharedPreferences("config", Activity.MODE_PRIVATE);
		service = new CourseService(CourseActivity.this, preferences);
		layout = (RelativeLayout) findViewById(R.id.relativelayout);
		if (!preferences.getBoolean("saveDB", false)) {
			service.getCourse(student);
		} else {
			DBManager mgr = new DBManager(this);
			List<MyClass> querys = mgr.query();
			service.fillCourse(querys);
			Log.i("TAG", "离线缓存");
		}
		Toast.makeText(getApplicationContext(), "xixiixii", 1).show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 应用的最后一个Activity关闭死活释放DB

	}

}
