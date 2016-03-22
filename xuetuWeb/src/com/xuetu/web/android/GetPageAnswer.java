package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuetu.dao.QuestionIml;
import com.xuetu.entity.Answer;
import com.xuetu.service.QuestionService;
import com.xuetu.service.inter.QuestionServiceInter;

/**
 * Servlet implementation class GetPageAnswer
 */
@WebServlet("/GetPageAnswer")
public class GetPageAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int ques_id = 0;
	List<Answer> list = new ArrayList<Answer>();
	String jsonStr = null;
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
		ques_id = Integer.parseInt(request.getParameter("Ques_id"));
		list = Qservice.getAnswerByQuesId(ques_id,1,2);
		Gson gson = new Gson();
		jsonStr = gson.toJson(list);
		PrintWriter pw = response.getWriter();
		pw.write(jsonStr);
		pw.flush();
		pw.close();
	}

}
