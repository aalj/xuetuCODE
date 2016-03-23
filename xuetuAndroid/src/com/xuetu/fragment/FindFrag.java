package com.xuetu.fragment;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.xuetu.entity.LongTime;
import com.xuetu.entity.Student;
import com.xuetu.ui.FindTaskListActivity;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.BarChartView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FindFrag extends Fragment {

	LinearLayout linearTask;

	Student student;
	LinearLayout linearData;

	LinearLayout linearGetup;

	LinearLayout linearSleep;

	LinearLayout linearCountdown;

	LinearLayout linearSupervise;
	View inflate;
	BarChartView br;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		inflate = inflater.inflate(R.layout.find_frag, null);

		initView();
		return inflate;
	}
	
	
	Handler handler = new Handler(){
		@Override
		public void dispatchMessage(Message msg) {
			if(msg.what==1){
				Map<Integer, LongTime> getshijainshuju =(Map<Integer, LongTime>) msg.obj;
				br.setGroupCount(7);
				br.setDataCount(1);
				for (Entry<Integer, LongTime> entry : getshijainshuju.entrySet()) {
					float f = entry.getValue().getMyTime();
					br.setGroupData(entry.getKey(), new float[] { f });
				}
				
				br.setBarColor(new int[] { Color.RED });
				br.setDataTitle(new String[] { "星期" });
				br.setGroupTitle(new String[] { "1", "2", "3", "4", "5", "6", "7" });
			}
			super.dispatchMessage(msg);
		}
	};
	
	

	/**
	 * 
	 * initView:(初始化页面需要的控件以及实现的点击事件监听器)<br/>
	 *
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	private void initView() {
		XueTuApplication application = (XueTuApplication) getActivity().getApplication();
		student = application.getStudent();

		br = (BarChartView) inflate.findViewById(R.id.bar);
		br.setGroupCount(7);
		br.setDataCount(1);
		br.setGroupData(0, new float[]{10f});
		br.setGroupData(1, new float[]{20f});
        br.setGroupData(2, new float[]{30f});
        br.setGroupData(3, new float[]{40f});
        br.setGroupData(4, new float[]{45f});
        br.setGroupData(5, new float[]{10f});
        br.setGroupData(6, new float[]{20f});
        br.setBarColor(new int[] { Color.RED });
		br.setDataTitle(new String[] { "星期" });
		br.setGroupTitle(new String[] { "1", "2", "3", "4", "5", "6", "7" });
		
//		 gettime ();
		

		linearTask = (LinearLayout) inflate.findViewById(R.id.linear_task);
		linearData = (LinearLayout) inflate.findViewById(R.id.linear_data);
		linearGetup = (LinearLayout) inflate.findViewById(R.id.linear_getup);
		linearSleep = (LinearLayout) inflate.findViewById(R.id.linear_sleep);
		linearCountdown = (LinearLayout) inflate.findViewById(R.id.linear_countdown);
		linearSupervise = (LinearLayout) inflate.findViewById(R.id.linear_supervise);

		MyOnClickLisener clickLisener = new MyOnClickLisener();
		linearTask.setOnClickListener(clickLisener);
		linearData.setOnClickListener(clickLisener);
		linearGetup.setOnClickListener(clickLisener);
		linearSleep.setOnClickListener(clickLisener);
		linearCountdown.setOnClickListener(clickLisener);
		linearSupervise.setOnClickListener(clickLisener);

	}

	public void gettime (){
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ()+"GetLongTime";
		
		RequestParams pra  = new RequestParams();
		pra.addBodyParameter("stuID",student.getStuId()+"");
		httpUtils.send(HttpMethod.POST, url ,pra  , new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Type type = new TypeToken<List<LongTime>>() {
				}.getType();
				Gson gson = new GsonBuilder()  
						  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
						  .create();
				List<LongTime> time = gson.fromJson(arg0.result, type);
				 List<LongTime> getshijainshuju = DataToTime.getshijainshuju(time);
				br.setGroupCount(7);
				br.setDataCount(1);
				
				
				for (int i=0;i<7;i++) {
					float[] f= new float[]{getshijainshuju.get(i).getMyTime()};
					br.setGroupData(i, f);
					
				}
					  
				   
				 
				
				
//				for (Entry<Integer, LongTime> entry : getshijainshuju.entrySet()) {
//					int f =Integer.parseInt( entry.getKey().toString());
//					System.out.println(f+"fhkdg-----------------");
//					br.setGroupData(f, new float[] { entry.getValue().getMyTime() });
//				}
				br.setBarColor(new int[] { Color.RED });
				br.setDataTitle(new String[] { "星期" });
				br.setGroupTitle(new String[] { "1", "2", "3", "4", "5", "6", "7" });
//				Message message = Message.obtain();
//				message.what=1;
//				if(getshijainshuju.size()>0){
//					message.obj=getshijainshuju;
//					handler.sendMessage(message);
//					
//				}else{
//					Toast
//					.makeText(getActivity(), "近七天没有学习", 0).show();
//				}
				
			}
		});
	}

	public void onClick(View v) {

	}

	private class MyOnClickLisener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(), "dianjiashijain ", Toast.LENGTH_SHORT).show();
			Intent intent = null;
			switch (v.getId()) {
			case R.id.linear_task:// 任务
				intent = new Intent();
				intent.setClass(getActivity(), FindTaskListActivity.class);

				break;
			case R.id.linear_data:// 资料

				break;
			case R.id.linear_getup:// 早睡

				break;
			case R.id.linear_sleep:// 早起

				break;
			case R.id.linear_countdown:// 倒计时

				break;
			case R.id.linear_supervise:// 全天监督

				break;

			default:
				intent = new Intent();
				break;
			}
			getActivity().startActivity(intent);
		}

	}

}
