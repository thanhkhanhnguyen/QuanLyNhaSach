package DAO;

import java.io.IOException;
import java.sql.*;
import Model.User;

public class UserDAO {
	
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
	
	public static int CheckAdminExist(String id,String pass) throws SQLException {
		int iVal =0;
		//int flag=0;
		String sql = "select * from library.user where id='" + id + "' AND password='"+pass +"' AND position=0";
		ResultSet rs = executeQuery(sql);
		if (rs.next()) {
			 iVal++;
			  
			}
		return iVal;  //0 is null  
	}
	
	public static int CheckUserExist(String id,String pass) throws SQLException {
		int iVal =0;
		//int flag=0;
		String sql = "select * from library.user where id='" + id + "' AND password='"+pass +"' AND position=1";
		ResultSet rs = executeQuery(sql);
		if (rs.next()) {
			  iVal++;
			}
		return iVal;  //0 is null  
	}
	
	public static ResultSet getAllUser() throws SQLException {
	
		String sql = "select * from library.user where position=1";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static ResultSet getUserById(String id) throws SQLException {
		
		String sql = "select * from library.user where id='"+id+"'";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static int CheckIdExist(String id) throws SQLException {
		int iVal =0;
		String sql = "select * from library.user where id='" + id+"'";
		ResultSet rs = executeQuery(sql);
		if (rs.next()) {
			iVal++;
			  
			}
		return iVal;  //0 is null  
	}
	
	public static Boolean AddUser(User a) throws SQLException {
		String sqlInsert = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?)";

		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, a.getId());
		stmt.setString(2, a.getName());
		stmt.setString(3, a.getBirthDay());
		stmt.setString(4, a.getPhone());
		stmt.setString(5, a.getAddress());
		stmt.setString(6, a.getPassWord());
		stmt.setInt(7, a.getPosition());
		stmt.setInt(8,a.getMoney());
		Boolean flag = stmt.execute();
		return flag;

		
	}
	
	public static int DeleteUser(String id) throws SQLException {
		String sql = "DELETE FROM user WHERE id ='" + id +"'";
			Connection conn = MY_DB.myConnection();
			Statement stat = conn.createStatement();
			int flag = stat.executeUpdate(sql);
			return flag; // neu = 0 thi xoa k duoc

	}
	
	public static int UpdateUser(User us) throws IOException, SQLException {
		String id =us.getId();
		String sqlInsert = "UPDATE user SET name = ?,birthday=?,phone=?,address=?,password=? " + "WHERE id = '" + id+"'";

	
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, us.getName());
		stmt.setString(2, us.getBirthDay());
		stmt.setString(3, us.getPhone());
		stmt.setString(4, us.getAddress());
		stmt.setString(5, us.getPassWord());
		int flag= stmt.executeUpdate();
		return flag;
		
	}
	
	
	
	
	public static int UpdateMoney(int check,String id,int money) throws IOException, SQLException {
		String sqlInsert;
		if(check == 1) {
			sqlInsert = "UPDATE user SET money = money + ? " + " WHERE id = '" + id+"'";
		}
		else {
			sqlInsert = "UPDATE user SET money = money - ? " + " WHERE id = '" + id+"'";
		}
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setInt(1, money);
		int flag= stmt.executeUpdate();
		return flag;
		
	}
	
	public static int getMoney(String id) throws SQLException {
		int money =0;
		String sql = "select * from library.user where id='"+id+"'";
		ResultSet rs = executeQuery(sql);
		while(rs.next()) {
			money = rs.getInt("money");
		}
		
		return money;
		
	}
	
}
