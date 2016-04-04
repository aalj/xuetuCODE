package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.xuetu.entity.SelfStudyPlan;
import com.xuetu.service.ShoopingServer;
import com.xuetu.service.inter.ShoppingInter;

/**
 * Servlet implementation class SaveFavorites
 */
@WebServlet("/SaveFavorites")
public class SaveFavorites extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ShoppingInter shoppingInter = new ShoopingServer(new ShoppingDao());
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String parameter = request.getParameter("facou");
		if(parameter!=null){
			
			parameter=URLDecoder.decode(parameter, "utf-8");
			System.out.println(parameter);
			Gson  gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			Type type = new TypeToken<FavoritesCoupons>() {
			}.getType();
			FavoritesCoupons fa = gson.fromJson(parameter, type);
			System.out.println("我要收藏优惠券");
			boolean saveFavorites = shoppingInter.saveFavorites(fa);
			PrintWriter writer = response.getWriter();
			if(saveFavorites){
				writer.print("ok");
			}
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
