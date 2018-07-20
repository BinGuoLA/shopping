package com.lanqiao.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shopping.dao.UsersDao;
import com.lanqiao.shopping.dao.impl.UsersDaoImpl;
import com.lanqiao.shopping.service.UsersService;
import com.lanqiao.shopping.service.impl.UsersServiceImpl;
import com.lanqiao.shopping.utils.MyBeanUtils;
import com.lanqiao.shopping.utils.UUIDUtils;
import com.lanqiao.shopping.vo.Users;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	UsersService usersService = new UsersServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.ÉèÖÃ±àÂë
		req.setCharacterEncoding("UTF-8");
		
		System.out.println("=========UserServlet============");
		String method = req.getParameter("method");
		System.out.println("method:"+method);
		if("registerUI".equals(method)) {
			req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
		}else if("userRegister".equals(method)) {
//UUID   USERNAME     PASSWORD    NAME    EMAIL    TELEPHONE    BIRTHDAY  SEX   STATE CODE
				
			 
			Users users = MyBeanUtils.populate(Users.class, req.getParameterMap());
			users.setUuid(UUIDUtils.getCode());
			users.setCode(UUIDUtils.getUUID64());
			users.setState(0);
			
			usersService.register(users);
		}else if("loginUI".equals(method)) {
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
		}else if("login".equals(method)) {
			/*System.out.println("login");
			Users users = new Users();
			users.setUsername(req.getParameter("loginname"));
			users.setPassword(req.getParameter("loginpassword"));
			//System.out.println(users.getUsername());
			//System.out.println(users.getPassword());
			boolean flag = usersDao.login(users);
			if(flag) {
				System.out.println("µÇÂ½³É¹¦");
			}else {
				System.out.println("µÇÂ½Ê§°Ü");
			}*/
			Users users = new Users();
			users.setUsername(req.getParameter("loginname"));
			users.setPassword(req.getParameter("loginpassword"));
			boolean flag = usersService.login(users);
			if(flag) {
				System.out.println("µÇÂ½³É¹¦");
			}else {
				System.out.println("µÇÂ½Ê§°Ü");
			}
		}else if("active".equals(method)) {
			String activeCode = req.getParameter("activeCode");
			
			Users users = usersService.active(activeCode);
			users.setBirthday(users.getBirthday().substring(0,10));
			users.setState(1);
			
			usersService.updateUsers(users);
		}
	}
}
