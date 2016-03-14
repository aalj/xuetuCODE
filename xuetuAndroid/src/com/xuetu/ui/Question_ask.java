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

	//声明页面控件
	EditText et_question = null;
	Button btn_ask = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_ask);
		
		
		//创建一个新question对象
		Question question = new Question();
		//学生id
		question.setStu_id(1);
		//问题信息
		question.setQues_text(et_question.getText().toString());
		//提问时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date(sdf.format(System.currentTimeMillis()));
		question.setQues_time(date);
		//图片
//		question.setQues_img(R.drawable.question);
		//积分
		question.setAcpo_num(10);
		//学科
		question.setSub_id(1);
		
		Gson gson = new Gson();
		HttpUtils hutils = new HttpUtils(10000);
		String url = "http://10.40.5.15:8080/xuetuweb/SubmitQuestionServlet";
		hutils.configCurrentHttpCacheExpiry(5000);
		RequestParams params = new RequestParams();
		String jsonStr = gson.toJson(question);
		params.addBodyParameter("newQuestion",jsonStr); 
		hutils.send(HttpMethod.POST, url,new RequestCallBack<String>(){

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("hehe", "failed");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// 存入数据成功，应该是要跳转到列表页面
				Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				  .create();
				Question q = new Question();
				Type type = new TypeToken<Question>(){}.getType();
				q = gson.fromJson(arg0.result, type);
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
