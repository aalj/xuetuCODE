package com.xuetu.web.android;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xuetu.dao.FindIml;
import com.xuetu.entity.Pattern;
import com.xuetu.service.FindService;
import com.xuetu.service.inter.FindServicesInter;

/**
 * Servlet implementation class GetPatterServlet
 */
@WebServlet("/GetPatterServlet")
public class GetPatterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FindServicesInter findServicesInter  = new FindService(new FindIml());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		List<Pattern> allPattern = findServicesInter.getAllPattern();
		Gson gson = new Gson();
		String json = gson.toJson(allPattern);
		System.out.println(json);
		response.getWriter().print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
