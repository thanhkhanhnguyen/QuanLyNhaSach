package DAO;

import java.sql.*;


public class MY_DB {
	public static Connection myConnection() throws SQLException
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url ="jdbc:mysql://localhost:3306/library";
		String user ="root";
		String pw ="Khanhthanh1@";
		
		Connection conn= DriverManager.getConnection(url, user, pw);
		return conn;
		
	}
}
