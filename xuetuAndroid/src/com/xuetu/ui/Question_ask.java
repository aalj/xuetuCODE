package com.xuetu.ui;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Question_ask extends Activity {
	//声明变量
	int stuId = 0;
	String quesText = null;
//	String quesImg = null;
	int acpoNum = 0;
	int subId = 0;
	long quesTime;
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
	public void ask(View v){
		Log.i("hehe", "subquestion");
		//学生id
		stuId = 1;
		//问题信息
		quesText = et_question.getText().toString();
		//提问时间
		quesTime = System.currentTimeMillis();
		//图片
//		quesImg = ;
		//积分
		acpoNum =10;
		//学科
		subId = 1;
		HttpUtils hutils = new HttpUtils(5000);
		String url = "http://10.201.1.13:8080/xuetuWeb/SubmitQuestion";
		hutils.configCurrentHttpCacheExpiry(1000);
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuId",String.valueOf(stuId));
		params.addBodyParameter("quesText",et_question.getText().toString());
		params.addBodyParameter("quesTime",quesTime+"");
		params.addBodyParameter("acpoNum",String.valueOf(acpoNum));
		params.addBodyParameter("subId",String.valueOf(subId));
		hutils.send(HttpMethod.POST,url,params,new RequestCallBack<String>(){

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("hehe", "failed");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// 存入数据成功，跳回问题列表页面
				Toast.makeText(Question_ask.this, "submit success"+arg0.result, 1).show();
				Question_ask.this.finish();
			}} );
	}
	
	//初始化控件
	public void init(){
		et_question = (EditText) findViewById(R.id.et_question);
		btn_ask = (Button) findViewById(R.id.btn_ask);
	}
}
