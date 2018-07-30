package com.lanqiao.shop.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shop.domain.Cart;
import com.lanqiao.shop.domain.CartItem;
import com.lanqiao.shop.domain.Order;
import com.lanqiao.shop.domain.OrderItem;
import com.lanqiao.shop.domain.Users;
import com.lanqiao.shop.service.OrderService;
import com.lanqiao.shop.service.impl.OrderServiceImpl;
import com.lanqiao.shop.utils.UUIDUtils;
import com.lanqiao.shop.web.base.BaseServlet;

@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	//创建OrderService对象
	OrderService orderService = new OrderServiceImpl();
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1、判断用户是否登录？  		保存“用户对象”   取得用户对象(修改)
			Users users = (Users)request.getSession().getAttribute("users");
			if(users==null) {
				return "/jsp/login.jsp";
			}
			//2、获取购物车对象
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			
			//3、创建订单及订单项
				//3-1:创建订单
				Order order = new Order();
				order.setOid(UUIDUtils.getCode());
				order.setOrderTime(new Date());
				order.setState(1);
				order.setTotal(cart.getTotal());//从购物车获取
				order.setUsers(users);
				//?订单项集合属性没有处理？
						
				//3-2：创建订单项
				/*遍历购物车项同时创建订单项，再把购物车项中的数据填充到订单项上*/
				for(CartItem cartItem : cart.getMap().values()) {
					OrderItem orderItem = new OrderItem();
					orderItem.setItemid(UUIDUtils.getCode());
					orderItem.setOrder(order);//给关联的"订单"对象赋值
					orderItem.setProduct(cartItem.getProduct());//关联商品对象赋值
					orderItem.setQuantity(cartItem.getCount());
					orderItem.setTotal(cartItem.getSubtotal());//小计
					
					//把订单项添加到订单中去
					order.getOrderItem().add(orderItem);
				}
				
				
			//4、调用业务层的保存方法实现数据的保存操作
				orderService.saveOrder(order);
			//5、request对象保存订单（Order）
				request.setAttribute("order", order);
			//6、清空购物车中的数据
				cart.clearCart();
			//7、跳转到页面：/jsp/order_info.jsp
				return "/jsp/order_info.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}
}

