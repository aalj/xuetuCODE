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
 * Servlet implementation class ReadSQLByUidServlet
 */
@WebServlet("/ReadSQLByUidServlet")
public class ReadSQLByUidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalServiceInter personalServiceInter = new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String phone = request.getParameter("telephone");
		String sex = request.getParameter("sex");
		String image = request.getParameter("image");
		String name = request.getParameter("name");

		String telephone = URLDecoder.decode(phone, "utf-8");
		String stu_sex = URLDecoder.decode(sex, "utf-8");
		String stu_image = URLDecoder.decode(image, "utf-8");
		String stu_name = URLDecoder.decode(name, "utf-8");
		boolean bb = personalServiceInter.addNewUser(telephone, stu_sex, stu_name, stu_image);
		boolean b = personalServiceInter.UpdataByUid(telephone);

		Gson gson = new Gson();
		String json = gson.toJson(b);
		System.out.println(b);
		response.getWriter().print(json);

	}

}
