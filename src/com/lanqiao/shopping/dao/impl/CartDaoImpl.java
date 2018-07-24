package com.lanqiao.shopping.dao.impl;



import com.lanqiao.shopping.dao.CartDao;
import com.lanqiao.shopping.service.ProductService;
import com.lanqiao.shopping.service.impl.ProductServiceImpl;
import com.lanqiao.shopping.vo.Cart;
import com.lanqiao.shopping.vo.CartItem;
import com.lanqiao.shopping.vo.Product;


public class CartDaoImpl implements CartDao{
	ProductService productService = new ProductServiceImpl();
	@Override
	public void addCart(Cart cart,String pid,int num) {
	    
			CartItem cartItem = new CartItem();
	  
			cartItem.setCount(num);
	   
			Product product = productService.findProductByPid(pid);
	   
			cartItem.setProduct(product);

			
			cart.addCart(cartItem);
			
		}
	

}
