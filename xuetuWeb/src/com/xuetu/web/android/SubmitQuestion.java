package com.xuetu.web.android;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	Date quesTime = null;
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		//将json对象转化成question对象
		
		//给变量赋值
		stuId = Integer.parseInt(request.getParameter("stuId"));
		quesText = request.getParameter("quesText");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date quesTime = new Date(sdf.format(new Date(request.getParameter("quesTime"))));
		System.out.println(quesTime+"");
//		String quesImg = null;
		acpoNum = Integer.parseInt(request.getParameter("acpoNum"));
		subId = Integer.parseInt(request.getParameter("subId"));
		
		
		
		
		
		String jsonStr = request.getParameter("newQuestion");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Type type = new TypeToken<Question>(){}.getType();
		Question q = gson.fromJson(jsonStr, type);
		
	}

}
