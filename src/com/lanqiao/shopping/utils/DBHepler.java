package com.lanqiao.shopping.utils;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;




public class DBHepler {
	private static String driver ;
    private static String url ;
    private static String user ;
    private static String pwd ;
    
    static {
    	try {
    	Properties properties = new Properties();
    	
    	InputStream resourceAsStream = DBHepler.class.getClassLoader().getResourceAsStream("config/jdbc.properties");    	
			
    	properties.load(resourceAsStream);
    	
    	driver = properties.getProperty("driver");
    	url = properties.getProperty("url");
    	user = properties.getProperty("user");
    	pwd = properties.getProperty("pwd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static Connection getConn() {
    	Connection conn = null;
    	
    		try {
    			Class.forName(driver);
				conn = DriverManager.getConnection(url,user,pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	
    	return conn;
    }
    
    public static void closeConn(ResultSet rs,Statement sm,Connection conn) {
    	
			try {
				if(rs != null) rs.close();
				if(sm != null) sm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    
    


	//3、实现通用的增删改操作的方法
	public static void common(String sql,Object...obj) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			//?
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
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
