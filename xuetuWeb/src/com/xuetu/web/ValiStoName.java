package com.xuetu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuetu.service.StorenameService;
/**
 * 
 * ClassName:ValiStoName<br/>
 * 
 * Function: 验证用户名是否唯一<br/>
 * 
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016	2016年2月25日		下午7:20:26
 *
 * @see
 */
@WebServlet("/ValiStoName")
public class ValiStoName extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("post");
		int flag =0;
		String username = request.getParameter("sto_user_name");
		StorenameService storenameService = new StorenameService();
		if(storenameService.Valisto_usernameOnly(username)==null){
			request.setAttribute("flag", flag+1);
			System.out.println(flag+1);
		}
			else {
				request.setAttribute("flag", flag);
				System.out.println(flag);
			}
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

}
