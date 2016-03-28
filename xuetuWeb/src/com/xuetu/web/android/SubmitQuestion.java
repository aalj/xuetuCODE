package com.xuetu.web.android;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.xuetu.dao.QuestionIml;
import com.xuetu.entity.Question;
import com.xuetu.service.QuestionService;
import com.xuetu.service.inter.QuestionServiceInter;

/**
 * Servlet implementation class SubmitQuestion
 */
@WebServlet("/SubmitQuestion")
public class SubmitQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//声明变量
	int stuId = 0;
	String quesText = null;
	java.sql.Date quesTime = null;
	String quesTimeStr = null;
	Question q = new Question();
	String quesImg = null;
	int acpoNum = 0;
	int subId = 0;
	//创建service对象
	QuestionServiceInter Qservice = new QuestionService(new QuestionIml());
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		SmartUpload smartUpload = new SmartUpload();
		//初始化
		smartUpload.initialize(getServletConfig(), request, response);
		try {
			smartUpload.upload();
			String realPath = this.getServletContext().getRealPath("xuetuImg");
			File dir = new File(realPath);
			System.out.println("realPath:"+realPath);
			if(!dir.exists()){
				//创建文件夹
				dir.mkdir();
				System.out.println("创建新文件夹");
			}
			
//			System.out.println(smartUpload.getFiles().getCount()+"count");
			com.jspsmart.upload.File poster = smartUpload.getFiles().getFile(0);
			if(!poster.isMissing()){
				String path = request.getServletContext().getRealPath("/");
				//poster.getFileName()    原文件名
				File file = new File(getServletContext().getRealPath("xuetuImg"),poster.getFileName());
				System.out.println("posterfilename"+poster.getFileName());
				System.out.println("getrealpathxurtuImg"+getServletContext().getRealPath("xuetuImg"));
				String saveFileName = file.getAbsolutePath();
				System.out.println("file getabsolutepath"+saveFileName);
				//文件保存路径
				poster.saveAs(saveFileName);
				poster.saveAs(path+poster.getFileName());
//				System.out.println(getServletContext().getRealPath("xuetuImg")+"-----servletContextrealPath");
			}
			//获得question对象,发表问题
			stuId = Integer.parseInt(smartUpload.getRequest().getParameter("stuId"));
			quesText = smartUpload.getRequest().getParameter("quesText");
			quesTimeStr = smartUpload.getRequest().getParameter("quesTime");
			long parseLong = Long.parseLong(quesTimeStr);
			quesTime = new Date(new Timestamp(parseLong).getTime());
			quesImg = "xuetuImg/"+poster.getFileName();
			System.out.println("quesImg"+quesImg);
			acpoNum = Integer.parseInt(smartUpload.getRequest().getParameter("acpoNum"));
			subId = Integer.parseInt(smartUpload.getRequest().getParameter("subId"));
			q = Qservice.createQuestion(stuId, quesText,quesImg,quesTime, acpoNum, subId,Qservice.getSchIdByStuId(stuId));
			Qservice.submitQuestion(q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*stuId = Integer.parseInt(request.getParameter("stuId"));
		System.out.println(stuId);
		quesText = request.getParameter("quesText");
		System.out.println(quesText);
		quesTimeStr = request.getParameter("quesTimeStr");
		System.out.println(quesTimeStr);
		long parseLong = Long.parseLong(quesTimeStr);
		quesTime = new java.sql.Date(parseLong);
		quesImg = request.getParameter("quesImg");
		acpoNum = Integer.parseInt(request.getParameter("acpoNum"));
		subId = Integer.parseInt(request.getParameter("subId"));
		q = Qservice.createQuestion(stuId, quesText,quesImg,quesTime, acpoNum, subId,Qservice.getSchIdByStuId(stuId));
		Qservice.submitQuestion(q);*/
	}

}
