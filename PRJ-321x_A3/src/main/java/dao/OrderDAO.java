package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import model.*;

public class OrderDAO {
	
	private int orderId;
	private Connection cnt;
	
	public OrderDAO (Connection cnt) {
		this.cnt = cnt;
	}
	
	public void insertOrder(Orders o, Cart a) throws Exception {
		
		int status;
		String mail, pnum, dis_code, address;
		Date date; double price;
		
		mail = o.getUserEmail(); pnum = o.getPhoneNumber();
		dis_code = o.getDiscount(); address = o.getAddress();
		
		status = o.getStatus(); date = o.getOrderDate(); price = a.totalPrice();
		this.orderId = getLastId()+1;
		
		String sqlSt = "insert into orders values(?,?,?,?,?,?,?,?);";
		PreparedStatement ps = cnt.prepareStatement (sqlSt);
		
		ps.setString(1, mail);
		ps.setString(2, pnum);
		ps.setInt(3, orderId);
		ps.setInt(4, status);
		ps.setDate(5, (java.sql.Date) date);
		ps.setString(6, dis_code);
		ps.setString(7, address);
		ps.setDouble(8, price);
		
		ps.executeUpdate();
		insertAllProductOrder(a);
		ps.close();
		
	}
	
	private void insertAllProductOrder(Cart a) throws Exception {
		
		List<Product> products = a.getItems();
		
		String sqlSt = "insert into orders_detail values(?,?,?,?);";
		PreparedStatement ps = cnt.prepareStatement(sqlSt);
		
		Iterator<Product> item = products.iterator();
		while (item.hasNext()) {
			
			Product tmp = item.next();
			
			ps.setInt(1, orderId);
			ps.setInt(2, tmp.getId());
			ps.setInt(3, tmp.getNumbersOfProduct());
			ps.setDouble(4, tmp.getPrice());
			
			ps.executeUpdate();
			
		}
		ps.close();
		
	}
	
	public void showOrder(HttpSession sess) throws Exception {
		
		String sqlSt = "select * from orders_detail where order_id = ANY(select order_id from orders where user_mail = ?);";
		PreparedStatement ps = cnt.prepareStatement (sqlSt);
		
		ps.setString(1, (String)sess.getAttribute("user_email"));
		
		ResultSet rs = ps.executeQuery();
		
		int order_id, product_id, amount;
		double price;
		
		ArrayList<Product_In_An_Order> lOp = new ArrayList<Product_In_An_Order>();
		Product_In_An_Order curr;
		
		for (;rs.next();) {
			
			order_id = rs.getInt("order_id");
			product_id = rs.getInt("product_id");
			amount = rs.getInt("amount_product");
			price = rs.getDouble("price_product");
			
			curr = new Product_In_An_Order(order_id, product_id, amount, price);
			lOp.add(curr);
			
		}
		
		rs.close();
		
		if (lOp.isEmpty()) {return;}
		
		sqlSt = "select * from orders where user_mail = ?;";
		ps = cnt.prepareStatement (sqlSt);
		
		ps.setString(1, (String)sess.getAttribute("user_email"));
		
		ResultSet rs2 = ps.executeQuery();
		
		ArrayList<Product_In_An_Order> cst = new ArrayList<>();
		List<Orders> allOrder = new ArrayList<>();
		int c = 0;
		
		for (;rs2.next();) {
			
			c = rs2.getInt("order_id");
			
			for (Product_In_An_Order tmp : lOp) {
				if (tmp.getOrderId() == c) {
					cst.add(tmp);
				}
			}
			
			@SuppressWarnings("unchecked")
			ArrayList<Product_In_An_Order> crr = (ArrayList<Product_In_An_Order>)cst.clone();
			Orders o = new Orders(rs2.getInt("order_id"), rs2.getDate("order_date"), rs2.getString("user_mail"), rs2.getString("phone_number"), rs2.getString("order_address"), rs2.getInt("order_status"), rs2.getDouble("price"), crr);
			allOrder.add(o);
			cst.clear();
			
		}
		
		sess.setAttribute("user_orders", allOrder);
		
	}
	
	private int getLastId() throws Exception {
		
		String sqlSt = "select max(order_id) as lastId from orders;";
		PreparedStatement ps = cnt.prepareStatement (sqlSt);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		int id = 0;
		
		if (rs != null) {
			id = rs.getInt("lastId");
		}
		
		rs.close();
		
		return id;
		
	}
	
}
