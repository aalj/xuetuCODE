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
import com.xuetu.service.FindService;
import com.xuetu.service.inter.FindServicesInter;
import com.xuetu.utils.ChuliShijian;

/**
 * Servlet implementation class Back
 */
@WebServlet("/BackStudyTime")
public class BackStudyTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FindServicesInter findService = new FindService(new FindIml());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String parameter = request.getParameter("StuID");
		parameter = URLDecoder.decode(parameter, "utf-8");
		System.out.println(parameter);
		SelfStudyPlan allSelfStudyPlan = null;
		List<SelfStudyPlan> allSelfStudyPlantemp = null;
		PrintWriter writer = response.getWriter();
		if (parameter != null) {

			allSelfStudyPlantemp = findService.getAllSelfStudyPlan(Integer.parseInt(parameter));
			System.out.println(allSelfStudyPlantemp.size());
			// 去除时间
			allSelfStudyPlan = ChuliShijian.shiJianPanDuanZuiJing(allSelfStudyPlantemp);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		String favcoupons = gson.toJson(allSelfStudyPlan);
		System.out.println(favcoupons+"aaaaaaaaaaaaaaaaaaa");
		writer.print(favcoupons);
		
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
