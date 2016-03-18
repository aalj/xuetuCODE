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
import javax.servlet.http.HttpSession;

import com.xuetu.entity.Coupon;
import com.xuetu.service.CouService;

/**
 * 
 * ClassName:couAddServlet<br/>
 * 
 * Function: 增加优惠券<br/>
 * 
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016 2016年2月25日 下午7:28:02
 *
 * @see
 */
@WebServlet("/couAddServlet")
public class couAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CouService couService = new CouService();

	/**
	 * Default constructor.
	 */
	public couAddServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 设置编码 获得页面数据，封装成Coupon对象，调用Service层的添加方法
		 */
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		int stoId = (int) session.getAttribute("storeNameId");
		if (stoId != 0) {

			String cou_num = request.getParameter("cou_num");
			String cou_info = request.getParameter("cou_info");
			String cou_name = request.getParameter("cou_name");
			String cou_price = request.getParameter("cou_price");
			String cou_Validity = request.getParameter("cou_Validity");
			String cou_redeem_points = request.getParameter("cou_redeem_points");
			try {
				// 创建Coupon对象
				Coupon coupon = new Coupon();
				coupon.setConNum(Integer.parseInt(cou_num));
				coupon.setCoouRedeemPoints(Integer.parseInt(cou_redeem_points));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				coupon.setConValidity(sdf.parse(cou_Validity));
				coupon.setCouName(cou_name);
				coupon.setCouPrice(Integer.parseInt(cou_price));
				coupon.setCouInfo(cou_info);
				coupon.setCouponCreate(new Date(System.currentTimeMillis()));
				// 调用Service方法添加优惠券
				couService.CouponAdd(coupon, stoId);
				request.getRequestDispatcher("/CouponListServlet").forward(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		}
	}

}
