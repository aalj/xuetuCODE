package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xuetu.dao.QuestionIml;
import com.xuetu.entity.Question;
import com.xuetu.service.QuestionService;
import com.xuetu.service.inter.QuestionServiceInter;

/**
 * Servlet implementation class SubmitQuestion
 */
@WebServlet("/SubmitQuestion")
public class SubmitQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//声明变量
	int stuId = 0;
	String quesText = null;
	java.sql.Date quesTime = null;
	String quesTimeStr = null;
	Question q = new Question();
//	String quesImg = null;
	int acpoNum = 0;
	int subId = 0;
	//创建service对象
	QuestionServiceInter Qservice = new QuestionService(new QuestionIml());
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

		System.out.println("post");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		//给变量赋值
		stuId = Integer.parseInt(request.getParameter("stuId"));
		System.out.println(request.getParameter("stuId"));
		quesText = request.getParameter("quesText");
		System.out.println(quesText);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		quesTimeStr = request.getParameter("quesTime");
		long parseLong = Long.parseLong(quesTimeStr);
		quesTime = new java.sql.Date(parseLong);
		System.out.println(sdf.format(quesTime));
//		String quesImg = null;
		acpoNum = Integer.parseInt(request.getParameter("acpoNum"));
		subId = Integer.parseInt(request.getParameter("subId"));
		q = Qservice.createQuestion(stuId, quesText,quesTime, acpoNum, subId,Qservice.getSchIdByStuId(stuId));
		Qservice.submitQuestion(q);
		System.out.println("submit ok");
		PrintWriter pw = response.getWriter();
		pw.write("1");
	
		
	}

}
