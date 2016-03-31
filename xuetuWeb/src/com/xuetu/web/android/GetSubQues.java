package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.QuestionIml;
import com.xuetu.dao.inter.QuesTionDao;
import com.xuetu.entity.Question;

/**
 * Servlet implementation class GetSubQues
 */
@WebServlet("/GetSubQues")
public class GetSubQues extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int sub_id;
	QuesTionDao q = new QuestionIml();
	private int page;
	private int num;
	List<Question> qs = new ArrayList<Question>();
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
		
		sub_id = Integer.parseInt(request.getParameter("sub_id"));
		qs = q.getQuesBySubId(sub_id);
//		System.out.println(qs.get(2).getQuesText());
		String jsonStr = null;
		Gson gson = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		jsonStr = gson.toJson(qs);
		PrintWriter pw = response.getWriter();
		pw.write(jsonStr);

//		System.out.println("end"+sdf.format(new Date(System.currentTimeMillis())));
		pw.close();
	}

}
