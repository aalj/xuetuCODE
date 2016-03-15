package com.xuetu.service;

import com.xuetu.dao.TimeDao;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.StudyTime;

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

public class TimeService {
	private TimeDao timedao = new TimeDao();
	
	public void timeAdd(StudyTime stu_time){
		timedao.addTime(stu_time);
		
		
	}	
		
		
	}
