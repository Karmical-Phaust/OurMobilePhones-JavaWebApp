package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class ListProduct {
	
	private Connection cnt;
	
	public ListProduct (Connection cnt) {
		this.cnt = cnt;
	}
	
	public List<Product> search(String name) throws SQLException {
		
		name = name.toLowerCase().replace(" ", "");
		
		String sqlSt = "select * from products where product_name like ?;";
		PreparedStatement ps = cnt.prepareStatement (sqlSt);
		
		ps.setString(1, "%" + name + "%");
		
		ResultSet rs = ps.executeQuery();
		
		String productName, des, src, type, brand;
		int id; double price;
		
		List<Product> ls = new ArrayList<Product>();
		
			for (;rs.next();) {
				
				id = rs.getInt("product_id");
				productName = rs.getNString("product_name");
				des = rs.getNString("product_des");
				price = rs.getDouble("product_price");
				src = rs.getString("product_img_source");
				type = rs.getString("product_type");
				brand = rs.getString("product_brand");
				
				ls.add(new Product(id, productName, des, price, src, type, brand));
				
			}
			
			rs.close();
		
		return ls;
		
	}
	
	public Product getProduct(int product_id) throws SQLException {
		
		String sqlSt = "select * from products where product_id = ?;";
		PreparedStatement ps = cnt.prepareStatement (sqlSt);
		
		ps.setInt(1, product_id);
		
		ResultSet rs = ps.executeQuery();
		String productName, des, src, type, brand;
		int id; double price;
		
		rs.next();
		
		id = rs.getInt("product_id");
		productName = rs.getNString("product_name");
		des = rs.getNString("product_des");
		price = rs.getDouble("product_price");
		src = rs.getString("product_img_source");
		type = rs.getString("product_type");
		brand = rs.getString("product_brand");
		
		Product a = new Product(id, productName, des, price, src, type, brand);
		
		rs.close();
		
		return a;
		
	}
	
}
