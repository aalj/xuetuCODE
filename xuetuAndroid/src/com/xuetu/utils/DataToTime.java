package com.xuetu.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xuetu.entity.LongTime;

import android.R.integer;
import android.util.Log;

public class DataToTime {

	private static final String TAG = "TAG";

	public static int dayForWeek(Date pTime) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(pTime);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return w;

	}

	public static List<float[]> getshijainshuju(List<LongTime> list) {
		
		
		List<float[]> mylist = new ArrayList<float[]>();

		for (int i = 0; i < 7; i++) {
			mylist.add(new float[] { 0 });
		}
		int week = dayForWeek(new Date(System.currentTimeMillis()));
		List<Integer> in = new ArrayList<>();
		//标记是否加载到星期集合里面
		for (int i = 0; i < list.size(); i++) {
			boolean temp = false;
			//的到第一条数据是星期几
			int dayForWeek = dayForWeek(list.get(i).getMyDate());
			
			for (int j = 0; j < 7; j++) {
				if (dayForWeek == week) {//4-1
					if (!in.contains(week)) {
						
						mylist.remove(dayForWeek);
						mylist.add(dayForWeek, new float[] { list.get(i).getMyTime()/60 });
						in.add(week);
						temp=true;
					}
				}
				week -= 1;
				if (week < 0)
					week = 6;
			}
			
			
			
			

		}
		List<float[]> lis= new ArrayList<>();
		for (int i = 0;i<mylist.size();i++) {
			lis.add(0,mylist.get(week));
			week -= 1;
			if (week < 0)
				week = 6;
		}
		

		return lis;

	}

	public static String dataToT(Date data) {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
		String string = dateFormat.format(data);

		return string;

	}

	public static String dataToh(Date data) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String string = dateFormat.format(data);

		return string;

	}

	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	public static String dataToHS(long data) {
		data = data / 1000;// 秒
		int h = (int) data / 3600;// 小时
		int f = (int) data % 60 / 60;
		String ff = "00";
		if (f == 0) {
			return h + ":" + ff + ":00";
		}

		return h + ":" + f + ":00";

	}

	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static int getDay(long s1) {

		long s2 = System.currentTimeMillis();// 得到当前的毫秒
		int day = (int) ((s1 - s2) / 1000 / 60 / 60 / 24);
		return day + 1;
	}

}
