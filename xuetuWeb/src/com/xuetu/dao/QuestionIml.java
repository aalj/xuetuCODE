package com.xuetu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.xuetu.dao.inter.QuesTionDao;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.entity.School;
import com.xuetu.entity.Student;
import com.xuetu.entity.Subject;
import com.xuetu.utils.DBconnection;

public class QuestionIml implements QuesTionDao {

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
	//分页查询所有问题
	@Override
	public List<Question> queryLimitQuestion(int page, int num) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prep= null;
		String sql = null;
		List<Question> questions = new ArrayList<Question>();
		ResultSet rs = null;
		// 指针从第一行属性字段开始
		try {
			conn = DBconnection.getConnection();
			sql = "select * from question limit "
					+ (page-1)*num+","+num;
			prep = conn.prepareStatement(sql);
			/*prep.setInt(1, 2);
			prep.setInt(2, 2);*/
			rs = prep.executeQuery(sql);
			while (rs.next()) {
				Question q = new Question();
				q.setQuesID(rs.getInt("ques_id"));
				q.setStudent(getStudentByStuId(rs.getInt("stu_id"), getSchIdByStuId(rs.getInt("stu_id"))));
				q.setQuesText(rs.getString("ques_text"));
				//q.setQuesIma(quesIma);
				q.setQuesDate(rs.getDate("ques_time"));
				q.setAcpo_num(rs.getInt("acpo_num"));
				q.setSubject(getSubjectBySubId(rs.getInt("sub_id")));
				questions.add(q);
			}
			return questions;
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

	@Override
	public void submitQuestion(Question q) {
		// TODO Auto-generated method stub
		// 1、连数据库
				PreparedStatement prep = null;
				Connection conn = null;
				try {
					conn = DBconnection.getConnection();
					// 2、SQL语句,图品还没加
					String sql = "insert into question"
							+ "(stu_id,ques_text,ques_time,acpo_num,sub_id)" 
					+ "values (?,?,?,?,?)";
					// 3、获得preparedStatement对象
					prep = conn.prepareStatement(sql);
					// 4、设置？的值
					prep.setInt(1,q.getStudent().getStuId() );
					prep.setString(2,q.getQuesText());
					prep.setTimestamp(3, new Timestamp(q.getQuesDate().getTime()));
//					prep.setDate(3, new java.sql.Date(q.getQuesDate().getTime()));
					prep.setInt(4,q.getAcpo_num());
					prep.setInt(5,q.getSubject().getSubId());
					//prep.setDate(6, new java.sql.Date(student.getBirthday().getTime()));
					// 5、执行sql语句
					prep.executeUpdate();
				} catch (Exception e) {
					// 一定要处理异常,异常的信息要存在日志文件
					// 转化为应用程序的异常，再抛出
					throw new RuntimeException(e);
				} finally {
					// 6、关闭资源
					try {
						if (prep != null)
							prep.close();
						if (conn != null)
							conn.close();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
	}

	@Override
	public Question createQuestion(int stuId, String quesText, Date quesTime, int acpoNum, int subId,int sch_id) {
		// TODO Auto-generated method stub
		Question q = new Question(getStudentByStuId(stuId, sch_id), quesText, quesTime, getSubjectBySubId(subId),acpoNum);
		return q;
	}

	@Override
	public int getSchIdByStuId(int stuId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = null;
		PreparedStatement prep = null;
		try {
			conn = DBconnection.getConnection();
			sql = "select sch_id from student where stu_id=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, stuId);
			ResultSet rs = prep.executeQuery();
			// 指针从第一行属性字段开始
			if (rs.next()) {
				return rs.getInt("sch_id");
			}
			return 0;
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


