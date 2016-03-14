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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.callback.Callback;

import org.apache.http.NameValuePair;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.MyQuestionListBaseAdapter;
import com.xuetu.entity.Question;
import com.xuetu.ui.Answer_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Int2;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
public class QuestionFrag extends Fragment {
	List<Question> list = new ArrayList<Question>();
	View view = null;
	ListView lv = null;
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.question_frag, null);
		lv = (ListView) view.findViewById(R.id.lv_question);
		Log.i("hehe", "fragment----->setAdapter");
		String url = "http://10.40.5.15:8080/studentweb/getStudentsBySex";
		HttpUtils hutils = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("sex", "1");
		hutils.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("hehe", "fail");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Log.i("hehe", "success");
				// TODO Auto-generated method stub
//				List<Student> list =null;
//				list = null;
//				Log.i("hehe", "success");
				//指定date格式的gson对象
				Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				  .create();
				Type listtype = new TypeToken<List<Question>>(){}.getType();
				list = gson.fromJson(arg0.result, listtype);
				
				view = inflater.inflate(R.layout.question_frag, null);
				lv = (ListView) view.findViewById(R.id.listView1);
				lv.setAdapter(new MyQuestionListBaseAdapter(getActivity(), list));
				Log.i("hehe", list.get(1).getQues_text()+"");		
			}
			
		});
		//Item的点击事件
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(getActivity(),Answer_list.class);
				//将quesID传入问题详情页面
				
				startActivity(intent);
			}
		});
		return view;
	}
	
}

