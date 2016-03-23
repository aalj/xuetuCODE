package com.xuetu.web.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.dao.LoginDao;

/**
 * Servlet implementation class SaveperInfo
 */
@WebServlet("/SaveperInfo")
public class SaveperInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String temp = request.getParameter("temp");
		String usepwd = request.getParameter("usepwd");
		String value = request.getParameter("value");
		if(temp!=null&&usepwd!=null&&value!=null){
			temp=URLDecoder.decode(temp, "utf-8");
			usepwd=URLDecoder.decode(usepwd, "utf-8");
			value=URLDecoder.decode(value, "utf-8");
		}
		LoginDao dao = new LoginDao();
		boolean flags= false;
		switch (temp) {
		case "2":
			flags=dao.savePerInfo("2",usepwd,value);
			break;
		case "3":
			flags=dao.savePerInfo("3",usepwd,value);
			break;
		case "4":
			flags=dao.savePerInfo("4",usepwd,value);
			break;
		case "5":
			
			break;
		case "6":
			flags=	dao.savePerInfo("6",usepwd,value);
			break;

		default:
			break;
		}
		
		
		
		
		
		
		
		
		
		
		PrintWriter writer = response.getWriter();
		if(flags){
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
