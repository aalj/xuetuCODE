package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.SendQianDaoDateDao;

/**
 * Servlet implementation class GetQianDaoMessage
 */
@WebServlet("/GetQianDaoMessage")
public class GetQianDaoMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SendQianDaoDateDao sendqiandaodao = new SendQianDaoDateDao();
	List <String>  list = new ArrayList<>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int stu_id = Integer.parseInt(request.getParameter("stu_id"));
		list = sendqiandaodao.get_qiandao_date(stu_id);
		PrintWriter writer = response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String qiandaoDate = gson.toJson(list);
		writer.print(qiandaoDate);
		
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
