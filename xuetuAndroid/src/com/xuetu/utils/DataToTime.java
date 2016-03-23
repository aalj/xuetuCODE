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

import android.util.Log;

public class DataToTime {

	public static int dayForWeek(Date pTime) throws Throwable {

		Calendar cal = new GregorianCalendar();

		cal.set(pTime.getYear(), pTime.getMonth(), pTime.getDay());

		return cal.get(Calendar.DAY_OF_WEEK);

	}

	public static List<LongTime> getshijainshuju(List<LongTime> list) {
		List<LongTime> mylist = new ArrayList<LongTime>();
		try {for(int i=0;i<7;i++){
			mylist.add(new LongTime());
			for (LongTime longTime : list) {

				int dayForWeek = dayForWeek(longTime.getMyDate());
				switch (dayForWeek) {
				case 0:
					mylist.remove(0);
					mylist.add(0, list.get(0));
					
					break;
				case 1:
					mylist.remove(1);
					mylist.add(1, list.get(1));					
					break;
				case 2:
					mylist.remove(2);
					mylist.add(2, list.get(2));					
					break;
				case 3:
					mylist.remove(3);
					mylist.add(3, list.get(3));					
					break;
				case 4:
					mylist.remove(4);
					mylist.add(4, list.get(4));					
					break;
				case 5:
					mylist.remove(5);
					mylist.add(5, list.get(5));					
					break;
				case 6:
					mylist.remove(6);
					mylist.add(0, list.get(6));					
					break;

				default:
					break;
				}}
				
			}
		return mylist;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String dataToT(Date data) {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
		String string = dateFormat.format(data);

		return string;

	}

	public static String dataToHS(long data) {
		data = data / 1000;
		int h = (int) data / 3600;
		int f = (int) data % 60 / 60;

		return h + ":" + f + ":00";

	}

}
