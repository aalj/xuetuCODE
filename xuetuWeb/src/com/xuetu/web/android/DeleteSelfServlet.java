package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.FindIml;

/**
 * Servlet implementation class DeleteSelfServlet
 */
@WebServlet("/DeleteSelfServlet")
public class DeleteSelfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parameter = request.getParameter("planID");
		int planid = -1;
		if(parameter!=null){
			planid=Integer.parseInt(parameter);
		}
		
		boolean delSelfPlan = new FindIml().delSelfPlan(planid);
		PrintWriter writer = response.getWriter();
		if(!delSelfPlan){
			writer.print(1);
		}
		
		writer.print(0);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
