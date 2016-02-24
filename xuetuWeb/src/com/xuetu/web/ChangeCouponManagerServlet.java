package com.xuetu.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.CouponDao2;
import com.xuetu.dao.StoreNameDao2;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.StoreName;
import com.xuetu.service.CouService2;

/**
 * Servlet implementation class ChangeCouponManagerServlet
 */
@WebServlet("/ChangeCouponManagerServlet")
public class ChangeCouponManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Content-type", "text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		try {
			Coupon coupon = null;
			int storeNmae = Integer.parseInt(request.getParameter("storeId"));
			StoreName storeName = new StoreNameDao2().getStoreNameById(storeNmae);
			System.out.println("kankan\t"+request.getParameter("cou_name"));
			String couName = request.getParameter("cou_name");
			
			
			
			System.out.println(couName);
			int couPrice = Integer.parseInt(request.getParameter("cou_price"));
			int couRedeemPoints = Integer.parseInt(request.getParameter("cou_redeem_points"));
			int couNum = Integer.parseInt(request.getParameter("cou_num"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
			String couValidity = request.getParameter("cou_Validity");
			Date date = dateFormat.parse(couValidity);
			String couInfo = request.getParameter("cou_info");
			int couPonID = Integer.parseInt(request.getParameter("couponID"));
			coupon = new Coupon(couPonID, storeName, couInfo, couNum, date, couName, couPrice, couRedeemPoints);

			new CouService2().saveCoupon(coupon);
			System.out.println("shifou zhixing dao gaihang ");
			request.getRequestDispatcher("/CouponListServlet").forward(request, response);

		} catch (ParseException e) {

			//
			e.printStackTrace();

		}
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
