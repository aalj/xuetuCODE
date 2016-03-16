/**
 * HomeInter.java
 * com.xuetu.dao.inter
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月5日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.dao.inter;

import java.util.List;

import com.xuetu.entity.ChckIns;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;

/**
 * ClassName:HomeInter
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016年3月5日		下午11:29:47
 *
 * @see 	 

 */
public interface HomeInter {
	/**
	 * 
	 * queryStudayTimeDay:(通过学生和当天的日期查询当天的学习时间)<br/>
	 * @param  @param Date  需要查询的当天时间
	 * @param  @param stu  学生对象
	 * @param  @return    设定文件
	 * @return StudyTime    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public StudyTime queryStudayTimeDay(String Date,Student stu);
	
	/**
	 * 
	 * queryStudayTimeDay:(当天的日期查询当天的学习时间)<br/>
	 * @param  @param Date  需要查询的当天时间
	
	 * @param  @return    设定文件
	 * @return Student    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<Student> queryAllStudayTimeDay(String Date);
	
	/**
	 * 
	 * queryChckInsByStuID:(通过学生对象获取签到表数据)<br/>
	 * TODO(这里描述这个方法适用条件 – 可选)<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选)<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选)<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选)<br/>
	 *
	 * @param  @param stu
	 * @param  @return    设定文件
	 * @return List<ChckIns>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public List<ChckIns> queryChckInsByStuID(Student stu);
	
	public void addTime(StudyTime stu_time);

}

