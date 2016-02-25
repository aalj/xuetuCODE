package com.xuetu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xuetu.entity.Coupon;
import com.xuetu.service.CouService2;

@WebServlet("/CouponListServlet")
public class CouponListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int attribute = (int)session.getAttribute("storeNameId");
		String string = request.getParameter("coupage");
		int page = 1;
		if(string!=null){
			page =Integer.parseInt(string);
		}
		
		int num = 10;
		CouService2 limitCoupon = new CouService2();
//		List<Coupon> coupinLost= loginService.getListCoupon(attribute);
		List<Coupon> coupinLost= limitCoupon.getLimitListCoupon(page,num,attribute);
		session.setAttribute("listCoupon", coupinLost);
		int couponCount = limitCoupon.getCouponCount(attribute);
		int pages = 0;
		if(couponCount%num==0)
			pages = couponCount/num;
		else 
			pages = couponCount/num + 1;
		session.setAttribute("page", page);
		session.setAttribute("pages", pages);
		
		
		request.getRequestDispatcher("cat_manage.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
