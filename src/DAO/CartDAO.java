package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CartDAO {
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
	
	public static ResultSet getCartById(String id) throws SQLException {
		
		String sql = "SELECT name,price,id From library.book , library.cart WHERE book.id = cart.id_book AND id_user ='" + id + "'";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static int CheckIdExist(String id_user,String id_book) throws SQLException {
		int iVal =0;
		String sql = "select * from cart where id_user='" + id_user+"' AND id_book ='" + id_book + "'";
		ResultSet rs = executeQuery(sql);
		if (rs.next()) {
			  iVal++;
			  
			}
		return iVal;  //0 is null  
	}
	
	public static Boolean AddCart(String id_user,String id_book) throws SQLException {
		String sqlInsert = "INSERT INTO cart(id_user,id_book) VALUES(?,?)";

		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, id_user);
		stmt.setString(2, id_book);
		
		Boolean flag = stmt.execute();
		return flag;

		
	}
	
	public static int DeleteCart(String id_user,String id_book) throws SQLException {
		String sql = "DELETE FROM cart where id_user='" + id_user+ "' AND id_book ='" + id_book + "'";
			Connection conn = MY_DB.myConnection();
			Statement stat = conn.createStatement();
			int flag = stat.executeUpdate(sql);
			return flag; // neu = 0 thi xoa k duoc

	}
	
	public static int DeleteAllCart(String id_user) throws SQLException {
		String sql = "DELETE FROM cart where id_user='" + id_user+ "'";
			Connection conn = MY_DB.myConnection();
			Statement stat = conn.createStatement();
			int flag = stat.executeUpdate(sql);
			return flag; // neu = 0 thi xoa k duoc

	}
	
	
	
}
