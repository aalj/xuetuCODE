package com.xuetu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.xuetu.entity.StoreName;
import com.xuetu.service.StorenameService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StorenameService storenameService = new StorenameService();
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
		/*设置编码
		 * 获得页面数据，封装成StoreName对象，调用Service层的注册方法
		 */
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String sto_user_name = request.getParameter("sto_user_name");
		String sto_tel = request.getParameter("sto_tel");
		String sto_address = request.getParameter("sto_address");
		String sto_name = request.getParameter("sto_name");
		String sto_introduction = request.getParameter("sto_introduction");
		//图片没写
		String sto_pwd = request.getParameter("sto_pwd");
		String sto_pwdConfirm = request.getParameter("sto_pwdConfirm");
		Part p = request.getPart("sto_img");
		String timeStr = System.currentTimeMillis()+"";
		System.out.println(sto_name);
		String sto_img = "/WebContent/xuetuImg/"+sto_name+timeStr+".jpg";
		//将这个文件保存在服务器的一个地方
//		p.write(sto_img);
		//创建StoreName对象
		
		StoreName storeName = new StoreName();
		storeName.setStoUserName(sto_user_name);
		storeName.setStoTel(sto_tel);
		storeName.setStoAddress(sto_address);
		storeName.setStoName(sto_name);
		storeName.setStoIntroduction(sto_introduction);
		storeName.setStoPwd(sto_pwd);
//		storeName.setStoImg(sto_img);
		//调用Service层方法将storeName添加到数据库
		storenameService.registerStore(storeName);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		//处理文件上传
		//获得普通域，不变
		//获得上传文件

	}

}
