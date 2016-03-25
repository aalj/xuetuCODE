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

	private static final String TAG = "TAG";

	public static int dayForWeek(Date pTime) {

		Calendar cal = new GregorianCalendar();

		cal.set(pTime.getYear(), pTime.getMonth(), pTime.getDay());

		return cal.get(Calendar.DAY_OF_WEEK);

	}

	public static List<float[]> getshijainshuju(List<LongTime> list) {
		List<float[]> mylist = new ArrayList<float[]>();

		for (int i = 0; i < 7; i++) {
			mylist.add(new float[]{2});
		}
		for (int i = 0; i < list.size(); i++) {

			int dayForWeek = dayForWeek(list.get(i).getMyDate());

			if (dayForWeek == 0) {
				mylist.remove(0);
				mylist.add(0, new float[]{list.get(i).getMyTime()});
			}
			if (dayForWeek == 1) {
				mylist.remove(1);
				mylist.add(1, new float[]{list.get(i).getMyTime()});
			}
			if (dayForWeek == 2) {
				mylist.remove(2);
				mylist.add(2, new float[]{list.get(i).getMyTime()});
			}
			if (dayForWeek == 3) {
				mylist.remove(3);
				mylist.add(3, new float[]{list.get(i).getMyTime()});
			}
			if (dayForWeek == 4) {
				mylist.remove(4);
				mylist.add(4, new float[]{list.get(i).getMyTime()});
			}
			if (dayForWeek == 5) {
				mylist.remove(5);
				mylist.add(5, new float[]{list.get(i).getMyTime()});
			}

			if (dayForWeek == 6) {
				mylist.remove(6);
				mylist.add(6, new float[]{list.get(i).getMyTime()});
			}

		}

		return mylist;

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
		String ff= "00";
		if(f==0){
			return h + ":" +ff+ ":00";
		}

		return h + ":" + f + ":00";

	}

}
