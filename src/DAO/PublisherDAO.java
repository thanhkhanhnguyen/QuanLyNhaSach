package DAO;

import java.io.IOException;
import java.sql.*;

import Model.Publisher;


public class PublisherDAO {
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
	
	public static ResultSet getAllPublisher() throws SQLException {
		
		String sql = "select * from publisher ";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static int CheckPublisherExist(Publisher pub) throws SQLException {
		int iVal =0;
		String sql = "select * from publisher where name='"+pub.getName()+"' AND email='" +pub.getEmail()+"' AND address='"+pub.getAddress()+"'";
		ResultSet rs = executeQuery(sql);
		if (rs.next()) {
			  if (rs.getObject("id") != null && !rs.wasNull()) {
			    iVal = rs.getInt("id");
			  }
			}
		return iVal;  //0 is null  
	}
	
	public static Boolean AddPublisher(Publisher a) throws SQLException {
		String sqlInsert = "INSERT INTO publisher(name,email,address) VALUES(?,?,?)";

		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, a.getName());
		stmt.setString(2, a.getEmail());
		stmt.setString(3, a.getAddress());

		Boolean flag = stmt.execute();
		return flag;

		
	}
	
	public static int DeletePublisher(int id) throws SQLException {
		String sql = "DELETE FROM publisher WHERE id =" + id ;
			Connection conn = MY_DB.myConnection();
			Statement stat = conn.createStatement();
			int flag = stat.executeUpdate(sql);
			return flag; // neu = 0 thi xoa k duoc

	}
	
	public static int UpdatePublisher(Publisher us) throws IOException, SQLException {
		int id =us.getId();
		String sqlInsert = "UPDATE publisher SET name = ?,email=?,address=?" + "WHERE id =" + id;

	
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, us.getName());
		stmt.setString(2, us.getEmail());
		stmt.setString(3, us.getAddress());
		int flag= stmt.executeUpdate();
		return flag;
		
	}
	
	public static ResultSet getSearchInformation(String text) throws SQLException {
		
		String sql = "select * from publisher where CONCAT(id,name,email,address) LIKE'%" + text + "%'";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	public static int getIdtoNamePublisher(String name) {
		String sql = "select * from publisher where CONCAT(name) LIKE'%" + name + "%'";
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
