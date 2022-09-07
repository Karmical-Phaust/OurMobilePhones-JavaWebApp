package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Accounts;

public class Accounts_checker {
	
	private Connection cnt;
	
	public Accounts_checker(Connection cnt) {
		this.cnt = cnt;
	}
	
	public Accounts getAcc(String mail) throws SQLException {
		
		String sqlSt = "select * from accounts where user_mail=?;";
		PreparedStatement ps = cnt.prepareStatement(sqlSt);
		
		ps.setString(1, mail);
		
		ResultSet rs = ps.executeQuery();
		
		String user_mail, password, user_name, user_address, user_phone;
		int role;
		
		rs.next();
		user_mail = rs.getString("user_mail");
		password = rs.getString("password");
		role = rs.getInt("account_role");
		user_name = rs.getNString("user_name");
		user_address = rs.getNString("user_address");
		user_phone = rs.getString("user_phone");
		
		rs.close();
		
		Accounts a = new Accounts(user_mail, password, role, user_name, user_address, user_phone);
		
		return a;
	}
	
	public void addAcc(Accounts a) throws SQLException {
		
		String sqlSt = "insert into accounts values(?,?,?,?,?,?);";
		PreparedStatement ps = cnt.prepareStatement(sqlSt);
		
		ps.setString(1, a.getUser_mail());
		ps.setString(2, a.getPassword());
		ps.setInt(3, a.getAccount_role());
		ps.setString(4, a.getUser_name());
		ps.setString(5, a.getUser_address());
		ps.setString(6, a.getUser_phone());
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	public boolean mailCheck(String mail) throws SQLException {
		
		String sqlSt = "select count(*) as count from accounts where user_mail=?;";
		PreparedStatement ps = cnt.prepareStatement(sqlSt);
		
		ps.setString(1, mail);
		
		ResultSet rs = ps.executeQuery();
		int res = 0;
		
		if (rs.next()) {
			res = rs.getInt("count");
		}
		
		rs.close();
		
		if (res == 1) {
			
			return true;
			
		} else {
			
			return false;
			
		}

	}
	
	public boolean login(String email, String pass) throws SQLException {
		
		String sqlSt = "select count(*) as count from accounts where user_mail=? and password=?;";
		PreparedStatement ps = cnt.prepareStatement(sqlSt);
		
		ps.setString(1, email);
		ps.setString(2, pass);
		
		ResultSet rs = ps.executeQuery();
		int res = 0;
		
		if (rs.next()) {
			res = rs.getInt("count");
		}
		
		rs.close();
		
		if (res == 1) {
			
			return true;
			
		} else {
			
			return false;
			
		}

	}
	
}
