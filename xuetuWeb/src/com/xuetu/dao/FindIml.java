/**
 * FindIml.java
 * com.xuetu.dao
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年3月12日 		liang
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
import java.util.ArrayList;
import java.util.List;

import com.xuetu.dao.inter.FindInter;
import com.xuetu.entity.Countdown;
import com.xuetu.entity.LongTime;
import com.xuetu.entity.Pattern;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;
import com.xuetu.utils.CloseDb;
import com.xuetu.utils.DBconnection;

/**
 * ClassName:FindIml Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年3月12日 下午3:14:00
 *
 * @see
 * 
 */
public class FindIml implements FindInter {
	Connection connection = null;

	@Override
	public Countdown getCountDown() {
		connection = DBconnection.getConnection();

		return null;

	}

	@Override
	public StudyTime getStudyTime(Student name) {
		connection = DBconnection.getConnection();

		return null;

	}

	@Override
	public List<StudyTime> getWeekTime(String beforeData, String afterData) {
		connection = DBconnection.getConnection();

		return null;

	}

	@Override
	public List<SelfStudyPlan> getSelfPlan(int stuId) {
		System.out.println("stuId------->>>>" + stuId);
		connection = DBconnection.getConnection();
		ResultSet query = null;
		PreparedStatement statement = null;
		try {

			String sql = "select * from selfstudyplan where  stu_id =? and del_flag=0 ORDER BY start_time   ";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, stuId);
			query = statement.executeQuery();
			SelfStudyPlan plan = null;
			List<SelfStudyPlan> list = new ArrayList<>();
			while (query.next()) {
				plan = new SelfStudyPlan();
				plan.setPlanID(query.getInt("plan_id"));
				Date date = query.getDate("start_time");
				plan.setStartTime(query.getTimestamp("start_time"));
				plan.setEndTime(query.getTimestamp("end_time"));
				plan.setPlanText(query.getString("plan_text"));
				plan.setPlanReming(query.getInt("plan_remind"));
				// 通过学习模式的Id的到对应的学习模式对象
				Pattern pattern = getPatternById(query.getInt("pattern_id"));
				plan.setPattern(pattern);
				// TODO 无法调用方法得到对应的对象
				plan.setStudent(null);
				plan.setPlanDate(query.getTimestamp("plan_date"));
				plan.setIsZhiXing(query.getInt("is_zhixing"));
				list.add(plan);

			}
			return list;

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} finally {
			CloseDb.close(connection, query, statement);
		}

	}

	public List<SelfStudyPlan> getDaySelfPlan(int stuId) {
		connection = DBconnection.getConnection();
		ResultSet query = null;
		PreparedStatement statement = null;
		try {

			String sql = "select * from selfstudyplan where  stu_id =? and del_flag=0 and start_time>= now()   ORDER BY start_time ";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, stuId);
			query = statement.executeQuery();
			SelfStudyPlan plan = null;
			List<SelfStudyPlan> list = new ArrayList<>();
			while (query.next()) {
				plan = new SelfStudyPlan();
				plan.setPlanID(query.getInt("plan_id"));
				Date date = query.getDate("start_time");
				plan.setStartTime(query.getTimestamp("start_time"));
				plan.setEndTime(query.getTimestamp("end_time"));
				plan.setPlanText(query.getString("plan_text"));
				plan.setPlanReming(query.getInt("plan_remind"));
				// 通过学习模式的Id的到对应的学习模式对象
				Pattern pattern = getPatternById(query.getInt("pattern_id"));
				plan.setPattern(pattern);
				// TODO 无法调用方法得到对应的对象
				plan.setStudent(null);
				plan.setPlanDate(query.getTimestamp("plan_date"));

				list.add(plan);

			}
			return list;

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} finally {
			CloseDb.close(connection, query, statement);
		}

	}

	@Override
	public Pattern getPatternById(int patID) {
		connection = DBconnection.getConnection();
		PreparedStatement statement = null;
		ResultSet query = null;

		try {
			String sql = "select * from pattern where pattern_id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, patID);
			query = statement.executeQuery();
			Pattern pattern = new Pattern();
			if (query.next()) {
				pattern.setPatternID(patID);
				pattern.setPattrenText(query.getString("pattern_text"));

			}
			return pattern;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} finally {
			CloseDb.close(connection, query, statement);
		}

	}

	@Override
	public List<Pattern> getAllPattern() {
		connection = DBconnection.getConnection();
		PreparedStatement statement = null;
		ResultSet query = null;
		String sql = "select * from pattern;";
		try {
			List<Pattern> list = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			query = statement.executeQuery();
			Pattern pattern = null;
			while (query.next()) {
				pattern = new Pattern();
				pattern.setPatternID(query.getInt("pattern_id"));
				pattern.setPattrenText(query.getString("pattern_text"));
				list.add(pattern);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			CloseDb.close(connection, query, statement);
		}
	}

	@Override
	public boolean updateSelfStudyPlan(SelfStudyPlan plan) {
		connection = DBconnection.getConnection();
		PreparedStatement prepareStatement = null;
		try {
			// update 表名 set name=?,password=?.... where id=?
			String sql = "update selfstudyplan set start_time=?,end_time=?, plan_text=?, plan_remind=?, pattern_id=? where plan_id=?";
			prepareStatement = connection.prepareStatement(sql);

			prepareStatement.setTimestamp(1, new Timestamp(plan.getStartTime().getTime()));
			prepareStatement.setTimestamp(2, new Timestamp(plan.getEndTime().getTime()));
			prepareStatement.setString(3, plan.getPlanText());
			prepareStatement.setInt(4, plan.getPlanReming());
			prepareStatement.setInt(5, plan.getPattern().getPatternID());
			prepareStatement.setInt(6, plan.getPlanID());
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

	@Override
	public boolean insertSelfStudyPlan(SelfStudyPlan plan) {
		connection = DBconnection.getConnection();
		PreparedStatement prepareStatement = null;
		try {
			// update 表名 set name=?,password=?.... where id=?
			String sql = "Insert into selfstudyplan  (start_time,end_time,plan_text,plan_remind,pattern_id,stu_id,plan_date)  values(?,?,?,?,?,?,?);";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setTimestamp(1, new Timestamp(plan.getStartTime().getTime()));
			prepareStatement.setTimestamp(2, new Timestamp(plan.getEndTime().getTime()));
			prepareStatement.setString(3, plan.getPlanText());
			prepareStatement.setInt(4, plan.getPlanReming());
			prepareStatement.setInt(5, plan.getPattern().getPatternID());
			// TODO 暂时存储的是固定的学生
			prepareStatement.setInt(6, plan.getStudent().getStuId());

			prepareStatement.setTimestamp(7, new Timestamp(plan.getPlanDate().getTime()));
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

	@Override
	public List<LongTime> getWeekTime(int stu_id,int i) {
		Connection conn = DBconnection.getConnection();
		List<LongTime> list = new ArrayList<>();
		PreparedStatement statement = null;
		ResultSet query = null;
		try {
			String sql = "select sum(sto_time) as mytime," + 
						"date_format(st_date,'%Y-%m-%d') as myDate,"
					+ "stu_id from studytime "
					+ "where stu_id=? and date_sub(curdate(), INTERVAL ? DAY) <= date(`st_date`) "
					+ "group by date_format(st_date,'%Y-%m-%d')  order by myDate desc;";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, stu_id);
			statement.setInt(2, i*7);
			query = statement.executeQuery();
			LongTime e = null;
			while (query.next()) {
				e = new LongTime();
				e.setMyDate(query.getDate("myDate"));
				e.setMyTime(query.getLong("mytime"));
				e.setStudent(null);
				list.add(e);

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

	public boolean delSelfPlan(int planid) {
		Connection conn = DBconnection.getConnection();
		PreparedStatement statement = null;

		String sql = "update selfstudyplan set del_flag=? where plan_id =? ";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, planid);
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDb.close(conn, statement);
		}
		return false;
	}
	
	
	public List<Countdown> getCountdown() {
		connection = DBconnection.getConnection();
		ResultSet query = null;
		PreparedStatement statement = null;
		try {

			String sql = "select * from countdown ORDER BY code_time   ";
			statement = connection.prepareStatement(sql);

//			codo_id
//			code_time
//			codo_text
			
			query = statement.executeQuery();
			Countdown plan = null;
			List<Countdown> list = new ArrayList<>();
			while (query.next()) {
				plan = new Countdown();
				plan.setCodoID(query.getInt("codo_id"));
				plan.setCodoTime(query.getTimestamp("code_time"));
				plan.setCodoText(query.getString("codo_text"));
				list.add(plan);
			}
			return list;

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} finally {
			CloseDb.close(connection, query, statement);
		}

	}
	
	

}
