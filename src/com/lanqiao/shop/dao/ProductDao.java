package com.lanqiao.shop.dao;

import java.util.List;

import com.lanqiao.shop.domain.Product;

public interface ProductDao {

	public List<Product> findHot();

	public List<Product> findNew();
	
	public int totalRecords(String cid) throws Exception;
	
	public List<Product> findProductsByCidWithPage(String cid,int startIndex,int endIndex)throws Exception;
}
