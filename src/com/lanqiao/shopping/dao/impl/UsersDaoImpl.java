package com.lanqiao.shopping.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.lanqiao.shopping.dao.UsersDao;
import com.lanqiao.shopping.utils.DBHepler;
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

	@Override
	public boolean login(Users users) {
		String sql = "select * from users where username = ? and password = ?";
		Object[] obj = {users.getUsername(),users.getPassword()};
		ResultSet rs = DBHepler.commomQuery(sql, obj);
		try {		
		if(rs.next()) {
			return true;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Users active(String activeCode) {
		String sql = "select * from users where code = ?";
		Object[] obj = {activeCode};
		ResultSet rs = DBHepler.commomQuery(sql, obj);
		try {		
		if(rs.next()) {
			Users users = new Users();
			users.setBirthday(rs.getString("BIRTHDAY"));
			users.setCode(rs.getString("code"));
			users.setEmail(rs.getString("email"));
			users.setName(rs.getString("name"));
			users.setPassword(rs.getString("password"));
			users.setSex(rs.getString("sex"));
			users.setState(rs.getInt("state"));
			users.setTelephone(rs.getString("telephone"));
			users.setUsername(rs.getString("username"));
			users.setUuid(rs.getString("uuid"));
			return users;

		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUsers(Users users) {
		
		String sql = "update users set USERNAME=?,PASSWORD=?,NAME=?,EMAIL=?,TELEPHONE=?,BIRTHDAY=to_date(?,'yyyy-mm-dd'),SEX=?,STATE=?,CODE=? where UUID=?";
		Object[] obj = {users.getUsername(),users.getPassword(),users.getName(),users.getEmail(),
				users.getTelephone(),users.getBirthday(),users.getSex(),users.getState(),users.getCode(),users.getUuid()};
		DBHepler.common(sql, obj);
	}

}
