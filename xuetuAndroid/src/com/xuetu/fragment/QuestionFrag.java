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
import com.xuetu.adapter.MyQuestionListBaseAdapter;
import com.xuetu.entity.Question;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

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
public class QuestionFrag extends Fragment {
	List<Question> list = new ArrayList<Question>();
	View view = null;
	ListView lv = null;
	RelativeLayout rl_top;
	String url = null;
	MyQuestionListBaseAdapter adapter = null;
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.question_frag, null);
		rl_top = (RelativeLayout) view.findViewById(R.id.rl_top);
		lv = (ListView) view.findViewById(R.id.lv_question);
		InitData();
		if(adapter!=null)
			adapter.notifyDataSetChanged();
		return view;

	}
	
	public void InitData(){
		url = "http://10.201.1.13:8080/xuetuWeb/GetPageQuestion";
		HttpUtils hutils = new HttpUtils(10000);
		hutils.configCurrentHttpCacheExpiry(5000);
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new java.sql.Timestamp(System.currentTimeMillis())));*/
		hutils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("hehe", "fail");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				//指定date格式的gson对象
				Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				  .create();
				Type listtype = new TypeToken<List<Question>>(){}.getType();
				list = gson.fromJson(arg0.result, listtype);
				adapter = new MyQuestionListBaseAdapter(getActivity(), list) ;
				lv.setAdapter(adapter);
			}
			
		});
	}
/*	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("hehe", "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}*/
}

