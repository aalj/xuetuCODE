package com.xuetu.adapter;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.xuetu.entity.Question;
import com.xuetu.ui.Answer_list;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.GetHttp;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionFragAdapter extends BaseAdapter {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private List<Question> questions;
	private Context context;
	XueTuApplication xt;
	LayoutInflater inflater;
	// List<Integer> listtag = new ArrayList<Integer>();
	BitmapUtils bitmapUtils = null;
	Boolean flag = false;
	int stu_id;
	String url = null;
	HttpUtils hutils = new HttpUtils();
	Set<Integer> s = new HashSet<Integer>();
	RequestParams params;

	// private RequestParams paramIsSave;
	// private String urlIsSave;
	public QuestionFragAdapter(List<Question> questions, Context context) {
		// Log.i("hehe", "listtag-----"+listtag.size());
		xt = (XueTuApplication) context.getApplicationContext();
		s = xt.getSetQuesWithImg();
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultBitmapMaxSize(150, 100);
		this.questions = questions;
		this.context = context;
		inflater = LayoutInflater.from(context);
		// Log.i("hehe",xt.getI()+"--------------------adapterApplication");
		// .getListtag();
		// listtag =
		// ((XueTuApplication)(context).getApplication()).getListtag();
	}

	@Override
	public int getCount() {
		return questions.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder v;
		// Log.i("hehe", "listtag-----"+listtag.size());
		// hutils.configCurrentHttpCacheExpiry(5000);
		// Log.i("hehe", "listtagSize"+listtag.size());
		if (convertView == null) {
			v = new ViewHolder();
			convertView = inflater.inflate(R.layout.question_listitem, null);
			v.ll_question_item = (LinearLayout) convertView
					.findViewById(R.id.ll_question_item);
			v.tv_answerNum = (TextView) convertView
					.findViewById(R.id.tv_ans_num);
			v.tv_ques_text = (TextView) convertView
					.findViewById(R.id.tv_ques_text);
			v.tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
			v.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			v.iv_ques_img = (ImageView) convertView
					.findViewById(R.id.iv_ques_img);

			convertView.setTag(v);
		} else {
			v = (ViewHolder) convertView.getTag();
		}
		v.tv_answerNum.setText(questions.get(position).getAns_num() + "评论");
		v.tv_ques_text.setText(questions.get(position).getQuesText());
		v.tv_subject.setText(questions.get(position).getSubject().getName());
		v.tv_time.setText(sdf.format(new Date(questions.get(position)
				.getQuesDate().getTime())));
		v.iv_ques_img.setVisibility(View.VISIBLE);
		if ("no".equals(questions.get(position).getQuesIma())) {
			v.iv_ques_img.setVisibility(View.GONE);
		} else {
			bitmapUtils.display(v.iv_ques_img, GetHttp.getHttpLC()
					+ questions.get(position).getQuesIma());
		}
		// if(("").equals(questions.get(position).getQuesIma())){
		// Log.i("hehe",
		// questions.get(position).getQuesText()+"-------------没图啊");
		// v.iv_ques_img.setVisibility(View.GONE);
		// }
		// else{
		// Log.i("hehe",
		// questions.get(position).getQuesText()+"-------------"+questions.get(position).getQuesIma());
		// bitmapUtils.display(v.iv_ques_img,GetHttp.getHttpLC()+questions.get(position).getQuesIma());
		// }
		v.ll_question_item.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, Answer_list.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("curQues", questions.get(position));
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		return convertView;
	}

	// 封装view的所有控件
	static class ViewHolder {
		TextView tv_answerNum;
		TextView tv_ques_text;
		TextView tv_subject;
		TextView tv_time;
		ImageView iv_ques_img;
		LinearLayout ll_question_item;

	}

}
