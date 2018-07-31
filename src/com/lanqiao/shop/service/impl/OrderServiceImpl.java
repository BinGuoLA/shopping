package com.lanqiao.shop.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.lanqiao.shop.dao.OrderDao;
import com.lanqiao.shop.dao.ProductDao;
import com.lanqiao.shop.dao.impl.OrderDaoImpl;
import com.lanqiao.shop.dao.impl.ProductDaoImpl;
import com.lanqiao.shop.domain.Order;
import com.lanqiao.shop.domain.OrderItem;
import com.lanqiao.shop.domain.Users;
import com.lanqiao.shop.service.OrderService;
import com.lanqiao.shop.utils.PageUtils;


public class OrderServiceImpl implements OrderService {
		//创建dao层对象
		OrderDao orderDao = new OrderDaoImpl();
		ProductDao productDao = new ProductDaoImpl();
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
		@Override
		public PageUtils findAllOrderByUid(Users users, int curPageNo) throws SQLException {
			int totalRecords = orderDao.totalRecords(users.getUuid());
			int limit = 3;
			PageUtils pageUtils = new PageUtils(curPageNo, totalRecords, limit);

			List<Order> oList = orderDao.findAllOrderByUid(users, pageUtils.getStartIndex(),
					pageUtils.getEndIndex());
			
			//for (Order order : oList) {
			//	List<OrderItem> orderItemList = orderDao.findOrderItemByOid(order.getOid());
			//	order.setOrderItem(orderItemList);
			//}
			
			
			pageUtils.setList(oList);
			pageUtils.setUrl("OrderServlet?method=findOrderByUid&uid=" + users.getUuid());

			return pageUtils;
		}
	

}
