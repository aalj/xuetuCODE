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
import com.xuetu.entity.PaiXu;

/**
 * Servlet implementation class GetPaiMing
 */
@WebServlet("/GetPaiMing")
public class GetPaiMing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SendQianDaoDateDao sendQianDaoDateDao = new SendQianDaoDateDao();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int stu_id = Integer.parseInt(request.getParameter("stu_id"));
		List <Integer> stu_all_id = new ArrayList<>();
		stu_all_id = sendQianDaoDateDao.get_paiming(stu_id);  //获得所有学生的id
		
		int [] today_studytime = sendQianDaoDateDao.get_every_studytime(stu_all_id); //全体学生的学习时间
		
		int time = (int) sendQianDaoDateDao.get_today_studytime(stu_id);//当前学生的学习时间
		String paixu = null;
		if(time==0){
			paixu="0%的学霸";
		}else{
			//用冒泡排序进行排序
			today_studytime = new PaiXu().paixu(today_studytime);
			int  hh=0;
			for(int i=0;i<today_studytime.length;i++)
			{
				if(today_studytime[i]==time)
				{
					hh=i+1;
				}
			}
			int students = today_studytime.length;
			if( students - hh ==0 )     //    总的学生数8  ,与   当前学生的排名  5
			{
				paixu="100%的学霸";
			}else
			{
				paixu=new PaiXu().getPercent(hh, students)+"的学霸";
			}
		}
		
				PrintWriter writer = response.getWriter();
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String qiandaoDate = gson.toJson(paixu);
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
