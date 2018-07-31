package com.lanqiao.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lanqiao.shop.dao.ProductDao;
import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.utils.DBHepler;
import com.lanqiao.shop.utils.PageUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findHot() {
		List<Product> phlist = new ArrayList<>();
		String sql = "select * from product where rownum < 10 and is_hot = 1 and pflag = 0 order by pdate desc";
		ResultSet rs = DBHepler.commomQuery(sql, new Object[0]);
		try {
			while (rs.next()) {
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
		DBHepler.getClose(rs, null, null);
		return phlist;
	}

	@Override
	public List<Product> findNew() {
		List<Product> pnlist = new ArrayList<>();
		String sql = "select * from product where pflag = 0 and rownum < 10 order by pdate";
		ResultSet rs = DBHepler.commomQuery(sql, new Object[0]);
		try {
			while (rs.next()) {
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
		DBHepler.getClose(rs, null, null);
		return pnlist;
	}

	// 根据id查找总记录数
	public int totalRecords(String cid) throws Exception {
		String sql = "select count(*) from product where cid = ?";
		Object[] obj = { cid };
		int totalRecord = 0;

		ResultSet rs = DBHepler.commomQuery(sql, obj);
		if (rs.next()) {
			totalRecord = rs.getInt(1);
		}
		DBHepler.getClose(rs, null, null);
		return totalRecord;
	}

	public int totalRecordsByName(String search) throws Exception {
		int totalRecord = 0;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBHepler.getConn();
			String sql = "select count(*) from product where pname like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			rs = ps.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHepler.getClose(rs, ps, conn);
		}
		return totalRecord;

	}

	// 根据cid查找分类并且分页显示
	public List<Product> findProductsByCidWithPage(String cid, int startIndex, int endIndex) throws Exception {

		List<Product> productList = new ArrayList<Product>();
		String sql = "select * from (select rownum rn ,p.* from product p where cid = ?) p1 where rn>=? and rn<=?";
		Object[] obj = { cid, startIndex, endIndex };
		ResultSet rs = DBHepler.commomQuery(sql, obj);
		while (rs.next()) {
			Product product = new Product();
			product.setIs_hot(rs.getInt("is_hot"));
			product.setMarket_price(rs.getString("market_price"));
			product.setPdate(rs.getString("pdate"));
			product.setPdesc(rs.getString("pdesc"));
			product.setPflag(rs.getInt("pflag"));
			product.setPid(rs.getString("pid"));
			product.setPimage(rs.getString("pimage"));
			product.setPname(rs.getString("pname"));
			product.setShop_price(rs.getString("shop_price"));
			productList.add(product);
		}
		DBHepler.getClose(rs, null, null);
		return productList;
	}

	// 根据pid查找商品
	public Product findProductByPid(String pid) throws Exception {
		String sql = "select * from product where pid = ?";
		Object[] obj = { pid };
		ResultSet rs = DBHepler.commomQuery(sql, obj);
		if (rs.next()) {
			Product product = new Product();
			product.setIs_hot(rs.getInt("is_hot"));
			product.setMarket_price(rs.getString("market_price"));
			product.setPdate(rs.getString("pdate"));
			product.setPdesc(rs.getString("pdesc"));
			product.setPflag(rs.getInt("pflag"));
			product.setPid(rs.getString("pid"));
			product.setPimage(rs.getString("pimage"));
			product.setPname(rs.getString("pname"));
			product.setShop_price(rs.getString("shop_price"));
			return product;
		}
		DBHepler.getClose(rs, null, null);
		return null;
	}

	@Override
	public List<Product> findProductsByName(String search, int startIndex, int endIndex) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Product> productList = new ArrayList<Product>();
		String sql = "select * from (select rownum rn ,p.* from product p where pname like ?) p1 where rn>=? and rn<=?";
		
		conn = DBHepler.getConn();
		ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + search + "%");
		ps.setInt(2, startIndex);
		ps.setInt(3, endIndex);
		rs = ps.executeQuery();
		
		while (rs.next()) {
			Product product = new Product();
			product.setIs_hot(rs.getInt("is_hot"));
			product.setMarket_price(rs.getString("market_price"));
			product.setPdate(rs.getString("pdate"));
			product.setPdesc(rs.getString("pdesc"));
			product.setPflag(rs.getInt("pflag"));
			product.setPid(rs.getString("pid"));
			product.setPimage(rs.getString("pimage"));
			product.setPname(rs.getString("pname"));
			product.setShop_price(rs.getString("shop_price"));
			productList.add(product);
		}
		DBHepler.getClose(rs, null, null);

		return productList;
	}

}
