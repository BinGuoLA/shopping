package com.lanqiao.shop.dao;

import com.lanqiao.shop.domain.Users;

public interface UsersDao {

		public void register(Users users);//javaBean

		public Users  userActive(String code);
		public void updateUsers(Users users);
		public Users userLogin(String username, String password);

		public boolean checkUsersExistance(String username);
}
