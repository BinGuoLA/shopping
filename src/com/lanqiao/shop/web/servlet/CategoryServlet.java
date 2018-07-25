package com.lanqiao.shop.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shop.domain.Category;
import com.lanqiao.shop.service.CategoryService;
import com.lanqiao.shop.service.impl.CategoryServiceImpl;
import com.lanqiao.shop.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;


@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	CategoryService categoryService = new CategoryServiceImpl();
	
	public void findCategoryByCid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=utf-8");//设置ajax乱码
		
		Jedis jedis = new Jedis("localhost");
		
		String jedis_cList = jedis.get("jedis_cList");
		
		if(jedis_cList == null) {
			System.out.println("从数据库中从获取分类");
			List<Category> cList = categoryService.finAll();
			JSONArray jsonArray =  JSONArray.fromObject(cList);
			jedis.set("jedis_cList", jsonArray.toString());
			response.getWriter().print(jsonArray);
		}else {
			System.out.println("从jedis从获取分类");
			response.getWriter().print(jedis_cList);
		}
		
	}
}
