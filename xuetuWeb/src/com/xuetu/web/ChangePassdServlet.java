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
 * ClassName:ChangePassdServlet<br/>
 * 
 * Function: 修改店家的登录密码<br/>
 * 
 * Reason:	 TODO ADD REASON<br/>
 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016	2016年2月25日		下午7:28:42
 *
 * @see
 */
@WebServlet("/ChangePassdServlet")
public class ChangePassdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到输入的值
		String oldPwd = request.getParameter("old_pwd");
		String newPwd = request.getParameter("new_pwd");
		String reNewPwd = request.getParameter("re_new_pwd");
		//得到登录用户的ID
		HttpSession session = request.getSession();
		int  attribute = (int) session.getAttribute("storeNameId");
		System.out.println(attribute);
		
		StorenameService2 service2 = new StorenameService2();
		StoreName storeName = service2.verificationName(attribute);
		if(storeName.getStoPwd().equals(oldPwd)){
			//原始密码正确，修改密码
			//改密码 
			service2.changePwd(attribute, reNewPwd);
			
			
			//
			session.setAttribute("storeNameId", 0);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			//原始密码不正确，   
			request.getRequestDispatcher("/mc_changepassword.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
