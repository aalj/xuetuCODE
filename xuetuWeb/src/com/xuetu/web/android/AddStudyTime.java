package com.xuetu.web.android;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.entity.StudyTime;
import com.xuetu.service.TimeService;

/**
 * ClassName:AddStudyTime
 * 
 * Function: 录入积分,学习时间,
 * 
 * @author 康毅
 * 
 * 3/14
 */
@WebServlet("/AddStudyTime")
public class AddStudyTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TimeService timeservice = new TimeService();
	
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//新建一个学习时间对象
		StudyTime studytime = new StudyTime();
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//从手机端获取值
//		String name = request.getParameter("name");
		String integral = request.getParameter("integral");
		String st_date = request.getParameter("st_date");
//		String stu_id = request.getParameter("stu_id");
		String st_id = request.getParameter("st_id");
		String st_time=request.getParameter("st_time");
		
		//把值放入对象
		studytime.setSttID(Integer.parseInt(st_id));
		studytime.setTime(Long.parseLong(st_time));
		studytime.setStudent(null);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			studytime.setDate(sdf.parse(st_date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TimeService service  = new TimeService();
		service.timeAdd(studytime);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
		
		
	}

}
