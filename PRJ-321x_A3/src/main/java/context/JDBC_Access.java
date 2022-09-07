package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.Class;


public class JDBC_Access {
	
	//change these parameters for your database:
	private String DBusername = "Phaust";
	private String DBpassword = "The Next SQL 2004";
	private String DBname = "shoppingdb";
	
	public Connection getConn() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/" + DBname;
		
		Connection conn = DriverManager.getConnection(url, DBusername, DBpassword);
		
		return conn;
		
	}
	
}
