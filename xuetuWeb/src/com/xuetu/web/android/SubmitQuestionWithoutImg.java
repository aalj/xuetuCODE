package com.xuetu.web.android;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.xuetu.dao.QuestionIml;
import com.xuetu.entity.Question;
import com.xuetu.service.QuestionService;
import com.xuetu.service.inter.QuestionServiceInter;

/**
 * Servlet implementation class SubmitQuestionWithoutImg
 */
@WebServlet("/SubmitQuestionWithoutImg")
public class SubmitQuestionWithoutImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//声明变量
		int stuId = 0;
		String quesText = null;
		java.sql.Date quesTime = null;
		String quesTimeStr = null;
		Question q = new Question();
		String quesImg = null;
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
				//获得question对象,发表问题
				stuId = Integer.parseInt(request.getParameter("stuId"));
				System.out.println(stuId);
				quesText = request.getParameter("quesText");
				System.out.println(quesText);
				quesTimeStr = request.getParameter("quesTime");
				System.out.println(quesTimeStr);
				long parseLong = Long.parseLong(quesTimeStr);
				quesTime = new Date(new Timestamp(parseLong).getTime());
				quesImg = "no";
				acpoNum = Integer.parseInt(request.getParameter("acpoNum"));
				subId = Integer.parseInt(request.getParameter("subId"));
				q = Qservice.createQuestion(stuId, quesText,quesImg,quesTime, acpoNum, subId,Qservice.getSchIdByStuId(stuId));
				Qservice.submitQuestion(q);
			
		}

}
