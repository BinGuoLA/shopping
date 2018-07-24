package com.lanqiao.shopping.service;

import java.util.List;

import com.lanqiao.shopping.utils.PageBean;
import com.lanqiao.shopping.vo.Product;

public interface ProductService {
	public List<Product> findHot();
	public List<Product> findNew();
	public Product findProductByPid(String pid);
	public PageBean<Product> findByCid(int cid, int page);
}
	

