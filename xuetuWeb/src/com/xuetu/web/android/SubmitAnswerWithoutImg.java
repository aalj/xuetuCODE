package com.xuetu.web.android;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jspsmart.upload.SmartUpload;
import com.xuetu.dao.QuestionIml;
import com.xuetu.entity.Answer;
import com.xuetu.service.QuestionService;
import com.xuetu.service.inter.QuestionServiceInter;

/**
 * Servlet implementation class SubmitAnswerWithoutImg
 */
@WebServlet("/SubmitAnswerWithoutImg")
public class SubmitAnswerWithoutImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int ques_id = 0;
	int stu_id = 0;
	String ans_text = null;
	String ans_ima = null;
	String ans_timeStr = null;
	Date ans_time = null;
	Answer a = new Answer();
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
			ques_id = Integer.parseInt(request.getParameter("quesId"));
			stu_id =  Integer.parseInt(request.getParameter("stu_id"));
			ans_text = request.getParameter("ans_text");
			ans_timeStr = request.getParameter("ans_time");
			long parseLong = Long.parseLong(ans_timeStr);
			ans_time = new Date(new Timestamp(parseLong).getTime());
			ans_ima = "";
			a = Qservice.createAnswer(ques_id, stu_id, ans_text, ans_ima, ans_time);
			Gson gson = new GsonBuilder()
					.enableComplexMapKeySerialization()
					.setPrettyPrinting()
					.disableHtmlEscaping()
					.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String jsonStr = gson.toJson(a);
			PrintWriter pw = response.getWriter();
			pw.write(jsonStr);
			pw.flush();
			pw.close();
	}

}
