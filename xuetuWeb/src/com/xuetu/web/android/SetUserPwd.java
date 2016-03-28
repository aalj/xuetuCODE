package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.LoginDao;

/**
 * Servlet implementation class SetUserPwd
 */
@WebServlet("/SetUserPwd")
public class SetUserPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String phone = URLDecoder.decode(request.getParameter("phone"), "utf-8");
		String pwd = URLDecoder.decode(request.getParameter("pwd"), "utf-8");
		System.out.println(phone+"\t"+pwd);
		boolean updateStu_pwd = new LoginDao().updateStu_pwd(phone, pwd);

		PrintWriter writer = response.getWriter();
		if (!updateStu_pwd) {
			writer.print("no");
		}
		writer.print("ok");
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
