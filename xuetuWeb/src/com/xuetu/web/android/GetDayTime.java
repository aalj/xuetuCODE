package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.FindIml;
import com.xuetu.entity.SelfStudyPlan;

/**
 * Servlet implementation class GetDayTime
 */
@WebServlet("/GetDayTime")
public class GetDayTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String parameter = request.getParameter("StuID");
		parameter = URLDecoder.decode(parameter, "utf-8");
		System.out.println(parameter);
		List<SelfStudyPlan> allSelfStudyPlantemp = null;
		PrintWriter writer = response.getWriter();
		if (parameter != null) {
			
			 allSelfStudyPlantemp = new FindIml().getDaySelfPlan(Integer.parseInt(parameter));
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>"+allSelfStudyPlantemp);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		writer.print(gson.toJson(allSelfStudyPlantemp));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
