package com.xuetu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xuetu.entity.Coupon;
import com.xuetu.service.CouService2;

/**
 * Servlet implementation class ShowCouponServlet
 */
@WebServlet("/ShowCouponServlet")
public class ShowCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.addHeader("Content-type", "text/html;charset=utf-8");
		
		
		
			CouService2 couService2 = new CouService2();
			Coupon coupon = couService2.getCoupon(Integer.parseInt(request.getParameter("strID")));
			HttpSession session =request.getSession();
			session.setAttribute("editCoupon", coupon);
			request.getRequestDispatcher("/cat_show.jsp").forward(request, response);
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
