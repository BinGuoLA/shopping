package com.lanqiao.shopping.service.impl;

import com.lanqiao.shopping.dao.UsersDao;
import com.lanqiao.shopping.dao.impl.UsersDaoImpl;
import com.lanqiao.shopping.service.UsersService;
import com.lanqiao.shopping.utils.SendJMail;
import com.lanqiao.shopping.vo.Users;

public class UsersServiceImpl implements UsersService{
	UsersDao userDao = new UsersDaoImpl();
	@Override
	public void register(Users users) {
		userDao.register(users);
		
		//发送邮件
		String emailMsg = "注册成功，请<a href='http://localhost:8080/shopping/UserServlet?method=active&activeCode="+users.getCode()+"'>激活</a>后登录";
		SendJMail.sendMail(users.getEmail(), emailMsg);
	}
	@Override
	public boolean login(Users users) {
		return userDao.login(users);
	}
	
	@Override
	public Users active(String activeCode) {
		return userDao.active(activeCode);	
	}
	@Override
	public void updateUsers(Users users) {
		userDao.updateUsers(users);
	}
}
