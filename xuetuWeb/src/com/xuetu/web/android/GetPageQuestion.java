package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.google.gson.reflect.TypeToken;
import com.xuetu.dao.QuestionIml;
import com.xuetu.entity.Question;
import com.xuetu.service.QuestionService;
import com.xuetu.service.inter.QuestionServiceInter;

/**
 * Servlet implementation class GetAllQuestion
 */
@WebServlet("/GetPageQuestion")
public class GetPageQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QuestionServiceInter q = new QuestionService(new QuestionIml());
	List<Question> questions = new ArrayList<Question>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("getQuestion");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		System.out.println("In"+sdf.format(new Date(System.currentTimeMillis())));
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String page = request.getParameter("page");
		String unm = request.getParameter("num");
		int pageunm = 1;
		int unmunm = 10;
		if(page!=null&&unm!=null){
			pageunm = Integer.parseInt(page);
			unmunm = Integer.parseInt(unm);
		}
//		Map<Set<Integer>, List<Question>> questions = new HashMap<Set<Integer>, List<Question>>();
//			questions = q.queryLimitQuestion(pageunm, unmunm);
		questions = q.queryLimitQuestion(pageunm, unmunm);
		String jsonStr = null;
		Gson gson = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		jsonStr = gson.toJson(questions);
		PrintWriter pw = response.getWriter();
		pw.write(jsonStr);
//		System.out.println("end"+sdf.format(new Date(System.currentTimeMillis())));
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
