package com.lanqiao.shop.service.impl;

import com.lanqiao.shop.dao.UsersDao;
import com.lanqiao.shop.dao.impl.UserDaoImpl;
import com.lanqiao.shop.domain.Users;
import com.lanqiao.shop.service.UsersService;
import com.lanqiao.shop.utils.SendJMail;

public class UsersServiceImpl implements UsersService {
	UsersDao usersDao=new UserDaoImpl();
	//ʵ��ת��
	@Override
	public void userRegister(Users users) {
		usersDao.register(users);
		SendJMail.sendMail(users.getEmail(), " ע��ɹ�����<a href='http://localhost:8080/shopping/UserServlet?method=userActive&code="+users.getCode()+"'>����</a>���¼");//email�������ߵ�����  emailMsg���������������

	}
	//�û�����
	@Override
	public Users  userActive(String code) {
		
		return usersDao.userActive(code);
		
	}
	//�޸��û���Ϣ
	@Override
	public void updateUsers(Users users) {
		usersDao.updateUsers(users);
		
	}
	@Override
	public Users userLogin(String username, String password) {
		return usersDao.userLogin(username,password);
		
	}

}
