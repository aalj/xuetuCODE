package com.xuetu.fragment;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xuetu.R;
import com.xuetu.adapter.MyBasesadapter;
import com.xuetu.adapter.ViewHodle;
import com.xuetu.entity.PersonalStudyTimeAll;
import com.xuetu.utils.GetHttp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class StudyTimeBillBoardFragment extends Fragment {
	ListView listview_billboard;
	List<PersonalStudyTimeAll> datas;
	MyBasesadapter<PersonalStudyTimeAll> myadapter;
	Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = getActivity();
		View view = inflater.inflate(R.layout.listview_paihangbang, null);
		listview_billboard = (ListView) view.findViewById(R.id.listview_billboard);
		getDate();
		return view;
		
		
	}

	/**
	 * 数据的获取
	 * 
	 */

	public void getDate() {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpBCL() + "PaiHangbangServlet";
		httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				Type type = new TypeToken<List<PersonalStudyTimeAll>>() {
				}.getType();
				datas = gson.fromJson(arg0.result, type);
				loadView();

			}
		});
	}

	/**
	 * 加载listv
	 * 
	 */
	int i = 1;

	public void loadView() {
		boolean temp = true;
		listview_billboard.setAdapter(
				myadapter = new MyBasesadapter<PersonalStudyTimeAll>(context, datas, R.layout.listitem_of_paihangbang) {

					@Override
					public void convert(ViewHodle viewHolder, PersonalStudyTimeAll item) {
						viewHolder.setText(R.id.paihangbangxuhao, item.getTimePosition() + "");
						i++;
						viewHolder.SetUrlImage(R.id.head_paihangbang,
								GetHttp.getHttpBCL() + item.getStudent().getStuIma());
						viewHolder.setText(R.id.nicheng_paihangbang, item.getStudent().getStuName());
						viewHolder.setText(R.id.number_paihangbang, item.getTimeAll());

					}
				});

	}
}
