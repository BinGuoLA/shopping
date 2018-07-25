package com.lanqiao.shop.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shop.domain.Category;
import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.service.ProductService;
import com.lanqiao.shop.service.impl.ProductServiceImpl;
import com.lanqiao.shop.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	ProductService productService = new ProductServiceImpl();
	Jedis jedis = new Jedis("localhost");
	public String findProductByCid(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");

		return "/jsp/product_list.jsp";
	}

	public void findHot(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");// 设置ajax乱码


		String jedis_phList = jedis.get("phList");

		try {
			if (jedis_phList == null) {
				System.out.println("从数据库中从获取最热");
				List<Product> phList = productService.findNew();
				JSONArray jsonArray = JSONArray.fromObject(phList);
				jedis.set("phList", jsonArray.toString());

				response.getWriter().print(jsonArray);

			} else {
				System.out.println("从jedis从获取最热");
				response.getWriter().print(jedis_phList);
			}
			System.out.println(jedis_phList);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findNew(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");// 设置ajax乱码

	

		String jedis_pnList = jedis.get("pnList");

		try {
			if (jedis_pnList == null) {
				System.out.println("从数据库中从获取最新");
				List<Product> pnList = productService.findHot();
				JSONArray jsonArray = JSONArray.fromObject(pnList);
				jedis.set("pnList", jsonArray.toString());
				response.getWriter().print(jsonArray);
			} else {
				System.out.println("从jedis从获取最新");
				response.getWriter().print(jedis_pnList);
			}
			
			System.out.println(jedis_pnList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
