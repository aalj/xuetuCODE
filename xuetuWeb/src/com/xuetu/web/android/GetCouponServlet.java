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
import com.xuetu.dao.ShoppingDao;
import com.xuetu.entity.Coupon;
import com.xuetu.service.ShoopingServer;
import com.xuetu.service.inter.ShoppingInter;

/**
 * Servlet implementation class GetCouponServlet
 */
@WebServlet("/GetCouponServlet")
public class GetCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ShoppingInter shoppingInter = new ShoopingServer(new ShoppingDao());
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String page = request.getParameter("page");
		String unm = request.getParameter("num");
		String reqtemp = request.getParameter("reqtemp");
		int pageunm = 0;
		int unmunm = 10;
		int reqtempnum=-1;
		if(page!=null&&unm!=null&&reqtemp != null){
			pageunm = Integer.parseInt(page);
			unmunm = Integer.parseInt(unm);
			reqtempnum=Integer.parseInt(reqtemp);
		}
		List<Coupon> couponAll = null;
		if(reqtempnum==0){//查询全部的优惠券
			couponAll = shoppingInter.getCouponlimmit(pageunm,unmunm);
			
		}else{//查询有ID上网店家
			couponAll= shoppingInter.getCouponAll(reqtempnum);
			
			
		}
		System.out.println("刷新数据");
		PrintWriter writer = response.getWriter();
		Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				  .create();
		String temp = gson.toJson(couponAll);
		writer.print(temp);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
