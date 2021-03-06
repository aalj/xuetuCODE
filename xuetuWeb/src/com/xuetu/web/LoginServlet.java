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
 * 
 * ClassName:LoginServlet<br/>
 * 
 * Function:实现登录登录的页面<br/>
 * 
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016 2016年2月25日 下午7:26:25
 *
 * @see
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Content-type", "text/html;charset=utf-8");
		// response.setCharacterEncoding("utf-8");
		String username = new String(request.getParameter("username").getBytes("iso8859-1"), "utf-8");
		String userpass = new String(request.getParameter("userpass").getBytes("iso8859-1"), "utf-8");
		HttpSession session = request.getSession();
		// 得到验证码的缓存
		//
		StorenameService2 loginService = new StorenameService2();
		StoreName storeName = loginService.getStoreName(username, userpass);
		if (IsLogin(storeName, username, userpass)) {
			session.setAttribute("storeNameId", storeName.getStoID());
			session.setAttribute("storeNameName", storeName.getStoUserName());
			request.getRequestDispatcher("/home_page.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		}
	}
	
	private boolean IsLogin(StoreName storeName, String username, String userpass) {

		if (storeName != null && username.equals(storeName.getStoUserName())
				&& userpass.equals(storeName.getStoPwd())) {
			return true;
		}

		return false;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
