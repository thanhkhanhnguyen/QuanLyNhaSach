package Model;

import java.sql.SQLException;

import DAO.MY_DB;
import GUI.LoginForm;

public class Program {

	public static void main(String[] args) throws SQLException {
		MY_DB.myConnection();
		LoginForm window = new LoginForm ();
		window.setVisible(true);
	}

}
