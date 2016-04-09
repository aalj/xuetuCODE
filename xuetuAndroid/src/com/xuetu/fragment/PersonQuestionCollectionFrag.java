package com.xuetu.fragment;

import java.lang.reflect.Type;
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
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.CollectionQuestion;
import com.xuetu.entity.Student;
import com.xuetu.ui.Answer_list;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.GetHttp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PersonQuestionCollectionFrag extends Fragment implements OnItemClickListener {
	ListView listview = null;
	Student student;
	List<CollectionQuestion> datas = new ArrayList<>();
	MyBasesadapter<CollectionQuestion> myadapter;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = getContext();
		View view = inflater.inflate(R.layout.listview_person_question, null);
		listview = (ListView) view.findViewById(R.id.listview_personquestion);
		listview.setOnItemClickListener(this);
		student = ((XueTuApplication) getActivity().getApplication()).getStudent();
//		getDatas();
		return view;

	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getDatas();
	}
	

	/**
	 * 通过学生id得到自己收藏的问题
	 * 
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	public void getDatas() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "GetPersonalCollectionQuestionByStuIDServlet";
		RequestParams params = new RequestParams();
		params.addBodyParameter("stuID", student.getStuId() + "");
		httpUtils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type type = new TypeToken<List<CollectionQuestion>>() {
				}.getType();
				datas = gson.fromJson(arg0.result, type);
				addView();
			}
		});
	}

	/**
	 * 
	 * listview的加载
	 */
	private void addView() {
		listview.setAdapter(
				myadapter = new MyBasesadapter<CollectionQuestion>(context, datas, 
						R.layout.question_listitem) {

					@Override
					public void convert(ViewHodle viewHolder, CollectionQuestion item) {
						
						viewHolder.setText(R.id.tv_ans_num,
								item.getQuestion().getAns_num() + "评论");
						viewHolder.setText(R.id.tv_time,
								sdf.format(new Date(item.getQues_time().getTime())));
						viewHolder.setText(R.id.tv_ques_text, item.getQuestion().getQuesText());
						viewHolder.setText(R.id.tv_subject, item.getQuestion().getSubject().getName() + "");
						viewHolder.getView(R.id.iv_ques_img).setVisibility(View.VISIBLE);
						if (item.getQuestion().getQuesIma().equals("no")) {
							viewHolder.getView(R.id.iv_ques_img).setVisibility(View.GONE);
						} else {
							viewHolder.SetUrlImage(R.id.iv_ques_img,
									GetHttp.getHttpLC() + item.getQuestion().getQuesIma());
						}
						
						viewHolder.getView(R.id.ll_question_item).setOnClickListener(new Myonclicklister(item));

					}
				});
	}

	public class Myonclicklister implements OnClickListener {
		CollectionQuestion collectionQuestion = null;

		public Myonclicklister(CollectionQuestion collectionQuestion) {

			this.collectionQuestion = collectionQuestion;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ll_question_item:
				Intent intentAnswer = new Intent(getContext(), Answer_list.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("curQues", collectionQuestion.getQuestion());
				intentAnswer.putExtras(bundle);
				startActivity(intentAnswer);
				break;
			default:
			}

		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}

}
