/**
 * HomePageFrag.java
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

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.xuetu.entity.Class_end;
import com.xuetu.entity.IsStudy;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
import com.xuetu.ui.AddSelfPlanActivity;
import com.xuetu.ui.LearingRecordActivity;
import com.xuetu.ui.TimerActivity;
import com.xuetu.ui.XueTuApplication;
import com.xuetu.utils.GetHttp;

import android.R.integer;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ClassName:HomePageFrag Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author view
 * @version
 * @since Ver 1.1
 * @Date 2015年11月8日 下午3:46:04
 * 
 * @see
 * 
 */
public class HomePageFrag extends Fragment implements OnTouchListener {

	int stu_id = 0;
	Button btn_up, btn_down, btn_center;
	ImageView img1, img2, img3, img4, img5, img6, imgup, imgdown;
	View view;
	RelativeLayout.LayoutParams layoutParams;
	ViewGroup root;
	private int _x;
	private int _y;
	private int btn_heigt;
	private int btn_width;
	private static final int UPDATE = 0;
	private int activity_width = 0;
	private int activity_height = 0;
	private int activity_top = 0;
	int lm;
	int tm;
	int b;
	Display display;
	TextView tv;
	int fHeight;
	int ii;
	private long ss;
	boolean flag = true;
	boolean planflag=true;
	boolean is_today_studyplan=false;//判断是不是今天的学习计划
	IsStudy isstudy;
	SelfStudyPlan studyplan;
	List <SelfStudyPlan>  todayplan ;
	List<String> todayplan_note;
	Student student ;
	Intent intent_learning;
	int x1=0,y1=0,x2=0,y2=0,y3=0,y4=0;
	//中间按钮  点击事件  触发条件
	boolean center_click_flag=true;
	private SharedPreferences pref;
	private Calendar c;
	//提示按钮
	TextView tv1,tv2;
	RelativeLayout rl;
	private String class_name;;
	int w = 0;
	String message1;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		//先获取Student对象
		
		XueTuApplication xuetu = (XueTuApplication) getActivity().getApplication();
		student = xuetu.getStudent();
		stu_id=student.getStuId();
		view = inflater.inflate(R.layout.home_page_frag, null);
		c=Calendar.getInstance();
		pref =getActivity().getSharedPreferences("qiandao",0);

		
				intent_learning = new Intent(getActivity(),
						LearingRecordActivity.class);
				
		
		
		layoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		display = getActivity().getWindowManager().getDefaultDisplay();
		System.out.println("height=" + display.getHeight());
		System.out.println("width=" + display.getWidth());
		activity_width = display.getHeight();
		activity_height = display.getWidth();
		// activity_top=display.
		
		flag     = true;
		planflag = true;
		tv = (TextView) getActivity().findViewById(R.id.person_tv);
		tv.getHeight();
		System.out.println("tv>>>>>>>>>>>>>>>>>>" + tv.getHeight());
		root = (ViewGroup) view.findViewById(R.id._root);
		btn_center = (Button) view.findViewById(R.id.imageButton1);
		img1 = (ImageView) view.findViewById(R.id.imageView7);
		img2 = (ImageView) view.findViewById(R.id.imageView3);
		img3 = (ImageView) view.findViewById(R.id.imageView5);
		img4 = (ImageView) view.findViewById(R.id.imageView4);
		img5 = (ImageView) view.findViewById(R.id.imageView6);
		img6 = (ImageView) view.findViewById(R.id.imageView8);

		imgup = (ImageView) view.findViewById(R.id.imageView1);
		imgdown = (ImageView) view.findViewById(R.id.imageView2);
		isstudy = new IsStudy();
		
		layoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		btn_center.setLayoutParams(layoutParams);
		btn_center.setOnTouchListener(this);

		
		
		
		new Thread() {
			@Override
			public void run() {
				synchronized (this) {
					try {
						wait(1000); // 1秒
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Message msg = new Message();
				msg.what = UPDATE;
				ii = getActivity().findViewById(R.id.main_title).getHeight();

				b = tv.getHeight();
				btn_heigt = btn_center.getHeight();
				btn_width = btn_center.getWidth();
				fHeight = getFheight();
				String s = btn_heigt + "|" + btn_width + "|" + b + "|"
						+ fHeight + "|" + ii;
				msg.obj = s;
				handler.sendMessage(msg);
			}
		}.start();

		if(	qiandao_ed())
		{
			btn_center.setText("");
		}
	
		//课程表点击事件
		view.findViewById(R.id.home_btn_up).setOnClickListener(
		new OnClickListener() {
			@Override
			public void onClick(View v) {
//				get_stu_studyTime();
				class_check();
			}
		});
        
		//计划点击事件
		view.findViewById(R.id.imageButton3).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				search_today_studyplan();
			}
		});
		
        
		ontouchinview();
		return view;

	}
	
	
	
	private View findViewById(int root2) {
		// TODO Auto-generated method stub
		return null;
	}



	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE:
				// TODO 接收消息并且去更新UI线程上的控件内容
				if (msg.what == UPDATE) {
					btn_center.setVisibility(View.VISIBLE);
					String s = (String) msg.obj;
					System.out.println(s);
					String[] ss = s.split("\\|".toString());
					String h = ss[0];
					String w = ss[1];
					String bo = ss[2];
					String fh = ss[3];
					String is = ss[4];
					b = Integer.parseInt(bo);
					fHeight = Integer.parseInt(fh);
					ii = Integer.parseInt(is);
					lm = (fHeight - ii - Integer.parseInt(h) + b) / 2;
					tm = (activity_height - Integer.parseInt(h)) / 2;
					layoutParams.leftMargin = tm;
					layoutParams.topMargin = lm;

					btn_center.setLayoutParams(layoutParams);

					PropertyValuesHolder visible = PropertyValuesHolder
							.ofFloat("alpha", 0F, 1F);
					ObjectAnimator.ofPropertyValuesHolder(btn_center, visible)
							.setDuration(1500).start();

				}
				break;
			case 456:
				message1=(String) msg.obj;
				
				String msg1[] = message1.split("-".toString());
				ss = Long.parseLong(msg1[0]);
				class_name = msg1[1];
				
				
				if (ss>0) {
					Intent intent = new Intent(getActivity(),
							TimerActivity.class);
					
					intent.putExtra("ss", ss);
					intent.putExtra("student", isstudy.stu_to_json(student));
					intent.putExtra("class_name", class_name);
					intent.putExtra("tag", 1);
					intent.putExtra("start_and_end_time",new SimpleDateFormat("hh:MM:ss").format(studyplan.getStartTime())
							+" ~ "+new SimpleDateFormat("hh:MM:ss").format(studyplan.getEndTime()));
					intent.putExtra("text", "目前是"+isstudy.get_which_chass()+"节课");
					
					flag = true;
					planflag=true;
					startActivity(intent);
				}else
				{
					
					System.out.println(class_name+"<<<<<<<<<<<<<<classname<<<<<<<<<");
					if(class_name.equals("null")){
						new AlertDialog.Builder(getActivity())
						.setTitle("提示")
						.setMessage("现在没有课程,需要查看自学计划吗?")
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								flag = true;
								planflag=true;
							}
						})
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								flag = true;
								planflag=true;
								search_today_studyplan();
							}
						}).show();
						
						
						
					}else{
					
					
//					Toast.makeText(getActivity(), "当前时间不符合", Toast.LENGTH_SHORT).show();
					new AlertDialog.Builder(getActivity())
									.setTitle("提示")
									.setMessage("当前时间不符合时间规则,需要查看自学计划吗")
									.setNegativeButton("取消", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											flag = true;
											planflag=true;
										}
									})
									.setPositiveButton("确定", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											flag = true;
											planflag=true;
											search_today_studyplan();
										}
									}).show();
					//获取课程失败后  执行下滑判断语句
					}
				}
				break;

			default:
				break;
			}

			super.handleMessage(msg);
		}
	};

	/**
	 * 图片返回 动画移动效果
	 * 
	 * @param vie
	 */
	public void moveBack(View vie) {
		PropertyValuesHolder up = PropertyValuesHolder.ofFloat("translationY",
				-80F, 0);
		PropertyValuesHolder down = PropertyValuesHolder.ofFloat(
				"translationY", 80F, 0);
		PropertyValuesHolder right = PropertyValuesHolder.ofFloat(
				"translationX", 80F, 0);
		PropertyValuesHolder left = PropertyValuesHolder.ofFloat(
				"translationX", -80F, 0);
		PropertyValuesHolder round = PropertyValuesHolder.ofFloat("rotation",
				120F, 0);

		ObjectAnimator.ofPropertyValuesHolder(img1, up, left, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img2, left, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img3, left, down, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img4, up, right, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img5, right, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img6, down, right, round)
				.setDuration(1000).start();
	}

	/**
	 * 动画移动效果
	 * 
	 * @param vie
	 */
	public void move(View vie) {
		//
		PropertyValuesHolder up = PropertyValuesHolder.ofFloat("translationY",
				0, -80F);
		PropertyValuesHolder down = PropertyValuesHolder.ofFloat(
				"translationY", 0, 80F);
		PropertyValuesHolder right = PropertyValuesHolder.ofFloat(
				"translationX", 0, 80F);
		PropertyValuesHolder left = PropertyValuesHolder.ofFloat(
				"translationX", 0, -80F);
		PropertyValuesHolder round = PropertyValuesHolder.ofFloat("rotation",
				0, 120F);

		ObjectAnimator.ofPropertyValuesHolder(img1, up, left, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img2, left, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img3, left, down, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img4, up, right, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img5, right, round)
				.setDuration(1000).start();
		ObjectAnimator.ofPropertyValuesHolder(img6, down, right, round)
				.setDuration(1000).start();

	}
