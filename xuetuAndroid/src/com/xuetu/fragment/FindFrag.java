package com.xuetu.fragment;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.xuetu.HelpActivity;
import com.xuetu.R;
import com.xuetu.entity.Class_end;
import com.xuetu.entity.GetToddayClass;
import com.xuetu.entity.IsStudy;
import com.xuetu.entity.LongTime;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
import com.xuetu.ui.AddSelfPlanActivity;
import com.xuetu.ui.CourseActivity;
import com.xuetu.ui.DaoJiShiActivity;
import com.xuetu.ui.FindTaskListActivity;
import com.xuetu.ui.LearingRecordActivity;
import com.xuetu.ui.ShowTimeActivity;
import com.xuetu.ui.TimerActivity;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.DataToTime;
import com.xuetu.utils.GetHttp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FindFrag extends Fragment {

	private static final String TAG = "TAG";
	LinearLayout linearTask;
	Student student;
	LinearLayout linear_supervise;
	/** 签到 */
	LinearLayout qiandao;
	/** 学习时长 */
	LinearLayout xuexishicahng;
	/** 课程 */
	LinearLayout kecheng;
	/** 计划 */
	LinearLayout jihua;
	View inflate;

	TextView tilte;
	TextView info;
	TextView tv_time;
	TextView myswitch;
	TextView textView3;
	ImageView meiri_yibu = null;

	// ***************************************
	// 判断执行什么的需要的一些变量
	boolean flag = true;
	boolean planflag = true;
	boolean is_today_studyplan = false;// 判断是不是今天的学习计划
	MyClass myclass = null;
	GetToddayClass getClass = new GetToddayClass();
	Class_end classend;
	private long ss;
	int which_class;
	IsStudy isstudy;
	List<SelfStudyPlan> todayplan;
	List<String> todayplan_note;
	SelfStudyPlan studyplan;
	int w = 0;
	private Calendar c;
	private SharedPreferences pref;
	
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		inflate = inflater.inflate(R.layout.find_frag, null);

		initView();
		return inflate;
	}

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
		// 执行计划，和课程相关的内容
		classend = new Class_end();
		which_class = getClass.getWhich_class();
		isstudy = new IsStudy();
		c = Calendar.getInstance();
		pref = getActivity().getSharedPreferences("qiandao", 0);
		
		
		
		
		student = application.getStudent();

		// br = (BarChartView) inflate.findViewById(R.id.bar);
		gettime(student.getStuId());

		meiri_yibu = (ImageView) inflate.findViewById(R.id.meiri_yibu);
		kecheng = (LinearLayout) inflate.findViewById(R.id.kecheng);
		jihua = (LinearLayout) inflate.findViewById(R.id.jihua);
		linearTask = (LinearLayout) inflate.findViewById(R.id.linear_task);
		linear_supervise = (LinearLayout) inflate.findViewById(R.id.linear_supervise);
		// 学习时长
		xuexishicahng = (LinearLayout) inflate.findViewById(R.id.xuexishicahng);
		// 签到
		qiandao = (LinearLayout) inflate.findViewById(R.id.qiandao);

		MyOnClickLisener clickLisener = new MyOnClickLisener();
		linearTask.setOnClickListener(clickLisener);
		linear_supervise.setOnClickListener(clickLisener);
		xuexishicahng.setOnClickListener(clickLisener);
		qiandao.setOnClickListener(clickLisener);
		kecheng.setOnClickListener(clickLisener);
		jihua.setOnClickListener(clickLisener);
		meiri_yibu.setOnClickListener(clickLisener);
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
				// handler.sendMessage(msg);

			}
		});
	}

	private class MyOnClickLisener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = intent = new Intent();
			;
			switch (v.getId()) {
			case R.id.meiri_yibu:// 一分钟了解
				
				intent.setClass(getActivity(), HelpActivity.class);
				getActivity().startActivity(intent);
				break;
			case R.id.linear_task:// 任务

				intent.setClass(getActivity(), FindTaskListActivity.class);
				getActivity().startActivity(intent);
				break;
			case R.id.linear_supervise:// 倒计时
				intent.setClass(getActivity(), DaoJiShiActivity.class);
				getActivity().startActivity(intent);

				break;
			case R.id.xuexishicahng:// 学习时长
				Toast.makeText(getContext(), "学习时长", 0).show();
				intent.setClass(getActivity(), ShowTimeActivity.class);
				startActivity(intent);

				break;
			case R.id.qiandao:// 签到
				Toast.makeText(getContext(), "签到", 0).show();
				intent.setClass(getActivity(), LearingRecordActivity.class);
				startActivity(intent);

				break;
			case R.id.kecheng:// 课程
				Toast.makeText(getContext(), "课程", 0).show();
				zhixingkecheng();

				break;
			case R.id.jihua:// 计划
				Toast.makeText(getContext(), "计划", 0).show();
				zhixingjihua();

				break;

			default:
				Toast.makeText(getContext(), "开发中", 0).show();
				intent = new Intent();
				break;
			}

		}

		/**
		 * 店家执行计划
		 */
		private void zhixingjihua() {
			if (planflag) {
				search_today_studyplan();
			}

		}

		/**
		 * 点击执行课程
		 */
		private void zhixingkecheng() {
			if (flag) {
				get_stu_studyTime();
			}

		}

	}

	/**
	 * 上滑执行方法
	 */
	public void get_stu_studyTime() {
		flag = false;
		planflag = false;
		// center_click_flag = false;
		String url = GetHttp.getHttpKY() + "GetClassTime";
		// String url = "http://10.201.1.8:8080/xuetuWeb/GetClassTime";
		HttpUtils httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("day_of_week", getClass.getDay_in_week() + "");
		requestParams.addBodyParameter("which_class", getClass.getWhich_class() + "");
		// json 解析Student对象,并传给服务器
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String st = gson.toJson(student);
		requestParams.addBodyParameter("student", st);

		httpUtils.send(HttpMethod.POST, url, requestParams, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				flag = true;
				planflag = true;
				// center_click_flag = false;
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String arg = arg0.result;
				Type type = new TypeToken<MyClass>() {
				}.getType();
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				myclass = gson.fromJson(arg, type);

				if (myclass == null) {
					new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("当前没有课程任务,是否查询自学计划?")
							.setNegativeButton("取消", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							flag = true;
							planflag = true;
							// center_click_flag = true;
						}
					}).setNeutralButton("查看课表", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							flag = true;
							planflag = true;
							Intent intent = new Intent(getActivity(), CourseActivity.class);
							getActivity().startActivity(intent);

						}
					}).setPositiveButton("查询", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

							search_today_studyplan();
						}
					}).setCancelable(false).show();
				} else// 这里执行 有课程时需要进行的判断
				{
					if (classend.what_time() == 0)
					// if(true)
					{
						new AlertDialog.Builder(getActivity()).setTitle("提示")
								.setMessage("即将进入课程" + myclass.getClasName())
								.setNegativeButton("返回", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								flag = true;
								planflag = true;
								// center_click_flag = true;
							}
						}).setPositiveButton("立即进入", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub

								ss = classend.getstu_time(classend.get_end_time(which_class - 1)); // 运行的时间
								Intent intent = new Intent(getActivity(), TimerActivity.class);
								intent.putExtra("ss", ss);
								intent.putExtra("student", isstudy.stu_to_json(student));
								intent.putExtra("class_name", myclass.getClasName());
								intent.putExtra("tag", 1);
								intent.putExtra("start_and_end_time", classend.get_start_time(which_class - 1) + "~"
										+ classend.get_end_time(which_class - 1));
								intent.putExtra("text", "目前是第" + which_class + "节课");
								flag = true;
								planflag = true;
								// center_click_flag = true;
								startActivity(intent);
							}
						}).setCancelable(false).show();

					} else {
						flag = false;
						planflag = false;
						// center_click_flag = false;
						new AlertDialog.Builder(getActivity()).setTitle("提示")
								.setMessage(
										"课程:" + myclass.getClasName() + "   你迟到了" + classend.getmin() + "分钟,不能进入计时积分页面")
								.setCancelable(false).setNegativeButton("返回", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								flag = true;
								planflag = true;
								// center_click_flag = true;
							}
						}).show();

					}
				}
			}
		});
	}

	/**
	 * 当前没有课程学习触发的查找 当日学习计划事件
	 */

	public void search_today_studyplan() {

		flag = false;
		planflag = false;
		// center_click_flag = false;
		String url = GetHttp.getHttpKY() + "GetDayTime"; //
		HttpUtils httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("StuID", student.getStuId() + "");
		httpUtils.send(HttpMethod.POST, url, requestParams, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("抱歉,网络异常")
						.setNegativeButton("返回", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						flag = true;
						planflag = true;
						// center_click_flag = true;
					}
				}).setCancelable(false).show();

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub

				String arg = arg0.result;
				Type type = new TypeToken<List<SelfStudyPlan>>() {
				}.getType();
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				todayplan = gson.fromJson(arg, type); // 如果这个时候,所有的值已经传完

				// 用for循环,把他们的备注显示在另一个集合里
				todayplan_note = new ArrayList<String>();
				String[] dsss = new String[todayplan.size()];
				if (!arg.equals("[]")) {

					for (int i = 0; i < todayplan.size(); i++) {
						todayplan_note.add(todayplan.get(i).getPlanText() + "  开始时间"
								+ new SimpleDateFormat("HH:mm").format(todayplan.get(i).getStartTime()));
					}
					for (int i = 0; i < todayplan_note.size(); i++) {
						dsss[i] = todayplan_note.get(i);
					}

					new AlertDialog.Builder(getActivity()).setTitle("选择计划")
							.setSingleChoiceItems(dsss, 0, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							w = which;
						}
					}).setCancelable(false).setPositiveButton("进入计划", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							// 确定后执行赋值,然后跳转页面
							flag = false;
							planflag = false;
							// center_click_flag = false;
							studyplan = todayplan.get(w);
							Intent intent = new Intent(getActivity(), TimerActivity.class);
							intent.putExtra("ss",
									isstudy.gotoss(new SimpleDateFormat("HH:mm:ss")
											.format(todayplan.get(w).getEndTime()))
									- isstudy.gotoss(
											new SimpleDateFormat("HH:mm:ss").format(todayplan.get(w).getStartTime())));
							intent.putExtra("stu_id", student.getStuId());
							intent.putExtra("student", isstudy.stu_to_json(student));

							intent.putExtra("tag", 2);
							intent.putExtra("start_and_end_time",
									new SimpleDateFormat("HH:mm").format(todayplan.get(w).getStartTime()) + "~"
											+ new SimpleDateFormat("HH:mm").format(todayplan.get(w).getEndTime()));
							intent.putExtra("text", todayplan.get(w).getPlanText());
							intent.putExtra("计划", true); // 标记,传过去的是自定义计划的计时
							intent.putExtra("plan_id", studyplan.getPlanID());
							flag = true;
							planflag = true;
							// center_click_flag = true;
							startActivity(intent);
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							flag = true;
							planflag = true;
							// center_click_flag = true;

						}
					}).show();

				} else {
					flag = false;
					planflag = false;
					// center_click_flag = false;
					new AlertDialog.Builder(getActivity()).setCancelable(false).setTitle("注意")
							.setMessage("当天没有可执行的学习计划,是否添加")
							.setNegativeButton("返回", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							flag = true;
							planflag = true;
							// center_click_flag = true;

						}
					}).setPositiveButton("添加", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							flag = true;
							planflag = true;
							// center_click_flag = true;
							gotoInsertplan();

						}
					}).show();

					// gotoInsertplan();
				}

			}
		});
	}
/**
 * 跳转到添加学习计划页面计划
 */
	public void gotoInsertplan() {
		Intent intent = new Intent(getActivity(), AddSelfPlanActivity.class);
		flag = true;
		planflag = true;
		// center_click_flag = true;
		startActivity(intent);
	}

	
	/**
	 * 判断是否已经签到
	 * 
	 * @return
	 */
	public boolean qiandao_ed() {

		boolean b = false;
		int i = pref.getInt("签到", 0); // 获取储存在文件里的 DAY_OF_YEAR int 类型
		System.out.println("i>>>>>>>>>>>>>>>" + i);
		if (i == c.get(Calendar.DAY_OF_YEAR)) {
			b = true;
		}
		return b;
	}
	
	
	
	
}
