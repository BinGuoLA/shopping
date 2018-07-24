package com.lanqiao.shop.service.impl;

import java.util.List;

import com.lanqiao.shop.dao.CategoryDao;
import com.lanqiao.shop.dao.impl.CategoryDaoImpl;
import com.lanqiao.shop.service.CategoryService;
import com.lanqiao.shop.domain.Category;

public class CategoryServiceImpl implements CategoryService{
	CategoryDao categoryDao = new CategoryDaoImpl();
	@Override
	public List<Category> finAll() {
		return categoryDao.findAll();
	}

}