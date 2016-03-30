package com.xuetu.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SendQianDaoDateDao {
	Calendar c = Calendar.getInstance();
	String s = (c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"
			+c.get(Calendar.DAY_OF_MONTH)+" " +
			c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
	SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	
	Date date ;
			
	public void send_qiandao_date()
	{
		try {
			date =  simpleDateFormat.parse(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
}
