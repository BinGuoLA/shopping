package com.lanqiao.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lanqiao.shop.dao.OrderDao;
import com.lanqiao.shop.domain.Order;
import com.lanqiao.shop.domain.OrderItem;
import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.domain.Users;
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
		@Override
		public List<Order> findAllOrderByUid(Users users, int startIndex, int endIndex) throws SQLException {
			ArrayList<Order> orderList = new ArrayList<Order>();
			String sql = "select * from (select rownum rn ,o.* from orders o where uuid = ?) o1 where rn>=? and rn<=?";
			Object[] obj = { users.getUuid(), startIndex, endIndex };
			ResultSet rs = DBHepler.commomQuery(sql, obj);
			while (rs.next()) {
				Order order = new Order();
				order.setOid(rs.getString("oid"));
				order.setOrderTime(rs.getDate("ordertime"));
				order.setState(rs.getInt("state"));
				order.setTotal(rs.getDouble("total"));
				order.setUsers(users);
				
				
				List<OrderItem> orderItemList = findOrderItemByOid(order.getOid());
				order.setOrderItem(orderItemList);
				
				orderList.add(order);
			}
			DBHepler.getClose(rs, null, null);
			return orderList;
		}
		@Override
		public int totalRecords(String uid) throws SQLException {
			String sql = "select count(*) from orders where uuid = ?";
			Object[] obj = { uid };
			int totalRecord = 0;

			ResultSet rs = DBHepler.commomQuery(sql, obj);
			if (rs.next()) {
				totalRecord = rs.getInt(1);
			}

			return totalRecord;
		}
		@Override
		public List<OrderItem> findOrderItemByOid(String oid) throws SQLException {
			ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
			String sql = "select * from ordersitem o,product p where p.pid = o.pid and o.oid = ?";
			
			Connection conn = DBHepler.getConn();;
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, oid);
			ResultSet rs=ps.executeQuery();
				
				
			while (rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setItemid(rs.getString("itemid"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setTotal(rs.getDouble("total"));
				
				Product product = new Product();
				product.setIs_hot(rs.getInt("is_hot"));
				product.setMarket_price(rs.getString("market_price"));
				product.setPdate(rs.getString("pdate"));
				product.setPdesc(rs.getString("pdesc"));
				product.setPflag(rs.getInt("pflag"));
				product.setPid(rs.getString("pid"));
				product.setPimage(rs.getString("pimage"));
				product.setPname(rs.getString("pname"));
				product.setShop_price(rs.getString("shop_price"));
				
				orderItem.setProduct(product);
				
				orderItemList.add(orderItem);
			}
			DBHepler.getClose(rs, ps, conn);
			return orderItemList;
			
		}
		
		
		
		
	}


