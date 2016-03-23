package com.xuetu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuetu.entity.Answer;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.Question;
import com.xuetu.entity.School;
import com.xuetu.entity.StoreName;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;
import com.xuetu.entity.Subject;
import com.xuetu.utils.CloseDb;
import com.xuetu.utils.DBconnection;

public class JifenDao {
	QuestionIml questionIml = new QuestionIml();
	LoginDao student = new LoginDao();

	/**
	 * 获得兑换消费的优惠券的积分
	 */
	public List<MyCoupon> queryAllCouponById(int stuId) {

		return student.getPoinCouByStuId(stuId);
	}

	/**
	 * 获得学习时间获得积分
	 */
	public List<StudyTime> queryAllStudyTimeById(int stuId) {

		Connection conn = DBconnection.getConnection();
		String sql = "select * from  studytime where   stu_id= ?";
		ResultSet query = null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, stuId);
			query = statement.executeQuery();
			List<StudyTime> list = new ArrayList<>();
			StudyTime studyTime = null;
			while (query.next()) {
				studyTime = new StudyTime();
				studyTime.setAcpo_num(query.getInt("acpo_num"));// st_id
				
				studyTime.setDate(query.getTimestamp("st_date"));// st_date
				studyTime.setTime(query.getLong("sto_time"));// sto_time
				
				Student stuByID = student.getStuByID(query.getInt("stu_id"));
				studyTime.setStudent(stuByID);// stu_id
				// acpo_num
				list.add(studyTime);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(conn, query, statement);
		}

		return null;
	}

	/**
	 * 获得回答问题的积分
	 */
	public List<Answer> queryAllAnswerByid(int stuId) {

		LoginDao student = new LoginDao();
		Connection conn = DBconnection.getConnection();
		String sql = "select * from answer where  stu_id= ?";
		ResultSet query = null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, stuId);
			query = statement.executeQuery();
			List<Answer> list = new ArrayList<>();
			Answer ans = null;
			while (query.next()) {
				ans = new Answer();
				ans.setAnsID(query.getInt("ans_id")); // 
				int ques_id = query.getInt("ques_id");
				ans.setQuestion(questionIml.getQuestionByQuesId(ques_id)); // ques_id
				int stu_id = query.getInt("stu_id");

				ans.setStudent(student.getStuByID(stu_id)); // stu_id

				ans.setAnsText(query.getString("ans_text")); // ans_text
				ans.setAnsImg(query.getString("ans_ima")); // ans_ima
				ans.setAnsTime(query.getTimestamp("ans_time")); // ans_time
				list.add(ans);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(conn, query, statement);
		}

		return null;

	}

	/**
	 * 获得提问题消费的积分
	 */
	public List<Question> queryAllQuestionByid(int stuId) {

		Connection conn = DBconnection.getConnection();
		String sql = "select * from question where  stu_id= ?";
		ResultSet query = null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, stuId);
			query = statement.executeQuery();
			List<Question> list = new ArrayList<>();
			while (query.next()) {
				Question q = new Question();
				q.setQuesID(query.getInt("ques_id"));
				q.setStudent(questionIml.getStudentByStuId(query.getInt("stu_id"),
						questionIml.getSchIdByStuId(query.getInt("stu_id"))));
				q.setQuesText(query.getString("ques_text"));
				// q.setQuesIma(quesIma);
				q.setQuesDate(query.getTimestamp("ques_time"));
				q.setAcpo_num(query.getInt("acpo_num"));
				q.setSubject(questionIml.getSubjectBySubId(query.getInt("sub_id")));
				list.add(q);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(conn, query, statement);
		}

		return null;
	}

	
	
	
	public int countjifen(int stuId){
		int count = 100;
		System.out.println("coun1t----->"+count);
		count+= queryAllAnswerByid(stuId).size()*5;
		count-=queryAllAnswerByid(stuId).size()*3;
		List<StudyTime> queryAllStudyTimeById = queryAllStudyTimeById(stuId);
		
		for(int i = 0;i<queryAllStudyTimeById.size();i++){
			count+=queryAllStudyTimeById.get(i).getAcpo_num();
		}
		List<MyCoupon> queryAllCouponById = queryAllCouponById(stuId);
		for (int i = 0; i < queryAllCouponById.size(); i++) {
			count-=queryAllCouponById.get(i).getCoupon().getCoouRedeemPoints();
		}
		
		return count;
	}


}
