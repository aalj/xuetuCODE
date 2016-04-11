package com.xuetu.web.android;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.LoginDao;
import com.xuetu.service.LoginService;
import com.xuetu.service.inter.PersonalServiceInter;

/**
 * Servlet implementation class ChangeSchoolServlet
 */
@WebServlet("/ChangeSchoolServlet")
public class ChangeSchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalServiceInter personalServiceInter = new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String userphone = request.getParameter("phone");
		String changeschool = request.getParameter("school");
		System.out.println(changeschool);
		String usergrade=URLDecoder.decode(request.getParameter("grade"), "utf-8");
		int parseInt = Integer.parseInt(changeschool);
		boolean changeSchool2 = personalServiceInter.ChangeSchool(userphone, parseInt,usergrade);
		System.out.println("修改学校" + changeSchool2);
		response.getWriter().print(changeSchool2);

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