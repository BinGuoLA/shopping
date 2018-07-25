package com.lanqiao.shop.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import com.lanqiao.shop.dao.ProductDao;
import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.utils.DBHepler;

public class ProductDaoImpl implements ProductDao{

	@Override
	public List<Product> findProductByCid(String cid) {
		String sql = "select * from product where cid = ?";
		Object[] obj = {cid};
		ResultSet rs = DBHepler.commomQuery(sql, obj);
		return null;
	}

}
