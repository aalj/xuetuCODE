package com.xuetu.web.android;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jspsmart.upload.SmartUpload;
import com.xuetu.dao.LoginDao;
import com.xuetu.entity.Student;
import com.xuetu.service.LoginService;
import com.xuetu.service.inter.PersonalServiceInter;

/**
 * Servlet implementation class SaveHead
 */
@WebServlet("/SaveHead")
public class SaveHead extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String stu_img = null;
	int stu_id = 0;
	Student student = new Student();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	PersonalServiceInter person = new LoginService(new LoginDao());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		// 用smartupload获得上传文件
		SmartUpload smartUpload = new SmartUpload();
		// 初始化
		smartUpload.initialize(getServletConfig(), request, response);
		try {
			smartUpload.upload();
			String realPath = this.getServletContext().getRealPath("xuetuImg");
			File dir = new File(realPath);
			if (!dir.exists()) {
				// 创建文件夹
				dir.mkdir();
				System.out.println("创建新文件夹");
			}

			// System.out.println(smartUpload.getFiles().getCount()+"count");
			com.jspsmart.upload.File poster = smartUpload.getFiles().getFile(0);
			if (!poster.isMissing()) {
				String path = request.getServletContext().getRealPath("/");
				// poster.getFileName() 原文件名
				File file = new File(getServletContext().getRealPath("xuetuImg"), poster.getFileName());
				System.out.println("filename" + poster.getFileName());
				String saveFileName = file.getAbsolutePath();
				stu_img = "xuetuImg/" + poster.getFileName();
				System.out.println("saveFileName" + saveFileName);
				// 文件保存路径
				poster.saveAs(saveFileName);
				poster.saveAs(path + stu_img);
				System.out.println(path + poster.getFileName()+"--------------->>>>>");
			} // E:\XueTu\xuetuCCOODDEE\2016.3.24\xuetuCODE\xuetuWeb\WebContent\xuetuImg
			stu_img = "xuetuImg/" + poster.getFileName();
			stu_id = Integer.parseInt(smartUpload.getRequest().getParameter("stu_id"));
			person.updateStu_img(stu_id, stu_img);
			student = person.getStuByID(stu_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		PrintWriter pw = response.getWriter();
		pw.write(gson.toJson(student));
		pw.flush();
		pw.close();

	}

}
