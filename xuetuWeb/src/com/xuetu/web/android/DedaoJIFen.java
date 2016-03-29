package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.JifenDao;

/**
 * Servlet implementation class DedaoJIFen
 */
@WebServlet("/DedaoJIFen")
public class DedaoJIFen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JifenDao dao = new JifenDao();
		String parameter = request.getParameter("stuid");
		System.out.println("------------->>>>>>>>>>的到积分");
		int countjifen = dao.countjifen(Integer.parseInt(parameter));
		
		PrintWriter writer = response.getWriter();
		writer.print(countjifen);
		System.out.println("------------->>>>>>>>>>的到积分结束");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
