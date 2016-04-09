package com.xuetu.entity;

import java.util.Calendar;

public class Class_end {
	Calendar c = Calendar.getInstance();
	
		private String [][] class_time={
				{"8:10","10:10","14:10", "16:10","19:05","20:00"},
				{"9:45","12:00","15:55","18:00","19:50","20:45"}};
		
//	private String [] class_class =
//		{"中国绘画","中国音乐史与名著欣赏","和声学基础","中国历史地理","CAD制图与与绘画","模拟电子技术"};

//	public String get_class_name(int i)
//	{
//		String ss=null;
//		ss=class_class[i];
//		return ss;
//	}
	
	public String  get_start_time(int which)  //获得开始时间
	{
		String  st=  class_time[0][ which];
		return st;
	}
	
	
	public String get_end_time(int which)
	{
		String  end=  class_time[1][ which   ];
		return end;
	};
	
	
	
	
	
	
	public int get_which_chass()   //获得现在是第几节课
	{
		int cls=0;
		
		int ss= c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE);
		System.out.println(ss+"```````````");
		if ( ss>485  && ss<585 )
		{
			cls=1;
		}else{
			if( ss>605  &&  ss<720 ){
				cls=2;
			}else{
				if( ss>845  && ss<950 ){
					cls=3;
				}else{
					
//			{"8:10","10:10","14:10", "16:10","19:05","20:00"},
//					{"9:45","12:00","15:55","18:00","19:50","20:45"}};
					
					if(( ss>965 )  && ( ss<1080) ){
						cls=4;
					}else{
						if(ss>1140  && ss<1190 ){
						cls=5;
						}else{
							if( ss>1195  && ss<1245){
								cls=6;
							}
						}
					}
				}
			}
		}
		return cls ;
		
	}
	
	
	//获得当前时间到下课时间的秒数
	//步骤   end_time的秒数,减去当前时间的秒数
	public int getstu_time(String  end_time)
	{
		int ss=0;
		//现在的秒数
		int mm= c.get(Calendar.HOUR_OF_DAY)*60*60+c.get(Calendar.MINUTE)*60+c.get(Calendar.SECOND);// 当前0点到现在所用的秒数
		//获得课程结束时间的秒数
		String s []= end_time.split(":".toString()) ;
		int first  = Integer.parseInt(s[0]) ;
		int second = Integer.parseInt(s[1]) ;
		
		ss= (first*60*60+second*60)-mm;
		return ss;
	}
	
	
	
	//显示提前还是迟到    都不符合,则表示可以上课
	public int what_time()
	{
		int i=0;
		String ss = null;
		String start =class_time[0][ get_which_chass() -1]; 
		int mm= c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE);// 当前0点到现在所用的m
		String s []= start.split(":".toString()) ;
		int first  = Integer.parseInt(s[0]) ;
		int second = Integer.parseInt(s[1]) ;
		 int start_minutes = (first*60+second);
		 if( start_minutes- mm >=5  )    //上课时间, 减去 当前时间
		 {
			 i=1 ;   //太早
		 }else{
			 if(mm- start_minutes > 10  )
			 {
				 i=2 ;   //迟到
			 }
		 }
		return i;   //i=0是,没有课
	}
//	 ss = "提前上课不得超过5分钟";ss="迟到10分钟以上不能进入计时积分页面,下次记得早点哦";
	
	
	/**
	 * 获取 迟到的分钟数
	 */
	public int getmin()
	{
		Calendar c = Calendar.getInstance();
		int mm= c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE);// 当前0点到现在所用的分钟数
		String start =class_time[0][ get_which_chass() -1]; 
		String s []= start.split(":".toString()) ;
		int first  = Integer.parseInt(s[0]) ;
		int second = Integer.parseInt(s[1]) ;
		
		 int start_minutes = (first*60+second);
		 mm=mm-start_minutes;
		return mm;
	}
	
	public int get_day_of_week()
	{
		int day = c.get(Calendar.DAY_OF_WEEK)-1;
		
		if(day ==1  )
		{
			day=7;
		}
		return day ;
	}
	
	
	
	
}
