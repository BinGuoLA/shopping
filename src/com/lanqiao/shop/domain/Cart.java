package com.lanqiao.shop.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();

	public Map getMap() {
		return map;
	}

	private double total;

	public double getTotal() {
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
		total += cartItem.getSubtotal();
	}

}
