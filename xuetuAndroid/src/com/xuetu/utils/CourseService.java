package com.xuetu.utils;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.R.color;
import com.xuetu.entity.MyClass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.Toast;

/**
 * Course处理
 * 
 * @author Mystery
 * 
 */
public class CourseService {
	int[][] lessons = {
			{ R.id.lesson11, R.id.lesson12, R.id.lesson13, R.id.lesson14, R.id.lesson15, R.id.lesson16, R.id.lesson17 },
			{ R.id.lesson21, R.id.lesson22, R.id.lesson23, R.id.lesson24, R.id.lesson25, R.id.lesson26, R.id.lesson27 },
			{ R.id.lesson31, R.id.lesson32, R.id.lesson33, R.id.lesson34, R.id.lesson35, R.id.lesson36, R.id.lesson37 },
			{ R.id.lesson41, R.id.lesson42, R.id.lesson43, R.id.lesson44, R.id.lesson45, R.id.lesson46, R.id.lesson47 },
			{ R.id.lesson51, R.id.lesson52, R.id.lesson53, R.id.lesson54, R.id.lesson55, R.id.lesson56, R.id.lesson57 },
			{ R.id.lesson61, R.id.lesson62, R.id.lesson63, R.id.lesson64, R.id.lesson65, R.id.lesson66,
					R.id.lesson67 }, };
	// 某结课的背景图，用于随机获取
	int[] bg = { R.drawable.kb1, R.drawable.kb2, R.drawable.kb3, R.drawable.kb4, R.drawable.kb5, R.drawable.kb6,
			R.drawable.kb7 };
	Gson gson = new Gson();
	MyClass myclass = null;
	private static volatile CourseService courseService;

	public void getCourse() {
		

		// http://localhost:8080/xuetuWeb/CourseAndroid
		HttpUtils httpUtils = new HttpUtils();
		String url = "http://192.168.1.106:8080/xuetuWeb/CourseAndroid";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuid", "1");
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				System.out.println(arg0.result);
				Type type = new TypeToken<List<MyClass>>() {
				}.getType();
				List<MyClass> myclasses = gson.fromJson(arg0.result, type);
				System.out.println(myclasses);
				fillCourse(myclasses);
				System.out.println("标记" + myclasses);

			}
		});

	}

	// 处理Course对象
	private void fillCourse(List<MyClass> myclasses) {
		for (int i = 0; i < myclasses.size(); i++) {
			myclass = myclasses.get(i);
			int clsWeek = myclass.getClsWeek();
			int clsFew = myclass.getClsFew();
			Button lesson = (Button) context.findViewById(lessons[clsWeek - 1][clsFew - 1]);
			lesson.setText(myclass.getClasName());
			int bgRes = bg[CommonUtil.getRandom(bg.length - 1)];// 随机获取背景色
			lesson.setBackgroundResource(bgRes);// 设置背景
			lesson.setText(myclass.getClasName() + "@" + myclass.getClsRoom());// 设置文本为课程名+“@”+教室
		}
	}

	Activity context = null;

	public CourseService(Activity context) {
		this.context = context;
	}

}
