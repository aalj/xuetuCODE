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
import java.util.ArrayList;
import java.util.List;

import com.xuetu.dao.inter.FindInter;
import com.xuetu.entity.Countdown;
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

	@Override
	public Countdown getCountDown() {

		return null;

	}

	@Override
	public StudyTime getStudyTime(Student name) {

		return null;

	}

	@Override
	public List<StudyTime> getWeekTime(String beforeData, String afterData) {

		return null;

	}

	@Override
	public List<SelfStudyPlan> getSelfPlan(int stuId) {
		Connection connection = null;
		ResultSet query = null;
		PreparedStatement statement = null;
		try {
			connection = DBconnection.getConnection();

			String sql = "select * from selfstudyplan where  stu_id =? ";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, stuId);
			query = statement.executeQuery();
			SelfStudyPlan plan = null;
			List<SelfStudyPlan> list = new ArrayList<>();
			while (query.next()) {
				plan = new SelfStudyPlan();
				plan.setPlanID(query.getInt("plan_id"));
				Date date = query.getDate("start_time");
				System.out.println(date.getTime());
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
		Connection conn= null;
		PreparedStatement statement = null;
		ResultSet query = null;

		try {
			conn = DBconnection.getConnection();
			String sql = "select * from pattern where pattern_id = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, patID);
			query = statement.executeQuery();
			Pattern pattern = new 
					Pattern();
			if(query.next()){
				pattern.setPatternID(patID);
				pattern.setPattrenText(query.getString("pattern_text"));
				
			}
			return pattern;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}finally {
			CloseDb.close(conn,query ,statement);
		}


	}

	@Override
	public List<Pattern> getAllPattern() {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet query=null;
		String sql="select * from pattern;";
		try {
			List<Pattern> list = new ArrayList<>();
			connection = DBconnection.getConnection();
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
		}finally {
			CloseDb.close(connection, query, statement);
		}
	}

}
