package com.xuetu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.xuetu.entity.StoreName;
import com.xuetu.service.StorenameService2;

/**
 * 
 * ClassName:ChangeStoneInfoServlet<br/>
 * 
 * Function: 修改店家的信息<br/>
 * 
 * Reason: TODO ADD REASON<br/>
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016 2016年2月25日 下午7:28:13
 *
 * @see
 */
@WebServlet("/ChangeStoneInfoServlet")
public class ChangeStoneInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		StorenameService2 service2 = new StorenameService2();
		
		int attribute = (int) session.getAttribute("storeNameId");
		if (!(0==attribute)) {
			String sto_name = request.getParameter("sto_name");
			String sto_tel = request.getParameter("sto_tel");
			String sto_address = request.getParameter("sto_address");
			String sto_info = request.getParameter("sto_info");
			StoreName storeName = service2.verificationName(attribute);
			storeName.setStoAddress(sto_address);
			storeName.setStoIntroduction(sto_info);
			storeName.setStoTel(sto_tel);
			storeName.setStoName(sto_name);
			
			
			
			

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
