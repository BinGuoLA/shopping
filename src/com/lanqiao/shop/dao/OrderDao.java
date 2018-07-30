package com.lanqiao.shop.dao;

import com.lanqiao.shop.domain.Order;
import com.lanqiao.shop.domain.OrderItem;

public interface OrderDao {

	public void saveOrder(Order order);

	public void saveOrderItem(OrderItem orderItem) throws Exception;

}
