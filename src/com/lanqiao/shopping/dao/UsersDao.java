package com.lanqiao.shopping.dao;

import com.lanqiao.shopping.vo.Users;

public interface UsersDao {
	
     public boolean register(Users users);
     
     public Users login(Users users);
     
     public Users active(String activeCode);

	public void updateUsers(Users users);
}
