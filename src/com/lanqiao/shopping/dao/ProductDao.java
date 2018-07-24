package com.lanqiao.shopping.dao;

import java.util.List;

import com.lanqiao.shopping.utils.PageBean;
import com.lanqiao.shopping.vo.Product;


public interface ProductDao {
	public List<Product> findHot();
	public List<Product> findNew();
	public Product findProductByPid(String pid);
	public int findCountCid(int cid);
	//public int findCount();
	public <T>List findByPageCid(int cid, int begin, int limit);
}
