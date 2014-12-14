package com.example.fw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcHelper {

	private Connection conn;
	
	public JdbcHelper(ApplicationManager app, String url) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public  String countUsers(User user) throws SQLException {
			String countUsers = null;
			Statement st = null;
			ResultSet res = null;
			
			try {
				st = conn.createStatement();
				res = st.executeQuery("SELECT count(*) from mantis_user_table where username = '" + user.login + "'");
				
				while (res.next()) {
					countUsers = res.getString("count(*)");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					res.close();
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return countUsers;
		}
}
