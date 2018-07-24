package com.lanqiao.shopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanqiao.shopping.service.CategoryService;
import com.lanqiao.shopping.service.ProductService;
import com.lanqiao.shopping.service.impl.CategoryServiceImpl;
import com.lanqiao.shopping.service.impl.ProductServiceImpl;
import com.lanqiao.shopping.vo.Category;
import com.lanqiao.shopping.vo.Product;
import com.lanqiao.shopping.web.baseServlet.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
	CategoryService categoryService = new CategoryServiceImpl();
	ProductService productService = new ProductServiceImpl();


	
	public String init(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		List<Category> clist = categoryService.finAll();
		List<Product> phlist = productService.findHot();
		List<Product> pnlist = productService.findNew();
		session.setAttribute("clist",clist);
		session.setAttribute("phlist",phlist);
		session.setAttribute("pnlist",pnlist);
		return "/jsp/index.jsp";
	}
		
	

}
