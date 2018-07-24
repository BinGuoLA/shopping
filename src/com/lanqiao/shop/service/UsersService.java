package com.lanqiao.shop.service;

import com.lanqiao.shop.domain.Users;

public interface UsersService {
	//用户注册
	public void userRegister(Users users);
	//用户激活
	public Users  userActive(String code);
	//修改用户信息
	public void updateUsers(Users users);
	public Users userLogin(String username, String password);
}
