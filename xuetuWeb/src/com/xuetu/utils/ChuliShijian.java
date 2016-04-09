package com.xuetu.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.xuetu.entity.SelfStudyPlan;

public class ChuliShijian {
	
	  
	
	
	// 用于排除list里面小于当前时间的对象
	public static List<SelfStudyPlan> shijianpanduan(List<SelfStudyPlan> list) {
		List<SelfStudyPlan> listtemp = new ArrayList<>();
		long time = System.currentTimeMillis();
		System.out.println("time----->" + new Date(time));
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStartTime().getTime() >= time) {
				listtemp.add(list.get(i));
				System.out.println("self-------->>>" + listtemp.toString());

			}
		}

		return listtemp;
	}

	public static List<SelfStudyPlan> isZhiXing(List<SelfStudyPlan> list) {
		long time = System.currentTimeMillis();
//		//用于缓存是否是否成为历史
		List<SelfStudyPlan> listtemp_old = new ArrayList<>();
		List<SelfStudyPlan> listtemp_0 = new ArrayList<>();
//		
//		//用于缓存已经执行完成的计划
		List<SelfStudyPlan> listtemp_2 = new ArrayList<>();
//		List<SelfStudyPlan> listtemp_2_2 = new ArrayList<>();
		List<SelfStudyPlan> listtemp = new ArrayList<>();
//		for (SelfStudyPlan selfStudyPlan : list) {//过滤出还没有执行的
//			if(selfStudyPlan.getStartTime().getTime()<=time){
//				listtemp_0.add(selfStudyPlan);//离现在时间较近的计划，按照由远到近排序
//				
//			}else{
//				listtemp_old.add(selfStudyPlan);
//			}
//		}
//		
//		for (SelfStudyPlan selfStudyPlan : listtemp_old) {//
//			if(selfStudyPlan.getIsZhiXing()==2){
//				listtemp_2_2.add(selfStudyPlan);
//				listtemp_old.remove(selfStudyPlan);
//			}
//		}
//		
//		for (SelfStudyPlan selfStudyPlan : listtemp_old) {
//			if(selfStudyPlan.getIsZhiXing()==2){
//				listtemp_2_1.add(selfStudyPlan);
//				listtemp_0.remove(selfStudyPlan);
//			}
//		}
//		
//		listtemp_0.addAll(listtemp_0.size()-1,listtemp_2_1);//组装好为执行，和提前执行玩的
//		
//		listtemp_old.addAll(listtemp_2_2);//组装好成为历史以及历史里面执行结束的
//		
//		
//		
//		
//		listtemp.clear();
//		listtemp.addAll(listtemp_0);
//		listtemp.addAll(0,listtemp_old);
		
		
		
		Comparator<SelfStudyPlan> ascComparator = new PersonComparator();
		Collections.sort(list, ascComparator);
		for (SelfStudyPlan selfStudyPlan : list) {
			if(selfStudyPlan.getIsZhiXing()==2){
				listtemp_2.add(selfStudyPlan);
			}
		}
		list.removeAll(listtemp_2);
		for (SelfStudyPlan selfStudyPlan : list) {//过滤出还没有执行的
			if(selfStudyPlan.getStartTime().getTime()<=time){
				listtemp_0.add(selfStudyPlan);//离现在时间较近的计划，按照由远到近排序
				
			}else{
				listtemp_old.add(selfStudyPlan);
			}
		}
		listtemp.clear();
		listtemp.addAll(0,listtemp_0);
		listtemp.addAll(0,listtemp_2);
		listtemp.addAll(0,listtemp_old);
		
		
		
		
		
		
		


		return listtemp;

	}

	
	
	
	
	
	
	public static SelfStudyPlan shiJianPanDuanZuiJing(List<SelfStudyPlan> list) {
		SelfStudyPlan listtemp = new SelfStudyPlan();
		long time = System.currentTimeMillis();
		System.out.println("time----->" + time);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStartTime().getTime() >= time) {
				listtemp = list.get(i);
				return listtemp;

			}
		}

		return null;
	}

}




class PersonComparator implements Comparator<SelfStudyPlan> {

	@Override
	public int compare(SelfStudyPlan s1, SelfStudyPlan s2) {
		return s2.getIsZhiXing()-s1.getIsZhiXing();
	}
  }
