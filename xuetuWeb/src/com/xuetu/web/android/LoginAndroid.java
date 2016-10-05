package com.xuetu.web.android;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.LoginDao;
import com.xuetu.entity.Student;
import com.xuetu.service.LoginService;
import com.xuetu.service.inter.PersonalServiceInter;

/**
 * Servlet implementation class LoginAndroid
 */
@WebServlet("/LoginAndroid")
public class LoginAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PersonalServiceInter personalServiceInter = new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");

		String telephone = request.getParameter("telephone");
		String password = request.getParameter("pwd");
		if (telephone != null & password != null) {
			telephone = URLDecoder.decode(telephone, "utf-8");
			password = URLDecoder.decode(password, "utf-8");
		}
		System.out.println(telephone+"-----"+password);
		Student stusByPhoneAndPwd = personalServiceInter.getStusByPhoneAndPwd(telephone, password);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String stusByPhoneAndPwds = gson.toJson(stusByPhoneAndPwd);
		response.getWriter().print(stusByPhoneAndPwds);

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
