package com.lanqiao.shop.service.impl;

import com.lanqiao.shop.dao.UsersDao;
import com.lanqiao.shop.dao.impl.UserDaoImpl;
import com.lanqiao.shop.domain.Users;
import com.lanqiao.shop.service.UsersService;
import com.lanqiao.shop.utils.SendJMail;

public class UsersServiceImpl implements UsersService {
	UsersDao usersDao=new UserDaoImpl();
	//实现转发
	@Override
	public void userRegister(Users users) {
		usersDao.register(users);
		SendJMail.sendMail(users.getEmail(), " 注册成功，请<a href='http://localhost:8080/shopping/UserServlet?method=userActive&code="+users.getCode()+"'>激活</a>后登录");//email：接收者的邮箱  emailMsg：发送邮箱的内容

	}
	//用户激活
	@Override
	public Users  userActive(String code) {
		
		return usersDao.userActive(code);
		
	}
	//修改用户信息
	@Override
	public void updateUsers(Users users) {
		usersDao.updateUsers(users);
		
	}
	@Override
	public Users userLogin(String username, String password) {
		return usersDao.userLogin(username,password);
		
	}

}
