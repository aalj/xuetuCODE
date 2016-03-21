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
import com.xuetu.entity.MyCoupon;
import com.xuetu.service.LoginService;
import com.xuetu.service.inter.PersonalServiceInter;

/**
 * Servlet implementation class TheCollectionOfYouHuiJuanServlet
 */
@WebServlet("/TheCollectionOfYouHuiJuanServlet")
public class TheCollectionOfYouHuiJuanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalServiceInter personalServiceInter = new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String parameter = request.getParameter("stuid");
		System.out.println(parameter);
		String decode = URLDecoder.decode(parameter, "utf-8");
		int stuid = Integer.valueOf(decode);
		List<MyCoupon> couByStuId = personalServiceInter.getPoinCouByStuId(stuid);
		System.out.println(couByStuId);
		Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				  .create();
		String mycoupons = gson.toJson(couByStuId);
		System.out.println(mycoupons);
		response.getWriter().print(mycoupons);

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
