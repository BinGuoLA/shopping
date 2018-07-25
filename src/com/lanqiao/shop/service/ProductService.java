package com.lanqiao.shop.service;

import java.util.List;

import com.lanqiao.shop.domain.Product;

public interface ProductService {
	public List<Product> findHot();
	public List<Product> findNew();
}
