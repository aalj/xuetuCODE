package com.xuetu.service;

import java.util.List;

import com.xuetu.dao.inter.PersonalDaoInterface;
import com.xuetu.entity.MyClass;
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

}
