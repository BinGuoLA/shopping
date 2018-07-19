package com.lanqiao.shopping.dao.impl;

import com.lanqiao.shopping.dao.UsersDao;
import com.lanqiao.shopping.util.DBHepler;
import com.lanqiao.shopping.vo.Users;

public class UsersDaoImpl implements UsersDao{
	//UUID   USERNAME     PASSWORD    NAME    EMAIL    TELEPHONE    BIRTHDAY  SEX   STATE CODE
	@Override
	public boolean register(Users users) {
		String sql = "insert into users values(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?)";
		Object[] obj = {users.getUuid(),users.getUsername(),users.getPassword(),users.getName()
				,users.getEmail(),users.getTelephone(),users.getBirthday(),users.getSex(),users.getState(),users.getCode()};
		DBHepler.common(sql, obj);
		return false;
	}

}
