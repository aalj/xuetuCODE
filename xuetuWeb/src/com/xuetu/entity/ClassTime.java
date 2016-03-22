package com.xuetu.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * 课程表时间
 * @author asus
 *
 */
public class ClassTime {
//		private int [] class_num= {1,2,3,4,5,6,7,8,9,10,11,12};
		private String [][] class_time={
				
				{"8:00","8:55","9:55", "10:50","11:45","13:30","14:25","15:20","16:15","17:10","18:30","19:25"},
				{"8:45","9:40","10:40","11:35","12:50","14:15","15:10","16:05","17:00","17:55","19:15","20:10"},
				{"1","2","3","4","5","6","7","8","9","10","11","12"}};
//		private String [] minute={"8:00","8:55","9:55", "10:50","11:45","13:30","14:25","15:20","16:15","17:10","18:30","19:25"};
		
		/**
		 * 
		 * 今天礼拜几
		 * @return int week   今天礼拜几
		 */
		
		
		public int getWeek()
		{
			Calendar c = Calendar.getInstance();
			int week = c.get(Calendar.DAY_OF_WEEK);
			
			if(week<=7&week>1)
			{
				week-=1;
			}else
			{
				week=7;
			}
			return week;
		}
		
		
		public int getClassTime_SS(int cls_few)  //4
		{
			int ii;
			String ss;
			Calendar c = Calendar.getInstance();
			ss= class_time [1][cls_few-1] ;
			String [] time =ss.split(":".toString()); 
			int first  = Integer.parseInt(time[0]) ;
			int second = Integer.parseInt(time[1]) ;
			
			return (c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE)-(first*60+second))*60+c.get(Calendar.SECOND);
		}
        
		/**
		 * 得到现在应该上第几节课/并且是否超出时间
		 * @return
		 */
		public int getFwe()
		{
			Calendar c = Calendar.getInstance();
			int mm= c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE);// 当前0点到现在所用的分钟数
//			int j=0;
			int []minute=new int[12];
			for (int i=0;i<class_time[0].length;i++)
			{
//				j++;
//				System.out.println(i);
				String min = class_time[0][i];
				String [] min1 = min.split(":".toString());
				int first  = Integer.parseInt(min1[0]) ;
				int second = Integer.parseInt(min1[1]) ;
				minute[i]=first*60+second;      //课程的开始时间   8:00;转换成分   8*60    480分钟
			}
			
			int b=0;
			int classtime=0;
			for(int i=0;i<minute.length;i++)  //获得当前时间-课程时间<=5  或者>=-5
			{
				b++;
				
				System.out.println(minute[i]);
				
				if(mm-minute[i]>=-5 && mm-minute[i]<=10)
				{
					classtime++;
					b=i+1;
					System.out.println(i);
					System.out.println("bbbbbbbbbbbbbb"+b);
					System.out.println("classtime"+classtime);
					break;
				}
			}
			
			if(classtime==0)
			{
				b=0;
			}
			return b;
		}
}
