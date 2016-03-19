/**
 * RecomFrag.java
 * com.librarybooksearch.fragment
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年11月8日 		view
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.xuetu.fragment;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.MyQuestionListBaseAdapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.Question;
import com.xuetu.ui.Question_ask;
import com.xuetu.utils.GetHttp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ClassName:RecomFrag
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   view
 * @version  
 * @since    Ver 1.1
 * @Date	 2015年11月8日		下午3:46:46
 *
 * @see 	 

 */
public class QuestionFrag extends Fragment implements OnClickListener{
	List<Question> list = new ArrayList<Question>();
	View view = null;
	ListView lv = null;
	RelativeLayout rl_top;
	RelativeLayout rl_left;
	RelativeLayout rl_right;
	String url = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Context mContext ;
	HttpUtils hutils = new HttpUtils();
	MyBasesadapter<Question> adapter = null;
//	MyQuestionListBaseAdapter adapter = null;
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContext = getContext();
		view = inflater.inflate(R.layout.question_frag, null);
		lv = (ListView) view.findViewById(R.id.lv_question);
		InitData();
	
		return view;

		
	}
	
	
	public void setOnclickListener(){
		rl_left.setOnClickListener(this);
		rl_right.setOnClickListener(this);
		rl_top.setOnClickListener(this);
	}
	/**发送网络请求，下载所有问题信息
	 * 
	 */int count=0;
	public void InitData(){
		
		
		url = GetHttp.getHttpLC()+"GetPageQuestion";
		
//		hutils.configCurrentHttpCacheExpiry(1000);
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new java.sql.Timestamp(System.currentTimeMillis())));*/
		hutils.send(HttpMethod.POST, url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("hehe", "fail");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Log.i("hehe", count+++"");
				// TODO Auto-generated method stub
				//指定date格式的gson对象
				Log.i("hehe", "success");
				Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				  .create();
				Type listtype = new TypeToken<List<Question>>(){}.getType();
				list = gson.fromJson(arg0.result, listtype);
				Log.i("hehe",list.get(2).getQuesText());
//				adapter = new MyQuestionListBaseAdapter(getActivity(), list) ;
//				lv.setAdapter(adapter);
				adapter = new MyBasesadapter<Question>(mContext,list,R.layout.question_listitem) {

					@Override
					public void convert(ViewHodle viewHolder, Question item) {
						// TODO Auto-generated method stub
						viewHolder.setText(R.id.tv_ques_text, item.getQuesText());
						viewHolder.setText(R.id.tv_subject, item.getSubject().getName());
						viewHolder.setText(R.id.tv_time,sdf.format(new Date(item.getQuesDate().getTime())) );
//						viewHolder.setIma(resID, drawableID)
						sdf.format(new Date(item.getQuesDate().getTime()));
						
						rl_top = viewHolder.getView(R.id.rl_top);
						rl_left = viewHolder.getView(R.id.rl_top);
						rl_right = viewHolder.getView(R.id.rl_right);
						setOnclickListener();
						
					}
				};
				lv.setAdapter(adapter);
				if(adapter!=null)
					adapter.notifyDataSetChanged();
			}
			
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.rl_top:
			Toast.makeText(mContext, "top", Toast.LENGTH_LONG).show();
			break;
		case R.id.rl_left:
			Toast.makeText(mContext, "left", Toast.LENGTH_LONG).show();
			break;
		case R.id.rl_right:
			Intent intent = new Intent(mContext,Question_ask.class);
			mContext.startActivity(intent);
			Toast.makeText(mContext, "right", Toast.LENGTH_LONG).show();
			break;
		}
	}
}

