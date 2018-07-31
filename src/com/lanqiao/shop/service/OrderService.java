package com.lanqiao.shop.service;

import java.sql.SQLException;

import com.lanqiao.shop.domain.Order;
import com.lanqiao.shop.domain.Users;
import com.lanqiao.shop.utils.PageUtils;

public interface OrderService {

	public void saveOrder(Order order) throws Exception;

	public PageUtils findAllOrderByUid(Users users, int curPageNo) throws SQLException;

}
