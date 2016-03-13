/**
 * FindServicesInter.java
 * com.xuetu.service.inter
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月6日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.service.inter;

import java.util.List;

import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;

/**
 * ClassName:FindServicesInter
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月6日		下午2:00:37
 *
 * @see 	 

 */
public interface FindServicesInter {
	/**
	 * 
	 * addStudyTime:(添加学习时间)<br/>
	 *
	 * @param  @return    设定文件
	 * @return boolean    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public boolean addStudyTime();
	
	/**
	 * 
	 * queryPatternById:(通过ID得到当前的模式)<br/>
	 *
	 * @param  @param PatternId
	 * @param  @return    设定文件
	 * @return Pattern    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public Pattern queryPatternById(int PatternId);
	
	/**
	 * 
	 * getAllSelfStudyPlan:(通过学生Id查找该id下的全部学生)
	 * <br/>
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * <br/>
	 * TODO(这里描述这个方法的执行流程 – 可选) 
	 * <br/>
	 * TODO(这里描述这个方法的使用方法 – 可选) 
	 * <br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选)
	 * <br/>
	 * @param  @return    设定文件
	 * @return List<SelfStudyPlan>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<SelfStudyPlan> getAllSelfStudyPlan(int stuID);
	
	

}

