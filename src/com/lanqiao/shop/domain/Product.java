package com.lanqiao.shop.domain;

import java.util.Date;

public class Product {
	private String pid;
	private String pname;
	private String market_price;
	private String shop_price;
	private String pimage;
	private String pdate;
	private int is_hot;
	private String pdesc;
	private int pflag;//是否下架  默认:0没有
	private String cid;//
	
	public String getPid() {
		return pid;
	}
	public String getPname() {
		return pname;
	}
	public String getMarket_price() {
		return market_price;
	}
	public String getShop_price() {
		return shop_price;
	}
	public String getPimage() {
		return pimage;
	}
	public String getPdate() {
		return pdate;
	}
	public int getIs_hot() {
		return is_hot;
	}
	public String getPdesc() {
		return pdesc;
	}
	public int getPflag() {
		return pflag;
	}
	public String getCid() {
		return cid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}
	public void setShop_price(String shop_price) {
		this.shop_price = shop_price;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public void setPflag(int pflag) {
		this.pflag = pflag;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String pid, String pname, String market_price, String shop_price, String pimage, String pdate,
			int is_hot, String pdesc, int pflag, String cid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.market_price = market_price;
		this.shop_price = shop_price;
		this.pimage = pimage;
		this.pdate = pdate;
		this.is_hot = is_hot;
		this.pdesc = pdesc;
		this.pflag = pflag;
		this.cid = cid;
	}
	
}
