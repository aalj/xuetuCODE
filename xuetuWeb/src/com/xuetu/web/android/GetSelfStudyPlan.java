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

/**
 * 
 * ClassName:GetSelfStudyPlan<br/>
 * 
 * Function: 用于获取数据库自定义自学计划的数据
	<br/>
 * 
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   liang
 * @version  
 * @since    Ver 1.1
 * @Date	 2016	2016年3月12日		下午3:00:38
 *
 * @see
 */

@WebServlet("/GetSelfStudyPlan")
public class GetSelfStudyPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FindServicesInter findService = new FindService(new FindIml());
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String parameter = request.getParameter("StuID");
		parameter = URLDecoder.decode(parameter, "utf-8");
		
		List<SelfStudyPlan> allSelfStudyPlan =  null;
		
		if(parameter!=null){
			
			allSelfStudyPlan = findService.getAllSelfStudyPlan(Integer .parseInt(parameter));
			
		}
		PrintWriter writer = response.getWriter();
		Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				  .create();
		String temp = gson.toJson(allSelfStudyPlan);
		writer.print(temp);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
