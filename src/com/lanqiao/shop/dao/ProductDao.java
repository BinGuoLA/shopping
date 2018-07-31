package com.lanqiao.shop.dao;

import java.util.List;

import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.utils.PageUtils;

public interface ProductDao {

	public List<Product> findHot();

	public List<Product> findNew();
	
	public int totalRecords(String cid) throws Exception;
	
	public List<Product> findProductsByCidWithPage(String cid,int startIndex,int endIndex)throws Exception;
	
	public Product findProductByPid(String pid)  throws Exception;

    public List<Product> findProductsByName(String cid, int startIndex, int endIndex) throws Exception;

	public int totalRecordsByName(String search) throws Exception;
}
