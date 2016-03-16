/**
 * FindService.java
 * com.xuetu.service
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月12日 		liang
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.service;

import java.util.List;

import com.xuetu.dao.inter.FindInter;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.service.inter.FindServicesInter;

/**
 * ClassName:FindService Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年3月12日 下午3:07:32
 *
 * @see
 * 
 */
public class FindService implements FindServicesInter {
	
	FindInter find;
	//传入dao层里面的发现页面的接口，通过java的多态调用接口实现类里面的方法
	public FindService(FindInter find) {
		this.find = find;
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean addStudyTime() {

		return false;

	}

	@Override
	public Pattern queryPatternById(int PatternId) {

		return null;

	}

	@Override
	public List<SelfStudyPlan> getAllSelfStudyPlan(int stuID) {
		return find.getSelfPlan(stuID);

	}

	@Override
	public List<Pattern> getAllPattern() {
		
		return find.getAllPattern();
	}

	@Override
	public boolean saveSelfStudyPlan(SelfStudyPlan plan) {
		return find.updateSelfStudyPlan(plan);
	}
	@Override
	public boolean addSelfStudyPlan(SelfStudyPlan plan) {
		return find.updateSelfStudyPlan(plan);
	}

}
