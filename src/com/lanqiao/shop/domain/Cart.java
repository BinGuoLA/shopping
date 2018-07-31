package com.lanqiao.shop.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<String, CartItem> map = new HashMap<String, CartItem>();// pid 产品项(产品 数量 小计)
	private double total;
	public Map<String, CartItem> getMap() {
		return map;
	}

	
	public double getTotal() {
		total = 0;
		for (CartItem cartItem : map.values()) {
			total += cartItem.getSubtotal();
		}
		return total;
	}

	public void addCart(CartItem cartItem) {

		String pid = cartItem.getProduct().getPid();

		if (map.containsKey(pid)) {
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			map.put(pid, cartItem);
		}
	}

	public void delCart(String pid) {
		map.remove(pid);
	}

	public void clearCart() {
		map.clear();
	}

	public void updateCart(String pid, String count) {
		map.get(pid).setCount(Integer.valueOf(count));
	}

}
