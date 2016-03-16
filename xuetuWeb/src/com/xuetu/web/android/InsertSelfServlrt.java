package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xuetu.dao.FindIml;
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.service.FindService;
import com.xuetu.service.inter.FindServicesInter;

/**
 * Servlet implementation class InsertSelfServlrt
 */
@WebServlet("/InsertSelfServlrt")
public class InsertSelfServlrt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FindServicesInter findServicesInter = new FindService(new FindIml());

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String plan_id = URLDecoder.decode(request.getParameter("self"), "UTF-8");
		System.out.println(plan_id);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		Type type = new TypeToken<SelfStudyPlan>() {
		}.getType();
		SelfStudyPlan users = gson.fromJson(plan_id, type);

		boolean plan = findServicesInter.saveSelfStudyPlan(users);
		PrintWriter writer = response.getWriter();
		if (!plan) {
			writer.print("提交失败");
		} else {

			writer.print("提交成功");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
