package com.xuetu.web.android;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xuetu.dao.LoginDao;
import com.xuetu.service.LoginService;
import com.xuetu.service.inter.PersonalServiceInter;

/**
 * Servlet implementation class RegisterAndroid
 */
@WebServlet("/RegisterAndroid")
public class RegisterAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalServiceInter personalServiceInter = new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterAndroid() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html,charset=utf-8");
		String usertelephone = request.getParameter("usertelephone");
		String password = request.getParameter("password");
		if (usertelephone != null && password != null) {
			usertelephone = URLDecoder.decode(usertelephone, "utf-8");
			password = URLDecoder.decode(password, "utf-8");
			boolean creatStudent = personalServiceInter.creatStudent(usertelephone, password);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(creatStudent);
			response.getWriter().print(jsonStr);
		
		}

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
