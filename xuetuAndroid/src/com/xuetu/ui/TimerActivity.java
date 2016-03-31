package com.xuetu.ui;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.R.menu;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
import com.xuetu.fragment.HomePageFrag;
import com.xuetu.utils.GetHttp;

import android.app.Activity;
import android.app.backup.FullBackupDataOutput;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



/**
 * ClassName:TimerActivity      计时页面(增加积分)
 * 			康毅
 * @author view
 * @version
 * @since Ver 1.1
 * @Date 2016年3月8日 下午7:30:00
 * 
 * 
 */

public class TimerActivity extends Activity {
	
	@ViewInject(R.id.tv_showtime)
	TextView tv_showtime;
	
	@ViewInject(R.id.tv_show_ss)
	TextView tv_show_ss;
	
//	private String studyTime=tv_showtime.getText().toString();
//	private Button startTime;
	private TextView showTime;
	private TextView showss;
	private Button runTime;
	private Handler mHandler = new Handler();
	private String hh;
	private String mm;
	private int acpo_num = 5;
	private String st_date = null;
	private int st_id = 0;
	private TextView tv_text;
	private TextView tv_selectedPlan;
	private TextView tv_pointText;
	private TextView tv_pointSum;
	private TextView tv_textPlan;
	String class_name;
//	boolean flag = true;
//	boolean planflag=true;
//	//这两个判断 实在改页面结束时返回给home_page_fragment 的值,以确保首页可以进行滑动判定
//	
//	
	int tag = 0;
	String start_and_end_time = null;
	String text = null;
	
	//用来显示在TextView上面的时间,同时记录总的学习时间(秒)
	int second=0;
	//alltime 从服务器上获取学生对象这节课/学习计划的总的学习时间
	long alltime = 10;
	//获取这节课/学习计划的学习时间
	int st_time = 0;
	
	int stu_id = 0;
	//每过十分钟,积分倍数+1
	int integral_double=0;
	//十分钟循环体
	int round = 0;
	Student student ;
	String stu_from_home;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_timer);
		
		Intent intent = getIntent();
		alltime = intent.getLongExtra("ss", 0);
		stu_id = intent.getIntExtra("stu_id", 0);
		stu_from_home = intent.getStringExtra("student");
		Type type = new TypeToken<Student>() {}.getType();
		Gson gson = new GsonBuilder().setDateFormat(
				"yyyy-MM-dd HH:mm:ss").create();
		student = gson.fromJson(stu_from_home, type);
		
		showTime=(TextView) findViewById(R.id.tv_showtime);
//		runTime= (Button) findViewById(R.id.home_btn_up);
		showss=(TextView) findViewById(R.id.tv_show_ss);
		tv_text=(TextView) findViewById(R.id.tv_text);
		tv_selectedPlan=(TextView) findViewById(R.id.tv_selectedPlan);
		tv_pointSum=(TextView) findViewById(R.id.tv_pointSum);
		tv_textPlan = (TextView) findViewById(R.id.tv_textPlan);
		Intent intentGet = getIntent();
		tag = intentGet.getIntExtra("tag", 0);
		start_and_end_time = intentGet.getStringExtra("start_and_end_time");	//tv_selectedPlan
		
		if(tag==1){
			tv_text.setText("课程任务");
			tv_textPlan.setText(intentGet.getStringExtra("class_name"));
			tv_selectedPlan.setText("课程时间"+start_and_end_time);
		}if(tag==2){
			tv_text.setText("自学计划");
			tv_selectedPlan.setText("原计划"+start_and_end_time);
			tv_textPlan.setText(intentGet.getStringExtra("text"));
		}	
		
		//tv_text
		
