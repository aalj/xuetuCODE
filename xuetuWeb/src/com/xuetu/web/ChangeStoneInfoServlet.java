package com.xuetu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
@MultipartConfig
public class ChangeStoneInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.addHeader("Content-type", "text/html;charset=utf-8");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		int stoId = (int) session.getAttribute("storeNameId");
		if (stoId != 0) {
			String path = request.getServletContext().getRealPath("/");
			StorenameService2 service2 = new StorenameService2();
			if (!(0 == stoId)) {
				StoreName storeName = service2.verificationName(stoId);
				String sto_name = request.getParameter("sto_name");
				String sto_tel = request.getParameter("sto_tel");
				String sto_address = request.getParameter("sto_address");
				String sto_info = request.getParameter("sto_info");

				Part p = request.getPart("sto_img");
				System.out.println(p);
				System.out.println(sto_name);
				System.out.println(sto_address);
				System.out.println(sto_info);
				System.out.println(sto_tel);
				// 图片存储的服务器路径
				String imgname = "xuetuImg/" + System.currentTimeMillis() + "-" + storeName.getStoID() + ".jpg";
				if (p != null) {

					// 把图片文件写到服务器对应的地址下
					p.write(path + imgname);
					storeName.setStoImg(imgname);
					System.out.println(path + imgname);
				} else {
					System.out.println("meiyou tupian ");
				}
				storeName.setStoAddress(sto_address);
				storeName.setStoIntroduction(sto_info);
				storeName.setStoTel(sto_tel);
				storeName.setStoName(sto_name);

				if (service2.changStoreName(stoId, storeName)) {
					request.getRequestDispatcher("/ShowStoneNameServlet").forward(request, response);
				}

			}
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
