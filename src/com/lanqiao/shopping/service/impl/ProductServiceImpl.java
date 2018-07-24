package com.lanqiao.shopping.service.impl;

import java.util.List;

import com.lanqiao.shopping.dao.ProductDao;
import com.lanqiao.shopping.dao.impl.ProductDaoImpl;
import com.lanqiao.shopping.service.ProductService;
import com.lanqiao.shopping.utils.PageBean;
import com.lanqiao.shopping.vo.Product;



public class ProductServiceImpl implements ProductService{
	ProductDao productDao = new ProductDaoImpl();
	@Override
	public List<Product> findHot() {		
		return productDao.findHot();
	}

	@Override
	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	@Override
	public Product findProductByPid(String pid) {
		return productDao.findProductByPid(pid);
	}
	
	public PageBean<Product> findByCid(int cid, int page) {
		
		PageBean<Product> pageBean=new PageBean<Product>();

		pageBean.setPage(page);
		int limit = 12;
		pageBean.setLimit(limit);

		int totalCount=0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);

		int totalPage = 0;
		if(totalCount % limit ==0 ) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;
		int end = page * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,end);
		for (Product product : list) {
			System.out.println(product.getPid());
		}
		pageBean.setList(list);
		
		return pageBean;
	}

}