//////////////////////////////////////////////////////////////////////////////////////////////////////   触   摸   事   件   ////////////////////////
	/**
	 * 触摸效果,我也不懂,能用就行了
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		// System.out.println("高>>>>>"+btn_center.getHeight());

		// layoutParams = new
		// RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT
		// );
		final int Y = (int) event.getRawY();

		
		
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		
		case MotionEvent.ACTION_DOWN:
			
			//监控点击事件,这里不采用onclick 而是由用户点击来确定,当按下时的xy坐标与弹起式的xy坐标不超过6是,跳转页面
				x1 = (int) event.getX(); 
				y1 = (int) event.getY(); 
				y3=(int) event.getRawY();   //667
			move(v);
			RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v
					.getLayoutParams();
			_y = Y - lParams.topMargin;
			break;
		case MotionEvent.ACTION_UP:
			//检测upshihouxy的坐标
			x2 = (int) event.getX(); 
			y2 = (int) event.getY(); 
			
			if(((Math.abs(x2-x1)<6&&Math.abs(x2-x1)>-6)&&(Math.abs(y2-y1)<6&&Math.abs(y2-y1)>-6))&&center_click_flag==true)
			{
				startActivity(intent_learning);
			}
			moveBack(v);
			layoutParams.leftMargin = tm;
			layoutParams.topMargin = lm;
			btn_center.setLayoutParams(layoutParams);
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v
					.getLayoutParams();
			layoutParams.topMargin = Y - _y; // 控制按钮在Y轴移动
			v.setLayoutParams(layoutParams);
			
			y4 = (int) event.getRawY();; 
			System.out.println("y3>>>>>>>>>>>>>>>>>>"+y3);//667
			System.out.println("y4>>>>>>>>>>>>>>>>>>"+y4 );//移动中
			System.out.println(stu_id+"<<<<<<<<<<<<");
			if(stu_id!=0)
			{
				System.out.println("进来了吗");
						if (y3-y4>200 && flag == true) 
							{
							System.out.println("进行的是上滑动");
								flag     = false;
								planflag = false;
								System.out.println("进行的是上滑动");
								class_check();
								
							} 
			
						if((y4-y3>200) && planflag==true)
							{
							System.out.println("进行的是下滑动");
								flag     = false;
								planflag = false;
								getStudyPlan();
							}
			}
			break;
		}
		root.invalidate();
		return true;
	}

	/**
	 * 获得 home page fragment 的高度
	 * 
	 * @return
	 */
	public int getFheight() {
		int aa = 0;
		aa = root.getHeight();
		return aa;
	}

	
	
	/**
	 * 上滑执行方法
	 */
	public void get_stu_studyTime() {
		
		String url =GetHttp.getHttpKY()+"GetClassTime";
//		String url = "http://10.201.1.8:8080/xuetuWeb/GetClassTime";
		HttpUtils httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("stu_id", stu_id + "");
		//json 解析Student对象,并传给服务器
		Gson  gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String st = gson.toJson(student);
		requestParams.addBodyParameter("student", st);
		httpUtils.send(HttpMethod.POST, url, requestParams,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						System.out.println("链接失败");
						flag     = true;
						planflag = true;
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						System.out.println("链接成功");
						String arg = arg0.result;
						Type type = new TypeToken<String>() {
						}.getType();
						Gson gson = new GsonBuilder().setDateFormat(
								"yyyy-MM-dd").create();
						String s = gson.fromJson(arg, type);
						Message message = Message.obtain();
						message.what=456;
						message.obj=s;
						handler.sendMessage(message);
					}
				});
	}
	
	/**
	 * 下滑判定,获取自学计划时间
	 */
	public void getStudyPlan()
	{
		center_click_flag=false;
		String url =GetHttp.getHttpKY()+"BackStudyTime";
//		String url = "http://10.201.1.26:8080/xuetuWeb/BackStudyTime";
		HttpUtils httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("StuID",stu_id+"");
		httpUtils.send(HttpMethod.POST, url, requestParams,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						System.out.println("链接失败");
						flag     = true;
						planflag = true;
						center_click_flag=true;
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						System.out.println("链接成功");
						//创建个人学习计划对象
//						SelfStudyPlan studyplan=new SelfStudyPlan();
						
						String arg = arg0.result;
						
						Type type = new TypeToken<SelfStudyPlan>() {}.getType();
						Gson gson = new GsonBuilder().setDateFormat(
								"yyyy-MM-dd HH:mm:ss").create();
						
						studyplan = gson.fromJson(arg, type);
						//进行判断,得到的对象是否为空,  如果不为空,在判断是不是今天的计划
						
						if(studyplan!=null)
						{
//							isstudy = new IsStudy();
							is_today_studyplan = isstudy.getStudyPlan(studyplan);
							
							if(is_today_studyplan)  //如果为true 则表明今天有计划
							{
								//计划学习时间 
								ss = isstudy.studyplanTime(studyplan);
								long starttime = isstudy.time_to_study(studyplan);
								//判断现在的时间  距离   计划学习开始时间,是否大于一个小时,不大于一个小时
								long p =isstudy.time_to_study(studyplan)- isstudy.zero_to_now_ss();
								if(p>3600)
								{
									new AlertDialog.Builder(getActivity())
										.setTitle("提示")
										.setMessage("离学习计划还有1个小时以上,是否提前学习")
										.setNegativeButton("取消", new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												flag     = true;
												planflag = true;
												center_click_flag=true;
											}
										})
										.setPositiveButton("确定", new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												System.out.println("开始执行计划时间");
												flag     = false;
												planflag = false;
												center_click_flag=false;
												Intent intent = new Intent(getActivity(),
														TimerActivity.class);
												intent.putExtra("ss", ss);
												intent.putExtra("stu_id", stu_id);
												intent.putExtra("student", isstudy.stu_to_json(student));
												
												intent.putExtra("tag", 2);
												intent.putExtra("start_and_end_time",new SimpleDateFormat("HH:mm:ss").format(studyplan.getStartTime())
														+" ~ "+new SimpleDateFormat("HH:mm:ss").format(studyplan.getEndTime()));
												intent.putExtra("text", studyplan.getPlanText())	;	
												
												flag = true;
												planflag=true;
												center_click_flag=true;
												startActivity(intent);
											}
										}).show();
									
								}else              //不大于一个小时执行这个语句
								{
									if(p>=0&&p<=3600)   //   if true  执行设定的学习时间    if false  执行剩余的时间
									{
										flag     = false;
										planflag = false;
										center_click_flag=false;
										System.out.println("开始执行计划时间");
										Intent intent = new Intent(getActivity(),
												TimerActivity.class);
										intent.putExtra("ss", ss);
										intent.putExtra("stu_id", stu_id);
										intent.putExtra("student", isstudy.stu_to_json(student));
										intent.putExtra("tag", 2);
										intent.putExtra("start_and_end_time",new SimpleDateFormat("HH:mm:ss").format(studyplan.getStartTime())
												+" ~ "+new SimpleDateFormat("HH:mm:ss").format(studyplan.getEndTime()));
										intent.putExtra("text", studyplan.getPlanText())	;	
										flag = true;
										planflag=true;
										center_click_flag=true;
										startActivity(intent);
									}else         //这里的p肯定为负数
									{
										flag     = false;
										planflag = false;
										center_click_flag=false;
										System.out.println("开始执行剩余的计划时间");
										Intent intent = new Intent(getActivity(),
												TimerActivity.class);
										intent.putExtra("ss", ss+p);
										intent.putExtra("stu_id", stu_id);
										intent.putExtra("student", isstudy.stu_to_json(student));
										intent.putExtra("tag", 2);
										intent.putExtra("start_and_end_time",new SimpleDateFormat("HH:mm:ss").format(studyplan.getStartTime())
												+" ~ "+new SimpleDateFormat("HH:mm:ss").format(studyplan.getEndTime()));
										intent.putExtra("text", studyplan.getPlanText())	;	
										flag = true;
										planflag=true;
										center_click_flag=true;
										startActivity(intent);
									}
								}
							}else{					
								flag     = false;
								planflag = false;
								//表明今天没有学习计划
								Toast.makeText(getActivity(), "今天没有计划", Toast.LENGTH_SHORT).show();
								//跳转到学习计划添加页
								gotoInsertplan();
							}
						}
						else{
							flag     = false;
							planflag = false;
							Toast.makeText(getActivity(), "今天没有计划", Toast.LENGTH_SHORT).show();
							//跳转到学习计划添加页
							gotoInsertplan();
						}
					}
				});
	}
	
	public void gotoInsertplan()
	{
		new AlertDialog.Builder(getActivity())
		.setTitle("提示")
		.setMessage("当前没有学习计划,是否添加")
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				flag     = true;
				planflag = true;
				center_click_flag=true;
			}
		})
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				System.out.println("开始执行计划时间");
				flag     = false;
				planflag = false;
				center_click_flag=false;
				Intent intent = new Intent(getActivity(),
						AddSelfPlanActivity.class);
				flag = true;
				planflag=true;
				center_click_flag=true;
				startActivity(intent);
			}
		}).show();
		
	}
	
	/**
	 * 判断是否已经签到
	 * @return
	 */
	public boolean qiandao_ed()
	{
		
		boolean b  = false;
		int i = pref.getInt("签到", 0);  //获取储存在文件里的   DAY_OF_YEAR  int 类型
		System.out.println("i>>>>>>>>>>>>>>>"+i);
		if(i ==  c.get(Calendar.DAY_OF_YEAR ))
		{
			b =true;
		}
		return b;
	}
	
	/**
	 * 屏幕触摸事件
	 * 
	 * 
	 */
	public void ontouchinview()
	{
		tv1 = (TextView) view.findViewById(R.id.tv_xianshi1);
		tv2 = (TextView) view.findViewById(R.id.tv_xianshi2);
		rl = (RelativeLayout) view.findViewById(R.id._root);
		rl.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				tv1.setVisibility(View.GONE);
				tv2.setVisibility(View.GONE);
				return false;
			}
		});		
	}
	
	/**
	 *    当前没有课程学习触发的查找 当日学习计划事件
	 */
	
	public void search_today_studyplan()
	{
		
		String url =GetHttp.getHttpKY()+"GetDayTime";    //  计划还未实现
		HttpUtils httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("StuID", student.getStuId()+"");
		httpUtils.send(HttpMethod.POST, url, requestParams,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
								System.out.println("网络异常");
								new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("抱歉,网络异常").setNegativeButton("返回", null).show();
								flag     = true;
								planflag = true;
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						
						String arg = arg0.result;
						System.out.println(arg+"arg<<<<<<<<<<<<<");
						Type type = new TypeToken<List<SelfStudyPlan>>() {
						}.getType();
						Gson gson = new GsonBuilder().setDateFormat(
								"yyyy-MM-dd HH:mm:ss").create();
						todayplan =gson.fromJson(arg, type); //如果这个时候,所有的值已经传完
						System.out.println(todayplan);
						//用for循环,把他们的备注显示在另一个集合里
						todayplan_note=new ArrayList<String>();
						String [] dsss = new String  [todayplan.size()];
						if(!arg.equals("[]")){
							
							
							for(int i=0;i<todayplan.size();i++)
							{
								todayplan_note.add(todayplan.get(i).getPlanText());
							}
							for(int i=0;i<todayplan_note.size();i++)
							{
								dsss[i]=todayplan_note.get(i);
							}
							
							new AlertDialog.Builder(getActivity())
							.setTitle("选择计划")
							.setSingleChoiceItems(dsss, 0, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									w= which;
								}
							}).setPositiveButton("进入计划", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									//确定后执行赋值,然后跳转页面
									flag     = false;
									planflag = false;
									Intent intent = new Intent(getActivity(),
											TimerActivity.class);
									System.out.println(todayplan.get(w).getEndTime()+"<<<<<<<<<<<<<<<<<<<<<");
									intent.putExtra("ss",isstudy.gotoss(new SimpleDateFormat("HH:mm:ss").format(todayplan.get(w).getEndTime()))
											- isstudy.gotoss(new SimpleDateFormat("HH:mm:ss").format(todayplan.get(w).getStartTime())));
									intent.putExtra("stu_id", student.getStuId());
									intent.putExtra("student",isstudy.stu_to_json(student));
									
									intent.putExtra("tag", 2);
									intent.putExtra("start_and_end_time", new SimpleDateFormat("HH:mm:ss").format(todayplan.get(w).getStartTime())+" ~ "+
											 new SimpleDateFormat("HH:mm:ss").format(todayplan.get(w).getEndTime())
											);
									intent.putExtra("text", todayplan.get(w).getPlanText())	;	
									
									flag = true;
									planflag=true;
									startActivity(intent);
								}
							}).setNegativeButton("取消", null).show();
							
						}else{
							new AlertDialog.Builder(getActivity()).setTitle("注意").setMessage("当天没有可执行的学习计划,是否添加").setNegativeButton("返回", null).setPositiveButton("添加", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
												gotoInsertplan();
								}
							}).show();
							flag     = true;
							planflag = true;
