package com.xuetu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;

import com.xuetu.dao.inter.QuestionInter;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.entity.School;
import com.xuetu.entity.Student;
import com.xuetu.entity.Subject;
import com.xuetu.utils.DBconnection;

public class QuestionIml implements QuestionInter {

	@Override
	public List<Question> queryQuestionByStuID(Student stu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer getAnswerByQuesId(Question qu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> queryQuestionByStuJectId(Subject sub) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> queryLimitQuestion(int page, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentByStuId(int stuId,int sch_id) {
		// TODO Auto-generated method stub
			Connection conn = null;
			String sql = null;
			PreparedStatement prep = null;
			try {
				conn = DBconnection.getConnection();
				sql = "select * from student where stu_id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, stuId);
				ResultSet rs = prep.executeQuery();
				Student s = new Student();
				// 指针从第一行属性字段开始
				if (rs.next()) {
					s.setStuId(rs.getInt("stu_id"));
					s.setStuName(rs.getString("stu_name"));
					s.setStuEmail(rs.getString("stu_email"));
					s.setStuPhone(rs.getString("stu_phone"));
					s.setStuIma(rs.getString("stu_img"));
					s.setStuSex(rs.getString("stu_sex"));
					s.setStuAge(rs.getInt("stu_age"));
					s.setStuUgrade(rs.getString("stu_ugrade"));
					s.setStuMajor(rs.getString("stu_major"));
					s.setStuSigner(rs.getString("stu_signer"));
					s.setSchool(getSchoolBySch_id(rs.getInt("sch_id")));
					s.setStuPwd(rs.getString("stu_pwd"));
					s.setStu_create_date(rs.getDate("stu_create_date"));
				}
				return s;
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (prep != null)
						prep.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	

	
	@Override
	public Subject getSubjectBySubId(int subId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = null;
		PreparedStatement prep = null;
		try {
			conn = DBconnection.getConnection();
			sql = "select * from subject where sub_id=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, subId);
			ResultSet rs = prep.executeQuery();
			Subject s = new Subject();
			// 指针从第一行属性字段开始
			if (rs.next()) {
				s.setSubId(rs.getInt("sub_id"));
				s.setName(rs.getString("sub_name"));
			}
			return s;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (prep != null)
					prep.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public School getSchoolBySch_id(int schId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = null;
		PreparedStatement prep = null;
		try {
			conn = DBconnection.getConnection();
			sql = "select * from school where sch_id=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, schId);
			ResultSet rs = prep.executeQuery();
			School s = new School();
			// 指针从第一行属性字段开始
			if (rs.next()) {
				s.setSchId(rs.getInt("sch_id"));
				s.setSchName(rs.getString("sch_name"));
				s.setSchLongitude(rs.getString("sch_longitude"));
				s.setSchLatitude(rs.getString("sch_latitude"));
			}
			return s;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (prep != null)
					prep.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}


