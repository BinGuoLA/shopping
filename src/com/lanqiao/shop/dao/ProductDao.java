package com.lanqiao.shop.dao;

import java.util.List;

import com.lanqiao.shop.domain.Product;

public interface ProductDao {
	public List<Product> findProductByCid(String cid);
}