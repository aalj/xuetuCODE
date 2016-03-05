/**
 * FindInter.java
 * com.xuetu.inter
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年2月29日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.dao.inter;
import java.util.List;

import com.xuetu.entity.Countdown;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;
/**
 * ClassName:FindInter
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年2月29日		下午11:43:36
 *
 * @see 	 

 */
public interface FindInter {
	
	
	/**
	 * 
	 * getCountDown:(得到服务器上倒计时)<br/>
	 * @param      设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Countdown getCountDown();
	
	
	
	
	/**
	 * 
	 * getStudyTime:(获得一个学生的学习时间)<br/>
	 *
	 * @param  @param name
	 * @param  @return    设定文件
	 * @return StudyTime    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public StudyTime getStudyTime(Student name);
	
	
	
	
	/**
	 * 
	 * getWeekTime:(通过时间段获取一段时间内的所有学生的学习时间)<br/>
	 *
	 * @param  @param beforeData 之前的时间
	 * @param  @param afterData  之后的时间
	 * @param  @return    设定文件
	 * @return List<StudyTime>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<StudyTime> getWeekTime(String beforeData,String afterData);
	
	
	
	
	

}

