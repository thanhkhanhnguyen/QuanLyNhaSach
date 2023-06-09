package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Author;

public class AuthorDAO {
	
	public static int CheckIdExist(int id) throws SQLException {
		int iVal =0;
		String sql = "select * from author where id=" + id;
		ResultSet rs = executeQuery(sql);
		if (rs.next()) {
			  if (rs.getObject("id") != null && !rs.wasNull()) {
			    iVal = rs.getInt("id");
			  }
			
			}
		return iVal;  //0 is null  
	}
	
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
	
	public static ResultSet getAllAuthor() throws SQLException {
		
		String sql = "select * from author";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static Boolean AddAuthor(Author a) throws SQLException {
		String sqlInsert = "INSERT INTO author(name,gender,information) VALUES(?,?,?)";

		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, a.getName());
		stmt.setString(2, a.getGender());
		stmt.setString(3, a.getInformation());

		Boolean flag = stmt.execute();
		return flag;

		
	}
	
	public static int DeleteAuthor(int id) throws SQLException {
		String sql = "DELETE FROM author WHERE id =" + id ;
			Connection conn = MY_DB.myConnection();
			Statement stat = conn.createStatement();
			int flag = stat.executeUpdate(sql);
			return flag; // neu = 0 thi xoa k duoc

	}
	
	public static int UpdateAuthor(Author us) throws IOException, SQLException {
		int id =us.getId();
		String sqlInsert = "UPDATE author SET name = ?,gender=?,information=?" + "WHERE id =" + id;

	
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, us.getName());
		stmt.setString(2, us.getGender());
		stmt.setString(3, us.getInformation());
		int flag= stmt.executeUpdate();
		return flag;
		
	}
	public static int getIdtoNameAuthor(String name) {
		String sql = "select * from author where CONCAT(name) LIKE'%" + name + "%'";
		ResultSet rs = executeQuery(sql);
		int id = 0;
		try {
			while (rs.next()) {
				id=rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}
}
