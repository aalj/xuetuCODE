package com.xuetu.ui;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.entity.MyClass;
import com.xuetu.utils.CourseService;

/**
 * 
 * @author BCL 课程
 * 
 * 
 */
public class CourseActivity extends Activity {
	CourseService service = new CourseService(CourseActivity.this);
	RelativeLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		layout = (RelativeLayout) findViewById(R.id.relativelayout);
		Toast.makeText(getApplicationContext(), "xixiixii", 1).show();
		service.getCourse();
	}
}
