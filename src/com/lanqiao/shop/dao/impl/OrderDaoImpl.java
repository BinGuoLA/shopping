package com.lanqiao.shop.dao.impl;

import com.lanqiao.shop.dao.OrderDao;
import com.lanqiao.shop.domain.Order;
import com.lanqiao.shop.domain.OrderItem;
import com.lanqiao.shop.utils.DBHepler;

public class OrderDaoImpl implements OrderDao{
		//保存订单
		@Override
		public void saveOrder(Order order) {
			String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
			Object[] obj= {order.getOid(),new java.sql.Date(order.getOrderTime().getTime()),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUsers().getUuid()};
			DBHepler.commom(sql, obj);
		}
		//保存订单项
		@Override
		public void saveOrderItem(OrderItem orderItem) throws Exception {
			String sql = "insert into ordersitem values(?,?,?,?,?)";
			Object[] obj= {orderItem.getItemid(),orderItem.getQuantity(),orderItem.getTotal(),orderItem.getProduct().getPid(),orderItem.getOrder().getOid()};
			DBHepler.commom(sql, obj);
		}
	}


