
package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xuetu.dao.CLassTimeDao;
import com.xuetu.entity.MyClass;
import com.xuetu.entity.Student;
import com.xuetu.service.ClassTimeService;
import com.xuetu.service.inter.ClassTimeServiceInter;

/**
 * Servlet implementation class GetClassTime
 */
@WebServlet("/GetClassTime")
public class GetClassTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Student student;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String sst = request.getParameter("student");// 从安卓端传过来的学生对象
		CLassTimeDao classtimedao = new CLassTimeDao(); // 新建一个CLassTimeDao 对象
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		//解析传送过来的学生对象
		Type type = new TypeToken<Student>() {}.getType();
		student = gson.fromJson(sst, type);
		
		// 将安卓端传送来的学生id 查找对应的学生的所选课程,然后将这些课程id装到集合里面
		int stu_id = student.getStuId();
		List<Integer> classID = classtimedao.get_student_clsID(stu_id);
		// 获得符合现在时间的课程,(条件-- 星期几,第几节课,)得出符合现在时间段课程的所有课程对象
		List<MyClass> myclasslist = classtimedao.getMyClass();
		// 将两个所得出的值中的 classID进行匹配,匹配到相同的则 表示有对应的课程,并且符合 提前5分钟以内,并且迟到不超过10分钟
		MyClass myclass = classtimedao.isStudy(classID, myclasslist);
		long time_ss = 0;
		String class_name=null;
		//如果课程对象不为空
		if (myclass!=null) {
			time_ss = classtimedao.sendTime_SS();
			class_name= myclass.getClasName();
		}
		System.out.println("sssssssssssssssss" + time_ss);
		
		PrintWriter pw = response.getWriter();
	
		// int intgson = 0;
		String jsonStr = null;

		jsonStr = gson.toJson(time_ss+"-"+class_name);

		pw.write(jsonStr);
		
		pw.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		System.out.println("add doget");
		doGet(request, response);
	}

}
