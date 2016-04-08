package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.QuestionIml;
import com.xuetu.entity.Question;

/**
 * Servlet implementation class GetquestionBuyId
 */
@WebServlet("/GetquestionBuyId")
public class GetquestionBuyId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String parameter = request.getParameter("Ques_id");
		int i=-1;
		if(parameter!=null){
			i = Integer.parseInt(parameter);
		}
		Question queryQuestionById = new QuestionIml().getQuestionByQuesId(i);
		Gson gson = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String jsonStr = gson.toJson(queryQuestionById);
		
		
		PrintWriter writer = response.getWriter();
		writer.print(jsonStr);
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
