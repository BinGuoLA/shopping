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


@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	CategoryService categoryService = new CategoryServiceImpl();
	
	public void findCategoryByCid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");//…Ë÷√ajax¬“¬Î
		List<Category> cList = categoryService.finAll();
		JSONArray jsonArray =  JSONArray.fromObject(cList);
		System.out.println("jsonArray£∫"+jsonArray);
		response.getWriter().print(jsonArray);
	}
}
