package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.QuestionIml;
import com.xuetu.dao.inter.QuesTionDao;

/**
 * Servlet implementation class GetAgreeAnswer
 */
@WebServlet("/GetAgreeAnswer")
public class GetAgreeAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int stu_id;
	QuesTionDao q = new QuestionIml();
	Set<Integer> s = new HashSet<Integer>();
	private String jsonStr;
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
		s = q.getAgreeAnswerByStuId(stu_id);
		Gson gson = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		jsonStr = gson.toJson(s);
		PrintWriter pw = response.getWriter();
		pw.write(jsonStr);
	}

}
