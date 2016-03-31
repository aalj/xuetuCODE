package com.xuetu.entity;

import java.util.Calendar;

public class Class_end {
		private String [][] class_time={
				{"8:10","9:15","10:20", "13:50","15:05","16:20"},
				{"9:05","10:10","11:15","14:55","16:10","17:30"}};
		
	private String [] class_class =
		{"中国绘画","中国音乐史与名著欣赏","和声学基础","中国历史地理","CAD制图与与绘画","模拟电子技术"};

	public String get_class_name(int i)
	{
		String ss=null;
		ss=class_class[i];
		return ss;
	}
	
	public String  get_start_time(int which)  //获得开始时间
	{
		String  st=  class_time[0][ which   ];
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
		Calendar c = Calendar.getInstance();
		
		int ss= c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE);
		
		if ((ss>(480-5)+10)  && (ss<(540+5)) )
		{
			cls=1;
		}else{
			if((ss>(540-5)+15)  && (ss<(600+10)) ){
				cls=2;
			}else{
				if((ss>(540-5)+20)  && (ss<(660+15)) ){
					cls=3;
				}else{
					if((ss>(13*60-5)+50)  && (ss<(14*60+55)) ){
						cls=4;
					}else{
						if((ss>(15*60-5)+5)  && (ss<(16*60+10)) ){
						cls=5;
						}else{
							if((ss>(16*60-5)+20)  && (ss<(17*60+30)) ){
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
		Calendar c = Calendar.getInstance();
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
		Calendar c = Calendar.getInstance();
		String start =class_time[0][ get_which_chass() -1]; 
		
		int mm= c.get(Calendar.HOUR_OF_DAY)*60+c.get(Calendar.MINUTE);// 当前0点到现在所用的秒数
		
		String s []= start.split(":".toString()) ;
		int first  = Integer.parseInt(s[0]) ;
		int second = Integer.parseInt(s[1]) ;
		
		 int start_minutes = (first*60+second);
		 
		 if( start_minutes- mm >=5  )    //上课时间, 减去 当前时间
		 {
			 i=1 ;   //太早
		 }else{
			 if( start_minutes -mm <= (-10)  )
			 {
				 i=2 ;   //迟到
			 }
		 }
		return i;   //i=0是,没有课
	}
//	 ss = "提前上课不得超过5分钟";ss="迟到10分钟以上不能进入计时积分页面,下次记得早点哦";
	
	
	
	
	
	
	
}
