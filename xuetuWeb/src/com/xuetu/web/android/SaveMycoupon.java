package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xuetu.dao.ShoppingDao;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.entity.MyCoupon;
import com.xuetu.service.ShoopingServer;
import com.xuetu.service.inter.ShoppingInter;

/**
 * Servlet implementation class SaveMycoupon
 */
@WebServlet("/SaveMycoupon")
public class SaveMycoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ShoppingInter shoppingInter = new ShoopingServer(new ShoppingDao());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String parameter = request.getParameter("mycoupon");
		if(parameter!=null){
			parameter=URLDecoder.decode(parameter, "utf-8");
		}
		System.out.println(parameter);
		Gson  gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Type type = new TypeToken<MyCoupon>() {
		}.getType();
		MyCoupon myCoupon=gson.fromJson(parameter, type);
		System.out.println(myCoupon);
		boolean b = shoppingInter.saveMycoupon(myCoupon);
		PrintWriter writer = response.getWriter();
		if(b){
			writer.print("ok");
		}else{
			writer.print("no");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
