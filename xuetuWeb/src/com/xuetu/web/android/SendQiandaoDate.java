package com.xuetu.web.android;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.SendQianDaoDateDao;

/**
 * Servlet implementation class SendQiandaoDate
 */
@WebServlet("/SendQiandaoDate")
public class SendQiandaoDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SendQianDaoDateDao sendqiandaodao = new SendQianDaoDateDao();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int stu_id = Integer.parseInt(request.getParameter("stu_id"));
		sendqiandaodao.send_qiandao_date(stu_id);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
