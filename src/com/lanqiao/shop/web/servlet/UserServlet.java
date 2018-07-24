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
    //��ת��ע��ҳ��
	public String registerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ж�cookie�Ƿ����,������ڣ���ֱ�ӽ��е�¼��������뵽��¼����(login.jsp)
				
				return "/jsp/register.jsp";
	}
	 //��ת����ҳ
		public String indexUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			return "/jsp/index.jsp";
		}
	//��ת����¼ҳ��
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies =   request.getCookies();//Ĭ�ϴ���sessionID��cookie   ,autoLogin
		
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("autoLogin")) {
				//����session��username�ı���
				
				request.getSession().setAttribute("username",cookies[i].getValue().split("#")[0]);//session�����ֵ����cookie����
				//���뵽��¼��Ľ���
				return "/jsp/index.jsp";
			}
			
			if(cookies[i].getName().equals("remUser")) {
				//�û�������session��,��cookie��ȡ���뵽seesion��
				request.getSession().setAttribute("remUser",cookies[i].getValue());//ֵ��cookie�л�ȡ
			}
		}
		
		return "/jsp/login.jsp";
	}
	//�û�ע��
	public String userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]> map=request.getParameterMap();
		
		//�������ݣ��൱�ڸ����Ը�ֵ
		Users users=MyBeanUtils.populate(Users.class,map);
		users.setUuid(UUIDUtils.getCode());
		users.setCode(UUIDUtils.getCode());
		users.setPassword(MD5Util.string2MD5(request.getParameter("password")));
		users.setState(0);
		usersService.userRegister(users);
		return "/jsp/regissucces.jsp";
	}
	
	//�û�����
		public String userActive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//����code������
			String code = request.getParameter("code");
			//ͨ��������ȥ�����û�����
			Users users = usersService.userActive(code);
			//�޸�state״̬Ϊ1
			users.setState(1);
			//����jdbcʵ��״̬�޸�
			usersService.updateUsers(users);//�޸��û�����
			return "/jsp/info.jsp";
		}
		
		//�û���¼
		public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			HttpSession seesion = request.getSession();
			//�������������ܣ�123 = 202cb962ac59075b964b07152d234b70
			String md5Pwd = MD5Util.string2MD5(password);
			//2������service��userLogin����
			Users users = usersService.userLogin(username.trim(), md5Pwd);
			if(users!=null) {
				

				if(users.getState()==1) {
					
					String autoLogin = request.getParameter("autoLogin");
					System.out.println(autoLogin);
					if("yes".equals(autoLogin)) {
						//����cookie
						Cookie ck = new Cookie("autoLogin", users.getUsername()+"#"+users.getPassword());//tom#123
						//����cookice��Чʱ��
						ck.setMaxAge(1*24*60*60);//1��
						//����cookie
						response.addCookie(ck);
					}else {
						Cookie ck = new Cookie("autoLogin", "");//���cookie
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
					
					//�ж��Ƿ��ס�û���(cookie)
					String remUser = request.getParameter("remUser");
					System.out.println(remUser);
					if("yes".equals(remUser)) {
						//����cookie
						Cookie ck = new Cookie("remUser", users.getUsername());//�û�����Ϣjson
						//����cookice��Чʱ��
						ck.setMaxAge(1*24*60*60);//1��
						//����cookie
						response.addCookie(ck);
					}else {
						Cookie ck = new Cookie("remUser", "");//���cookie
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
					
					seesion.setAttribute("username", users.getName());
					return "/jsp/index.jsp";
				}else {
					request.setAttribute("logininfo","�û�δ���");
					System.out.println("�û�δ����");
				}

			}else {
				request.setAttribute("logininfo","�˺Ż��������!");
				System.out.println("�˺Ż��������!");
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
