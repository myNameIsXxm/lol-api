package com.es.dao.impl;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BaseSqlConnect {

	private static String url;
	private static String username;
	private static String password;

	static {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(
					BaseSqlConnect.class.getClassLoader().getResource("").toURI().getPath() + "app.properties"));
			url = prop.getProperty("sql.conn.url");
			username = prop.getProperty("sql.conn.username");
			password = prop.getProperty("sql.conn.password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String[]> queryForList(String sql, Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String[] arr = new String[rs.getMetaData().getColumnCount()];
				for (int i = 0; i < arr.length; i++) {
					arr[i] = rs.getString(i + 1);
				}
				list.add(arr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
