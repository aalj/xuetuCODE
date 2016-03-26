package com.xuetu.ui;

import java.util.Calendar;
import java.util.List;

import com.xuetu.R;
import com.xuetu.R.id;
import com.xuetu.R.layout;
import com.xuetu.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

public class LearingRecordActivity extends Activity {

	CalendarView calendarView;
	List<TextView> tv_list ;
	Calendar c ;
	int today_num;
	int day_of_week_in_month;
	int day_of_week;
	int today_day;
	int start_day;
	TextView tv_rili;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learing_record);
		
		TextView []  Rili = {
	        	
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
		
		
		c  = Calendar.getInstance();
        today_num = c.getActualMaximum(Calendar.DATE);	//这个月的最大天数
        day_of_week_in_month = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);	//今天是这个月的第几个礼拜
        day_of_week = c.get(Calendar.DAY_OF_WEEK); // 今天是这个礼拜的
        today_day=c.get(Calendar.DAY_OF_MONTH); // 今天几号
		
		tv_rili  = (TextView) findViewById(R.id.tv_qiandaorili);
		tv_rili.setText(getdate());
        
        
        
		getRili(Rili);
		
	}
	
	
	
	
	public void getRili( TextView [] Rili)
	{

        start_day = ((day_of_week_in_month-1)*7+day_of_week)-today_day;          //就从XXXX[start_day] 开始settext
        int add_day_ = start_day;
        int qiandaori [] ={1,2,3,4,5,6,9,12,10,11,13,14,15,16,26};
        
        for(int i=1;i<=today_num;i++)  // 这个月所有天数的数值         31天   循环这么多天
        {
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
	
	public String getdate()
	{
		String s  =null;
		s = "签到日历 "+c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日";
		return s;
	}
}
