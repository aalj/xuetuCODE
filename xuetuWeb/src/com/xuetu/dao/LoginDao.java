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

public class LoginDao implements PersonalDaoInterface {

	@Override
	public Student login(String telephone, String password) {
		Connection connection = DBconnection.getConnection();
		PreparedStatement prepareStatement = null;
		Student student = null;
		ResultSet resultSet = null;
		School school = null;
		String sql = "select * from student where stu_phone=? and stu_pwd =?;";
		try {
			// long currentTimeMillis = System.currentTimeMillis();
			// Date date=new Date(currentTimeMillis);
			// student.setStu_create_date(date);
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, telephone);
			prepareStatement.setString(2, password);
			prepareStatement.executeQuery();
			resultSet = prepareStatement.getResultSet();
			if (resultSet.next()) {
				// int int1 = resultSet.getInt("sch_id");
				// school = getSchoolById(resultSet.getInt("sch_id"));
				// System.currentTimeMillis();
				student = new Student();
				student.setStuId(resultSet.getInt("stu_id"));
				student.setStuName(resultSet.getString("stu_name"));
				student.setStuPhone(telephone);
				student.setStuIma(resultSet.getString("stu_img"));
				student.setStuSex(resultSet.getString("stu_sex"));
				student.setStuAge(resultSet.getInt("stu_age"));
				student.setStuUgrade(resultSet.getString("stu_ugrade"));
				student.setStuMajor(resultSet.getString("stu_major"));
				student.setStuSigner(resultSet.getString("stu_signer"));
				student.setSchool(getSchoolById(resultSet.getInt("sch_id")));
				Date date = new Date(resultSet.getTimestamp("stu_create_date").getTime());
				student.setStu_create_date(date);
				return student;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, resultSet, prepareStatement);
		}

		return null;

	}

	@Override
	public Student getStuByNamepwd(String name, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStuByID(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public School getSchoolById(int schID) {
		Connection connection = DBconnection.getConnection();
		String sql = "select * from school where sch_id=?;";
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, schID);
			// prepareStatement.setString(2, password);
			executeQuery = prepareStatement.executeQuery();
			School school = null;
			if (executeQuery.next()) {
				school = new School();
				school.setSchId(schID);
				school.setSchName(executeQuery.getString("sch_name"));
				school.setSchLatitude(executeQuery.getString("sch_latitude"));
				school.setSchLongitude("sch_longitude");
				return school;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, executeQuery, prepareStatement);
		}

		return null;

	}

	@Override
	public List<MyCoupon> getPoinCouByStuId(int stuID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudyTime> getPoinStuTimeByStuID(int stuID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Answer> getPoinAnsByStuID(int stuID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getPoinQuesByStuID(int stuID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreName getStoreNameByCouID(int stoID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FavoritesCoupons> getFavoritecouByStuID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public boolean savePoints(PointNum pointNum) {
		Connection connection = DBconnection.getConnection();
		PreparedStatement prepareStatement = null;
		String sql = "insert into point_num (point_num,point_from_id,point_create_time)values(?,?,?);";
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, pointNum.getPointNum());
			prepareStatement.setInt(2, pointNum.getFromPoint().getFromPointid());
			prepareStatement.setTimestamp(3, new Timestamp(pointNum.getPointTime().getTime()));
			prepareStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		} finally {
			CloseDb.close(connection, prepareStatement);
		}

	}

	/**
	 * 通过手机和密码在数据库里加入一个学生对象 返回一个布尔类型 加入成功true 没加入false
	 */
	@Override
	public boolean register(String telephone, String password) {
		Connection connection = DBconnection.getConnection();
		// INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
		String sql = "insert into student (stu_phone,stu_pwd) values(?,?)";
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, telephone);
			prepareStatement.setString(2, password);
			prepareStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			CloseDb.close(connection, prepareStatement);
		}

	}

}
