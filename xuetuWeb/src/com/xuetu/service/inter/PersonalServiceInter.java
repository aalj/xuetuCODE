package com.xuetu.service.inter;

import java.util.List;

import com.xuetu.entity.Answer;
import com.xuetu.entity.CollectionQuestion;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.PersonAnswerAll;
import com.xuetu.entity.PersonalStudyTimeAll;
import com.xuetu.entity.Question;
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

	/**
	 * 通过stu_id获得student对象
	 * 
	 * @param telephpne
	 * @param password
	 * @return
	 */
	public Student getStuByID(int Id);

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

	/**
	 * 通过学生id得到个人收藏的优惠劵
	 */
	public List<FavoritesCoupons> getFavoritecouByStuID(int stuID);

	public void updateStu_img(int stu_id, String stu_img);

	public boolean register(String telephone, String password);

	/**
	 * 查询所有学生的学习时间
	 */
	public List<PersonalStudyTimeAll> findAllTime();

	public boolean ChangeName(int stuID, String change_name);

	public boolean ChangeQianMing(int stuID, String change_qianming);

	public boolean ChangeSex(int stuID, String change_sex);

	public boolean ChangeAge(int stuID, String change_age);

	public boolean ChangeGrade(int stuID, String change_grade);

	public boolean UpdataByUid(String telephone);

	public boolean addNewUser(String telephone, String sex, String name, String img, String telephone_pwd);

	public Student getStuByTelephone(String telephone);

	public List<Question> getPoinQuesByStuID(int stuID);

	public List<CollectionQuestion> getPersonalCollectionQuestionByStuID(int stuID);

	public List<PersonAnswerAll> getAnswerAll();
	public List<Answer> getAnswerByStuID(int stuID);
}
