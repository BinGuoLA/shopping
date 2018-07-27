package com.lanqiao.shop.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shop.dao.ProductDao;
import com.lanqiao.shop.dao.impl.ProductDaoImpl;
import com.lanqiao.shop.domain.Cart;
import com.lanqiao.shop.domain.CartItem;
import com.lanqiao.shop.domain.Category;
import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.web.base.BaseServlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	ProductDao productDao = new ProductDaoImpl();

	public String addCart(HttpServletRequest request, HttpServletResponse response) {

		String username = (String) request.getSession().getAttribute("username");
		if (username == null) {
			return "/jsp/login.jsp";
		}

		String pid = request.getParameter("pid");
		String count = request.getParameter("count");

		Product product;
		try {
			product = productDao.findProductByPid(pid);

			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCount(Integer.valueOf(count));

			// 4、把购物车项添加购物车中
			// 5、从session中获取购物车

			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if (cart == null) {
				// 5-1：session中没有购物车
				// 新建购物车
				cart = new Cart();// 购物车为空
				// 购物保存到session中
				request.getSession().setAttribute("cart", cart); // 空？
			}
			// 5-2：session中存在购物车
			// 从session获取购物车
			// 6、把购物车添加到购物车
			cart.addCart(cartItem);// map(商品)
			// 7、跳转到页面(cart.jsp)

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/jsp/cart.jsp";
	}

	public String delCart(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.delCart(pid);
		return "/jsp/cart.jsp";
	}

	public String clearCart(HttpServletRequest request, HttpServletResponse response) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clearCart();
		return "/jsp/cart.jsp";
	}
	
	public void updateCart(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("==========updateCart==========");
		response.setContentType("text/html;charset=utf-8");//设置ajax乱码
		String pid = request.getParameter("pid");
		String count = request.getParameter("count");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.updateCart(pid,count);
		JSONArray jsonArray =  JSONArray.fromObject(cart);
	    System.out.println(jsonArray);
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
