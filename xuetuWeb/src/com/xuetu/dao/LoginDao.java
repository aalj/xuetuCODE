package com.xuetu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xuetu.dao.inter.PersonalDaoInterface;
import com.xuetu.entity.Answer;
import com.xuetu.entity.CollectionQuestion;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.PersonAnswerAll;
import com.xuetu.entity.PersonalStudyTimeAll;
import com.xuetu.entity.PointNum;
import com.xuetu.entity.Question;
import com.xuetu.entity.School;
import com.xuetu.entity.StoreName;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;
import com.xuetu.entity.UserState;
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
		Connection connection = DBconnection.getConnection();
		String sql = "select * from student where stu_id=?;";
		PreparedStatement prepareStatement = null;
		Student student = null;
		ResultSet resultSet = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, Id);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				student = new Student();
				student.setStuId(resultSet.getInt("stu_id"));
				student.setStuName(resultSet.getString("stu_name"));
				student.setStuPhone(resultSet.getString("stu_phone"));
				student.setStuIma(resultSet.getString("stu_img"));
				student.setStuSex(resultSet.getString("stu_sex"));
				student.setStuAge(resultSet.getInt("stu_age"));
				student.setStuUgrade(resultSet.getString("stu_ugrade"));
				student.setStuMajor(resultSet.getString("stu_major"));
				student.setStuSigner(resultSet.getString("stu_signer"));
				student.setSchool(getSchoolById(resultSet.getInt("sch_id")));
				Date date = new Date(resultSet.getTimestamp("stu_create_date").getTime());
				student.setStu_create_date(date);
				student.setStuPwd(resultSet.getString("stu_pwd"));
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

	public boolean getStuByID(String phone) {
		Connection connection = DBconnection.getConnection();
		String sql = "select * from student where stu_phone=?;";
		PreparedStatement prepareStatement = null;
		Student student = null;
		ResultSet resultSet = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, phone);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, resultSet, prepareStatement);
		}

		return false;
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

	/**
	 * 通过学生id查询个人优惠劵
	 */
	@Override
	public List<MyCoupon> getPoinCouByStuId(int stuID) {
		CouponDao2 couponDao2 = new CouponDao2();
		Connection connection = DBconnection.getConnection();
		String sql = "select * from mycoupon where stu_id = ?";
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, stuID);
			ResultSet resultSet = prepareStatement.executeQuery();
			MyCoupon myCoupon;
			List<MyCoupon> myCoupons = new ArrayList<>();
			while (resultSet.next()) {
				myCoupon = new MyCoupon();
				myCoupon.setMycouExchangeTime(resultSet.getTimestamp("mycou_exchange_time"));
				myCoupon.setMycouID(resultSet.getInt("mycou_id"));
				myCoupon.setUserState(findUserStateByUsta_id(resultSet.getInt("usta_id")));
				myCoupon.setCoupon(couponDao2.queryCoupon(resultSet.getInt("cou_id")));
				myCoupon.setStudent(getStuByID(resultSet.getInt("stu_id")));
				myCoupons.add(myCoupon);
			}
			return myCoupons;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, prepareStatement);
		}

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
		QuestionIml questionIml = new QuestionIml();
		Connection connection = DBconnection.getConnection();
		String sql = "select * from question where stu_id=?;";
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		List<Question> questionPersons = new ArrayList<>();
		Question question;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, stuID);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				question = new Question();
				question.setAns_num(getAnsNum(resultSet.getInt("ques_id")));
				question.setStudent(getStuByID(resultSet.getInt("stu_id")));
				question.setAcpo_num(resultSet.getInt("acpo_num"));
				question.setQuesID(resultSet.getInt("ques_id"));
				question.setQuesText(resultSet.getString("ques_text"));
				question.setQuesIma(resultSet.getString("ques_img"));
				Date date = new Date(resultSet.getTimestamp("ques_time").getTime());
				question.setQuesDate(date);
				question.setSubject(questionIml.getSubjectBySubId(resultSet.getInt("sub_id")));
				questionPersons.add(question);
			}
			return questionPersons;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public int getAnsNum(int ques_id){
		int count = 0;
		Connection conn = null;
		PreparedStatement prep = null;
		String sql = null;
		conn = DBconnection.getConnection();
		try {
			sql = "select * from answer where ques_id = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1,ques_id);
			ResultSet rs = prep.executeQuery();
			// 指针从第一行属性字段开始
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	@Override
	public StoreName getStoreNameByCouID(int stoID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FavoritesCoupons> getFavoritecouByStuID(int id) {
		Connection connection = DBconnection.getConnection();
		PreparedStatement prepareStatement = null;
		CouponDao2 couponDao2 = new CouponDao2();
		ResultSet resultSet = null;
		FavoritesCoupons favoritesCoupons = null;
		String sql = "select * from favoritescoupons where stu_id= ?";
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			List<FavoritesCoupons> couponlist = new ArrayList<>();
			while (resultSet.next()) {
				favoritesCoupons = new FavoritesCoupons();
				favoritesCoupons.setCreateDate(resultSet.getDate("cou_date"));
				favoritesCoupons.setCoupon(couponDao2.queryCoupon(resultSet.getInt("cou_id")));
				favoritesCoupons.setFacoID(resultSet.getInt("faco_id"));
				favoritesCoupons.setStudent(getStuByID(id));
				couponlist.add(favoritesCoupons);
			}
			return couponlist;
		} catch (SQLException e) {

			//
			e.printStackTrace();
			return null;
		} finally {
			CloseDb.close(connection, resultSet, prepareStatement);

		}

	}

	/**
	 * 通过学生id查询课程表
	 */

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
		String sql = "insert into student (stu_phone,stu_pwd,stu_create_date) values(?,?,?)";
		PreparedStatement prepareStatement = null;
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日
		// HH:mm:ss ");
		// Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		// String str = formatter.format(curDate);
		Timestamp d = new Timestamp(System.currentTimeMillis());
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, telephone);
			prepareStatement.setString(2, password);
			prepareStatement.setTimestamp(3, d);
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
	 * 通过使用状态id查询使用状态名称
	 */

	@Override
	public UserState findUserStateByUsta_id(int usta_id) {
		Connection connection = DBconnection.getConnection();
		PreparedStatement prepareStatement = null;
		String sql = "select * from userstate where usta_id=?";
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, usta_id);
			ResultSet resultSet = prepareStatement.executeQuery();
			UserState userState;
			while (resultSet.next()) {
				userState = new UserState();
				userState.setUstaID(usta_id);
				userState.setUstaName(resultSet.getString("usta_name"));
				return userState;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			CloseDb.close(connection, prepareStatement);
		}
		return null;
	}

	@Override
	public void updateStu_img(int stu_id, String stu_img) {
		// TODO Auto-generated method stub
		Connection connection = DBconnection.getConnection();
		PreparedStatement prepareStatement = null;
		String sql = " update student set stu_img=? where stu_id=?;";
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, stu_img);
			prepareStatement.setInt(2, stu_id);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				if (connection != null)
					connection.close();
				if (prepareStatement != null)
					prepareStatement.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 修改密码
	 */
	public boolean updateStu_pwd(String phone, String pwd) {
		// TODO Auto-generated method stub
		Connection connection = DBconnection.getConnection();
		PreparedStatement prepareStatement = null;
		String sql = " update student set stu_pwd=? where stu_phone=?;";
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, pwd);
			prepareStatement.setString(2, phone);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			CloseDb.close(connection, prepareStatement);
		}
		return false;
	}

	@Override
	public List<PersonalStudyTimeAll> findAllTime() {
		Connection connection = DBconnection.getConnection();
		// SELECT COUNT(*), finddate FROM yourtable GROUP BY finddate;
		String sql = "select sum(sto_time),stu_id  from studytime group by stu_id order by sum(sto_time) desc;";
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			PersonalStudyTimeAll personalStudyTimeAlls;
			List<PersonalStudyTimeAll> lists = new ArrayList<>();
			int i = 1;
			while (resultSet.next()) {
				personalStudyTimeAlls = new PersonalStudyTimeAll();
				personalStudyTimeAlls.setTimePosition(i++);
				personalStudyTimeAlls.setStudent(getStuByID(resultSet.getInt("stu_id")));
				personalStudyTimeAlls.setTimeAll(resultSet.getString("sum(sto_time)"));
				lists.add(personalStudyTimeAlls);
			}
			return lists;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, resultSet, prepareStatement);
		}

		return null;
	}

	@Override
	public boolean ChangeName(int stuID, String change_name) {
		Connection connection = DBconnection.getConnection();
		String sql = "update student set stu_name = ? where stu_id = ?";
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, change_name);
			prepareStatement.setInt(2, stuID);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, prepareStatement);
		}
		return false;
	}

	@Override
	public boolean ChangeQianMing(int stuID, String change_qianming) {
		Connection connection = DBconnection.getConnection();
		String sql = "update student set stu_signer = ? where stu_id = ?";
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, change_qianming);
			prepareStatement.setInt(2, stuID);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, prepareStatement);
		}
		return false;
	}

	@Override
	public boolean ChangeSex(int stuID, String change_sex) {
		Connection connection = DBconnection.getConnection();
		String sql = "update student set stu_sex = ? where stu_id = ?";
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, change_sex);
			prepareStatement.setInt(2, stuID);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, prepareStatement);
		}
		return false;
	}

	@Override
	public boolean ChangeAge(int stuID, String change_age) {
		Connection connection = DBconnection.getConnection();
		String sql = "update student set stu_age = ? where stu_id = ?";
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, change_age);
			prepareStatement.setInt(2, stuID);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, prepareStatement);
		}
		return false;
	}

	@Override
	public boolean ChangeGrade(int stuID, String change_grade) {
		Connection connection = DBconnection.getConnection();
		String sql = "update student set stu_ugrade = ? where stu_id = ?";
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, change_grade);
			prepareStatement.setInt(2, stuID);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, prepareStatement);
		}
		return false;
	}

	@Override
	public boolean UpdataByUid(String telephone) {
		Connection connection = DBconnection.getConnection();
		String sql = "insert into student (stu_phone,stu_pwd) values (?,?)";
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, telephone);
			prepareStatement.setString(2, telephone);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, prepareStatement);
		}

		return false;
	}

	@Override
	public boolean addNewUser(String telephone, String sex, String name, String img, String telephone_pwd) {
		Connection connection = DBconnection.getConnection();

		String sql = "insert into student (stu_phone,stu_sex,stu_name,stu_img,stu_create_date,stu_pwd) values (?,?,?,?,?,?) ";
		PreparedStatement prepareStatement = null;
		Timestamp d = new Timestamp(System.currentTimeMillis());
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, telephone);
			prepareStatement.setString(2, sex);
			prepareStatement.setString(3, name);
			prepareStatement.setString(4, img);
			prepareStatement.setTimestamp(5, d);
			prepareStatement.setString(6, telephone_pwd);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, prepareStatement);
		}
		return false;
	}

	@Override
	public Student getStuByTelephone(String telephone) {
		Connection connection = DBconnection.getConnection();
		String sql = "select * from student where stu_phone =?";
		PreparedStatement prepareStatement = null;
		Student student;
		ResultSet resultSet;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, telephone);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
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
			CloseDb.close(connection, prepareStatement);
		}
		return null;
	}

	@Override
	public List<CollectionQuestion> getPersonalCollectionQuestionByStuID(int stuID) {
		Connection connection = DBconnection.getConnection();
		String sql = "select * from collectionquestion where stu_id=?;";
		PreparedStatement prepareStatement = null;
		QuestionIml questionIml = new QuestionIml();
		ResultSet resultSet = null;
		CollectionQuestion collectionQuestion;
		List<CollectionQuestion> listPerCollQuestions = new ArrayList<>();
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, stuID);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				collectionQuestion = new CollectionQuestion();
				collectionQuestion.setCoqoID(resultSet.getInt("coqu_id"));
				collectionQuestion.setStudent(getStuByID(resultSet.getInt("stu_id")));
				collectionQuestion.setQuestion(questionIml.getQuestionByQuesId(resultSet.getInt("ques_id")));
				Date date = new Date(resultSet.getTimestamp("ques_time_collect").getTime());
				collectionQuestion.setQues_time(date);
				listPerCollQuestions.add(collectionQuestion);
			}
			return listPerCollQuestions;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, resultSet, prepareStatement);

		}
		return null;
	}

	@Override
	public List<PersonAnswerAll> getAnswerAll() {
		Connection connection = DBconnection.getConnection();
		// SELECT COUNT(*), finddate FROM yourtable GROUP BY finddate;
		String sql="select count(ans_id),stu_id from answer group by stu_id order by count(ans_id) desc;";
//		String sql = "select sum(sto_time),stu_id  from studytime group by stu_id order by sum(sto_time) desc;";
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			PersonAnswerAll personAnswerAll;
			List<PersonAnswerAll> lists = new ArrayList<>();
			int i = 1;
			while (resultSet.next()) {
				personAnswerAll = new PersonAnswerAll();
				personAnswerAll.setAnswerPosition(i++);
				personAnswerAll.setStudent(getStuByID(resultSet.getInt("stu_id")));
				personAnswerAll.setAnswerAll(resultSet.getString("count(ans_id)"));
				lists.add(personAnswerAll);
			}
			return lists;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(connection, resultSet, prepareStatement);
		}

		return null;
	}

}
