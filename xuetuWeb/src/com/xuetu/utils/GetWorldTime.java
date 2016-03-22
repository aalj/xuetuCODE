package com.xuetu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetWorldTime {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间格式
	
	
	/**
	 * 获得当前系统时间  yyyy-MM-dd HH:mm:ss
	 */
	public String getWorldTime_Now()
	{
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date  = sdf.format(new Date());
		return date;
	}

	/**
	 * 获得当前系统时间  yyyy
	 */
	public String getWorldTime_YYYY()
	{
		sdf = new SimpleDateFormat("yyyy");
		String date  = sdf.format(new Date());
		return date;
	}
	

	/**
	 * 获得当前系统时间  MM
	 */
	public String getWorldTime_MM()
	{
		sdf = new SimpleDateFormat("MM");
		String date  = sdf.format(new Date());
		return date;
	}

	/**
	 * 获得当前系统时间  MM
	 */
	public String getWorldTime_DD()
	{
		sdf = new SimpleDateFormat("dd");
		String date  = sdf.format(new Date());
		return date;
	}

	/**
	 * 获得当前系统时间  HH
	 */
	public String getWorldTime_HH()
	{
		sdf = new SimpleDateFormat("HH");
		String date  = sdf.format(new Date());
		return date;
	}

	/**
	 * 获得当前系统时间  MM
	 */
	public String getWorldTime_mm()
	{
		sdf = new SimpleDateFormat("mm");
		String date  = sdf.format(new Date());
		return date;
	}

	/**
	 * 获得当前系统时间  MM
	 */
	public String getWorldTime_ss()
	{
		sdf = new SimpleDateFormat("ss");
		String date  = sdf.format(new Date());
		return date;
	}
	
	/**
	 * 今天礼拜几,
	 * @return  int
	 */
	public int getDayInWeek()
	{
		Calendar c = Calendar.getInstance();
		int week =c.get(Calendar.DAY_OF_WEEK);
        System.out.println(week);
        
        if(week<=7&&week!=1)
        {
        	week-=1;
        }else
        {
        	week=7;
        }
        return week;
	}
	
}
