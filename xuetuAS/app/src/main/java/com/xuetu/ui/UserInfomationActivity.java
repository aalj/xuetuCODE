package com.xuetu.ui;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.R.color;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.CircleImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfomationActivity extends Baseactivity implements OnClickListener {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<Question> datas = new ArrayList<>();
	List<Answer> answers = new ArrayList<>();
	CircleImageView img_head;
	TextView user_name;
	TextView school_name;
	TextView text_qianming;
	TextView day;
	TextView question_name1;
	TextView kind_of_questions1;
	TextView time_of_question1;
	TextView questioninformation;
	TextView kind_of_question2;
	TextView question_time2;
	TextView name_of_answer1;
	TextView kind_of_answer1;
	TextView time_of_answer1;
	TextView answer_questioninformation2;
	TextView kind_of_answer2;
	TextView time_of_question2;
	RelativeLayout wenti1;
	RelativeLayout wenti2;
	RelativeLayout daan1;
	RelativeLayout daan2;
	RelativeLayout questionall;
	RelativeLayout answerall;
	LinearLayout linear_question;
	LinearLayout linear_answer;
	Student student;
	ImageView back_to_be;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_infomation);
		Intent intent = getIntent();
		student = (Student) intent.getSerializableExtra("curStu");
		question();
		answer();

		Log.i("XXX", student + "");
		img_head = (CircleImageView) findViewById(R.id.img_head);
		user_name = (TextView) findViewById(R.id.user_name);
		school_name = (TextView) findViewById(R.id.school_name);
		text_qianming = (TextView) findViewById(R.id.text_qianming);
		day = (TextView) findViewById(R.id.day);
		question_name1 = (TextView) findViewById(R.id.question_name1);
		kind_of_questions1 = (TextView) findViewById(R.id.kind_of_questions1);
		time_of_question1 = (TextView) findViewById(R.id.time_of_question1);
		questioninformation = (TextView) findViewById(R.id.questioninformation);
		kind_of_question2 = (TextView) findViewById(R.id.kind_of_question2);
		question_time2 = (TextView) findViewById(R.id.question_time2);
		name_of_answer1 = (TextView) findViewById(R.id.name_of_answer1);
		kind_of_answer1 = (TextView) findViewById(R.id.kind_of_answer1);
		time_of_answer1 = (TextView) findViewById(R.id.time_of_answer1);
		answer_questioninformation2 = (TextView) findViewById(R.id.answer_questioninformation2);
		kind_of_answer2 = (TextView) findViewById(R.id.kind_of_answer2);
		time_of_question2 = (TextView) findViewById(R.id.time_of_question2);
		wenti1 = (RelativeLayout) findViewById(R.id.wenti1);
		wenti2 = (RelativeLayout) findViewById(R.id.wenti2);
		daan1 = (RelativeLayout) findViewById(R.id.daan1);
		daan2 = (RelativeLayout) findViewById(R.id.daan2);
		wenti1.setOnClickListener(this);
		wenti2.setOnClickListener(this);
		daan1.setOnClickListener(this);
		daan2.setOnClickListener(this);
		linear_question = (LinearLayout) findViewById(R.id.linear_question);
		linear_answer = (LinearLayout) findViewById(R.id.linear_answer);
		questionall = (RelativeLayout) findViewById(R.id.questionall);
		answerall = (RelativeLayout) findViewById(R.id.answerall);
		questionall.setOnClickListener(this);
		answerall.setOnClickListener(this);
		back_to_be = (ImageView) findViewById(R.id.back_to_be);
		back_to_be.setOnClickListener(this);
	}

	public void loadData() {
		BitmapUtils bitmapUtils = new BitmapUtils(this);
		bitmapUtils.display(img_head, GetHttp.getHttpLJ() + student.getStuIma());
		user_name.setText(student.getStuName());
		school_name.setText(student.getStuUgrade());
		if (student.getStuSigner() != null) {
			text_qianming.setText(student.getStuSigner());
		} else {
			text_qianming.setText("这人很懒，什么都没有写");
			text_qianming.setTextColor(getResources().getColor(R.color.gray));
		}
		int days = (int) ((System.currentTimeMillis() - student.getStu_create_date().getTime()) / 1000 / 60 / 60 / 24)
				+ 1;
		day.setText(days + "天");
		if (datas.size() > 0) {
			question_name1.setText(datas.get(0).getQuesText());
			kind_of_questions1.setText(datas.get(0).getSubject().getName());
			time_of_question1.setText(sdf.format(new Date(datas.get(0).getQuesDate().getTime())));
			if (datas.size() > 1) {
				questioninformation.setText(datas.get(1).getQuesText());
				kind_of_question2.setText(datas.get(1).getSubject().getName());
				question_time2.setText(sdf.format(new Date(datas.get(1).getQuesDate().getTime())));
			} else {
				wenti2.setVisibility(View.GONE);
			}
		} else {
			linear_question.setVisibility(View.GONE);
		}
	}

	private void setAnswer() {
		/**************/

		if (answers.size() > 0) {
			name_of_answer1.setText(answers.get(0).getQuestion().getQuesText());
			kind_of_answer1.setText(answers.get(0).getQuestion().getSubject().getName());
			time_of_answer1.setText(sdf.format(new Date(answers.get(0).getAnsTime().getTime())));
			if (answers.size() > 1) {
				answer_questioninformation2.setText(answers.get(1).getQuestion().getQuesText());
				kind_of_answer2.setText(answers.get(1).getQuestion().getSubject().getName());
				time_of_question2.setText(sdf.format(new Date(answers.get(1).getAnsTime().getTime())));
				;
			} else {
				daan2.setVisibility(View.GONE);
			}
		} else {
			linear_answer.setVisibility(View.GONE);

		}
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			if (msg.what == 11) {// 问题
				loadData();// 加载问题数据
				Log.i("xxxxxxxxx", "问题");
			}
			if (msg.what == 12) {// 答案
				setAnswer();// 加载答案数据
				Log.i("xxxxxxxxx", "答案");
			}

		}
	};

	/**************/

	public void question() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "GetPersonQuestionByStuid";
		RequestParams params = new RequestParams();
		try {
			params.addBodyParameter("stuID", URLEncoder.encode(student.getStuId() + "", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
						.disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type type = new TypeToken<List<Question>>() {
				}.getType();
				datas = gson.fromJson(arg0.result, type);
				Message message = Message.obtain();
				message.what = 11;
				handler.sendMessage(message);

			}
		});
	}

	public void answer() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "GetAnswerBystuID";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuID", student.getStuId() + "");
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(getApplicationContext(), arg1, 0).show();

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
						.disableHtmlEscaping().setDateFormat("yyyy-MM-dd hh-mm-ss").create();
				Type type = new TypeToken<List<Answer>>() {
				}.getType();

				answers = gson.fromJson(arg0.result, type);
				Log.i("xxxxxxxxx", answers.toString());
				Message message = Message.obtain();
				message.what = 12;
				handler.sendMessage(message);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.questionall:
			Intent intentAnswer = new Intent(this, UserQuestionInfomationActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("curQues", (Serializable) datas);
			intentAnswer.putExtras(bundle);
			startActivity(intentAnswer);
			break;
		case R.id.answerall:
			Intent intentAnswer1 = new Intent(this, UserAnswerInformationActivity.class);
			Bundle bundle1 = new Bundle();
			bundle1.putSerializable("curQues", (Serializable) answers);
			intentAnswer1.putExtras(bundle1);
			startActivity(intentAnswer1);
			break;
		case R.id.back_to_be:
			finish();
			break;
		case R.id.wenti1:
			Intent intentwenti1 = new Intent(getApplicationContext(), Answer_list.class);
			Bundle bundlewenti1 = new Bundle();
			bundlewenti1.putSerializable("curQues", datas.get(0));
			intentwenti1.putExtras(bundlewenti1);
			startActivity(intentwenti1);
			break;
		case R.id.wenti2:
			Intent intentwenti2 = new Intent(getApplicationContext(), Answer_list.class);
			Bundle bundllewenti2 = new Bundle();
			bundllewenti2.putSerializable("curQues", datas.get(1));
			intentwenti2.putExtras(bundllewenti2);
			startActivity(intentwenti2);
			break;
		case R.id.daan1:
			Intent intentdaan1 = new Intent(getApplicationContext(), Answer_list.class);
			Bundle bundlledaan1 = new Bundle();
			bundlledaan1.putSerializable("curQues", answers.get(0).getQuestion());
			intentdaan1.putExtras(bundlledaan1);
			startActivity(intentdaan1);
			break;
		case R.id.daan2:
			Intent intentdaan2 = new Intent(getApplicationContext(), Answer_list.class);
			Bundle bundlledaan2 = new Bundle();
			bundlledaan2.putSerializable("curQues", answers.get(1).getQuestion());
			intentdaan2.putExtras(bundlledaan2);
			startActivity(intentdaan2);
			break;

		default:
			break;
		}

	}

}
