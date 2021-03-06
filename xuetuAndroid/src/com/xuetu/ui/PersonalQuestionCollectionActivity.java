package com.xuetu.ui;

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
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.Question;
import com.xuetu.entity.Student;
import com.xuetu.utils.GetHttp;
import com.xuetu.utils.MyBaseAdapter;
import com.xuetu.utils.ViewHolder;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PersonalQuestionCollectionActivity extends Activity implements OnClickListener, OnItemClickListener {
	TitleBar backtoperson_question;
	ListView listView_personquestion;
	Student student;
	List<Question> datas = new ArrayList<>();
	MyBasesadapter<Question> myadapter;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	RelativeLayout rl_top;
	RelativeLayout rl_left;
	RelativeLayout rl_right;
	RelativeLayout right_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_question_collection);
		backtoperson_question = (TitleBar) findViewById(R.id.backtoperson_question);
		listView_personquestion = (ListView) findViewById(R.id.listView_personquestion);
		backtoperson_question.setLeftLayoutClickListener(this);
		listView_personquestion.setOnItemClickListener(this);
		student = ((XueTuApplication) getApplication()).getStudent();
		getPersonQuestionsDatas();

	}

	@Override
	public void onClick(View v) {
		finish();
	}

	/**
	 * 加载listView
	 * 
	 */
	private void addView() {
		listView_personquestion
				.setAdapter(myadapter = new MyBasesadapter<Question>(this, datas, R.layout.question_listitem) {

					@Override
					public void convert(ViewHodle viewHolder, final Question item) {
						
						viewHolder.setText(R.id.tv_ans_num, item.getAns_num() + "评论");
						viewHolder.setText(R.id.tv_time,
								sdf.format(new Date(item.getQuesDate().getTime())));
						viewHolder.setText(R.id.tv_ques_text, item.getQuesText());
						viewHolder.setText(R.id.tv_subject, item.getSubject().getName() + "");
						viewHolder.getView(R.id.iv_ques_img).setVisibility(View.VISIBLE);
						if (!item.getQuesIma().equals("no")) {
							viewHolder.SetUrlImage(R.id.iv_ques_img,
									GetHttp.getHttpLC() + item.getQuesIma());
						} else {
							viewHolder.getView(R.id.iv_ques_img).setVisibility(View.GONE);
						}
						viewHolder.getView(R.id.ll_question_item)
								.setOnClickListener(new MyOnclickListener(item));
					}
				});
	}

	public class MyOnclickListener implements OnClickListener {
		Question curQues = null;

		public MyOnclickListener(Question curQues) {
			this.curQues = curQues;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ll_question_item:
				Intent intentAnswer = new Intent(getApplicationContext(), Answer_list.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("curQues", curQues);
				intentAnswer.putExtras(bundle);
				startActivity(intentAnswer);
				break;
			}

		}

	}

	/**
	 * 从服务器获得值
	 */
	public void getPersonQuestionsDatas() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "GetPersonQuestionByStuid";
		RequestParams params = new RequestParams();
		try {
			params.addBodyParameter("stuID", URLEncoder.encode(student.getStuId() + "", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
						.disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type type = new TypeToken<List<Question>>() {
				}.getType();
				datas = gson.fromJson(arg0.result, type);
				addView();
			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}
}
