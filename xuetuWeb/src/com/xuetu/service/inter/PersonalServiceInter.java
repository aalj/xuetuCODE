package com.xuetu.service.inter;

import java.util.List;

import com.xuetu.entity.MyClass;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.Student;

public interface PersonalServiceInter {
	/**
	 * 通过手机和密码得到学生对象的一系列属性
	 * 
	 * @param telephpne
	 * @param password
	 * @return
	 */
	public Student getStusByPhoneAndPwd(String telephone, String password);

	public List<MyClass> getListCourse(int Stuid);

	/**
	 * 
	 * 通过手机和密码创建一个学生
	 * 
	 * @param telephpne
	 * @param password
	 * @return
	 */
	public boolean creatStudent(String telephone, String password);
	/**
	 * 通过学生id得到个人优惠劵
	 * 
	 */
	public List<MyCoupon> getPoinCouByStuId(int stuID);
	

}
