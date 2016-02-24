package com.xuetu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xuetu.entity.StoreName;
import com.xuetu.service.StorenameService2;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Content-type", "text/html;charset=utf-8");
//		response.setCharacterEncoding("utf-8");
		String username =new String( request.getParameter("username").getBytes("iso8859-1"),"utf-8");
		String userpass = new String(request.getParameter("userpass").getBytes("iso8859-1"),"utf-8");
		String valiimage = new String(request.getParameter("valiimage").getBytes("iso8859-1"),"utf-8");
		System.out.println(username+"\t"+userpass);
		HttpSession session = request.getSession();
		//得到验证码的缓存
		String systemVailimage=(String) session.getAttribute("rand");
//		if (systemVailimage.equals(valiimage)) {
		StorenameService2 loginService = new StorenameService2();
			StoreName storeName = loginService.getStoreName(username, userpass);
			if(IsLogin(storeName,username, userpass)){
				System.out.println(storeName);
				session.setAttribute("storeNameId", storeName.getStoID());
				session.setAttribute("storeNameName", storeName.getStoUserName());
				request.getRequestDispatcher("/home_page.jsp").forward(request, response);
				
			}else{
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				session.setAttribute("flagname", "账号密码错误");
				
			}
//		}else{
//			request.getRequestDispatcher("/index.jsp").forward(request, response);
//			session.setAttribute("flagval", "验证码错误");
//			
//		}
		
		
		
		
		
	}

	private boolean IsLogin(StoreName storeName,String username, String userpass) {
		if(username.equals(storeName.getStoUserName())&&userpass.equals(storeName.getStoPwd())){
			return true;
		}
		
		return false;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