//							gotoInsertplan();
						}
						
					}
				});
	}
	
	/**
	 * 点击课程按钮获取信息
	 */
	
	
	Class_end classend ;
	int which_class;
	public void class_check()
	{
		flag = false;
		planflag=false;
		
		classend = new Class_end();
		
		which_class = classend.get_which_chass();
		System.out.println("``````````````1````````````````````");
		if(which_class!=0)
		{
			flag = false;
			planflag=false;
			System.out.println("``````````````2````````````````````");
			if(classend.what_time()==0)  //不迟到也不太早,可以上课
			{System.out.println("`````````````3`````````````````````");
				new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("即将进入课程"+ classend.get_class_name( which_class-1 )).setNegativeButton("返回", null).setPositiveButton("立即进入", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						ss = classend.getstu_time(classend.get_end_time(which_class-1)); //运行的时间
						Intent intent = new Intent(getActivity(),
								TimerActivity.class);
						intent.putExtra("ss", ss);System.out.println("```````````````4```````````````````");
						intent.putExtra("student", isstudy.stu_to_json(student));
						intent.putExtra("class_name", classend.get_class_name( which_class-1 ));
						intent.putExtra("tag", 1);System.out.println("```````````````5```````````````````");
						intent.putExtra("start_and_end_time",classend.get_start_time(which_class-1) + classend.get_end_time(which_class-1)  );
						intent.putExtra("text", "目前是第"+which_class+"节课");
						System.out.println("````````````````6``````````````````");
						flag = true;
						planflag=true;
						startActivity(intent);
					}
				}).show();
				System.out.println("``````````````````7````````````````");
				
			}else{
				System.out.println("`````````````8`````````````````````");
				if(classend.what_time()==1)
				{
					System.out.println("````````````````9``````````````````");
					new AlertDialog.Builder(getActivity())
					.setTitle("提示")
					.setMessage("还没到上课时间呢,提前请在5分钟以内")
					.setNegativeButton("返回", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							flag = true;
							planflag=true;
						}
					}).show();
					
				}else{
					flag = false;
					planflag=false;
					new AlertDialog.Builder(getActivity())
					.setTitle("提示")
					.setMessage("课程:"+classend.get_class_name( which_class-1 )+"   你迟到的太久了,不能进入计时积分页面")
					.setNegativeButton("返回", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							flag = true;
							planflag=true;
						}
					}).show();
					
					
				}
			}
			
		}else
		{
			System.out.println("````````````````14``````````````````");
			new AlertDialog.Builder(getActivity())
			.setTitle("提示")
			.setMessage("当前没有课程,是否查看学习计划")
			.setNegativeButton("返回", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					flag = true;System.out.println("````````````````15``````````````````");
					planflag=true;
				}
			}).setPositiveButton("查看", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					flag = true;System.out.println("```````````````16```````````````````");
					planflag=true;
					search_today_studyplan();
					
				}
			}).show();
				
			
			
			
			
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("即将进入课程"+ classend.get_class_name( which_class-1 )).setNegativeButton("返回", null).setPositiveButton("立即进入", new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//				ss = classend.getstu_time(classend.get_end_time(which_class-1)); //运行的时间
//				Intent intent = new Intent(getActivity(),
//						TimerActivity.class);
//				intent.putExtra("ss", ss);
//				intent.putExtra("student", isstudy.stu_to_json(student));
//				intent.putExtra("class_name", classend.get_class_name( which_class-1 ));
//				intent.putExtra("tag", 1);
//				intent.putExtra("start_and_end_time",classend.get_start_time(which_class-1) + classend.get_end_time(which_class-1)  );
//				intent.putExtra("text", "目前是第"+which_class+"节课");
//				
//				flag = true;
//				planflag=true;
//				startActivity(intent);
//			}
//		}).show();
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
}
