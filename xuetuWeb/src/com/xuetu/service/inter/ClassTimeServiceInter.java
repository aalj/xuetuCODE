package com.xuetu.service.inter;

import java.util.List;

import com.xuetu.entity.MyClass;

public interface ClassTimeServiceInter {
	public List<Integer> get_student_clsID(int stu_id);
	
	public List<MyClass> getMyClass(List<String> cls_id);
	
	
}
