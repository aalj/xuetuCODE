package com.xuetu.ui;

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

import android.app.Activity;
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
 * 			kangyi
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
	
//	private String studyTime=tv_showtime.getText().toString();
//	private Button startTime;
	private TextView showTime;
	private Button runTime;
	private Handler mHandler = new Handler();
	private String hh;
	private String mm;
	private String acpo_num = "5";
	private String st_time = "5000";
	private String stu_id = "003";
	private String st_date = null;
	private String st_id = null;
	
	//循环时间 10分钟一循环
	int second=0;
	//alltime 记录下运行的所有时间
	int  alltime = 0;
	
	
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_timer);
		
		showTime=(TextView) findViewById(R.id.tv_showtime);
		runTime=(Button) findViewById(R.id.btn_start_runtime);
		
		
	}

	
	private class TimeOnclisten implements OnClickListener{
        @Override
        public void onClick(View v) {
            new Thread(new ClassCut()).start();//开启倒计时
        }
    }
    class ClassCut implements Runnable{//倒计时逻辑子线程
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(second<600){//整个倒计时执行的循环
            	second++;
            	alltime++;
                if(second==600){
                mHandler.post(new Runnable() {//通过它在UI主线程中修改显示的剩余时间
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                    	showTime.setText(secondFormat(alltime));
                        second=0;//初始时间赋值0;重新开始计时
                        
                        //////////想服务器发送数据的方法/////////////

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
	                        	showTime.setText(secondFormat(alltime));//显示剩余时间
	                        }
	                    });
	                    try {
	                        Thread.sleep(1000);//线程休眠一秒钟     这个就是倒计时的间隔时间
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
                }
            }
            //下面是倒计时结束逻辑
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                	showTime.setText("0");//一轮倒计时结束  修改剩余时间为一分钟
                    Toast.makeText(TimerActivity.this, "倒计时完成", Toast.LENGTH_LONG).show();//提示倒计时完成
                }
            });
            second = 0;//修改倒计时剩余时间变量为0秒
        }
    }
    
    /*
     * 输出格式显示方法 00:00
     * 
     * 
     */
    public  String secondFormat(int second)
    {
    	  hh=second/3600>9?second/3600+"":"0"+second/3600;
          mm=(second % 3600)/60>9?(second % 3600)/60+"":"0"+(second % 3600)/60;
         return hh+":"+mm;
    }
    
    class SaveTimeAndIntegral {
    	
    	
    	
    	
    	
    	
    	
    	
    	public void saveStudyTime()
    	{
    		String url = null;
    		String integral="5";
    		HttpUtils httpUtils = new HttpUtils();
    		RequestParams requestParams = new RequestParams();
//    		String time = ;
    		requestParams.addBodyParameter("time", alltime+"");
    		requestParams.addBodyParameter("integral", acpo_num);
    		httpUtils.send(HttpMethod.POST, url,new RequestCallBack<String>() {

    			@Override
    			public void onFailure(HttpException arg0, String arg1) {
    				// TODO Auto-generated method stub
    				
    			}

    			@Override
    			public void onSuccess(ResponseInfo<String> arg0) {
    				// TODO Auto-generated method stub
    				
    				
    				
    			}
    		} );
    		
    	}
    	
    	
    }

}
