package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.SendQianDaoDateDao;

/**
 * Servlet implementation class GetTodayStudyTime
 */
@WebServlet("/GetTodayStudyTime")
public class GetTodayStudyTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SendQianDaoDateDao sendqiandaodao = new SendQianDaoDateDao();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int stu_id = Integer.parseInt(request.getParameter("stu_id"));
		long ss = sendqiandaodao.get_today_studytime(stu_id);
		Gson gson = new GsonBuilder().create();
		PrintWriter pw = response.getWriter();
		String todayTime = gson.toJson(ss);
		pw.write(todayTime);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
