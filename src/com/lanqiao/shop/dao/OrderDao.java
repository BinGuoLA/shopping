package com.lanqiao.shop.dao;

import java.sql.SQLException;
import java.util.List;

import com.lanqiao.shop.domain.Order;
import com.lanqiao.shop.domain.OrderItem;
import com.lanqiao.shop.domain.Users;
import com.lanqiao.shop.utils.PageUtils;

public interface OrderDao {

	public void saveOrder(Order order);

	public void saveOrderItem(OrderItem orderItem) throws Exception;

	public int totalRecords(String uid) throws SQLException;

	public List<OrderItem> findOrderItemByOid(String oid) throws SQLException;

	public List<Order> findAllOrderByUid(Users users, int startIndex, int endIndex) throws SQLException;


}
