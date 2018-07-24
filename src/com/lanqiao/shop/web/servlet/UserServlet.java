package com.lanqiao.shop.web.servlet;

import com.lanqiao.shop.domain.Users;
import com.lanqiao.shop.service.UsersService;
import com.lanqiao.shop.service.impl.UsersServiceImpl;
import com.lanqiao.shop.utils.MD5Util;
import com.lanqiao.shop.utils.MyBeanUtils;
import com.lanqiao.shop.utils.UUIDUtils;
import com.lanqiao.shop.web.base.BaseServlet;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	
	UsersService usersService=new UsersServiceImpl();
    public UserServlet() {
        super();
       
    }
    //跳转到注册页面
	public String registerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断cookie是否存在,如果存在，则直接进行登录，否则进入到登录界面(login.jsp)
				
				return "/jsp/register.jsp";
	}
	 //跳转到主页
		public String indexUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			return "/jsp/index.jsp";
		}
	//跳转到登录页面
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies =   request.getCookies();//默认存在sessionID的cookie   ,autoLogin
		
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("autoLogin")) {
				//设置session的username的保存
				
				request.getSession().setAttribute("username",cookies[i].getValue().split("#")[0]);//session保存的值，从cookie查找
				//进入到登录后的界面
				return "/jsp/index.jsp";
			}
			
			if(cookies[i].getName().equals("remUser")) {
				//用户名保存session中,从cookie获取存入到seesion中
				request.getSession().setAttribute("remUser",cookies[i].getValue());//值从cookie中获取
			}
		}
		
		return "/jsp/login.jsp";
	}
	//用户注册
	public String userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]> map=request.getParameterMap();
		
		//拷贝数据，相当于给属性赋值
		Users users=MyBeanUtils.populate(Users.class,map);
		users.setUuid(UUIDUtils.getCode());
		users.setCode(UUIDUtils.getCode());
		users.setPassword(MD5Util.string2MD5(request.getParameter("password")));
		users.setState(0);
		usersService.userRegister(users);
		return "/jsp/regissucces.jsp";
	}
	
	//用户激活
		public String userActive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//接收code激活码
			String code = request.getParameter("code");
			//通过激活码去查找用户对象
			Users users = usersService.userActive(code);
			//修改state状态为1
			users.setState(1);
			//连接jdbc实现状态修改
			usersService.updateUsers(users);//修改用户对象
			return "/jsp/info.jsp";
		}
		
		//用户登录
		public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			HttpSession seesion = request.getSession();
			//对输入的密码加密，123 = 202cb962ac59075b964b07152d234b70
			String md5Pwd = MD5Util.string2MD5(password);
			//2、调用service层userLogin方法
			Users users = usersService.userLogin(username.trim(), md5Pwd);
			if(users!=null) {
				

				if(users.getState()==1) {
					
					String autoLogin = request.getParameter("autoLogin");
					System.out.println(autoLogin);
					if("yes".equals(autoLogin)) {
						//创建cookie
						Cookie ck = new Cookie("autoLogin", users.getUsername()+"#"+users.getPassword());//tom#123
						//设置cookice有效时间
						ck.setMaxAge(1*24*60*60);//1天
						//保存cookie
						response.addCookie(ck);
					}else {
						Cookie ck = new Cookie("autoLogin", "");//清空cookie
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
					
					//判断是否记住用户名(cookie)
					String remUser = request.getParameter("remUser");
					System.out.println(remUser);
					if("yes".equals(remUser)) {
						//创建cookie
						Cookie ck = new Cookie("remUser", users.getUsername());//用户名信息json
						//设置cookice有效时间
						ck.setMaxAge(1*24*60*60);//1天
						//保存cookie
						response.addCookie(ck);
					}else {
						Cookie ck = new Cookie("remUser", "");//清空cookie
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
					
					seesion.setAttribute("username", users.getName());
					return "/jsp/index.jsp";
				}else {
					request.setAttribute("logininfo","用户未激活！");
					System.out.println("用户未激活");
				}

			}else {
				request.setAttribute("logininfo","账号或密码错误!");
				System.out.println("账号或密码错误!");
			}
			return  "/jsp/login.jsp";
		}

		public String logout(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			session.invalidate();
			Cookie ck = new Cookie("autoLogin","");
			ck.setMaxAge(0);
			response.addCookie(ck);
			return "/jsp/index.jsp";
		}
	

}
