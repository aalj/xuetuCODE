
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
import com.xuetu.dao.CLassTimeDao;
import com.xuetu.entity.MyClass;
import com.xuetu.service.ClassTimeService;
import com.xuetu.service.inter.ClassTimeServiceInter;

/**
 * Servlet implementation class GetClassTime
 */
@WebServlet("/GetClassTime")
public class GetClassTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		System.out.println("stu_id>>>>>>>>>>>>111>>>>>>>>>>>>>");
		String sstu_id = request.getParameter("stu_id");// 从安卓端传过来的学生id
		CLassTimeDao classtimedao = new CLassTimeDao(); // 新建一个CLassTimeDao 对象
		System.out.println("stu_id>>>>>>>>>>>>222>>>>>>>>>>>>>");
		// 将安卓端传送来的学生id 查找对应的学生的所选课程,然后将这些课程id装到集合里面
		int stu_id = Integer.parseInt(sstu_id);
		System.out.println("stu_id>>>>>>>>>>>>333>>>>>>>>>>>>>");
		List<Integer> classID = classtimedao.get_student_clsID(stu_id);
		System.out.println("stu_id>>>>>>>>>>>>444>>>>>>>>>>>>>");
		// 获得符合现在时间的课程,(条件-- 星期几,第几节课,)得出符合现在时间段课程的所有课程对象
		List<MyClass> myclasslist = classtimedao.getMyClass();
		System.out.println("stu_id>>>>>>>>>>>>555>>>>>>>>>>>>>");
		// 将两个所得出的值中的 classID进行匹配,匹配到相同的则 表示有对应的课程,并且符合 提前5分钟以内,并且迟到不超过10分钟
		boolean b = classtimedao.isStudy(classID, myclasslist);
		long time_ss = 0;

		// 全部匹配成功后则,如果b的值为true 获取time_ss的值,否则值为0;
		if (b) {
			time_ss = classtimedao.sendTime_SS(b);
		}
		System.out.println("sssssssssssssssss" + time_ss);
		PrintWriter pw = response.getWriter();
		Gson gson = new GsonBuilder().create();
		// int intgson = 0;
		String jsonStr = null;

		jsonStr = gson.toJson(time_ss);

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
