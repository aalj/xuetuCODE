package com.xuetu.service;

import java.util.List;

import com.xuetu.dao.inter.PersonalDaoInterface;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.PersonalStudyTimeAll;
import com.xuetu.entity.Question;
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

	@Override
	public List<PersonalStudyTimeAll> findAllTime() {
		// TODO Auto-generated method stub
		return personalDaoInterface.findAllTime();
	}

	@Override
	public boolean ChangeName(int stuID, String change_name) {
		// TODO Auto-generated method stub
		return personalDaoInterface.ChangeName(stuID, change_name);
	}

	@Override
	public boolean ChangeQianMing(int stuID, String change_qianming) {
		// TODO Auto-generated method stub
		return personalDaoInterface.ChangeQianMing(stuID, change_qianming);
	}

	@Override
	public boolean ChangeSex(int stuID, String change_sex) {
		// TODO Auto-generated method stub
		return personalDaoInterface.ChangeSex(stuID, change_sex);
	}

	@Override
	public boolean ChangeAge(int stuID, String change_age) {
		// TODO Auto-generated method stub
		return personalDaoInterface.ChangeAge(stuID, change_age);
	}

	@Override
	public boolean ChangeGrade(int stuID, String change_grade) {
		// TODO Auto-generated method stub
		return personalDaoInterface.ChangeGrade(stuID, change_grade);
	}

	@Override
	public boolean UpdataByUid(String telephone) {
		// TODO Auto-generated method stub
		return personalDaoInterface.UpdataByUid(telephone);
	}

	@Override
	public boolean addNewUser(String telephone, String sex, String name, String img, String telephone_pwd) {
		// TODO Auto-generated method stub
		return personalDaoInterface.addNewUser(telephone, sex, name, img, telephone_pwd);
	}

	@Override
	public Student getStuByTelephone(String telephone) {
		// TODO Auto-generated method stub
		return personalDaoInterface.getStuByTelephone(telephone);
	}

	@Override
	public boolean register(String telephone, String password) {
		// TODO Auto-generated method stub
		return personalDaoInterface.register(telephone, password);
	}

	@Override
	public List<Question> getPoinQuesByStuID(int stuID) {
		// TODO Auto-generated method stub
		return personalDaoInterface.getPoinQuesByStuID(stuID);
	}

}
