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
 * Servlet implementation class ChangeQianMingServlet
 */
@WebServlet("/ChangeQianMingServlet")
public class ChangeQianMingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalServiceInter personalServiceInter=new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;utf-8");
		String stuID = request.getParameter("id");
		String changeName = request.getParameter("changeqianming");
		String decode_stuID = URLDecoder.decode(stuID, "utf-8");
		int stu_ID=Integer.parseInt(decode_stuID);
		String decode_changeName=URLDecoder.decode(changeName, "utf-8");
		boolean flag = personalServiceInter.ChangeQianMing(stu_ID, decode_changeName);
		response.getWriter().print(flag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
