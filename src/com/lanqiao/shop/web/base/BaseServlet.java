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
		//处理乱码
		request.setCharacterEncoding("UTF-8");
		//获取method参数
		String method=request.getParameter("method");
		if(method==null || method.equals("") || method.trim().equals("")) {
			method = "execute";
		}else {
			//、获取对象的class:this代表正在访问的子类servlet对象
			try {
				Method md=this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				
				String path=(String) md.invoke(this, request,response);
				
				if(path==null) {
					System.out.println("跳转路径为空，请检查！");
				}else {
					//页面跳转(转发)
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
