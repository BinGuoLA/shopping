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

			// 4���ѹ��ﳵ����ӹ��ﳵ��
			// 5����session�л�ȡ���ﳵ

			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if (cart == null) {
				// 5-1��session��û�й��ﳵ
				// �½����ﳵ
				cart = new Cart();// ���ﳵΪ��
				// ���ﱣ�浽session��
				request.getSession().setAttribute("cart", cart); // �գ�
			}
			// 5-2��session�д��ڹ��ﳵ
			// ��session��ȡ���ﳵ
			// 6���ѹ��ﳵ��ӵ����ﳵ
			cart.addCart(cartItem);// map(��Ʒ)
			// 7����ת��ҳ��(cart.jsp)

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
		response.setContentType("text/html;charset=utf-8");//����ajax����
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
