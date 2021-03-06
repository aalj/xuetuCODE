package com.xuetu.web.android;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xuetu.dao.FindIml;
import com.xuetu.dao.TimeDao;
import com.xuetu.entity.Student;
import com.xuetu.entity.StudyTime;
import com.xuetu.service.FindService;
import com.xuetu.service.TimeService;
import com.xuetu.service.inter.FindServicesInter;

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
	FindServicesInter findService = new FindService(new FindIml());
	
	
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//新建一个学习时间对象
		StudyTime studytime = new StudyTime();
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//新建一个学生对象
		Student student = new Student();
		//取到解析过的student类
		String student_from_android = request.getParameter("student");
		Type type = new TypeToken<Student>() {}.getType();
		System.out.println(student_from_android);
		Gson gson = new GsonBuilder().setDateFormat(
				"yyyy-MM-dd HH:mm:ss").create();
		student = gson.fromJson(student_from_android, type);
		
		
		//从手机端获取值
//		String name = request.getParameter("name");
		String integral = request.getParameter("integral");
		System.out.println(integral);
		String st_date = request.getParameter("st_date");
		System.out.println(st_date);
		String stu_id = request.getParameter("stu_id");
		System.out.println(stu_id);
		String st_id = request.getParameter("sto_id");
		System.out.println(st_id);
		String st_time = request.getParameter("st_time");
		System.out.println(st_time);
		
		int plan_state = Integer.parseInt(request.getParameter("plan_state"));
		 System.out.println(plan_state+"plan_state<<<<<<<<<<<<<<<<");
		if (plan_state==2)
		{
			int plan_id = Integer.parseInt(request.getParameter("plan_id"));
			System.out.println(plan_id+"plan_id");
			new TimeDao().change_plan_state(plan_id);
		}
		
		
		studytime.setSttID(15);  //学习时间id
		studytime.setTime(Long.parseLong(st_time));  //学习时长
		studytime.setStudent(student);					//学生对象
		studytime.setAcpo_num(Integer.parseInt(integral));
//		studytime.set
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			studytime.setDate(sdf.parse(st_date));	//获得积分的时间
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TimeService timeservice = new TimeService(new TimeDao());
		timeservice.timeAdd(studytime);

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

