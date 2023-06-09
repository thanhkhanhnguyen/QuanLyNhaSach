package DAO;

import java.io.IOException;
import java.sql.*;

import Model.Book;


public class BookDAO {
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
	
	public static ResultSet getAllBook() throws SQLException {
		
		String sql = "SELECT book.id,book.name as BookName,author.name as AuthorName,publisher.name as PublisherName,number,limitDay,price FROM library.book INNER JOIN library.author ON book.id_author = author.id INNER JOIN library.publisher ON book.id_publisher = publisher.id";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static ResultSet getBookById(String id) throws SQLException {
		
		String sql = "SELECT book.id,book.name as BookName,author.name as AuthorName,publisher.name as PublisherName,number,limitDay,price FROM library.book INNER JOIN library.author ON book.id_author = author.id INNER JOIN library.publisher ON book.id_publisher = publisher.id WHERE book.id='"+id+"'";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static ResultSet getNameAuthor() {
		String sql = "SELECT name FROM author";
		ResultSet rs = executeQuery(sql);
		return rs;
	}
	
	public static ResultSet getNamePublisher() {
		String sql = "SELECT name FROM publisher";
		ResultSet rs = executeQuery(sql);
		return rs;
	}
	
	public static ResultSet getNameBook() {
		String sql = "SELECT name FROM book";
		ResultSet rs = executeQuery(sql);
		return rs;
	}
	
	public static String getNameBookById(String id) {
		String sql = "SELECT * FROM book WHERE id ='"+id + "'";
		ResultSet rs = executeQuery(sql);
		String name = null;
		try {
			while(rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	public static ResultSet getBookByName(String name) {
		String sql = "SELECT book.id,book.name as BookName,author.name as AuthorName,publisher.name as PublisherName,number,limitDay,price FROM library.book INNER JOIN library.author ON book.id_author = author.id INNER JOIN library.publisher ON book.id_publisher = publisher.id WHERE book.name = '" + name + "'";
		ResultSet rs = executeQuery(sql);
		return rs;
	}
	
	public static String getIdByName(String name) {
		String sql = "SELECT book.id,book.name as BookName,author.name as AuthorName,publisher.name as PublisherName,number,limitDay,price FROM library.book INNER JOIN library.author ON book.id_author = author.id INNER JOIN library.publisher ON book.id_publisher = publisher.id WHERE book.name = '" + name + "'";
		ResultSet rs = executeQuery(sql);
		String id = null;
		try {
			while(rs.next()) {
				id =rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	
	public static int CheckIdExist(String id) throws SQLException {
		int iVal =0;
		String sql = "select * from book where id='" + id+"'";
		ResultSet rs = executeQuery(sql);
		if (rs.next()) {
			  iVal++;
			  
			}
		return iVal;  //0 is null  
	}
	
	public static Boolean AddBook(Book a) throws SQLException {
		String sqlInsert = "INSERT INTO book VALUES(?,?,?,?,?,?,?)";

		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, a.getId());
		stmt.setString(2, a.getName());
		stmt.setInt(3, a.getAuthorId());
		stmt.setInt(4, a.getPublisherId());
		stmt.setInt(5, a.getNumber());
		stmt.setInt(6, a.getLimitDay());
		stmt.setInt(7, a.getPrice());
		Boolean flag = stmt.execute();
		return flag;

		
	}
	
	public static int DeleteBook(String id) throws SQLException {
		String sql = "DELETE FROM book WHERE id ='" + id +"'";
			Connection conn = MY_DB.myConnection();
			Statement stat = conn.createStatement();
			int flag = stat.executeUpdate(sql);
			return flag; // neu = 0 thi xoa k duoc

	}
	
	public static int UpdateBook(Book us) throws IOException, SQLException {
		String id =us.getId();
		String sqlInsert = "UPDATE book SET name= ?,id_author=?,id_publisher=?,number=?,limitDay=?,price=? " + "WHERE id = '" + id+"'";

	
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		stmt.setString(1, us.getName());
		stmt.setInt(2, us.getAuthorId());
		stmt.setInt(3, us.getPublisherId());
		stmt.setInt(4, us.getNumber());
		stmt.setInt(5, us.getLimitDay());
		stmt.setInt(6, us.getPrice());
		int flag= stmt.executeUpdate();
		return flag;
		
	}
	
	public static int UpdateNumberBook(String id_book) throws IOException, SQLException {
		String sqlInsert = "UPDATE book SET number = number - 1 " + "WHERE id = '" + id_book+"'";

	
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		int flag= stmt.executeUpdate();
		return flag;
		
	}
	
	public static int UpdateNumberBookToReturn(String id_book) throws IOException, SQLException {
		String sqlInsert = "UPDATE book SET number = number + 1 " + "WHERE id = '" + id_book+"'";

	
		Connection conn = MY_DB.myConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
		int flag= stmt.executeUpdate();
		return flag;
		
	}
	
	public static ResultSet getSearchInformation(String text) throws SQLException {
		
		String sql = "SELECT book.id,book.name as BookName,author.name as AuthorName,publisher.name as PublisherName,number,limitDay,price FROM library.book INNER JOIN library.author ON book.id_author = author.id INNER JOIN library.publisher ON book.id_publisher = publisher.id where CONCAT(book.id,book.name,author.name,publisher.name) LIKE'%" + text + "%'";
		ResultSet rs = executeQuery(sql);
		return rs;
		
	}
	
	public static String getIdtoNameBook(String name) {
		//String sql = "select * from book where CONCAT(name) LIKE'%" + name + "%'";
		String sql = "select * from book where name='" + name+"'";
		ResultSet rs = executeQuery(sql);
		String id = null;
		try {
			while (rs.next()) {
				id=rs.getString("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}
	
	public static int getLimitDayToId(String id) {
		//String sql = "select * from book where CONCAT(name) LIKE'%" + name + "%'";
		String sql = "select * from book where id='" + id+"'";
		ResultSet rs = executeQuery(sql);
		int limit=0;
		try {
			while (rs.next()) {
				limit=rs.getInt("limitDay");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return limit;
		
	}
	
	public static int getFeeToId(String id) {
		
		String sql = "select * from book where id='" + id+"'";
		ResultSet rs = executeQuery(sql);
		int fee=0;
		try {
			while (rs.next()) {
				fee=rs.getInt("price");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fee;
		
	}
	
	public static boolean checkNumberBook(String id_book) {
		int num = -1;
		try {
			ResultSet rs = BookDAO.getBookById(id_book);
			while(rs.next()) {
				num = rs.getInt("number");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(num > 0) {
			return true; //thoa man
		}
		
		return false;
		
	}
	
	
	
	
}
