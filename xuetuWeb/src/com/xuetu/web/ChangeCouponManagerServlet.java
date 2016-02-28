package com.xuetu.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xuetu.dao.CouponDao2;
import com.xuetu.dao.StoreNameDao2;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.StoreName;
import com.xuetu.service.CouService2;

/**
 * 
 * ClassName:ChangeCouponManagerServlet<br/>
 * 
 * Function: 修改优惠券的信息<br/>
 * 
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016 2016年2月25日 下午7:29:02
 *
 * @see
 */
@WebServlet("/ChangeCouponManagerServlet")
public class ChangeCouponManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Content-type", "text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int stoId = (int) session.getAttribute("storeNameId");
		if (stoId != 0) {

			try {
				Coupon coupon = null;
				int storeNmae = Integer.parseInt(request.getParameter("storeId"));
				StoreName storeName = new StoreNameDao2().getStoreNameById(storeNmae);
				System.out.println("kankan\t" + request.getParameter("cou_name"));
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

				request.getRequestDispatcher("/CouponListServlet").forward(request, response);

			} catch (ParseException e) {

				//
				e.printStackTrace();

			}
		} else

		{
			request.getRequestDispatcher("/index.jsp").forward(request, response);

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
