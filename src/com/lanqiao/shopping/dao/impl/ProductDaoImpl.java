package com.lanqiao.shopping.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lanqiao.shopping.dao.ProductDao;
import com.lanqiao.shopping.utils.DBHepler;
import com.lanqiao.shopping.utils.PageBean;
import com.lanqiao.shopping.vo.Category;
import com.lanqiao.shopping.vo.Product;


public class ProductDaoImpl implements ProductDao{
	
	@Override
	public List<Product> findHot() {
		List<Product> phlist = new ArrayList<>();
		String sql = "select * from product where rownum < 10 and is_hot = 1 order by pdate";
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
		String sql = "select * from product where rownum < 10 order by pdate";
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


	@Override
	public Product findProductByPid(String pid) {
		String sql = "select * from product where pid = ?";
		Object[] obj = {pid};
		ResultSet rs = DBHepler.commomQuery(sql,obj);
		Product product = new Product();
		try {		
			if(rs.next()) {
				
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
				
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return product;
	}


	@Override
	 public int findCountCid(int cid) {
    	String sql = "select * from Product  where cid = ?";
    	Object[] obj = {cid};
		ResultSet rs = DBHepler.commomQuery(sql,obj);
		int sum = 0;
		try {		
			while(rs.next()) {
				sum++;
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("sum:"+sum);
			return sum;
	}
	
	/*@Override
	public int findCount() {
    	String hql = "select count(*) from Product";
    	List<Long> list = this.getHibernateTemplate().find(hql);
    	if(list !=null && list.size()>0) {
    		return list.get(0).intValue();
    	}
    	return 0;
    	}*/
	@Override
	public <T> List findByPageCid(int cid, int begin, int limit) {
		List<Product> pcidlist = new ArrayList<>();
    	String sql = "select * from (select p.*,rownum rn from product p where cid = ?)  where   rn > ? and rn <= ?";
    	Object[] obj = {cid,begin,limit};
    	ResultSet rs = DBHepler.commomQuery(sql,obj);
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
				pcidlist.add(product);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return pcidlist;
}


	



	
}
