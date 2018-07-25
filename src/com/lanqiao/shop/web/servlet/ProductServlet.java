package com.lanqiao.shop.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shop.service.ProductService;
import com.lanqiao.shop.service.impl.ProductServiceImpl;
import com.lanqiao.shop.web.base.BaseServlet;


@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	ProductService productService = new ProductServiceImpl();
	
	public String findProductByCid(HttpServletRequest request, HttpServletResponse response) {
		String cid  = request.getParameter("cid");
		
		return "/jsp/product_list.jsp";
	}
	
}
