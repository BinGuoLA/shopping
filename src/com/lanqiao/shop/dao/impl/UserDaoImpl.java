package com.lanqiao.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.lanqiao.shop.dao.UsersDao;
import com.lanqiao.shop.domain.Users;
import com.lanqiao.shop.utils.DBHepler;

public class UserDaoImpl implements UsersDao {

	@Override
	public void register(Users users) {
		String sql = "insert into users values(?,?,?,?,?,?,?,?,?,?)";

		Object[] obj= {users.getUuid(),users.getUsername(),users.getPassword(),users.getName(),users.getEmail(),users.getTelephone(),new java.sql.Date(users.getBirthday().getTime()),users.getSex(),users.getState(),users.getCode()};
		
		DBHepler.commom(sql, obj);
	}

	@Override
	public Users  userActive(String code) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBHepler.getConn();
			String sql="select * from users where code=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, code);
			rs=ps.executeQuery();
			if(rs.next()) {
			
				Users users=new Users();
				users.setBirthday(rs.getDate("birthday"));
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
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBHepler.getClose(rs, ps, conn);
		}
		
		return null;
	}
	@Override
	public void updateUsers(Users users) {
		String sql = "update users set USERNAME=?,PASSWORD=?,NAME=?,EMAIL=?,TELEPHONE=?,BIRTHDAY=?,SEX=?,STATE=?,CODE=? where UUID=?";

		Object[] obj= {users.getUsername(),users.getPassword(),users.getName(),users.getEmail(),users.getTelephone(),new java.sql.Date(users.getBirthday().getTime()),users.getSex(),users.getState(),users.getCode(),users.getUuid()};
		
		DBHepler.commom(sql, obj);
	}
	@Override
	public Users userLogin(String username, String password) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBHepler.getConn();
			String sql="select * from users where username=? and password=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2,password);
			rs=ps.executeQuery();
			if(rs.next()) {
				
				Users users=new Users();
				users.setBirthday(rs.getDate("birthday"));
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
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBHepler.getClose(rs, ps, conn);
		}
		return null;
	}
	
	public boolean checkUsersExistance(String username) {
		String sql = "select * from users where username = ?";

		Object[] obj= {username};
		
		ResultSet rs = DBHepler.commomQuery(sql, obj);
		try {
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
