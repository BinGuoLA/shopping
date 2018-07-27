package com.lanqiao.shop.domain;

public class CartItem {
    private Product product;
    private int count;
    private double subtotal;
    
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return count * Double.valueOf(product.getShop_price());

}
}
