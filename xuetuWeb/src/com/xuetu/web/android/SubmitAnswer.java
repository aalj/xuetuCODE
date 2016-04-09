package com.xuetu.web.android;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jspsmart.upload.SmartUpload;
import com.xuetu.dao.QuestionIml;
import com.xuetu.dao.inter.QuesTionDao;
import com.xuetu.entity.Answer;
import com.xuetu.entity.Question;
import com.xuetu.entity.School;
import com.xuetu.entity.Student;
import com.xuetu.entity.Subject;
import com.xuetu.service.QuestionService;
import com.xuetu.service.inter.QuestionServiceInter;

/**
 * Servlet implementation class SubmitAnswer
 */
@WebServlet("/SubmitAnswer")
public class SubmitAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int ques_id = 0;
	int stu_id = 0;
	String ans_text = null;
	String ans_ima = null;
	String ans_timeStr = null;
	Date ans_time = null;
	Answer a = new Answer();
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
		
		//用smartupload获得上传文件
		SmartUpload smartUpload = new SmartUpload();
		//初始化
		smartUpload.initialize(getServletConfig(), request, response);
		try {
			smartUpload.upload();
			String realPath = this.getServletContext().getRealPath("xuetuImg");
			File dir = new File(realPath);
			if(!dir.exists()){
				//创建文件夹
				dir.mkdir();
				System.out.println("创建新文件夹");
			}
			com.jspsmart.upload.File poster = smartUpload.getFiles().getFile(0);
			if(!poster.isMissing()){	
				String path = request.getServletContext().getRealPath("/");
				//poster.getFileName()    原文件名
				File file = new File(getServletContext().getRealPath("xuetuImg"),poster.getFileName());
				String saveFileName = file.getAbsolutePath();
				//文件保存路径
				poster.saveAs(saveFileName);
				poster.saveAs(path+"xuetuImg/"+poster.getFileName());	
//				poster.saveAs("F:\\xuetuGIT\\xuetuCODE\\xuetuWeb\\WebContent\\xuetuImg\\"+poster.getFileName());	
			}
			ques_id = Integer.parseInt(smartUpload.getRequest().getParameter("quesId"));
			stu_id =  Integer.parseInt(smartUpload.getRequest().getParameter("stu_id"));
			ans_text = smartUpload.getRequest().getParameter("ans_text");
			ans_timeStr = smartUpload.getRequest().getParameter("ans_time");
			long parseLong = Long.parseLong(ans_timeStr);
			ans_time = new Date(new Timestamp(parseLong).getTime());
			ans_ima = "xuetuImg/"+poster.getFileName();
			a = Qservice.createAnswer(ques_id, stu_id, ans_text, ans_ima, ans_time);
//			Qservice.submitAnswer(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String jsonStr = gson.toJson(a);
		PrintWriter pw = response.getWriter();
		pw.write(jsonStr);
		pw.flush();
		pw.close();
	}

}
