package com.xuetu.entity;

import java.util.Calendar;

public class IsStudy {
	
	private String [][] class_time={
			{"8:00","8:55","9:55", "10:50","11:45","13:30","14:25","15:20","16:15","17:10","18:30","19:25"},
			{"8:45","9:40","10:40","11:35","12:50","14:15","15:10","16:05","17:00","17:55","19:15","20:10"},
			{"1","2","3","4","5","6","7","8","9","10","11","12"}};
	
	/**
	 * 判断当前是否有课  条件,提前5分钟,并且迟到不超过10分钟
	 * @return
	 */
	public int startStydy()
	{
		boolean boo=false;;
		Calendar c = Calendar.getInstance();
		int mm= c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE);// 当前0点到现在所用的分钟数
//		int j=0;
		int []minute=new int[12];
		for (int i=0;i<class_time[0].length;i++)
		{
//			j++;
//			System.out.println(i);
			String min = class_time[0][i];
			String [] min1 = min.split(":".toString());
			int first  = Integer.parseInt(min1[0]) ;
			int second = Integer.parseInt(min1[1]) ;
			minute[i]=first*60+second;      //课程的开始时间   8:00;转换成分   8*60    480分钟
		}
		
		int b=0;
		int classtime=0;
		for(int i=0;i<minute.length;i++)  //获得当前时间-课程时间<=5  或者>=-10
		{
			b++;
			
			System.out.println(minute[i]);
			
			if(mm-minute[i]>=-5 && mm-minute[i]<=10)
			{
				classtime++;
				b=i+1;
				break;
			}
		}
		
		if(classtime==0)
		{
			b=0;
		}
		
		return b;
	}
	
	/**
	 * 上课时间时间是没到,还是迟到了10分钟.
	 */
	
	public String say(int b)
	{
		
		
		return "ppp";
	}
	
}
