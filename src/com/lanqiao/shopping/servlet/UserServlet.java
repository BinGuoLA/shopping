package com.lanqiao.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shopping.dao.UsersDao;
import com.lanqiao.shopping.dao.impl.UsersDaoImpl;
import com.lanqiao.shopping.vo.Users;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	UsersDao usersDao = new UsersDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.…Ë÷√±‡¬Î
		req.setCharacterEncoding("UTF-8");
		
		String method = req.getParameter("method");
		if("registerUI".equals(method)) {
			req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
		}else if("userRegister".equals(method)) {
//UUID   USERNAME     PASSWORD    NAME    EMAIL    TELEPHONE    BIRTHDAY  SEX   STATE CODE
				
			Users users = new Users();
			users.setUuid("10");
			users.setUsername(req.getParameter("username"));
			users.setPassword(req.getParameter("password"));
			users.setName(req.getParameter("name"));
			users.setEmail(req.getParameter("email"));
			users.setTelephone(req.getParameter("telephone"));
			users.setBirthday(req.getParameter("birthday"));
			users.setSex(req.getParameter("sex"));
			users.setState(0);
			users.setCode("12346");
			
			usersDao.register(users);
		}
	}
}
