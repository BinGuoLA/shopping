package com.lanqiao.shop.service;

import com.lanqiao.shop.domain.Users;

public interface UsersService {
	//�û�ע��
	public void userRegister(Users users);
	//�û�����
	public Users  userActive(String code);
	//�޸��û���Ϣ
	public void updateUsers(Users users);
	public Users userLogin(String username, String password);
}
