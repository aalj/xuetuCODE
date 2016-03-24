package com.xuetu.service;

import java.util.List;

import com.xuetu.dao.inter.PersonalDaoInterface;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.Student;
import com.xuetu.service.inter.PersonalServiceInter;

public class LoginService implements PersonalServiceInter {

	PersonalDaoInterface personalDaoInterface;

	public LoginService(PersonalDaoInterface personalDaoInterface) {
		this.personalDaoInterface = personalDaoInterface;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Student getStusByPhoneAndPwd(String telephone, String password) {
		// TODO Auto-generated method stub
		return personalDaoInterface.login(telephone, password);
	}

	@Override
	public List<MyClass> getListCourse(int Stuid) {
		// TODO Auto-generated method stub
		return personalDaoInterface.getClassByStuId(Stuid);
	}

	@Override
	public boolean creatStudent(String telephone, String password) {
		 return personalDaoInterface.register(telephone, password);
	}

	@Override
	public List<MyCoupon> getPoinCouByStuId(int stuID) {
		// TODO Auto-generated method stub
		return personalDaoInterface.getPoinCouByStuId(stuID);
	}

	@Override
	public List<FavoritesCoupons> getFavoritecouByStuID(int stuID) {
		// TODO Auto-generated method stub
		return personalDaoInterface.getFavoritecouByStuID(stuID);
	}

	@Override
	public void updateStu_img(int stu_id, String stu_img) {
		// TODO Auto-generated method stub
		personalDaoInterface.updateStu_img(stu_id, stu_img);
	}

	@Override
	public Student getStuByID(int Id) {
		// TODO Auto-generated method stub
		return personalDaoInterface.getStuByID(Id);
	}

}
