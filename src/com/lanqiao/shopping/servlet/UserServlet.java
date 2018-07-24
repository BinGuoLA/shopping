package com.lanqiao.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanqiao.shopping.dao.UsersDao;
import com.lanqiao.shopping.dao.impl.UsersDaoImpl;
import com.lanqiao.shopping.service.UsersService;
import com.lanqiao.shopping.service.impl.UsersServiceImpl;
import com.lanqiao.shopping.utils.MyBeanUtils;
import com.lanqiao.shopping.utils.UUIDUtils;
import com.lanqiao.shopping.vo.Users;
import com.lanqiao.shopping.web.baseServlet.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
			UsersService usersService = new UsersServiceImpl();			
			public String registerUI(HttpServletRequest request, HttpServletResponse response) {
				return "/jsp/register.jsp";
			}
			
			public String userRegister(HttpServletRequest request, HttpServletResponse response) {
				 
				Users users = MyBeanUtils.populate(Users.class, request.getParameterMap());
				users.setUuid(UUIDUtils.getCode());
				users.setCode(UUIDUtils.getUUID64());
				users.setState(0);
				
				usersService.register(users);
				return "/jsp/index.jsp";
			}
			
			public String loginUI(HttpServletRequest request, HttpServletResponse response) {
				return "/jsp/login.jsp";
			}
			
			public String login(HttpServletRequest request, HttpServletResponse response) {
				Users users = new Users();
				users.setUsername(request.getParameter("loginname"));
				users.setPassword(request.getParameter("loginpassword"));
				Users existusers = usersService.login(users);
				if(existusers != null) {
					if(existusers.getState() == 0) {
						System.out.println("用户未激活");
						return "jsp/login.jsp";
					}
					HttpSession session = request.getSession();
					session.setAttribute("existusers", existusers);
					return "jsp/index.jsp";
	
				}else {
					System.out.println("用户或密码错误");
					return "jsp/login.jsp";
				}
			}
			
			public String active(HttpServletRequest request, HttpServletResponse response) {
				String activeCode = request.getParameter("activeCode");
				
				Users users = usersService.active(activeCode);
				System.out.println(users.getBirthday());
				users.setBirthday(users.getBirthday().substring(0,10));
				users.setState(1);
				
				usersService.updateUsers(users);
				return "jsp/index.jsp";
			}
			
			public String quit(HttpServletRequest request, HttpServletResponse response) {
				HttpSession session = request.getSession();
				session.invalidate();
				//resp.sendRedirect("index.jsp");
				return "index.jsp";
			}
		
		
	
}
