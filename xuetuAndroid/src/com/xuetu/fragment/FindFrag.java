package com.xuetu.fragment;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.xuetu.db.DBFindManager;
import com.xuetu.entity.Countdown;
import com.xuetu.entity.LongTime;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
import com.xuetu.ui.DaoJiShiActivity;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FindFrag extends Fragment {

	private static final String TAG = "TAG";
	LinearLayout linearTask;
	Student student;
	LinearLayout linear_supervise;
	View inflate;
//	BarChartView br;

	TextView tilte;
	TextView info;
	TextView tv_time;
	TextView myswitch;

	// TextView tilte_daojishi;
	// TextView textView_daojishi;
	TextView textView3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		inflate = inflater.inflate(R.layout.find_frag, null);

		initView();
		return inflate;
	}

//	Handler handler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			if (msg.what == 12) {
//				List<LongTime> time = (List<LongTime>) msg.obj;
//				List<float[]> getshijainshuju = DataToTime.getshijainshuju(time);
//				br.setGroupCount(getshijainshuju.size());
//				br.setDataCount(1);
//
//				for (int i = 0; i < getshijainshuju.size(); i++) {
//					br.setGroupData(i , getshijainshuju.get(i));
//				}
//				br.setBarColor(new int[] { 0xff9575cd });
//				br.setDataTitle(new String[] { "学习时长(分钟)" });
//				SimpleDateFormat sim = new SimpleDateFormat("dd");
//				String[] string = new String[7];
//				int dayForWeek = DataToTime.dayForWeek(new Date(System.currentTimeMillis()));
//				
//				 String[] weekDays = { "星期日","星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
//				for (int i =0; i<getshijainshuju.size() ; i++) {
//					string[6-i]=weekDays[dayForWeek];
//					dayForWeek-=1;
//					if(dayForWeek<0){
//						dayForWeek=6;
//					}
//				}
//				br.setGroupTitle(string);
//			}
//			if (msg.what == 123) {
//				SelfStudyPlan self = (SelfStudyPlan) msg.obj;
//				tilte.setText(self.getPlanText());
//				info.setText(DataToTime.dataToT(self.getStartTime()));
//				long time = System.currentTimeMillis();
//				long timete = (self.getEndTime().getTime() - self.getStartTime().getTime());
//
//				String dataToHS = DataToTime.secToTime((int) (timete / 1000));
//				tv_time.setText(dataToHS);
//				if (self.getIsZhiXing() == 2) {
//					myswitch.setText("计划完成");
//				} else {
//					if (self.getStartTime().getTime() > time) {
//						myswitch.setText("未开始计划");
//					} else {
//						myswitch.setText("计划已过时");
//
//					}
//				}
//				super.handleMessage(msg);
//			}
//		}
//	};

	@Override
	public void onHiddenChanged(boolean hidden) {
		if (!hidden) {
			gettime(student.getStuId());

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

//		br = (BarChartView) inflate.findViewById(R.id.bar);
		gettime(student.getStuId());

		linearTask = (LinearLayout) inflate.findViewById(R.id.linear_task);
		linear_supervise = (LinearLayout) inflate.findViewById(R.id.linear_supervise);

		MyOnClickLisener clickLisener = new MyOnClickLisener();
		linearTask.setOnClickListener(clickLisener);
		linear_supervise.setOnClickListener(clickLisener);
	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

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

				List<float[]> getshijainshuju = DataToTime.getshijainshuju(time);
				Message msg = Message.obtain();
				msg.what = 12;
				msg.obj = time;
//				handler.sendMessage(msg);

			}
		});
	}

	private class MyOnClickLisener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = intent = new Intent();
			;
			switch (v.getId()) {
			case R.id.linear_task:// 任务

				intent.setClass(getActivity(), FindTaskListActivity.class);
				getActivity().startActivity(intent);
				break;
			case R.id.linear_supervise:// 倒计时
				intent.setClass(getActivity(), DaoJiShiActivity.class);
				getActivity().startActivity(intent);

				break;

			default:
				Toast.makeText(getContext(), "开发中", 0).show();
				intent = new Intent();
				break;
			}

		}

	}
}
