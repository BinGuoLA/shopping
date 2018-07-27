package com.lanqiao.shop.service.impl;

import java.util.List;

import com.lanqiao.shop.dao.ProductDao;
import com.lanqiao.shop.dao.impl.ProductDaoImpl;
import com.lanqiao.shop.domain.Product;
import com.lanqiao.shop.service.ProductService;
import com.lanqiao.shop.utils.PageUtils;

public class ProductServiceImpl implements ProductService {
	ProductDao productDao = new ProductDaoImpl();

	@Override
	public List<Product> findHot() {
		return productDao.findHot();
	}

	@Override
	public List<Product> findNew() {
		return productDao.findNew();
	}

	@Override
	public PageUtils findProductByCidWithPage(String cid, int curPageNo) throws Exception {
		int totalRecords = productDao.totalRecords(cid);
		int limit = 12;
		PageUtils pageUtils = new PageUtils(curPageNo, totalRecords, limit);

		List<Product> pList = productDao.findProductsByCidWithPage(cid, pageUtils.getStartIndex(),
				pageUtils.getEndIndex());
		pageUtils.setList(pList);
		pageUtils.setUrl("ProductServlet?method=findProductByCidWithPage&cid=" + cid);

		return pageUtils;
	}

	public Product findProductByPid(String pid) throws Exception {
		return productDao.findProductByPid(pid);
	}

}
