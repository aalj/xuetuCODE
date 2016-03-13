/**
 * CourseService.java
 * com.xuetu.service
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月11日 		Mystery
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.service;

import java.util.List;

import com.xuetu.dao.CourseDao;
import com.xuetu.entity.MyClass;

/**
 * ClassName:CourseService Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author Mystery
 * @version
 * @since Ver 1.1
 * @Date 2016年3月11日 上午10:45:25
 *
 * @see
 * 
 */
public class CourseService {
	CourseDao coursedao = new CourseDao();

	public List<MyClass> getListCourse(int Stuid) {
		CourseDao coursedao = new CourseDao();
		return coursedao.getClassByStuId(Stuid);
	}
}
