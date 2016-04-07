package com.xuetu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.xuetu.entity.Answer;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.JiFenMingXi;
import com.xuetu.entity.MyCoupon;
import com.xuetu.entity.Question;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;
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

	public int countjifen(int stuId) {
		int count = 0;
		count += queryAllAnswerByid(stuId).size() * 5;
		count -= queryAllQuestionByid(stuId).size() * 3;
		List<StudyTime> queryAllStudyTimeById = queryAllStudyTimeById(stuId);

		for (int i = 0; i < queryAllStudyTimeById.size(); i++) {
			count += queryAllStudyTimeById.get(i).getAcpo_num();
		}
		List<MyCoupon> queryAllCouponById = queryAllCouponById(stuId);
		for (int i = 0; i < queryAllCouponById.size(); i++) {
			count -= queryAllCouponById.get(i).getCoupon().getCoouRedeemPoints();
		}

		return count;
	}

	/**
	 * 分组查询一周以内的回答产生的积分
	 * 
	 * @param stuid
	 * @param weekpage
	 * @return
	 */
	public List<JiFenMingXi> getAnswerLimitByStuId(int stuid, int weekpage) {
		// select * from studytime where stu_id=? and date_sub(curdate(),
		// INTERVAL 1 DAY) <= date(`st_date`) and acpo_num>0 ORDER BY st_date
		// desc;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from answer  where stu_id=? and date_sub(curdate(), INTERVAL ? DAY) <= date(`ans_time`)  ORDER BY ans_time desc;";
		PreparedStatement statement = null;
		ResultSet query = null;
		List<JiFenMingXi> list = new ArrayList<JiFenMingXi>();
		JiFenMingXi jiFenMingXi = null;
		try {

			statement = conn.prepareStatement(sql);
			statement.setInt(1, stuid);
			statement.setInt(2, weekpage*2);
			query = statement.executeQuery();
			while (query.next()) {
				jiFenMingXi = new JiFenMingXi();
				jiFenMingXi.setImgUrl(2 + "");
				jiFenMingXi.setText("回答问题得到到积分");
				jiFenMingXi.setTime(query.getTimestamp("ans_time"));
				jiFenMingXi.setUnmpuint(5);// st_id
				jiFenMingXi.setId(query.getInt("ans_id"));
				// acpo_num
				list.add(jiFenMingXi);

			}

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分组查询一周以内提问题花费的积分
	 * 
	 * @param stuid
	 * @param weekpage
	 * @return
	 */
	public List<JiFenMingXi> getQuestionLimitByStuId(int stuid, int weekpage) {
		// select * from studytime where stu_id=? and date_sub(curdate(),
		// INTERVAL 1 DAY) <= date(`st_date`) and acpo_num>0 ORDER BY st_date
		// desc;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from question  where stu_id=? and date_sub(curdate(), INTERVAL ? DAY) <= date(`ques_time`)   ORDER BY ques_time desc;";
		PreparedStatement statement = null;
		ResultSet query = null;
		List<JiFenMingXi> list = new ArrayList<JiFenMingXi>();
		JiFenMingXi jiFenMingXi = null;
		try {

			statement = conn.prepareStatement(sql);
			statement.setInt(1, stuid);
			statement.setInt(2, weekpage*2);
			query = statement.executeQuery();
			while (query.next()) {
				jiFenMingXi = new JiFenMingXi();
				jiFenMingXi.setImgUrl(1 + "");
				jiFenMingXi.setText("回答问题得到到积分");
				jiFenMingXi.setTime(query.getTimestamp("ques_time"));
				jiFenMingXi.setUnmpuint(5);// st_id
				jiFenMingXi.setId(query.getInt("ques_id"));
				// acpo_num
				list.add(jiFenMingXi);

			}

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分组查询一周兑换优惠券消费的优惠券
	 */
	public List<JiFenMingXi> getCouponLimitByStuId(int stuid, int weekpage) {
		// select * from studytime where stu_id=? and date_sub(curdate(),
		// INTERVAL 1 DAY) <= date(`st_date`) and acpo_num>0 ORDER BY st_date
		// desc;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from mycoupon  where stu_id=? and date_sub(curdate(), INTERVAL ? DAY) <= date(`mycou_exchange_time`)   ORDER BY mycou_exchange_time desc;";
		PreparedStatement statement = null;
		ResultSet query = null;
		List<JiFenMingXi> list = new ArrayList<JiFenMingXi>();
		JiFenMingXi jiFenMingXi = null;
		try {

			statement = conn.prepareStatement(sql);
			statement.setInt(1, stuid);
			statement.setInt(2, weekpage*2);
			query = statement.executeQuery();
			while (query.next()) {
				jiFenMingXi = new JiFenMingXi();
				query.getInt("cou_id");
				jiFenMingXi.setImgUrl(getStoneNameByMyCouponId(query.getInt("mycou_id")));
				String stoneNameByMyCouponId = getStoneNameByMyCouponId(query.getInt("mycou_id"));
				jiFenMingXi.setText("兑换优惠券花费到积分");
				jiFenMingXi.setTime(query.getTimestamp("mycou_exchange_time"));
				
				Coupon queryCoupon = dao2.queryCoupon(query.getInt("cou_id"));
				jiFenMingXi.setUnmpuint(queryCoupon.getCoouRedeemPoints());// st_id
				
				jiFenMingXi.setId(query.getInt("mycou_id"));
				// acpo_num
				list.add(jiFenMingXi);

			}

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分组查询一周以内产生的学习时间
	 */
	public List<JiFenMingXi> getStudyTimeLimitByStuId(int stuid, int weekpage) {
		// select * from studytime where stu_id=? and date_sub(curdate(),
		// INTERVAL 1 DAY) <= date(`st_date`) and acpo_num>0 ORDER BY st_date
		// desc;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from studytime  where stu_id=? and date_sub(curdate(), INTERVAL ? DAY) <= date(`st_date`) and acpo_num>0  ORDER BY st_date desc;";
		PreparedStatement statement = null;
		ResultSet query = null;
		List<JiFenMingXi> list = new ArrayList<JiFenMingXi>();
		JiFenMingXi jiFenMingXi = null;
		try {

			statement = conn.prepareStatement(sql);
			statement.setInt(1, stuid);
			statement.setInt(2, weekpage*2);
			query = statement.executeQuery();
			while (query.next()) {
				jiFenMingXi = new JiFenMingXi();
				jiFenMingXi.setImgUrl(3 + "");
				jiFenMingXi.setText("学习时间的到积分");
				jiFenMingXi.setTime(query.getTimestamp("st_date"));
				jiFenMingXi.setUnmpuint(query.getInt("acpo_num"));// st_id
				jiFenMingXi.setId(query.getInt("st_id"));
				// acpo_num
				list.add(jiFenMingXi);

			}

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	CouponDao2 dao2 = new CouponDao2();
	public String getStoneNameByMyCouponId(int myCouponid) {
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement statement = null;
			ResultSet query = null;
			String sql = "select * from mycoupon where mycou_id = ? ";
			statement = conn.prepareStatement(sql);
			statement.setInt(1,myCouponid);
			query = statement.executeQuery();
			
			if (query.next()) {
				//通过的到优惠券的id查询优惠券的，然后通过优惠券的到店家图片的
				Coupon queryCoupon = dao2.queryCoupon(query.getInt("cou_id"));
				String stoImg = queryCoupon.getCouIma();
				return stoImg;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	public List<JiFenMingXi> paixuJifenMingxi(int stuid,int weekpage ){
		List<JiFenMingXi> list= new ArrayList<JiFenMingXi>();
		list.addAll(getAnswerLimitByStuId(stuid, weekpage));
		list.addAll(getQuestionLimitByStuId(stuid, weekpage));
		list.addAll(getStudyTimeLimitByStuId(stuid, weekpage));
		list.addAll(getCouponLimitByStuId(stuid, weekpage));
		
		 Collections.sort(list, new Comparator<JiFenMingXi>(){  
			  
	            /*  
	             * int compare(Student o1, Student o2) 返回一个基本类型的整型，  
	             * 返回负数表示：o1 小于o2，  
	             * 返回0 表示：o1和o2相等，  
	             * 返回正数表示：o1大于o2。  
	             */  
	            

				@Override
				public int compare(JiFenMingXi o1, JiFenMingXi o2) {
					 //按照学生的年龄进行升序排列  
	                if(o1.getTime().getTime() < o2.getTime().getTime()){  
	                    return 1;  
	                }  
	                if(o1.getTime().getTime() == o2.getTime().getTime()){  
	                    return 0;  
	                }  
	                return -1; 
				}  
	        });   
	   
		
		return list;
	}
	
	
	
	
	

}
