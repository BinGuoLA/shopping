package com.lanqiao.shop.service.impl;

import com.lanqiao.shop.dao.OrderDao;
import com.lanqiao.shop.dao.impl.OrderDaoImpl;
import com.lanqiao.shop.domain.Order;
import com.lanqiao.shop.domain.OrderItem;
import com.lanqiao.shop.service.OrderService;


public class OrderServiceImpl implements OrderService {
		//创建dao层对象
		OrderDao orderDao = new OrderDaoImpl();
		//保存订单及订单项
		@Override
		public void saveOrder(Order order)throws Exception {
			//1、保存订单
			orderDao.saveOrder(order);
			for(OrderItem orderItem : order.getOrderItem()) {
				//2、保存订单项
				orderDao.saveOrderItem(orderItem);//OrderItem
			}
		}
	

}
