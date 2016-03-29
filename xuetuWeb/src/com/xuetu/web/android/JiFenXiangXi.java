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
import com.xuetu.dao.JifenDao;
import com.xuetu.entity.JiFenMingXi;

/**
 * Servlet implementation class JiFenXiangXi
 */
@WebServlet("/JiFenXiangXi")
public class JiFenXiangXi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String stuid = request.getParameter("stuid");
		String weeknum = request.getParameter("weeknum");
		int stuidnum= 0;
		int weeknumnum= 0;
		if(stuid!=null&&weeknum!=null){
			stuidnum = Integer.parseInt(URLDecoder.decode(stuid, "utf-8"));
			weeknumnum = Integer.parseInt(URLDecoder.decode(weeknum, "utf-8"));
		}
		System.out.println("---->>>"+stuidnum+"----->>>>>>>"+weeknumnum);
		List<JiFenMingXi> paixuJifenMingxi = new JifenDao().paixuJifenMingxi(stuidnum, weeknumnum);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		PrintWriter writer = response.getWriter();
		String json = gson.toJson(paixuJifenMingxi);
		System.out.println("积分详情"+json);
		writer.print(json);
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
