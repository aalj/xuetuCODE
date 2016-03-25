package com.xuetu.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xuetu.entity.SelfStudyPlan;

public class ChuliShijian {
	//用于排除list里面小于当前时间的对象
	public  static List<SelfStudyPlan> shijianpanduan(List<SelfStudyPlan> list) {
		List<SelfStudyPlan> listtemp=new ArrayList<>();
		long time = System.currentTimeMillis();
		System.out.println("time----->"+new Date(time));
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getStartTime().getTime()>=time){
				listtemp.add(list.get(i));
				System.out.println("self-------->>>"+listtemp.toString());
				
			}
		}
		
		return listtemp;
	}
	public  static SelfStudyPlan shiJianPanDuanZuiJing(List<SelfStudyPlan> list) {
		SelfStudyPlan listtemp=new SelfStudyPlan();
		long time = System.currentTimeMillis();
		System.out.println("time----->"+time);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getStartTime().getTime()>=time){
				listtemp=list.get(i);
				return listtemp;
				
			}
		}
		
		return null;
	}

}
