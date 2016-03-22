package com.xuetu.ui;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
import com.xuetu.entity.Student;

import android.app.Activity;
import android.app.backup.FullBackupDataOutput;
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
	
	
	

	
	//用来显示在TextView上面的时间,同时记录总的学习时间(秒)
	int second=0;
	//alltime 从服务器上获取学生对象这节课/学习计划的总的学习时间
	int alltime = 10;
	//获取这节课/学习计划的学习时间
	int st_time = 0;
	
	int stu_id = 3;
	//每过十分钟,积分倍数+1
	int integral_double=0;
	//十分钟循环体
	int round = 0;
	Student student ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_timer);
		
		showTime=(TextView) findViewById(R.id.tv_showtime);
//		runTime= (Button) findViewById(R.id.home_btn_up);
		showss=(TextView) findViewById(R.id.tv_show_ss);
		
		student= ((XueTuApplication) getApplication()).getStudent();

		//功能还没有完善,需要添加判断条件,从数据库获得课程表时间,与当前时间\课程进行判断,并且判断经纬度是否相同
		Thread th =  new Thread(new ClassCut());//创建一个新线程,并且赋值给一个变量
		th.start(); //运行这个线程执行>>>计时器
		}
//		new TimeOnclisten();
//		findViewById(R.id.home_btn_up).setOnClickListener(new TimeOnclisten());
	
//	private class TimeOnclisten implements OnClickListener{
//        @Override
//        public void onClick(View v) {
//            new Thread(new ClassCut()).start();//开启倒计时
//        }
//    }
	
    class ClassCut implements Runnable{//倒计时逻辑子线程
        @Override
        public void run() {
            // TODO Auto-generated method stub
	        	while(alltime>0)//当循环达到10分钟的时候,执行该语句
	            {
	            	alltime--;//学习总时间 逐渐减少
	        		round++;//10分钟循环变量
	        		st_time++;//记录学生学习时间
	                if(round==10){
	                	integral_double++;//每到600秒,积分倍数+1(初始值0)
//	                	System.out.println("alltime>>>>>"+alltime);
//	                	System.out.println("round>>>>>"+round);
//	                	System.out.println("integral_double>>>>>"+integral_double);
//	                	System.out.println("st_time>>>>>"+st_time);
	                	mHandler.post(new Runnable() {//通过它在UI主线程中修改显示的剩余时间
	                    @Override
	                    public void run() {
	                        // TODO Auto-generated method stub
	                    	showTime.setText(secondFormat(st_time));//显示 00:00
	                    	showss.setText(ssFormat(st_time)); //显示00 秒
	                    	round=0;//初始时间赋值0;重新开始计时
	                        
	                    	if(alltime==0){
	   	                	 //下面是倒计时结束逻辑
	   	                    mHandler.post(new Runnable() {
	   	                    	
	   	                        @Override
	   	                        public void run() {
	   	                            // TODO Auto-generated method stub
	   	                        	showTime.setText("00:00");//计时器结束,把时分秒的值归零
	   	                        	showss.setText("00");
	   	                        	new SaveTimeAndIntegral().saveStudyTime(st_time, integral_double);
	   	                            Toast.makeText(TimerActivity.this, "下课啦"+getIntegral(integral_double)+"积分到手咯!!", Toast.LENGTH_LONG).show();//提示倒计时完成
//	   	                            System.out.println("alltime>>>>>"+alltime);
//	   	                        	System.out.println("round>>>>>"+round);
//	   	                        	System.out.println("integral_double>>>>>"+integral_double);
//	   	                        	System.out.println("st_time>>>>>"+st_time);
	   	                        }
	   	                    });
	   	                }
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
	                
	                
            }
           
            alltime = 0;//修改倒计时剩余时间变量为0秒
            integral_double=0;
            st_time=0;
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
    	
    	public void saveStudyTime(int st_time,int integral_double)
    	{
    		String url ="http://10.201.1.26:8080/xuetuWeb/AddStudyTime";
    		
    		HttpUtils httpUtils = new HttpUtils();
    		RequestParams requestParams = new RequestParams();
    		requestParams.addBodyParameter("st_time", String.valueOf(st_time));
    		requestParams.addBodyParameter("integral",String.valueOf(getIntegral(integral_double)));
    		requestParams.addBodyParameter("st_date", getTime());
    		requestParams.addBodyParameter("st_id", "1");
//    		requestParams.addBodyParameter("stu_id", student.getStuId()+"");//学生id
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
    

}
