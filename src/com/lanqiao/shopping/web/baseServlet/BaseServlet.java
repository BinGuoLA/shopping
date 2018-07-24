package com.lanqiao.shopping.web.baseServlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String method = request.getParameter("method");
		//System.out.println(method);
		if (method == null || method.equals("") || method.trim().equals("")) {
			method = "execute";
		} else {
			try {

				Method md = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
				String path = (String) md.invoke(this, request, response);
				if (path == null || path.equals("") || path.trim().equals("")) {
					System.out.println("Â·¾¶Îª¿Õ");
				} else {
					request.getRequestDispatcher(path).forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
