package com.xuetu.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
import com.xuetu.entity.MyClass;
import com.xuetu.entity.Student;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Course处理
 * 
 * @author Mystery
 * 
 */
public class CourseService implements OnClickListener {
	Activity context = null;
	SharedPreferences preferences = null;
	Editor edit = null;

	public CourseService(Activity context, SharedPreferences preferences) {
		this.context = context;
		this.preferences = preferences;
		edit = preferences.edit();
	}

//	DBManager mgr;
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

	public void getCourse(Student student) {
		// 初始化DBManager
//		mgr = new DBManager(context);
		// http://localhost:8080/xuetuWeb/CourseAndroid
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "CourseAndroid";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuid", String.valueOf(student.getStuId()));
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				System.out.println(arg0.result);
				Log.i("TAG", "Success");
				// 得到值 Gson解析list集合
				Type type = new TypeToken<List<MyClass>>() {
				}.getType();
				List<MyClass> myclasses = gson.fromJson(arg0.result, type);
				// sharedpreference的键值对立flag
				boolean falgs = preferences.getBoolean("saveDB", false);		

				fillCourse(myclasses);
				Log.i("TAG", "填课程表");

			}
		});

	}

	List<Button> buttons = new ArrayList<Button>();

	// 处理Course对象 
	public void fillCourse(List<MyClass> myclasses) {
		for (int i = 0; i < myclasses.size(); i++) {
			myclass = myclasses.get(i);
			int clsWeek = myclass.getClsWeek();
			int clsFew = myclass.getClsFew();
			Button lesson = (Button) context.findViewById(lessons[clsFew - 1][clsWeek - 1]);
			lesson.setText(myclass.getClasName());
			int bgRes = bg[CommonUtil.getRandom(bg.length - 1)];// 随机获取背景色
			lesson.setBackgroundResource(bgRes);// 设置背景
			lesson.setText(myclass.getClasName() + "@" + myclass.getClsRoom());// 设置文本为课程名+“@”+教室
			Log.i("TAG", "成功加入课表");
			buttons.add(lesson);
		}

	}

	/**
	 * 为显示的课表增加点击事件 点击课表 到详细信息
	 */
	public void onclickthings() {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setOnClickListener(this);
			// buttons.get(i).setTag();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
