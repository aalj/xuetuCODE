package com.xuetu.dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:AddStudyTime
 * 
 * Function: 录入积分,学习时间,
 * 
 * @author 康毅
 * 
 *
 */
@WebServlet("/AddStudyTime")
public class AddStudyTimeDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String sql_insert = "insert into studytime(st_id,st_date,st_time,stu_id,acpo+num)"
			+ " values(?,?,?,?,?);";
	
	String sql_update="update studytime set stu_id=";
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
