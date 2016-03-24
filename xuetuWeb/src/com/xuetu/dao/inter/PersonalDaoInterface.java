/**
 * PersonalDaoInterface.java
 * com.xuetu.dao.inter
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月1日 		Stone
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.dao.inter;

import java.util.List;

import com.xuetu.entity.Answer;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.PointNum;
import com.xuetu.entity.Question;
import com.xuetu.entity.School;
import com.xuetu.entity.StoreName;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;
import com.xuetu.entity.UserState;

/**
 * ClassName:PersonalDaoInterface Function:个人中心的接口 Reason: TODO ADD REASON
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016年3月1日 下午11:34:49
 *
 * @see
 * 
 */
public interface PersonalDaoInterface {
	/**
	 * 
	 * getStuByNamepwd:(通过账号密码得到学生对象)<br/>
	 *
	 * @param @param
	 *            name 登录账号
	 * @param @param
	 *            pwd 登录密码
	 * @param @return
	 *            设定文件
	 * @return Student DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public Student getStuByNamepwd(String name, String pwd);

	/**
	 * 
	 * getStuByID:(通过ID得到学生对ixna)<br/>
	 *
	 * @param @param
	 *            id 学生的ID
	 * @param @return
	 *            设定文件
	 * @return Student DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public Student getStuByID(int Id);

	/**
	 * 
	 * getSchoolById:(通过ID得到学校的Id)<br/>
	 *
	 * @param schID
	 *            学校ID
	 * @return 设定文件
	 * @return School DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public School getSchoolById(int schID);

	/**
	 * 
	 * getPoinCouByStuId:(通过学生得到优惠劵集合)<br/>
	 *
	 * @param stuID
	 *            学生ID
	 * @return 设定文件
	 * @return MyCoupon DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public List<MyCoupon> getPoinCouByStuId(int stuID);

	/**
	 * 
	 * getPoinStuTimeByStuID:(通过学生id得到学习时间)<br/>
	 *
	 * @param @param
	 *            stuID
	 * @param @return
	 *            设定文件
	 * @return StudyTime DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public List<StudyTime> getPoinStuTimeByStuID(int stuID);

	/**
	 * 
	 * getPoinAnsByStuID:(通过学生ID得到答案的集合)<br/>
	 *
	 * @param @param
	 *            stuID
	 * @param @return
	 *            设定文件
	 * @return List<Answer> DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public List<Answer> getPoinAnsByStuID(int stuID);

	/**
	 * 
	 * getPoinQuesByStuID:(通过学生ID得到问题的集合)<br/>
	 *
	 * @param @param
	 *            stuID
	 * @param @return
	 *            设定文件
	 * @return List<Question> DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public List<Question> getPoinQuesByStuID(int stuID);

	/**
	 * 
	 * getStoreNameByCouID:(通过店家ID得到店家)<br/>
	 *
	 * @param @param
	 *            stoID
	 * @param @return
	 *            设定文件
	 * @return StoreName DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public StoreName getStoreNameByCouID(int stoID);

	/**
	 * 
	 * getFavoritecouByStuID:(通过学生ID得到学生收藏的优惠券)<br/>
	 *
	 * @param @param
	 *            id
	 * @param @return
	 *            设定文件
	 * @return List<FavoritesCoupons> DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public List<FavoritesCoupons> getFavoritecouByStuID(int id);

	/**
	 * 
	 * getClassByStuId:(通过学生Id查询CourseList表得到学生的课程)<br/>
	 *
	 * @param @param
	 *            Stuid
	 * @param @return
	 *            设定文件
	 * @return Class DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public List<MyClass> getClassByStuId(int Stuid);

	/**
	 * 将学习积分和答题积分归纳到一个表中
	 * 
	 * 通过积分来源id查询积分得到积分表
	 * 
	 */
	public boolean savePoints(PointNum pointNum);

	/**
	 * 
	 * 通过手机和密码得到学生对象的一系列属性
	 * 
	 * @param telephone
	 * @param password
	 * @return
	 */
	public Student login(String telephone, String password);

	/**
	 * 通过账户，密码 返回一个对象
	 * 
	 */

	public boolean register(String telephone, String password);

	/**
	 * 通过使用状态id得到状态
	 * 
	 */
	public UserState findUserStateByUsta_id(int usta_id);
	/**
	 * 改变id 为 stu_id的用户的头像
	 * 
	 */
	public void updateStu_img(int stu_id,String stu_img);
}
