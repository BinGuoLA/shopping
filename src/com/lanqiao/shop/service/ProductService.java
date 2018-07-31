package com.lanqiao.shop.service;

import java.util.List;

import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.utils.PageUtils;

public interface ProductService {
	public List<Product> findHot();

	public List<Product> findNew();

	public PageUtils findProductByCidWithPage(String cid, int curPageNo) throws Exception;

	public Product findProductByPid(String pid) throws Exception;

	public PageUtils findProductsByName(String search, int curPageNo) throws Exception;
}
