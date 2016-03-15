package com.xuetu.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataToTime {
	public static String dataToT(Date data){
		DateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
		String string = dateFormat.format(data);
		
		
		return string;
		
	}
	public static String dataToHS(long data){
		data = data/1000;
		int h=(int)data/3600;
		int f = (int)data%60/60;
		
		
		
		return h+":"+f+":00";
		
	}

}
