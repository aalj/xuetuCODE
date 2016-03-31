package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.xuetu.entity.CollectionQuestion;

/**
 * Servlet implementation class IsSaveQuestion
 */
@WebServlet("/IsSaveQuestion")
public class IsSaveQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int stu_id;
	private int ques_id;
	ArrayList<Integer> ids = new ArrayList<Integer>();
	QuesTionDao q = new QuestionIml();
	List<CollectionQuestion> cqs = new ArrayList<CollectionQuestion>();
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
		PrintWriter pw = response.getWriter();
		ids = (ArrayList<Integer>) q.isSave();
//		Set<Map.Entry<Integer,CollectionQuestion>> cqSet = 
//		Gson gson = new GsonBuilder()
//				.enableComplexMapKeySerialization()
//				.setPrettyPrinting()
//				.disableHtmlEscaping()
//				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(ids);
		pw.write(jsonStr);
	}

}
