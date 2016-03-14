/**
 * CourseDao.java
 * com.xuetu.dao
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月10日 		Mystery
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.xuetu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.xuetu.dao.inter.PersonalDaoInterface;
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
import com.xuetu.utils.CloseDb;
import com.xuetu.utils.DBconnection;

/**
 * ClassName:CourseDao Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author Mystery
 * @version
 * @since Ver 1.1
 * @Date 2016年3月10日 下午10:40:16
 *
 * @see
 * 
 */
public class CourseDao implements PersonalDaoInterface {
	@Override
	public List<MyClass> getClassByStuId(int Stuid) {

		Connection connection = DBconnection.getConnection();
		PreparedStatement prepareStatement = null;
		String sql = "select * from class where cls_id in (select cls_id from courselist where stu_id= ?)";
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, Stuid);
			ResultSet resultSet = prepareStatement.executeQuery();
			MyClass classes = null;
			List<MyClass> classlist = new ArrayList<>();
			while (resultSet.next()) {
				classes = new MyClass();
				classes.setClsId(resultSet.getInt("cls_id"));
				classes.setClsRoom(resultSet.getString("cls_room"));
				classes.setClasName(resultSet.getString("cls_name"));
				classes.setClsFew(resultSet.getInt("cls_few"));
				classes.setClsWeek(resultSet.getInt("cls_week"));
				classlist.add(classes);
			}
			return classlist;
		} catch (SQLException e) {

			//
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (connection != null && prepareStatement != null) {
					connection.close();
					prepareStatement.close();
				}
			} catch (SQLException e) {

				//
				e.printStackTrace();

			}

		}

	}

	@Override
	public Student getStuByNamepwd(String name, String pwd) {

		return null;

	}

	@Override
	public Student getStuByID(int Id) {

		return null;

	}

	@Override
	public School getSchoolById(int schID) {

		return null;

	}

	@Override
	public List<MyCoupon> getPoinCouByStuId(int stuID) {

		return null;

	}

	@Override
	public List<StudyTime> getPoinStuTimeByStuID(int stuID) {

		return null;

	}

	@Override
	public List<Answer> getPoinAnsByStuID(int stuID) {

		return null;

	}

	@Override
	public List<Question> getPoinQuesByStuID(int stuID) {

		return null;

	}

	@Override
	public StoreName getStoreNameByCouID(int stoID) {

		return null;

	}

	@Override
	public List<FavoritesCoupons> getFavoritecouByStuID(int id) {

		return null;

	}

	@Override
	public boolean savePoints(PointNum pointNum) {
		Connection connection = DBconnection.getConnection();
		// INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
		PreparedStatement prepareStatement = null;
		String sql = "insert into point_num (point_num,point_from_id,point_create_time) values(?,?,?);";
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, pointNum.getPointNum());
			prepareStatement.setInt(2, pointNum.getFromPoint().getFromPointid());
			prepareStatement.setTimestamp(3, new Timestamp(pointNum.getPointTime().getTime()));
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, prepareStatement);
		}

		return true;
	}

}
