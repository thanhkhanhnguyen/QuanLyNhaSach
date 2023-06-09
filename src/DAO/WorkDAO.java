package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Work;

public class WorkDAO {
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
	
	public static int getIdWork(String id_user,String id_book) {
		int id=0;
		String sql = "select * from work WHERE id_user ='" + id_user + "' AND id_book ='"+id_book + "' AND status <> 'YES'";
		ResultSet rs = executeQuery(sql);
		try {
			while(rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static ResultSet getAllWork() throws SQLException {
		
		String sql = "SELECT  library.work.id as WorkId,user.id as UserId,user.name as UserName,book.name as BookName,start_date,end_date,status,total "
				+" FROM library.work "
				+" INNER JOIN library.user ON work.id_user = user.id, "
				+" library.book "
				+" INNER JOIN library.author ON book.id_author = author.id "
				+" INNER JOIN library.publisher ON book.id_publisher = publisher.id "
				+" WHERE book.id= work.id_book ORDER BY start_date DESC";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	
	
	public static ResultSet getStatusBook(String status) throws SQLException {
		
		String sql = "SELECT  library.work.id as WorkId,user.id as UserId,user.name as UserName,book.name as BookName,start_date,end_date,status,total "
				+" FROM library.work "
				+" INNER JOIN library.user ON work.id_user = user.id, "
				+" library.book "
				+" INNER JOIN library.author ON book.id_author = author.id "
				+" INNER JOIN library.publisher ON book.id_publisher = publisher.id "
				+" WHERE book.id= work.id_book AND status='"+status+"' ORDER BY start_date DESC";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static ResultSet findInformation(String text) throws SQLException {
		
		String sql = "SELECT  library.work.id as WorkId,user.id as UserId,user.name as UserName,book.name as BookName,start_date,end_date,status,total "
				+" FROM library.work "
				+" INNER JOIN library.user ON work.id_user = user.id, "
				+" library.book "
				+" INNER JOIN library.author ON book.id_author = author.id "
				+" INNER JOIN library.publisher ON book.id_publisher = publisher.id "
				+" WHERE book.id= work.id_book AND CONCAT(work.id,user.id,user.name,book.name,start_date,end_date) LIKE'%" + text + "%' ORDER BY start_date DESC";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static ResultSet getWork() {
		String sql = "select * from work WHERE status <> 'YES'";
		ResultSet rs = executeQuery(sql);
		return rs;
	}
	
	public static ResultSet getWorkById(int id) {
		String sql = "select * from work WHERE id ="+id;
		ResultSet rs = executeQuery(sql);
		return rs;
	}
	
	public static int checkIdWork(int id) {
		String sql = "select * from work WHERE id ="+id;
		ResultSet rs = executeQuery(sql);
		int val =0;
		try {
			while(rs.next()) {
				val ++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return val;
	}
	
	public static int UpdateStatusWork(int id,String status) throws IOException, SQLException {
		
		String sqlInsert = "UPDATE work SET status = ? " + "WHERE id = " + id;

	
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, status);
		int flag= stmt.executeUpdate();
		return flag;
		
	}
	
	public static int UpdateReturnBook(int id,String end_date) throws IOException, SQLException {
		
		String sqlInsert = "UPDATE work SET end_date = ? " + "WHERE id = " + id;

	
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, end_date);
		int flag= stmt.executeUpdate();
		return flag;
		
	}
	
	public static int UpdateMoney(int id, int fee) throws SQLException {
		
		String sqlInsert = "UPDATE work SET total = ? " + "WHERE id = " + id;

		
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setInt(1, fee);
		int flag= stmt.executeUpdate();
		return flag;
	}
	
	//============
	public static ResultSet getBookById(String id) throws SQLException {
		
		String sql = "SELECT  library.work.id as WorkId,user.id as UserId,user.name as UserName,book.name as BookName,start_date,end_date,status,total,limitDay "
				+" FROM library.work "
				+" INNER JOIN library.user ON work.id_user = user.id, "
				+" library.book "
				+" INNER JOIN library.author ON book.id_author = author.id "
				+" INNER JOIN library.publisher ON book.id_publisher = publisher.id "
				+" WHERE book.id= work.id_book AND user.id ='"+id+"' AND status <> 'YES' ORDER BY start_date";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	//==============
	public static ResultSet getBookByIdBook(String id_user,String id_book) throws SQLException {
		
		String sql = "SELECT  library.work.id as WorkId,user.id as UserId,user.name as UserName,book.name as BookName,start_date,end_date,status,total,limitDay "
				+" FROM library.work "
				+" INNER JOIN library.user ON work.id_user = user.id, "
				+" library.book "
				+" INNER JOIN library.author ON book.id_author = author.id "
				+" INNER JOIN library.publisher ON book.id_publisher = publisher.id "
				+" WHERE book.id= work.id_book AND user.id ='"+id_user+"' AND book.id ='" +id_book +"' AND status <> 'YES' ORDER BY start_date";
		ResultSet rs = executeQuery(sql);
		return rs;
	}
	
	//===============
	public static Boolean AddWork(Work a) throws SQLException {
		String sqlInsert = "INSERT INTO work(`id_user`, `id_book`, `start_date`, `end_date`, `status`, `total`) VALUES(?,?,?,?,?,?)";

		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, a.getUser_id());
		stmt.setString(2, a.getBook_id());
		stmt.setString(3, a.getStart_date());
		stmt.setString(4, a.getEnd_date());
		stmt.setString(5, a.getStatus());
		stmt.setInt(6, a.getTotal());
		Boolean flag = stmt.execute();
		return flag;

		
	}
	
	public static int DeleteWork(int id) throws SQLException {
		String sql = "DELETE FROM work WHERE id =" + id;
			Connection conn = MY_DB.myConnection();
			Statement stat = conn.createStatement();
			int flag = stat.executeUpdate(sql);
			return flag; // neu = 0 thi xoa k duoc

	}
	
	public static int CheckIdExist(String id_user,String id_book) throws SQLException {
		int iVal =0;
		String sql = "select * from work where id_user='" + id_user+"' AND id_book ='" + id_book + "' AND status <> 'YES'";
		ResultSet rs = executeQuery(sql);
		if (rs.next()) {
			  iVal++;
			  
			}
		return iVal;  //0 is null  
	}
	
	
}
