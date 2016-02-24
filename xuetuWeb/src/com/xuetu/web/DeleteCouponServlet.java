package com.xuetu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xuetu.service.CouService2;

/**
 * Servlet implementation class DeleteCouponServlet
 */
@WebServlet("/DeleteCouponServlet")
public class DeleteCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.addHeader("Content-type", "text/html;charset=utf-8");
		HttpSession session =request.getSession();
		int stoid = (int)session.getAttribute("storeNameId");
		int couid = Integer.parseInt(request.getParameter("strID"));
		System.out.println(stoid+"\t"+couid);
		if(new CouService2().delCoupon(stoid, couid)){
			request.getRequestDispatcher("/CouponListServlet").forward(request, response);
			System.out.println("ok");
		}else{
			
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
