package com.xuetu;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.Question;
import com.xuetu.ui.Answer_list;
import com.xuetu.ui.Baseactivity;
import com.xuetu.utils.ActivityColector;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class UserQuestionInfomationActivity extends Baseactivity implements OnClickListener, OnItemClickListener {
	List<Question> datas;
	TitleBar backtoperson_question;
	ListView listView_personquestion;
	MyBasesadapter<Question> myadapter;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_question_infomation);
		backtoperson_question = (TitleBar) findViewById(R.id.backtoperson_question);
		listView_personquestion = (ListView) findViewById(R.id.listView_personquestion);
		Intent intent = getIntent();
		datas = (List<Question>) intent.getSerializableExtra("curQues");
		Log.i("ZZZZZZZZZZ0000", datas.toString());
		backtoperson_question.setLeftLayoutClickListener(this);
		addView();
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
						viewHolder.setText(R.id.tv_time, sdf.format(new Date(item.getQuesDate().getTime())));
						viewHolder.setText(R.id.tv_ques_text, item.getQuesText());
						viewHolder.setText(R.id.tv_subject, item.getSubject().getName() + "");
						viewHolder.getView(R.id.iv_ques_img).setVisibility(View.VISIBLE);
						if (!item.getQuesIma().equals("no")) {
							viewHolder.SetUrlImage(R.id.iv_ques_img, GetHttp.getHttpLC() + item.getQuesIma());
						} else {
							viewHolder.getView(R.id.iv_ques_img).setVisibility(View.GONE);
						}
						viewHolder.getView(R.id.ll_question_item).setOnClickListener(new MyOnclickListener(item));
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		finish();

	}

}
