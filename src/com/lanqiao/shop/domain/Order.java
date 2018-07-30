package com.lanqiao.shop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private String oid;  //����ID
	private Date orderTime;//�µ�ʱ��
	private int   state;//״̬ 1:�µ�δ���� 2����δ���� 3����δ�ջ� 4�ջ�����
	private double total;//�ܼ�
	private String address;  //�ջ��˵�ַ
	private String name;  //�ջ�������
	private String telephone;  //�ջ��˵绰
	//private String uuid;  �������ݿ�Ĺ���ʵ�ֶ������ϵ
	private Users users;  //���������������������ֶ���Ĺ�����ϵ   1:1����
	//�������ж�����ļ���
	List<OrderItem> orderItem = new ArrayList<OrderItem>();//1��N����
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

