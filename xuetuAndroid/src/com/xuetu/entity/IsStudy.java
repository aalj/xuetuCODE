
package com.xuetu.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IsStudy {
	
	private String [][] class_time={
			{"8:00","8:55","9:55", "10:50","11:45","13:30","14:25","15:20","16:15","17:10","18:30","19:25"},
			{"8:45","9:40","10:40","11:35","12:50","14:15","15:10","16:05","17:00","17:55","19:15","20:10"},
			{"1","2","3","4","5","6","7","8","9","10","11","12"  }  }  ;
	
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
	 * 判断今天有没有学习计划
	 */
	
	public boolean getStudyPlan(SelfStudyPlan studyplan)
	{
		boolean b = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//数据库的开始时间转换成String
		String time = sdf.format(studyplan.getStartTime());
		
		//得到现在的时间,并转换成String
        Date d = new Date();  
        System.out.println(d);  
        String nowtime = sdf.format(d);  
        System.out.println("当前日期：" + nowtime);  
        System.out.println("``````time``````"+time);
        System.out.println("```````nowtime`````"+nowtime);
        if(time.equals(nowtime))
        {
        	System.out.println("是当天");
        	//表名今天有学习计划,并开始学习,学习时间为计划学习的时间
        	b = true;
        }
		return  b;
	}
	
	
	public long studyplanTime(SelfStudyPlan studyplan)
	{
		SimpleDateFormat hms = new SimpleDateFormat("HH-mm-ss");
		
		String starttime  = null;  //开始时间的字符串类型("HH-mm-ss")
		String endtime    = null;  //结束时间
		long studyttime = 0;       //学习时间  秒计算
		
		//得到"HH-mm-ss"  String的学习时间
		starttime = hms.format(studyplan.getStartTime());
		endtime   = hms.format(studyplan.getEndTime());
		
		//将上面的starttime转换成时间,秒...
		String [] time_s = starttime.split("-".toString());
		long first  =Long.parseLong(time_s[0])*60*60;
		long second =Long.parseLong(time_s[1])*60;
		long third =Long.parseLong(time_s[2]);
		//将上面的endtime转换成时间,秒...
		
		
		String [] time_e = endtime.split("-".toString());
		long first_e  =Long.parseLong(time_e[0])*60*60;
		long second_e =Long.parseLong(time_e[1])*60;
		long third_e =Long.parseLong(time_e[2]);
		
		
		studyttime = (first_e+second_e+third_e)-(first+second+third);
		return studyttime;
	}
	
	
	
	
	
	/**
	 * 判断现在是否到了学习计划时间,一个小时内,
	 * 超前一个小时进行提醒,不超过自动进行计时
	 */
	public long time_to_study(SelfStudyPlan studyplan)
	{
		System.out.println("判断现在是否到了学习计划时间,一个小时内,* 超前一个小时进行提醒,不超过自动进行计时");
		long ss = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String hhmmss = sdf.format(studyplan.getStartTime()); 
		ss=gotoss(hhmmss);
		System.out.println("-------开始时间的ss---------"+ss);
		return ss;
	}
	
	
	
	/**
	 * 从0点到现在的秒数
	 */
	public long zero_to_now_ss()
	{
		long ss = 0;
		Calendar c = Calendar.getInstance();
		ss = c.get(Calendar.HOUR_OF_DAY)*60*60+c.get(Calendar.MINUTE)*60+c.get(Calendar.SECOND);
		return ss;
	}
	
	
	/**
	 * 
	 * 给我一个参数  字符串  "HH:mm:ss"  可以转化成ss  00  到改时间的秒数
	 * 
	 */
	public long gotoss(String hhmmss)
	{
		System.out.println("计算秒数");
		long ss = 0;
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		String sss=sdf.format(hhmmss);
		String [] timess = hhmmss.split(":".toString());
		long first  = Long.parseLong(timess[0])*60*60;
		long second = Long.parseLong(timess[1])*60;
		long third  = Long.parseLong(timess[2]);
		
		ss= first+second+third;
		return ss;
	}
	

	
	/**
	 * Student 类转化成json
	 */
	
	public String stu_to_json(Student student)
	{
		Gson  gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String stu = gson.toJson(student);
		return stu;
	}
	
	
	/**
	 * 判断当前是第几节课
	 */
	
	public int get_which_chass()
	{
		int cls=0;
		Calendar c = Calendar.getInstance();
		
		int ss= c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE);
		
		if ((ss>(480-5))  && (ss<(480+55)) )
		{
			cls=1;
		}else{
			if((ss>(480-5)+55)  && (ss<(540+40)) ){
				cls=2;
			}else{
				if((ss>(540-5)+55)  && (ss<(600+40)) ){
					cls=3;
				}else{
					if((ss>(600-5)+50)  && (ss<(660+35)) ){
						cls=4;
					}else{
						if((ss>(660-5)+45)  && (ss<(12*60+50)) ){
						cls=5;
						}else{
							if((ss>(13*60-5)+30)  && (ss<(14*60+15)) ){
								cls=6;
							}else{
								if((ss>(14*60-5)+25)  && (ss<(15*60+10)) ){
									cls=7;
								}else{
									if((ss>(15*60-5)+20)  && (ss<(16*60+5)) ){
										cls=8;
									}if((ss>(16*60-5)+15)  && (ss<(17*60)) ){
										cls=9;
									}else{
										if((ss>(17*60-5)+10)  && (ss<(17*60+55)) ){
											cls=10;
										}else{
											if((ss>(18*60-5)+30)  && (ss<(19*60+15)) ){
											cls=11;	
											}else{
												if((ss>(19*60-5)+25)  && (ss<(20*60+10)) ){
													cls=12;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return cls ;
	}
	
	
	
	
	
	
	
}

