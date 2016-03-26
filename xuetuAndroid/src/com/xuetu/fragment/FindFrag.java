package com.xuetu.fragment;

import java.lang.reflect.Type;
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
import com.xuetu.entity.LongTime;
import com.xuetu.entity.Student;
import com.xuetu.ui.FindTaskListActivity;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;
import com.xuetu.view.BarChartView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FindFrag extends Fragment {

	private static final String TAG = "TAG";

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
		public void handleMessage(Message msg) {
			if(msg.what==12){
				List<float[]> getshijainshuju=(List<float[]>) msg.obj;
				br.setGroupCount(getshijainshuju.size());
				br.setDataCount(1);
				
				for(int i =0;i<getshijainshuju.size();i++){
					Log.i(TAG, "initView---------->2");
					br.setGroupData(i, getshijainshuju.get(i));
					Log.i(TAG, "initView---------->21");
				}
				Log.i(TAG, "initView---------->3");
		        br.setBarColor(new int[] { 0xff9575cd });
		        Log.i(TAG, "initView---------->4");
				br.setDataTitle(new String[] { "星期" });
				Log.i(TAG, "initView---------->5");
				br.setGroupTitle(new String[] { "1", "2", "3", "4", "5", "6", "7" });
			}
			super.handleMessage(msg);
		}
	};
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		
		gettime(student.getStuId());
		
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
		Log.i(TAG, "initView---------->");
		XueTuApplication application = (XueTuApplication) getActivity().getApplication();
		student = application.getStudent();

		br = (BarChartView) inflate.findViewById(R.id.bar);
		gettime(student.getStuId());
//		List<float[]> list2 = ((XueTuApplication)getActivity().getApplication()).getList();
//		Log.i("TAG", "-------------->"+list2.size()+"");
		
//		Log.i(TAG, "initView---------->6");
		
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



	public void onClick(View v) {

	}
	
	
	
	
	/**
	 * 加载学习时间
	 */
	public void gettime(int stuid) {
		HttpUtils httpUtils = new HttpUtils();
		String url = GetHttp.getHttpLJ() + "GetLongTime";

		RequestParams pra = new RequestParams();
		pra.addBodyParameter("stuID", stuid + "");
		httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Type type = new TypeToken<List<LongTime>>() {
				}.getType();
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				List<LongTime> time = gson.fromJson(arg0.result, type);
				Log.i("TAG", time.size()+"time-------------->");
				 List<float[]> getshijainshuju = DataToTime.getshijainshuju(time);
				 Message msg = Message.obtain();
				 msg.what=12;
				 msg.obj=getshijainshuju;
				handler.sendMessage(msg );
				 
				 
				 
				// TODO
				Log.i("TAG", getshijainshuju.size()+"getshijainshuju-------------->");
//				((XueTuApplication) getApplication()).setList(getshijainshuju);

			}
		});
	}
	
	
	

	private class MyOnClickLisener implements OnClickListener {

		@Override
		public void onClick(View v) {
//			Toast.makeText(getActivity(), "dianjiashijain ", Toast.LENGTH_SHORT).show();
			Intent intent = null;
			switch (v.getId()) {
			case R.id.linear_task:// 任务
				intent = new Intent();
				intent.setClass(getActivity(), FindTaskListActivity.class);
				getActivity().startActivity(intent);
				break;
			case R.id.linear_data:// 资料
				Toast.makeText(getContext(), "开发中", 0).show();
				break;
			case R.id.linear_getup:// 早睡
				Toast.makeText(getContext(), "开发中", 0).show();

				break;
			case R.id.linear_sleep:// 早起
				Toast.makeText(getContext(), "开发中", 0).show();

				break;
			case R.id.linear_countdown:// 倒计时
				Toast.makeText(getContext(), "开发中", 0).show();

				break;
			case R.id.linear_supervise:// 全天监督
				Toast.makeText(getContext(), "开发中", 0).show();

				break;

			default:
				Toast.makeText(getContext(), "开发中", 0).show();
				intent = new Intent();
				break;
			}
			
		}

	}

}
