package com.lanqiao.shopping.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanqiao.shopping.dao.CartDao;
import com.lanqiao.shopping.dao.impl.CartDaoImpl;
import com.lanqiao.shopping.vo.Cart;
import com.lanqiao.shopping.web.baseServlet.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	CartDao cartDao = new CartDaoImpl();
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public String addCart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();	
		String pid = request.getParameter("pid");
		String quantity = request.getParameter("quantity");
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		cartDao.addCart(cart,pid, Integer.valueOf(quantity));
		session.setAttribute("cart", cart);
		return "/jsp/cart.jsp";
	}
	

}
