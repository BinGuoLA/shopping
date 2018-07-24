package com.lanqiao.shopping.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanqiao.shopping.service.ProductService;
import com.lanqiao.shopping.service.UsersService;
import com.lanqiao.shopping.service.impl.ProductServiceImpl;
import com.lanqiao.shopping.service.impl.UsersServiceImpl;
import com.lanqiao.shopping.utils.PageBean;
import com.lanqiao.shopping.vo.Product;
import com.lanqiao.shopping.web.baseServlet.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	ProductService productService = new ProductServiceImpl();

	public String pinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.…Ë÷√±‡¬Î
		String pid = request.getParameter("pid");
		Product productInfo = productService.findProductByPid(pid);
		HttpSession session = request.getSession();
		session.setAttribute("productInfo", productInfo);
		return "/jsp/product_info.jsp";
	}

	public String plistbycid(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");

		String cid = request.getParameter("cid");
		PageBean<Product> pagebean = productService.findByCid(Integer.valueOf(cid), Integer.valueOf(page));
		HttpSession session = request.getSession();
		session.setAttribute("pagebean", pagebean);
		return "/jsp/product_list.jsp";

	}
}
