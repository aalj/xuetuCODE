package com.xuetu.web.android;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xuetu.dao.LoginDao;
import com.xuetu.entity.MyClass;
import com.xuetu.service.LoginService;
import com.xuetu.service.inter.PersonalServiceInter;

/**
 * Servlet implementation class CourseAndroid
 */
@WebServlet("/CourseAndroid")
public class CourseAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalServiceInter personalServiceInter = new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String parameter = request.getParameter("stuid");
		System.out.println(parameter);
		int stuid = 0;
		if (!parameter.equals(null)) {
			stuid = Integer.parseInt(parameter);
		}

		List<MyClass> listCourse = personalServiceInter.getListCourse(stuid);
		Gson gson = new Gson();
		// 带泛型的list转化为json
		String class2 = gson.toJson(listCourse);
		System.out.println(class2);
		response.getWriter().print(class2);

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
