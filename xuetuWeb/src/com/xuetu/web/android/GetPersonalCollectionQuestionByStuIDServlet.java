package com.xuetu.web.android;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuetu.dao.LoginDao;
import com.xuetu.entity.CollectionQuestion;
import com.xuetu.service.LoginService;
import com.xuetu.service.inter.PersonalServiceInter;

/**
 * Servlet implementation class GetPersonalCollectionQuestionByStuIDServlet
 */
@WebServlet("/GetPersonalCollectionQuestionByStuIDServlet")
public class GetPersonalCollectionQuestionByStuIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalServiceInter personalServiceInter = new LoginService(new LoginDao());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String parameter = request.getParameter("stuID");
		String decode = URLDecoder.decode(parameter, "utf-8");
		System.out.println(decode);
		List<CollectionQuestion> collectionQuestionByStuID = personalServiceInter
				.getPersonalCollectionQuestionByStuID(Integer.parseInt(decode));
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String collectionQuestionByStuIDs = gson.toJson(collectionQuestionByStuID);
		System.out.println("个人收藏问题都她表姐的有耶耶耶耶耶******" + collectionQuestionByStuIDs);
		response.getWriter().print(collectionQuestionByStuIDs);
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
