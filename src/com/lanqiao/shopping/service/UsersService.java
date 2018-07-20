package com.lanqiao.shopping.service;

import com.lanqiao.shopping.vo.Users;

public interface UsersService {
	
	public void register(Users users);
	
	public boolean login(Users users);
	
	public Users active(String activeCode);

	public void updateUsers(Users users);
}
