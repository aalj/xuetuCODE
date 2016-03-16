package com.xuetu.service;

import com.xuetu.dao.TimeDao;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.StudyTime;
import com.xuetu.service.inter.HomeServiceInter;

/**
 * ClassName:TimeService
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   康毅
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月14日		下午8:52:00
 *
 * @see 	 

 */

public class TimeService implements HomeServiceInter{
	private TimeDao timedao = new TimeDao();
	public TimeService (TimeDao timedao)
	{
		this.timedao=timedao;
	}
	public void timeAdd(StudyTime stu_time){
		timedao.addTime(stu_time);
		
		
	}

	@Override
	public void addTime(StudyTime stu_time) {
		// TODO Auto-generated method stub
		
	}	
		
		
	}
