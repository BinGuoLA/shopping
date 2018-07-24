package com.lanqiao.shop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.spi.DirStateFactory.Result;

import oracle.jdbc.driver.OracleDriver;
/**
 * @author ZHG 1、连接数据库 2、关闭数据库
 */
public class DBHepler {
	// 四个变量
	private static String driver;
	private static String url;
	private static String name;
	private static String pwd;

	// 初始化属性文件的加载
	static {
		try {
			// 创建Properties对象
			Properties properties = new Properties();
			// 内加载器加载文件
			InputStream is = DBHepler.class.getClassLoader().getResourceAsStream("config/jdbc.properties");
			// 把流对象传递给Properties对象
			properties.load(is);

			// 调用属性文件中的值
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			name = properties.getProperty("name");
			pwd = properties.getProperty("pwd");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 1、连接数据库
	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,name,pwd);
			//System.out.println("===="+conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
	
	
	
	

	// 2、关闭数据库
	public static void getClose(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//3、实现通用的增删改操作的方法
	public static void commom(String sql,Object...obj) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			//?
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
				System.out.println("====="+obj[i]);
			}
			int num = ps.executeUpdate();
			if(num>0) {
				System.out.println("操作成功");
			}else {
				System.out.println("操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet commomQuery(String sql,Object...obj) {
		try {
			Connection conn = getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//处理问题传值
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
			}
			//结果集
			 ResultSet executeQuery = ps.executeQuery();
			
			
			return executeQuery;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
