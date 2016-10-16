package com.xuetu.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.base.Baseactivity;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.utils.GetHttp;
import com.xuetu.widget.TitleBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class UserAnswerInformationActivity extends Baseactivity implements OnClickListener, OnItemClickListener {
	List<Answer> datas;
	TitleBar backtoperson_question;
	ListView listView_personquestion_answer;
	MyBasesadapter<Answer> myadapter;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_answer_information);
		backtoperson_question = (TitleBar) findViewById(R.id.backtoperson_question);
		listView_personquestion_answer = (ListView) findViewById(R.id.listView_personquestion_answer);
		Intent intent = getIntent();
		datas = (List<Answer>) intent.getSerializableExtra("curQues");
		Log.i("ZZZZZZZZZZ0000", datas.toString());
		backtoperson_question.setLeftLayoutClickListener(this);
		addView();
	}

	public void addView() {
		listView_personquestion_answer
				.setAdapter(myadapter = new MyBasesadapter<Answer>(this, datas, R.layout.question_listitem) {

					@Override
					public void convert(ViewHodle viewHolder, Answer item) {
						viewHolder.setText(R.id.tv_ans_num, item.getQuestion().getAns_num() + "答案");
						viewHolder.setText(R.id.tv_time,
								sdf.format(new Date(item.getQuestion().getQuesDate().getTime())));
						viewHolder.setText(R.id.tv_ques_text, item.getQuestion().getQuesText());
						viewHolder.setText(R.id.tv_subject, item.getQuestion().getSubject().getName() + "");
						viewHolder.getView(R.id.iv_ques_img).setVisibility(View.VISIBLE);
						if (!item.getQuestion().getQuesIma().equals("no")) {
							viewHolder.SetUrlImage(R.id.iv_ques_img,
									GetHttp.getHttpLC() + item.getQuestion().getQuesIma());
						} else {
							viewHolder.getView(R.id.iv_ques_img).setVisibility(View.GONE);
						}
						viewHolder.getView(R.id.ll_question_item).setOnClickListener(new MyOnclickListener(item));

					}

				});
	}

	public class MyOnclickListener implements OnClickListener {
		Question curQues = null;

		public MyOnclickListener(Answer curQues) {
			this.curQues = curQues.getQuestion();
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

	}

	@Override
	public void onClick(View v) {
		finish();
	}

}
