package com.lanqiao.shop.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
	
		String method=request.getParameter("method");
		if(method==null || method.equals("") || method.trim().equals("")) {
			method = "execute";
		}else {
			
			try {
				Method md=this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				
				String path=(String)md.invoke(this, request,response);
				
				if(path==null) {
					System.out.println("·��Ϊ��");
				}else {
					
					request.getRequestDispatcher(path).forward(request, response);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

	}
	//给子类用于重写，实现无参数传递的模块操作
		protected String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			return null;
		}


}
