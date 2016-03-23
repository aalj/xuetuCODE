package com.xuetu.web.android;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.CLassTimeDao;
import com.xuetu.service.ClassTimeService;
import com.xuetu.service.inter.ClassTimeServiceInter;

/**
 * Servlet implementation class GetClassTime
 */
@WebServlet("/GetClassTime")
public class GetClassTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		int stu_id=Integer.parseInt(request.getParameter("stu_id"));//从安卓端传过来的学生id
		System.out.println("stu_id>>>>>>>>>>>>>>>>>>>>>>>>>"+stu_id);  
		CLassTimeDao classtimedao = new CLassTimeDao();
		List<Integer> classID = classtimedao.get_student_clsID(stu_id);
//		int [] classid ={};
		
//		int ii[] = null ;
//		
//		for(int i=0;i<classID.size();i++)
//		{
//			ii[i]=classID.get(i);
//		}
//		
		if(classID.get(0)!= null)
		{
			for(int i=0;i<classID.size();i++)
			classtimedao.getClass();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		System.out.println("add doget");
		doGet(request, response);
	}

}
