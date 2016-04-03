package com.xuetu.ui;

import java.lang.reflect.Type;
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
import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.R.menu;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
import com.xuetu.fragment.HomePageFrag;
import com.xuetu.utils.GetHttp;

import android.R.integer;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class LearingRecordActivity extends Activity {

	private CalendarView calendarView;
	private List<TextView> tv_list ;
	private Calendar c ;
	private int today_num;
	private int week_of_month;
	private int day_of_week;
	private int today_day;
	private int start_day;
	private TextView tv_rili;
	private SharedPreferences pref ;
	private Editor editor;
	private int add_day_;
	private TextView []Rili ;
	private Button btn_qiandao;
	Student student ;
	TextView tv_today_study;
	TextView tv_today_paiming;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learing_record);
		
		XueTuApplication xuetu = (XueTuApplication)getApplication();
		student = xuetu.getStudent();
		System.out.println(">>>>>>>>>  stu_id  >>>>"+student.getStuId());
		getqiandao_message();
		tv_today_study = (TextView) findViewById(R.id.TextView02);
		tv_today_paiming = (TextView) findViewById(R.id.tv_jibaixueba);
		
		Rili = new TextView[] {
	        	
//	    		第一个星期
	    	    (TextView) findViewById(R.id.week_one_1),
	            (TextView) findViewById(R.id.week_one_2),
	            (TextView) findViewById(R.id.week_one_3),
	            (TextView) findViewById(R.id.week_one_4),
	            (TextView) findViewById(R.id.week_one_5),
	            (TextView) findViewById(R.id.week_one_6),
	            (TextView) findViewById(R.id.week_one_7),
	            //第二个星期
	            (TextView) findViewById(R.id.week_two_1),
	            (TextView) findViewById(R.id.week_two_2),
	            (TextView) findViewById(R.id.week_two_3),
	            (TextView) findViewById(R.id.week_two_4),
	            (TextView) findViewById(R.id.week_two_5),
	            (TextView) findViewById(R.id.week_two_6),
	            (TextView) findViewById(R.id.week_two_7),
	            //第三个星期
	            (TextView) findViewById(R.id.week_three_1),
	            (TextView) findViewById(R.id.week_three_2),
	            (TextView) findViewById(R.id.week_three_3),
	            (TextView) findViewById(R.id.week_three_4),
	            (TextView) findViewById(R.id.week_three_5),
	            (TextView) findViewById(R.id.week_three_6),
	            (TextView) findViewById(R.id.week_three_7),
	            //第四个星期
	            (TextView) findViewById(R.id.week_four_1),
	            (TextView) findViewById(R.id.week_four_2),
	            (TextView) findViewById(R.id.week_four_3),
	            (TextView) findViewById(R.id.week_four_4),
	            (TextView) findViewById(R.id.week_four_5),
	            (TextView) findViewById(R.id.week_four_6),
	            (TextView) findViewById(R.id.week_four_7),
	            //第五个星期
	            (TextView) findViewById(R.id.week_five_1),
	            (TextView) findViewById(R.id.week_five_2),
	            (TextView) findViewById(R.id.week_five_3),
	            (TextView) findViewById(R.id.week_five_4),
	            (TextView) findViewById(R.id.week_five_5),
	            (TextView) findViewById(R.id.week_five_6),
	            (TextView) findViewById(R.id.week_five_7)
	    };
		
		pref = getSharedPreferences("qiandao", MODE_PRIVATE);//  私有的文件   用到存放签到日期
		editor = pref.edit();
		btn_qiandao = (Button) findViewById(R.id.qiandao_btn);
		
		
		
		
		c  = Calendar.getInstance();
        today_num = c.getActualMaximum(Calendar.DATE);	//这个月的最大天数
     
        week_of_month = c.get(Calendar.WEEK_OF_MONTH);	//今天是这个月的第几个礼拜
        day_of_week = c.get(Calendar.DAY_OF_WEEK); // 今天是这个礼拜的
        today_day=c.get(Calendar.DAY_OF_MONTH); // 今天几号
		
		tv_rili  = (TextView) findViewById(R.id.tv_qiandaorili);
		tv_rili.setText(getdate());
        
		   System.out.println("这个月的最大天数"+today_num+"今天是这个月的第几个礼拜"+week_of_month+"今天是这个礼拜的第几天"+day_of_week+"几号"+today_day);
        
		   getqiandao_message();
		
		   
		   
		if(qiandao_ed()==true)
		{
			Rili[today_day+add_day_-1].setBackgroundResource(R.drawable.background);
			
			btn_qiandao.setText("已签到");
			btn_qiandao.setTextColor(0xffffffff);
			
		}
		
		get_today_study_time();//获得今日学习的时间
		get_paiming(); //获得排名
		
	}//end```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````
	
	
	/**
	 * 签到日历显示
	 * @param Rili
	 */
	
	public void getRili( TextView [] Rili,int qiandaori[])
	{

        start_day = ((week_of_month-1)*7+day_of_week)-today_day;          //就从XXXX[start_day] 开始settext
//		start_day=7-((today_day - day_of_week)%7);
        add_day_ = start_day;
      
        
        for(int i=1;i<=today_num;i++)  // 这个月所有天数的数值         31天   循环这么多天
        {
        	System.out.println(start_day);
        	Rili[start_day].setBackgroundResource(R.drawable.background_nothing);  //先给每个textview增加背景,保证尺寸
        	Rili[start_day].setText(i+"");																		//向textview  添加日期  
        	 for(int j=0;j<qiandaori.length;j++)   														//遍历所有从服务器上 获取的签到日期
        	 {
		        	if(i==qiandaori[j])      //签到日期加背景
		        	{
		        		Rili[start_day].setBackgroundResource(R.drawable.background);
		        		Rili[start_day].setTextColor(0xff878787);
		        	}
        	 }
        	start_day++;
        }
        Rili[today_day+add_day_-1].setTextColor(0xffd54643);
	}
	/**
	 * 签到日历标题显示
	 * @return
	 */
	public String getdate()
	{
		String s  =null;
		s = "签到日历 "+c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日";
		return s;
	}
	
	
	/**
	 *   需要签到的时候, 点击事件所需要用的方法
	 */
	public void needqiandao( TextView[]   Rili)
	{
		editor.remove("签到");
		editor.commit();
		editor.putInt("签到", c.get(Calendar.DAY_OF_YEAR));
		editor.commit();
		Rili[today_day+add_day_-1].setBackgroundResource(R.drawable.background);
		btn_qiandao.setText("已签到");
		btn_qiandao.setTextColor(0xffffffff);
		
	}
	
	
	/**
	 * 判断签到按钮 是需要签到状态还是 不需要签到状态
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
	
	public void onclick(View v)
	{
		if(qiandao_ed()!=true)
		{
			needqiandao(Rili);//执行签到动画,加背景
			//往数据库发送签到日期信息
			String url = GetHttp.getHttpKY()+"SendQiandaoDate";
			HttpUtils httpUtils = new HttpUtils();
			RequestParams requestParams = new RequestParams();
			requestParams.addBodyParameter("stu_id", String.valueOf(student.getStuId()));
			httpUtils.send(HttpMethod.POST, url, requestParams,null);
		}
	}
	
	
	/**
	 * 初始化签到功能
	 */
	public void getqiandao_message()
	{
		String url = GetHttp.getHttpKY()+"GetQianDaoMessage";
		HttpUtils httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("stu_id", String.valueOf(student.getStuId()));
		httpUtils.send(HttpMethod.POST, url, requestParams,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("连接失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				System.out.println("连接成功过");
				
				String date = arg0.result;
				Type type = new TypeToken<List<String>>(){}.getType();
				Gson gson = new GsonBuilder().setDateFormat("yy-mm-dd").create();
				List<String> date1 = new ArrayList<>();
				date1=gson.fromJson(date, type);
				System.out.println(date1);
				if(!date1.equals("[]"))
				{
					int [] dd = new int [date1.size()];
					for(int i=0;i<date1.size();i++)
					{
						dd[i] = Integer.parseInt(date1.get(i));
						if(dd[i]==c.get(Calendar.DAY_OF_MONTH))
						{
							editor.remove("签到");
							editor.commit();
							editor.putInt("签到", c.get(Calendar.DAY_OF_YEAR));
							editor.commit();
							btn_qiandao.setText("已签到");
							btn_qiandao.setTextColor(0xffffffff);
						}
					}
					getRili( Rili , dd );
				}
			}
		});
	}
	
	/**
	 * 进入学习记录
	 */
	public void get_today_study_time()
	{
		String url = GetHttp.getHttpKY()+"GetTodayStudyTime";
		HttpUtils httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("stu_id", String.valueOf(student.getStuId()));
		httpUtils.send(HttpMethod.POST, url, requestParams,new RequestCallBack<String>() {
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("连接失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				System.out.println("连接成功");
				String date = arg0.result;
				Type type = new TypeToken<Long>(){}.getType();
				Gson gson = new GsonBuilder().create();
				long  dodayss = 0;
				dodayss=gson.fromJson(date, type);
				long mm =0;
				if(dodayss<60)
				{
					tv_today_study.setText("不给力啊");
				}else{
					if(dodayss>60&&dodayss<3600)
					{
						tv_today_study.setText(dodayss/60+"分钟");
					}else{
						tv_today_study.setText(secondFormat(dodayss));
					}
				}
			}
		});
	}
	
	/**
	 * 获得排名
	 * 
	 *
	 */
	public void get_paiming()
	{
		String url = GetHttp.getHttpKY()+"GetPaiMing";
		HttpUtils httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addBodyParameter("stu_id", String.valueOf(student.getStuId()));
		httpUtils.send(HttpMethod.POST, url, requestParams,new RequestCallBack<String>() {
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("连接失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				System.out.println("连接成功");
				String date = arg0.result;
				Type type = new TypeToken<String>(){}.getType();
				Gson gson = new GsonBuilder().create();
				String  s = null;
				s=gson.fromJson(date, type);
				tv_today_paiming.setText(s);
				
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public  String secondFormat(long  second)
	    {
		 	String hh=null;
		 	String mm=null;
	    	  hh=second/3600>9?second/3600+"":"0"+second/3600;
	          mm=(second % 3600)/60>9?(second % 3600)/60+"":"0"+(second % 3600)/60;
	         return hh+"小时"+mm+"分钟";
	    }
	
}
