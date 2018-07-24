package com.lanqiao.shopping.service.impl;

import java.util.List;

import com.lanqiao.shopping.dao.CategoryDao;
import com.lanqiao.shopping.dao.impl.CategoryDaoImpl;
import com.lanqiao.shopping.service.CategoryService;
import com.lanqiao.shopping.vo.Category;

public class CategoryServiceImpl implements CategoryService{
	CategoryDao categoryDao = new CategoryDaoImpl();
	@Override
	public List<Category> finAll() {
		return categoryDao.findAll();
	}

}
