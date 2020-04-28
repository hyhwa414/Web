package com.ssafy.happyhouse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
//	static final String URL = "jdbc:mysql://127.0.0.1:3305/ssafyweb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
//	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//	static final String ID = "ssafy";
//	static final String PASSWORD = "ssafy";
//
//	static {
//		try {
//			Class.forName(DRIVER);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(URL, ID, PASSWORD);
//	}

	public static Connection getConnection() throws SQLException{
		DataSource dataSource = null;
		try {
			Context iCtx = new InitialContext();
			Context ctx = (Context) iCtx.lookup("java:comp/env");
			dataSource = (DataSource) ctx.lookup("jdbc/ssafy"); // 오브젝트를 찾으므로 형변환
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource.getConnection();
	}
	
	
	public static void close(AutoCloseable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
