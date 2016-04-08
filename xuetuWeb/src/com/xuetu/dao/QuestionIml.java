package com.xuetu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xuetu.dao.inter.QuesTionDao;
import com.xuetu.entity.Answer;
import com.xuetu.entity.CollectionQuestion;
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
	public  List<Answer> getAnswerByQuesId(int ques_id,int page,int num) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = null;
		
		PreparedStatement prep = null;
		try {
			conn = DBconnection.getConnection();
			sql = "select * from answer where ques_id=? order by answer.ans_time desc limit "+ (page-1)*num+","+num;
			prep = conn.prepareStatement(sql);
			prep.setInt(1, ques_id);
			ResultSet rs = prep.executeQuery();
			List<Answer> answers = new ArrayList<Answer>();
			 Map<List<Integer>,List<Answer>> m = new  HashMap<List<Integer>,List<Answer>>();
			// 指针从第一行属性字段开始
			while (rs.next()) {
				Answer a = new Answer();
				a.setAnsID(rs.getInt("ans_id"));
				a.setQuestion(getQuestionByQuesId(rs.getInt("ques_id")));
				a.setStudent(getStudentByStuId(rs.getInt("stu_id"), getSchIdByStuId(rs.getInt("stu_id"))));
				a.setAnsText(rs.getString("ans_text"));
				a.setAnsImg(rs.getString("ans_ima"));
				a.setAnsTime(rs.getTimestamp("ans_time"));
				a.setAgrNum(getAgrNumByAnsId(rs.getInt("ans_id")));
				answers.add(a);
			}
			return answers;
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
	public Integer getAgrNumByAnsId(int ans_id){
		int count = 0;
		Connection conn = null;
		PreparedStatement prep = null;
		String sql = null;
		conn = DBconnection.getConnection();
		try {
			sql = "select count(*) from agreement where ans_id=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1,ans_id);
			ResultSet rs = prep.executeQuery();
			rs.next();
			// 指针从第一行属性字段开始
//			while (rs.next()) {
//				count++;
//			}
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		List<Integer> saveNum = null;
		Map<List<Integer>,List<Question>> qreturn = new HashMap<List<Integer>,List<Question>>();
//		Set<Integer> saveNum = null;
//		Map<Set<Integer>,List<Question>> qreturn = new HashMap<Set<Integer>,List<Question>>();
		// 指针从第一行属性字段开始
		try {
			conn = DBconnection.getConnection();
			sql = "select * from question order by ques_time desc limit "
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
				q.setQuesIma(rs.getString("ques_img"));
				q.setQuesDate(rs.getTimestamp("ques_time"));
				q.setAcpo_num(rs.getInt("acpo_num"));
				q.setSubject(getSubjectBySubId(rs.getInt("sub_id")));
				q.setAns_num(getAnsNum(rs.getInt("ques_id")));
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
							+ "(stu_id,ques_text,ques_img,ques_time,acpo_num,sub_id)" 
					+ "values (?,?,?,?,?,?)";
					// 3、获得preparedStatement对象
					prep = conn.prepareStatement(sql);
					// 4、设置？的值
					prep.setInt(1,q.getStudent().getStuId() );
					prep.setString(2,q.getQuesText());
					prep.setString(3, q.getQuesIma());
					prep.setTimestamp(4, new Timestamp(q.getQuesDate().getTime()));
					prep.setInt(5,q.getAcpo_num());
					prep.setInt(6,q.getSubject().getSubId());
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
	public Question createQuestion(int stuId, String quesText,String quesIma, Date quesTime, int acpoNum, int subId,int sch_id) {
		// TODO Auto-generated method stub
		Question q = new Question(getStudentByStuId(stuId, sch_id), quesText,quesIma, quesTime, getSubjectBySubId(subId),acpoNum);
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

	@Override
	public void submitAnswer(Answer answer) {
		// 1、连数据库
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句,图品还没加
			String sql = "insert into answer"
					+ "(ques_id,stu_id,ans_text,ans_ima,ans_time)" 
			+ "values (?,?,?,?,?)";
			// 3、获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setInt(1,answer.getQuestion().getQuesID());
			prep.setInt(2,answer.getStudent().getStuId());
			prep.setString(3,answer.getAnsText());
			prep.setString(4,answer.getAnsImg());
			prep.setTimestamp(5, new Timestamp(answer.getAnsTime().getTime()));
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
	public List<Answer> queryLimitAnswer(int page, int num) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prep= null;
		String sql = null;
		List<Answer> answers = new ArrayList<Answer>();
		ResultSet rs = null;
		// 指针从第一行属性字段开始
		try {
			conn = DBconnection.getConnection();
			sql = "select * from answer order by ans_time desc limit "
					+ (page-1)*num+","+num;
			prep = conn.prepareStatement(sql);
			/*prep.setInt(1, 2);
			prep.setInt(2, 2);*/
			rs = prep.executeQuery(sql);
			while (rs.next()) {
				Answer a = new Answer();
				a.setAnsID(rs.getInt("ans_id"));
				a.setQuestion(getQuestionByQuesId(rs.getInt("ques_id")));
				a.setStudent(getStudentByStuId(rs.getInt("stu_id"), getSchIdByStuId(rs.getInt("stu_id"))));
				a.setAnsText(rs.getString("ans_text"));
				a.setAnsImg(rs.getString("ans_ima"));
				a.setAnsTime(rs.getTimestamp("ans_time"));
				answers.add(a);
			}
			return answers;
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
	public Question getQuestionByQuesId(int ques_id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = null;
		PreparedStatement prep = null;
		try {
			conn = DBconnection.getConnection();
			sql = "select * from question where ques_id=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, ques_id);
			ResultSet rs = prep.executeQuery();
			Question q = new Question();
			// 指针从第一行属性字段开始
			if (rs.next()) {
				q.setQuesID(rs.getInt("ques_id"));
				q.setStudent(getStudentByStuId(rs.getInt("stu_id"), getSchIdByStuId(rs.getInt("stu_id"))));
				q.setQuesText(rs.getString("ques_text"));
				q.setQuesIma(rs.getString("ques_img"));
				q.setQuesDate(rs.getTimestamp("ques_time"));
				q.setAcpo_num(rs.getInt("acpo_num"));
				q.setAns_num(getAnsNum(rs.getInt("ques_id")));
				q.setSubject(getSubjectBySubId(rs.getInt("sub_id")));
			}
			return q;
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
	public Answer createAnswer(int ques_id, int stu_id, String ans_text,String ans_ima,Date ans_time) {
		// TODO Auto-generated method stub
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句,图品还没加
			String sql = "insert into answer"
					+ "(stu_id,ques_id,ans_text,ans_ima,ans_time)" 
			+ "values (?,?,?,?,?)";
			// 3、获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setInt(1,stu_id);
			prep.setInt(2,ques_id);
			prep.setString(3, ans_text);
			prep.setString(4, ans_ima);
			prep.setTimestamp(5, new Timestamp(ans_time.getTime()));
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
		return getAns(ques_id,stu_id,ans_text);
	}

	public Answer getAns(int ques_id,int stu_id,String ans_text){
		PreparedStatement prep = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句,图品还没加
			String sql = "select * from answer where ques_id=? and stu_id=? and ans_text=?";
			// 3、获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setInt(1,ques_id);
			prep.setInt(2, stu_id);
			prep.setString(3, ans_text);
			// 5、执行sql语句
			rs = prep.executeQuery();
			
			if (rs.next()) {
				Answer a = new Answer();
				a.setAnsID(rs.getInt("ans_id"));
				a.setQuestion(getQuestionByQuesId(rs.getInt("ques_id")));
				a.setStudent(getStudentByStuId(rs.getInt("stu_id"), getSchIdByStuId(rs.getInt("stu_id"))));
				a.setAnsText(rs.getString("ans_text"));
				a.setAnsImg(rs.getString("ans_ima"));
				a.setAnsTime(rs.getTimestamp("ans_time"));
				return a;
			}
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
		return null;
	}
	@Override
	public List<Question> getAllQuestion() {
		// TODO Auto-generated method stub
			Connection conn = null;
			PreparedStatement prep= null;
			String sql = null;
			List<Question> questions = new ArrayList<Question>();
			ResultSet rs = null;
			// 指针从第一行属性字段开始
			try {
				conn = DBconnection.getConnection();
				sql = "select * from question order by ques_time desc ";
				prep = conn.prepareStatement(sql);
				/*prep.setInt(1, 2);
				prep.setInt(2, 2);*/
				rs = prep.executeQuery(sql);
				while (rs.next()) {
					Question q = new Question();
					q.setQuesID(rs.getInt("ques_id"));
					q.setStudent(getStudentByStuId(rs.getInt("stu_id"), getSchIdByStuId(rs.getInt("stu_id"))));
					q.setQuesText(rs.getString("ques_text"));
					q.setQuesIma(rs.getString("ques_img"));
					q.setQuesDate(rs.getTimestamp("ques_time"));
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
	public void collectQuestion(int stu_id, int ques_id, Date ques_time) {
		// TODO Auto-generated method stub
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句,图品还没加
			String sql = "insert into collectionquestion"
					+ "(stu_id,ques_id,ques_time_collect)" 
			+ "values (?,?,?)";
			// 3、获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setInt(1,stu_id);
			prep.setInt(2,ques_id);
			prep.setTimestamp(3, new Timestamp(ques_time.getTime()));
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
	public void collectCancelQuestion(int stu_id, int ques_id) {
		// TODO Auto-generated method stub
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句,图品还没加
			String sql = " delete from collectionquestion where stu_id=? and ques_id=?";
			// 3、获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setInt(1,stu_id);
			prep.setInt(2,ques_id);
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
	public void agreeAnswer(int ans_id, int stu_id, Date agr_date) {
		// TODO Auto-generated method stub
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句,图品还没加
			String sql = "insert into agreement"
					+ "(ans_id,stu_id,agr_date)" 
			+ "values (?,?,?)";
			// 3、获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setInt(1,ans_id);
			prep.setInt(2,stu_id);
			prep.setTimestamp(3, new Timestamp(agr_date.getTime()));
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
	public void disAgreeAnswer(int ans_id, int stu_id) {
		// TODO Auto-generated method stub
		PreparedStatement prep = null;
		Connection conn = null;
		try {
			conn = DBconnection.getConnection();
			// 2、SQL语句,图品还没加
			String sql = " delete from agreement where ans_id=? and stu_id=?";
			// 3、获得preparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setInt(1,ans_id);
			prep.setInt(2,stu_id);
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
	public Boolean isSave(int ques_id,int stu_id) {
		Connection conn = null;
		PreparedStatement prep= null;
		String sql = null;
		ResultSet rs = null;
		Set<Integer> l = new HashSet<Integer>();
		// 指针从第一行属性字段开始
			conn = DBconnection.getConnection();
//			sql = "select * from collectionquestion where stu_id=?";
			sql = " select * from collectionquestion where ques_id=? and stu_id=?";
			List<CollectionQuestion> cqs = new ArrayList<CollectionQuestion>();
			try {
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ques_id);
				prep.setInt(2, stu_id);
				rs = prep.executeQuery();
				if (rs.next()) {
					return true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}

	@Override
	public List<Question> getQuesBySubId(int sub_id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = null;
		
		PreparedStatement prep = null;
		try {
			conn = DBconnection.getConnection();
			sql = "select * from question where sub_id=? order by ques_time desc ";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, sub_id);
			ResultSet rs = prep.executeQuery();
			List<Question> questions = new ArrayList<Question>();
			// 指针从第一行属性字段开始
			while (rs.next()) {
				Question q = new Question();
				q.setQuesID(rs.getInt("ques_id"));
				q.setStudent(getStudentByStuId(rs.getInt("stu_id"), getSchIdByStuId(rs.getInt("stu_id"))));
				q.setQuesText(rs.getString("ques_text"));
				q.setQuesIma(rs.getString("ques_img"));
				q.setQuesDate(rs.getTimestamp("ques_time"));
				q.setAcpo_num(rs.getInt("acpo_num"));
				q.setSubject(getSubjectBySubId(rs.getInt("sub_id")));
				q.setAns_num(getAnsNum(rs.getInt("ques_id")));
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
	public Set<Integer> getAgreeAnswerByStuId(int stu_id) {
		Connection conn = null;
		String sql = null;
		PreparedStatement prep = null;
		Set<Integer> s = new HashSet<Integer>();
		try {
			conn = DBconnection.getConnection();
			sql = "select ans_id from agreement where stu_id=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, stu_id);
			ResultSet rs = prep.executeQuery();
			// 指针从第一行属性字段开始
			while (rs.next()) {
				s.add(rs.getInt("ans_id"));
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


