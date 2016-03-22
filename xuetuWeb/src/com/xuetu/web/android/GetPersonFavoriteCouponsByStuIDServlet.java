package com.xuetu.web.android;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.LoginDao;
import com.xuetu.entity.FavoritesCoupons;
import com.xuetu.service.LoginService;
import com.xuetu.service.inter.PersonalServiceInter;

/**
 * Servlet implementation class GetPersonFavoriteCouponsByStuID
 */
@WebServlet("/GetPersonFavoriteCouponsByStuIDServlet")
public class GetPersonFavoriteCouponsByStuIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalServiceInter personalServiceInter = new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String parameter = request.getParameter("stuid");
		System.out.println("dljfgnpldkfngpldg------"+parameter);
		String stuid = URLDecoder.decode(parameter, "UTF-8");
		System.out.println("--->"+stuid);
		int stuID = Integer.parseInt(stuid);
		List<FavoritesCoupons> favoritesCoup = personalServiceInter.getFavoritecouByStuID(stuID);
		System.out.println(favoritesCoup);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String favcoupons = gson.toJson(favoritesCoup);
		System.out.println(favcoupons);
		response.getWriter().print(favcoupons);
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
