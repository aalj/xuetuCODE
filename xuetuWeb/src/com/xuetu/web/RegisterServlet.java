package com.xuetu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.xuetu.entity.StoreName;
import com.xuetu.service.StorenameService;

/**
 * 
 * ClassName:RegisterServlet<br/>
 * 
 * Function: 用于注册的servlet<br/>
 * 
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016 2016年2月25日 下午7:25:48
 *
 * @see
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StorenameService storenameService = new StorenameService();

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
		 * 设置编码 获得页面数据，封装成StoreName对象，调用Service层的注册方法
		 */
		System.out.println("组侧");
		
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String sto_user_name = request.getParameter("sto_user_name");
		String sto_tel = request.getParameter("sto_tel");
		String sto_address = request.getParameter("sto_address");
		String sto_name = request.getParameter("sto_name");
		String sto_introduction = request.getParameter("sto_introduction");

		// 获得验证码

		String valima = request.getParameter("valiimage");
		// 得到系统自动生成的验证码
		HttpSession session = request.getSession();
		String attribute = (String) session.getAttribute("rand");

		String sto_pwd = request.getParameter("sto_pwd");
		Part p = request.getPart("sto_img");
		// 将这个文件保存在服务器的一个地方
		//TODO  在这里需要判断如何实现在验证码你没有素如正确的情况下对用户进行提醒
//		if (attribute.equals(valima)) {

			StoreName storeName = new StoreName();
			storeName.setStoUserName(sto_user_name);
			storeName.setStoTel(sto_tel);
			storeName.setStoAddress(sto_address);
			storeName.setStoName(sto_name);
			storeName.setStoIntroduction(sto_introduction);
			storeName.setStoPwd(sto_pwd);
			// 调用Service层方法将storeName添加到数据库
			storenameService.registerStore(storeName);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
//		} else {
//			System.out.println("验证码错误");
//			request.getRequestDispatcher("/register.jsp").forward(request, response);
//		}

	}

}
