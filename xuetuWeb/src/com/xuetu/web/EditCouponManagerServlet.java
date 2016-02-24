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
import javax.sound.midi.Soundbank;

import com.xuetu.dao.StoreNameDao2;
import com.xuetu.entity.Coupon;
import com.xuetu.entity.StoreName;
import com.xuetu.service.CouService2;

@WebServlet("/EditCouponManagerServlet")
public class EditCouponManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到本次请求属于什么类型
		//1---表示显示操作
		//2---表示修改数据提交
		String flag = request.getParameter("flags");
		request.setCharacterEncoding("UTF-8");
		response.addHeader("Content-type", "text/html;charset=utf-8");
		System.out.println("flag\t"+flag);
		
		if("1".equals(flag)){
			CouService2 couService2 = new CouService2();
			Coupon coupon = couService2.getCoupon(Integer.parseInt(request.getParameter("strID")));
			HttpSession session =request.getSession();
			session.setAttribute("editCoupon", coupon);
			request.getRequestDispatcher("/cat_edit.jsp").forward(request, response);
			request.removeAttribute("flags");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
