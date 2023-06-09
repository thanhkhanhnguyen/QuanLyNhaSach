package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SaleDAO {
	public static ResultSet executeQuery(String sql) {

		ResultSet rs = null;
		try {

			Connection conn = MY_DB.myConnection();
			Statement stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
	
	public static ResultSet getTotalMoney() throws SQLException {
		
		String sql = "SELECT date,Sum(money) as Total_Money FROM  library.sale  GROUP BY date ORDER BY date DESC";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static ResultSet getMoney(String date) throws SQLException {
		
		String sql = "SELECT id_user,money as Total_Money FROM  library.sale WHERE date='"+date +"'";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static Boolean AddSale(String date,String id_user,int money) throws SQLException {
		String sqlInsert = "INSERT INTO `library`.`sale` (`date`, `id_user`, `money`) VALUES(?,?,?)";

		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, date);
		stmt.setString(2, id_user);
		stmt.setInt(3, money);
		
		Boolean flag = stmt.execute();
		return flag;

		
	}
}
