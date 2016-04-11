package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.CouponDao2;
import com.xuetu.entity.Coupon;

/**
 * Servlet implementation class GetACoupon
 */
@WebServlet("/GetACoupon")
public class GetACoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String parameter = request.getParameter("id");
		int i = 0;
		if(parameter!=null){
			i=Integer.parseInt(parameter);
		}
		Coupon queryCoupon = new CouponDao2().queryCoupon(i);
		Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				  .create();
		String json = gson.toJson(queryCoupon);
		
		PrintWriter writer = response.getWriter();
		writer.print(json);
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
