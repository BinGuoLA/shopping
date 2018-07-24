package com.lanqiao.shopping.test;

import java.util.List;

import com.lanqiao.shopping.dao.impl.ProductDaoImpl;
import com.lanqiao.shopping.utils.DBHepler;
import com.lanqiao.shopping.vo.Product;

public class Test {
	
		public static boolean foo(char c){
			System.out.println(c);
			return true;
		}
		public static void main(String[] args) {
			int i = 0;
			for(foo('A');foo('B')&&(i<2);foo('C')){
				i++;
				foo('D');
			}
		}
	

}
