package com.lanqiao.shopping.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lanqiao.shopping.dao.CategoryDao;
import com.lanqiao.shopping.utils.DBHepler;
import com.lanqiao.shopping.vo.Category;
import com.lanqiao.shopping.vo.Users;


public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() {
		List<Category> clist = new ArrayList<>();
		String sql = "select * from category";
		ResultSet rs = DBHepler.commomQuery(sql, new Object[0]);
		try {		
			while(rs.next()) {
				Category category = new Category();
				category.setCid(rs.getString("cid"));
				category.setCname(rs.getString("cname"));
				clist.add(category);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return clist;
	}

}
