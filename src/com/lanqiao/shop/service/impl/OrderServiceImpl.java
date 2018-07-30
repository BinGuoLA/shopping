package com.lanqiao.shop.service.impl;

import com.lanqiao.shop.dao.OrderDao;
import com.lanqiao.shop.dao.impl.OrderDaoImpl;
import com.lanqiao.shop.domain.Order;
import com.lanqiao.shop.domain.OrderItem;
import com.lanqiao.shop.service.OrderService;


public class OrderServiceImpl implements OrderService {
		//����dao�����
		OrderDao orderDao = new OrderDaoImpl();
		//���涩����������
		@Override
		public void saveOrder(Order order)throws Exception {
			//1�����涩��
			orderDao.saveOrder(order);
			for(OrderItem orderItem : order.getOrderItem()) {
				//2�����涩����
				orderDao.saveOrderItem(orderItem);//OrderItem
			}
		}
	

}
