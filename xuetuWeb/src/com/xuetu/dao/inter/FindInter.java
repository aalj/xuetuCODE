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
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;
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
	
	
	/**
	 * 
	 * getSelfPlan:(通过学生ID的到该学生里面的的自己定义的自学计划)
	 * <br/>
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * <br/>
	 * TODO(这里描述这个方法的执行流程 – 可选) 
	 * <br/>
	 * TODO(这里描述这个方法的使用方法 – 可选) 
	 * <br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选)
	 * <br/>
	 * @param  @param stuId
	 * @param  @return    设定文件
	 * @return List<SelfStudyPlan>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<SelfStudyPlan> getSelfPlan(int stuId);
	
	
	/**
	 * 
	 * getPatternById:(通过Id的到该自学计划的学习模式)
	 * <br/>
	 * @param  @param patID
	 * @param  @return    设定文件
	 * @return Pattern    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Pattern  getPatternById(int patID);
	/**
	 * 获取全部的学习模式
	 * @return
	 */
	public List<Pattern> getAllPattern();




	public boolean updateSelfStudyPlan(SelfStudyPlan plan);
	
	
	
	

}

