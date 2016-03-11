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
 * ClassName:ShowStoneNameServlet<br/>
 * 
 * Function: 用于显示店家的信息<br/>
 * 
 *
 * @author Stone
 * @version
 * @since Ver 1.1
 * @Date 2016 2016年2月25日 下午7:22:26
 *
 * @see
 */
@WebServlet("/ShowStoneNameServlet")
public class ShowStoneNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		int stoId = (int) session.getAttribute("storeNameId");
		if (stoId != 0) {

			String flags = request.getParameter("flags");
			
			int attribute = (int) session.getAttribute("storeNameId");
			StorenameService2 storenameService2 = new StorenameService2();
			StoreName storeName = storenameService2.verificationName(attribute);

			session.setAttribute("storename", storeName);
			if ("1".equals(flags)) {
				request.getRequestDispatcher("/mc_changepersonal.jsp").forward(request, response);

			} else
				request.getRequestDispatcher("/mc_personal.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
