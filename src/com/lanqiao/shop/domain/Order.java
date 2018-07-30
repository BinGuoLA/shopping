package com.lanqiao.shop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private String oid;  //订单ID
	private Date orderTime;//下单时间
	private int   state;//状态 1:下单未付款 2付款未发货 3发货未收货 4收货结束
	private double total;//总计
	private String address;  //收货人地址
	private String name;  //收货人姓名
	private String telephone;  //收货人电话
	//private String uuid;  参照数据库的关联实现对象的联系
	private Users users;  //对象中引用其它对象，体现对象的关联关系   1:1关联
	//包含所有订单项的集体
	List<OrderItem> orderItem = new ArrayList<OrderItem>();//1：N关联
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public List<OrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
}

