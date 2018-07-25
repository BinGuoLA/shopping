package com.lanqiao.shop.service.impl;

import java.util.List;

import com.lanqiao.shop.dao.ProductDao;
import com.lanqiao.shop.dao.impl.ProductDaoImpl;
import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.service.ProductService;

public class ProductServiceImpl implements ProductService {
	ProductDao productDao = new ProductDaoImpl();
	@Override
	public List<Product> findProductByCid(String cid) {
		
		return null;
	}

}
