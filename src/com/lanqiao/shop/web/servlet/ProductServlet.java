package com.lanqiao.shop.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shop.domain.Category;
import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.service.ProductService;
import com.lanqiao.shop.service.impl.ProductServiceImpl;
import com.lanqiao.shop.utils.PageUtils;
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

		/*
		 * String jedis_phList = jedis.get("phList");
		 * 
		 * try { if (jedis_phList == null) { System.out.println("从数据库中从获取最热");
		 */
		List<Product> phList = productService.findNew();
		JSONArray jsonArray = JSONArray.fromObject(phList);
		// jedis.set("phList", jsonArray.toString());

		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * } else { System.out.println("从jedis从获取最热");
		 * response.getWriter().print(jedis_phList); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}

	public void findNew(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");// 设置ajax乱码

		/*
		 * String jedis_pnList = jedis.get("pnList");
		 * System.out.println("jedis_pnList:"+jedis_pnList); try { if (jedis_pnList ==
		 * null) { System.out.println("从数据库中从获取最新");
		 */
		List<Product> pnList = productService.findHot();
		JSONArray jsonArray = JSONArray.fromObject(pnList);
		// jedis.set("pnList", jsonArray.toString());
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * } else { System.out.println("从jedis从获取最新");
		 * response.getWriter().print(jedis_pnList); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	public String findProductByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		String curPageNo = request.getParameter("num");

		try {
			PageUtils pageUtils = productService.findProductByCidWithPage(cid, Integer.valueOf(curPageNo));
			request.setAttribute("page", pageUtils);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Cookie[] cookies = request.getCookies();
		List<Product> historyPdList = new ArrayList<>();
		for (int i = 0; cookies != null && i < cookies.length; i++){
			
			//找到我们想要的cookie
			if (cookies[i].getName().equals("historyCookie")){
				String[] pids = cookies[i].getValue().split("\\-");
				
				//得到cookie中存在的id，展现浏览过的商品
				for (String pid : pids){
					historyPdList.add(productService.findProductByPid(pid));
				}
			}
		}
		request.setAttribute("historyPdList", historyPdList);
		
		
		return "/jsp/product_list.jsp";
	}
	
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		Product productInfo;
		//浏览历史记录储存
		String cookieValue = buildCookie(pid, request);//产生想要的cookie中的值
		Cookie cookie = new Cookie("historyCookie", cookieValue);
		cookie.setMaxAge(1*24*3600);
		response.addCookie(cookie);
		try {
			productInfo = productService.findProductByPid(pid);
			request.setAttribute("productInfo", productInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/jsp/product_info.jsp";
	}
	
		private String buildCookie(String pid, HttpServletRequest request) {
		
		String historyCookie = null;
		
		//得到请求中带来的cookie值
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++){
			if (cookies[i].getName().equals("historyCookie") ){
				historyCookie = cookies[i].getValue();
			}
		}
		
		//如果为空返回当前商品的id
		if (historyCookie == null){
			return pid;
		}
		
		LinkedList<String> list = new LinkedList<String>(Arrays.asList((historyCookie.split("\\-"))));
		
		//对不同的情况进行分析返回id的值
		if (list.contains(pid)){
			list.remove(pid);
		}else{
			if (list.size() >= 7){
				list.removeLast();
			}
		}
		list.addFirst(pid);
		
		StringBuffer sb = new StringBuffer();
		for (String sid : list){
			sb.append(sid + "-");
		}
		sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}

	public String findProductsByName(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		
		String search = request.getParameter("search");
		
		String curPageNo = request.getParameter("num");
		
		System.out.println("search:"+search);
		System.out.println(curPageNo);
		PageUtils pageUtils = productService.findProductsByName(search,Integer.valueOf(curPageNo));
		
		request.setAttribute("page", pageUtils);
		
		return "/jsp/product_list.jsp";
	}


}
