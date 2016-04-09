package com.xuetu.web.android;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.LoginDao;
import com.xuetu.entity.Answer;
import com.xuetu.service.LoginService;
import com.xuetu.service.inter.PersonalServiceInter;

/**
 * Servlet implementation class GetAnswerBystuID
 */
@WebServlet("/GetAnswerBystuID")
public class GetAnswerBystuID extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalServiceInter personalServiceInter = new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String string = request.getParameter("stuID");
		int stuid = Integer.valueOf(string);
		System.out.println(stuid+"这是数值");
		List<Answer> answerByStuIDs = personalServiceInter.getAnswerByStuID(stuid);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh-mm-ss").create();
		String json = gson.toJson(answerByStuIDs);
		System.out.println(json);
		response.getWriter().print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
