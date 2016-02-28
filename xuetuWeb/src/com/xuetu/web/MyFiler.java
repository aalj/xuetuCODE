package com.xuetu.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class MyFiler
 */
@WebFilter("/MyFiler")
public class MyFiler implements Filter {

	/**
	 * Default constructor.
	 */
	public MyFiler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("测试过滤器");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		chain.doFilter(new MyRequest(req), resp);
		
		int stoID=(int)req.getSession().getAttribute("storeNameId");
		if(stoID==0){
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
		
		
		

	}

	class MyRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request = null;

		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
