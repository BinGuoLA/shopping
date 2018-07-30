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
	//����OrderService����
	OrderService orderService = new OrderServiceImpl();
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1���ж��û��Ƿ��¼��  		���桰�û�����   ȡ���û�����(�޸�)
			Users users = (Users)request.getSession().getAttribute("users");
			if(users==null) {
				return "/jsp/login.jsp";
			}
			//2����ȡ���ﳵ����
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			
			//3������������������
				//3-1:��������
				Order order = new Order();
				order.setOid(UUIDUtils.getCode());
				order.setOrderTime(new Date());
				order.setState(1);
				order.setTotal(cart.getTotal());//�ӹ��ﳵ��ȡ
				order.setUsers(users);
				//?�����������û�д���
						
				//3-2������������
				/*�������ﳵ��ͬʱ����������ٰѹ��ﳵ���е�������䵽��������*/
				for(CartItem cartItem : cart.getMap().values()) {
					OrderItem orderItem = new OrderItem();
					orderItem.setItemid(UUIDUtils.getCode());
					orderItem.setOrder(order);//��������"����"����ֵ
					orderItem.setProduct(cartItem.getProduct());//������Ʒ����ֵ
					orderItem.setQuantity(cartItem.getCount());
					orderItem.setTotal(cartItem.getSubtotal());//С��
					
					//�Ѷ�������ӵ�������ȥ
					order.getOrderItem().add(orderItem);
				}
				
				
			//4������ҵ���ı��淽��ʵ�����ݵı������
				orderService.saveOrder(order);
			//5��request���󱣴涩����Order��
				request.setAttribute("order", order);
			//6����չ��ﳵ�е�����
				cart.clearCart();
			//7����ת��ҳ�棺/jsp/order_info.jsp
				return "/jsp/order_info.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}
}

