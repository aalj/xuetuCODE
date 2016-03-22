package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.ShoppingDao;
import com.xuetu.service.ShoopingServer;
import com.xuetu.service.inter.ShoppingInter;

/**
 * Servlet implementation class IssavefavoritesServlet
 */
@WebServlet("/IssavefavoritesServlet")
public class IssavefavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ShoppingInter shoppingInter = new ShoopingServer(new ShoppingDao());
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String couponIDs = request.getParameter("couponID");
		String studentids = request.getParameter("studentid");
		int couponID=0;
		int studentid=0;
		if(couponIDs!=null&&studentids!=null){
			couponID = Integer.parseInt(couponIDs);
			studentid = Integer.parseInt(studentids);
		}
		System.out.println(couponID+"\t"+studentid);
		boolean b = shoppingInter.Issavefavorites(couponID, studentid);
		PrintWriter writer = response.getWriter();
		
		if(b){
			writer.print("ok");
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
