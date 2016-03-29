package com.xuetu.web.android;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.QuestionIml;
import com.xuetu.dao.inter.QuesTionDao;

/**
 * Servlet implementation class DisAgreeAnswer
 */
@WebServlet("/DisAgreeAnswer")
public class DisAgreeAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String timeStr = null;
	private Date agr_date;
	private int stu_id;
	private int ans_id = 2;
	QuesTionDao q = new QuestionIml();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		stu_id = Integer.parseInt(request.getParameter("stu_id"));
		ans_id = Integer.parseInt(request.getParameter("ans_id"));
		q.disAgreeAnswer(ans_id,stu_id);
	}

}