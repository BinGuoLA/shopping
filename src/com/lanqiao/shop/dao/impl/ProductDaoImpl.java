package com.lanqiao.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lanqiao.shop.dao.ProductDao;
import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.utils.DBHepler;

public class ProductDaoImpl implements ProductDao{


	@Override
	public List<Product> findHot() {
		List<Product> phlist = new ArrayList<>();
		String sql = "select * from product where rownum < 10 and is_hot = 1 and pflag = 0 order by pdate desc";
		ResultSet rs = DBHepler.commomQuery(sql, new Object[0]);
		try {		
			while(rs.next()) {
				Product product = new Product();
				product.setCid(rs.getString("cid"));
				product.setMarket_price(rs.getString("Market_price"));
				product.setPdate(rs.getString("Pdate"));
				product.setPflag(rs.getInt("pflag"));
				product.setPid(rs.getString("pid"));
				product.setPimage(rs.getString("pimage"));
				product.setPname(rs.getString("pname"));
				product.setShop_price(rs.getString("Shop_price"));
				product.setPdesc(rs.getString("Pdesc"));
				product.setIs_hot(rs.getInt("is_hot"));
				phlist.add(product);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return phlist;
	}

	@Override
	public List<Product> findNew() {
		List<Product> pnlist = new ArrayList<>();
		String sql = "select * from product where pflag = 0 and rownum < 10 order by pdate";
		ResultSet rs = DBHepler.commomQuery(sql, new Object[0]);
		try {		
			while(rs.next()) {
				Product product = new Product();
				product.setCid(rs.getString("cid"));
				product.setMarket_price(rs.getString("Market_price"));
				product.setPdate(rs.getString("Pdate"));
				product.setPflag(rs.getInt("pflag"));
				product.setPid(rs.getString("pid"));
				product.setPimage(rs.getString("pimage"));
				product.setPname(rs.getString("pname"));
				product.setShop_price(rs.getString("Shop_price"));
				product.setPdesc(rs.getString("PDESC"));
				product.setIs_hot(rs.getInt("is_hot"));
				pnlist.add(product);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return pnlist;
	}

	

}
