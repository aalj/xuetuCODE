package com.xuetu.ui;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.callback.Callback;

import org.apache.http.params.HttpParams;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.entity.Question;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Question_ask extends Activity {
	//声明变量
	int stuId = 0;
	String quesText = null;
	Date quesTime = null;
//	String quesImg = null;
	int acpoNum = 0;
	int subId = 0;
	//声明页面控件
	EditText et_question = null;
	Button btn_ask = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_ask);
		init();
		
		
	}

	public void submitQuestion(Question q){
		//学生id
		stuId = 1;
		//问题信息
		quesText = et_question.getText().toString();
		//提问时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		quesTime = new Date(sdf.format(new java.sql.Timestamp(System.currentTimeMillis())));
		
		//图片
//		quesImg = ;
		//积分
		acpoNum =10;
		//学科
		subId = 1;
		
		Gson gson = new Gson();
		HttpUtils hutils = new HttpUtils(10000);
		String url = "http://10.40.5.15:8080/xuetuweb/SubmitQuestionServlet";
		hutils.configCurrentHttpCacheExpiry(5000);
		RequestParams params = new RequestParams();
		String jsonStr = gson.toJson(q);
		params.addBodyParameter("stuId",String.valueOf(stuId));
		params.addBodyParameter("quesText",et_question.getText().toString());
		params.addBodyParameter("quesTime",quesTime.toString());
		params.addBodyParameter("acpoNum",String.valueOf(acpoNum));
		params.addBodyParameter("subId",String.valueOf(subId));
		
		
		hutils.send(HttpMethod.POST, url,params,new RequestCallBack<String>(){

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("hehe", "failed");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// 存入数据成功，应该是要跳转到列表页面
				Log.i("hehe", "success");
				
			}} );
}
	public void ask(View v){
		
	}
	
	//初始化控件
	public void init(){
		et_question = (EditText) findViewById(R.id.et_question);
		btn_ask = (Button) findViewById(R.id.btn_ask);
	}
}
