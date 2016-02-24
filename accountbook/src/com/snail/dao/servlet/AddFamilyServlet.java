package com.snail.dao.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snail.dao.Family;

/**
 * Servlet implementation class ListFamilyServlet
 */
@WebServlet("/ListFamilyServlet")
public class AddFamilyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("fm_id");
		String userName = request.getParameter("userName");
		String realName = request.getParameter("realName");
		String pwd = request.getParameter("pwd");
		String permiss = request.getParameter("permiss");
		
		Family f = new Family();
		f.setFm_Id(Integer.parseInt(id));
		f.setPermiss(Integer.parseInt(permiss));
		f.setPwd(pwd);
		f.setRealName(realName);
		f.setUserName(userName);
		
		FamilyService fs = new FamilyService();
		fs.save(f);
		
		
	}

}
