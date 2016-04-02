package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.FindIml;
import com.xuetu.entity.Countdown;

/**
 * Servlet implementation class CountdownServlet
 */
@WebServlet("/CountdownServlet")
public class CountdownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat(
				"yyyy-MM-dd HH:mm:ss").create();
		 List<Countdown> countdown = new FindIml().getCountdown();
		
		
		PrintWriter writer = response.getWriter();
		writer.print(gson.toJson(countdown));
		writer.close();
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
