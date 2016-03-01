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
	 * getStudyTime:()<br/>
	 * TODO(这里描述这个方法适用条件 – 可选)<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选)<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选)<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选)<br/>
	 *
	 * @param  @param name
	 * @param  @return    设定文件
	 * @return StudyTime    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public StudyTime getStudyTime(Student name);
	
	

}

