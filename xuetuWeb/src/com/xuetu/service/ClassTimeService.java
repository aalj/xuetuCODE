package com.xuetu.service;

import java.util.List;

import com.xuetu.dao.CLassTimeDao;
import com.xuetu.entity.MyClass;
import com.xuetu.service.inter.ClassTimeServiceInter;

public class ClassTimeService implements ClassTimeServiceInter{
	
	@Override
	public List<Integer> get_student_clsID(int stu_id) {
		// TODO Auto-generated method stub
		CLassTimeDao classtimedao = new CLassTimeDao();
		
		
		return classtimedao.get_student_clsID(stu_id);
	}

	@Override
	public List<MyClass> getMyClass(List<String> cls_id) {
		// TODO Auto-generated method stub
		CLassTimeDao classtimedao = new CLassTimeDao();
		
		return classtimedao.getMyClass(cls_id);
	}

}
