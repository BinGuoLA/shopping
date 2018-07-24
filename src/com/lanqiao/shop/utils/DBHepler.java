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
 * @author ZHG 1���������ݿ� 2���ر����ݿ�
 */
public class DBHepler {
	// �ĸ�����
	private static String driver;
	private static String url;
	private static String name;
	private static String pwd;

	// ��ʼ�������ļ��ļ���
	static {
		try {
			// ����Properties����
			Properties properties = new Properties();
			// �ڼ����������ļ�
			InputStream is = DBHepler.class.getClassLoader().getResourceAsStream("config/jdbc.properties");
			// �������󴫵ݸ�Properties����
			properties.load(is);

			// ���������ļ��е�ֵ
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			name = properties.getProperty("name");
			pwd = properties.getProperty("pwd");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 1���������ݿ�
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
	
	
	
	

	// 2���ر����ݿ�
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
	
	//3��ʵ��ͨ�õ���ɾ�Ĳ����ķ���
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
				System.out.println("�����ɹ�");
			}else {
				System.out.println("����ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet commomQuery(String sql,Object...obj) {
		try {
			Connection conn = getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//�������⴫ֵ
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
			}
			//�����
			 ResultSet executeQuery = ps.executeQuery();
			
			
			return executeQuery;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
