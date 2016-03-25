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
import com.xuetu.entity.IsStudy;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
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
	Student student ;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//先获取Student对象
		
		XueTuApplication xuetu = (XueTuApplication) getActivity().getApplication();
		student = xuetu.getStudent();
		stu_id=student.getStuId();
		
		view = inflater.inflate(R.layout.home_page_frag, null);

//		view.findViewById(R.id.home_btn_up).setOnClickListener(
//				new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//						Intent intent = new Intent(getActivity(),
//								TimerActivity.class);
//						startActivity(intent);
//					}
//				});
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
		// Rect rect = new Rect();
		// System.out.println(ii);
		// getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

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

		return view;

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
				ss=(Long)msg.obj;
				if (ss>0) {
					Intent intent = new Intent(getActivity(),
							TimerActivity.class);
					
					intent.putExtra("ss", ss);
					intent.putExtra("student", isstudy.stu_to_json(student));
//					Gson  gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//					String st = gson.toJson(student);
//					intent.putExtra("student", st);
					
					flag = true;
					planflag=true;
					startActivity(intent);
				}else
				{
					
					Toast.makeText(getActivity(), "当前没有课程", Toast.LENGTH_SHORT).show();
//		            Toast.makeText(TimerActivity.this, "下课啦"+getIntegral(integral_double)+"积分到手咯!!", Toast.LENGTH_LONG).show();//提示倒计时完成
					flag     = true;
					planflag = true;
					
					//获取课程失败后  执行下滑判断语句
					
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

			move(v);

			RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v
					.getLayoutParams();

			// _x = X - lParams.leftMargin;
			_y = Y - lParams.topMargin;
			break;

		case MotionEvent.ACTION_UP:

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
			if(stu_id!=0)
			{
						if (Y < (fHeight / 2 - 150) && flag == true) 
							{
								flag     = false;
								planflag = false;
								System.out.println("进行的是上滑动");
								get_stu_studyTime();
								
							} 
			
						if(Y > (fHeight / 2 + 250) && planflag==true)
							{
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
		System.out.println("```````````11`````stu_id```````````"+stu_id);
		//json 解析Student对象,并传给服务器
		Gson  gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		System.out.println("````````````22```````````````");
		String st = gson.toJson(student);
		System.out.println("````````````33```````````````");
		requestParams.addBodyParameter("student", st);
		System.out.println("````````````44```````````````");
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
						ss = Long.parseLong(s);
						System.out.println("ssssssssssss" + ss);
						
						Message message = Message.obtain();
						message.what=456;
						message.obj=ss;
						handler.sendMessage(message);
					}
				});
	}
	
	/**
	 * 下滑判定,获取自学计划时间
	 */
	public void getStudyPlan()
	{
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
									System.out.println("距离学习计划时间还有一个小时以上,是否执行学习");
									new AlertDialog.Builder(getActivity())
										.setTitle("提示")
										.setMessage("离学习计划还有1个小时以上,是否提前学习")
										.setNegativeButton("取消", new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												flag     = true;
												planflag = true;
											}
										})
										.setPositiveButton("确定", new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												System.out.println("开始执行计划时间");
												flag     = false;
												planflag = false;
												Intent intent = new Intent(getActivity(),
														TimerActivity.class);
												intent.putExtra("ss", ss);
												intent.putExtra("stu_id", stu_id);
												intent.putExtra("student", isstudy.stu_to_json(student));
												flag = true;
												planflag=true;
												startActivity(intent);
											}
										}).show();
									
								}else              //不大于一个小时执行这个语句
								{
									if(p>=0&&p<=3600)   //   if true  执行设定的学习时间    if false  执行剩余的时间
									{
										flag     = false;
										planflag = false;
										System.out.println("开始执行计划时间");
										Intent intent = new Intent(getActivity(),
												TimerActivity.class);
										intent.putExtra("ss", ss);
										intent.putExtra("stu_id", stu_id);
										intent.putExtra("student", isstudy.stu_to_json(student));
										flag = true;
										planflag=true;
										startActivity(intent);
										
									}else         //这里的p肯定为负数
									{
										flag     = false;
										planflag = false;
										System.out.println("开始执行剩余的计划时间");
										Intent intent = new Intent(getActivity(),
												TimerActivity.class);
										intent.putExtra("ss", ss+p);
										intent.putExtra("stu_id", stu_id);
										intent.putExtra("student", isstudy.stu_to_json(student));
										flag = true;
										planflag=true;
										startActivity(intent);
									}
									
									
									
								}
								
							}else{						//表明今天没有学习计划
								Toast.makeText(getActivity(), "今天没有计划", Toast.LENGTH_SHORT).show();
								flag     = true;
								planflag = true;
							}
						}
						else{
							Toast.makeText(getActivity(), "今天没有计划", Toast.LENGTH_SHORT).show();
							flag     = true;
							planflag = true;
						}
						
						
					}
				});
	}
	
	
	
	
	

}