//		Drawable drawable = getResources().getDrawable(R.drawable.spinner_checked);
//		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
//		tv_text.setCompoundDrawables(null, null, drawable, null);
		student= ((XueTuApplication) getApplication()).getStudent();

		//功能还没有完善,需要添加判断条件,从数据库获得课程表时间,与当前时间\课程进行判断,并且判断经纬度是否相同
		Thread th =  new Thread(new ClassCut());//创建一个新线程,并且赋值给一个变量
		th.start(); //运行这个线程执行>>>计时器
		}
	
    class ClassCut implements Runnable{//倒计时逻辑子线程
        @Override
        public void run() {
        	
            // TODO Auto-generated method stub
	        	while(alltime>0)//当循环达到10分钟的时候,执行该语句
	            {
	            	alltime--;//学习总时间 逐渐减少
	        		round++;//10分钟循环变量
	        		st_time++;//记录学生学习时间
	        		System.out.println("round="+round);
	                if(round==10){
	                	integral_double++;//每到600秒,积分倍数+1(初始值0)
	                	Log.i("hehe",getIntegral(integral_double)+"");
	                	//  显示已获得积分，10秒刷新一次
	                	
	                	mHandler.post(new Runnable() {//通过它在UI主线程中修改显示的剩余时间
	                    @Override
	                    public void run() {
	                        // TODO Auto-generated method stub
	                    	tv_pointSum.setText("累积获得积分:"+getIntegral(integral_double));
	                    	showTime.setText(secondFormat(st_time));//显示 00:00
	                    	showss.setText(ssFormat(st_time)); //显示00 秒
	                    	round=0;//初始时间赋值0;重新开始计时
	                    }
	                });
	                try {
	                    Thread.sleep(1000);//线程休眠一秒钟     这个就是倒计时的间隔时间
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                }else
	                {
	                	 mHandler.post(new Runnable() {//通过它在UI主线程中修改显示的剩余时间
		                        @Override
		                        public void run() {
		                            // TODO Auto-generated method stub
		                        	showTime.setText(secondFormat(st_time));
		                        	showss.setText(ssFormat(st_time));//显示剩余时间
		                        }
		                    });
		                    try {
		                        Thread.sleep(1000);//线程休眠一秒钟     这个就是倒计时的间隔时间
		                    } catch (InterruptedException e) {
		                        e.printStackTrace();
		                    }
	                }
	                if(alltime==0){
  	                	 //下面是计时结束逻辑
  	                    mHandler.post(new Runnable() {
  	                    	
  	                        @Override
  	                        public void run() {
  	                        	endTime();
  	                        }
  	                    });
  	                }
	                
            }
           
           
        }
    }
    
    /**
     * 输出格式显示方法 00:00
     * 
     * @return String
     */
    public  String secondFormat(int second)
    {
    	  hh=second/3600>9?second/3600+"":"0"+second/3600;
          mm=(second % 3600)/60>9?(second % 3600)/60+"":"0"+(second % 3600)/60;
         return hh+":"+mm;
    }

    /**
     * 输出格式显示方法 00(秒)
     * 	
     * @return String
     */
    public  String ssFormat(int second)
    {
    	String ss=(second % 3600) % 60>9?(second % 3600) % 60+"":"0"+(second % 3600) % 60;
         return ss;
    }
    
    
    class SaveTimeAndIntegral {
    	
    	public void saveStudyTime(int st_time,int integral_double,String stu_from_home)
    	{
    		String url =GetHttp.getHttpKY()+"AddStudyTime";
    		
    		HttpUtils httpUtils = new HttpUtils();
    		RequestParams requestParams = new RequestParams();
    		requestParams.addBodyParameter("st_time", String.valueOf(st_time));
    		requestParams.addBodyParameter("integral",String.valueOf(getIntegral(integral_double)));
    		requestParams.addBodyParameter("st_date", getTime());
    		requestParams.addBodyParameter("st_id", "1");
    		requestParams.addBodyParameter("stu_id", stu_id+"");//学生id
    		requestParams.addBodyParameter("student", stu_from_home);//学生id
//    		System.out.println(stu_from_home);
    		System.out.println("------------1----------"+stu_id);
    		httpUtils.send(HttpMethod.POST, url,requestParams,new RequestCallBack<String>() {
    			
    			@Override
    			public void onFailure(HttpException arg0, String arg1) {
    				// TODO Auto-generated method stub
    				System.out.println("链接失败");
    			}

    			@Override
    			public void onSuccess(ResponseInfo<String> arg0) {
    				// TODO Auto-generated method stub
    				System.out.println("链接成功");
    			}
    		} );
    	}
    }
    
    /**
     * 获取当前时间的String类型 
     * yyyy-MM-dd
     * @return String 
     */
    public static String getTime()
    {
    	String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE) .format(Calendar.getInstance().getTime());
//    	System.out.println(time+"");
    	return time;
    }
    
    /**
     * 获取当前总积分
     * 
     * @return String 
     */
    public static int getIntegral(int integral_double)
    {
    	int itg=5 * integral_double;
    	return itg ;
    }
    
    
    /**
     * 计时页面结束时执行的语句
     */
    public  void endTime()
    {
            // TODO Auto-generated method stub
           	showTime.setText("00:00");//计时器结束,把时分秒的值归零
           	showss.setText("00");
           	if(alltime!=0)
           	{
               	new SaveTimeAndIntegral().saveStudyTime(st_time, integral_double,stu_from_home);
                Toast.makeText(TimerActivity.this, "下课啦"+getIntegral(integral_double)+"积分到手咯!!", Toast.LENGTH_LONG).show();//提示倒计时完成

           	}
            alltime = 0;//修改倒计时剩余时间变量为0秒
            integral_double=0;
            st_time=0;
			finish();
    }
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	endTime();
    	
    	super.onBackPressed();
    }
}
