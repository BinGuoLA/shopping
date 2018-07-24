package com.lanqiao.shopping.dao;

import com.lanqiao.shopping.vo.Cart;

public interface CartDao {
	public void addCart(Cart cart,String pid,int num);
}
