package com.xuetu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.plaf.synth.SynthStyle;

/**
 * 
 * ClassName:ImgServlet<br/>
 * 
 * Function:保存图片<br/>
 * 

 *
 * @author   Stone
 * @version  
 * @since    Ver 1.1
 * @Date	 2016	2016年2月25日		下午7:26:45
 *
 * @see
 */
@WebServlet("/ImgServlet")
@MultipartConfig
public class ImgServlet extends HttpServlet {
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
		//处理文件上传
				//获得普通域，不变
		String path = request.getServletContext().getRealPath("/");
				String userName = request.getParameter("sto_img");
				System.out.println("ok:"+userName);
				//获得上传文件
				Part p = request.getPart("sto_img");
//				Part p = request.getPart("sto_img");
				if(p==null)
					System.out.println("p=null");
				//将这个文件保存在服务器的一个地方
				else{
					System.out.println(path);
					p.write(path+"/xuetuImg/"+"b.jpg");
				}
	}

}

