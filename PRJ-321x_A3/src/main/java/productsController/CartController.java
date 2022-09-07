package productsController;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import context.*;
import dao.ListProduct;

/**
 * Servlet implementation class AddToCartController
 */

public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> actionMap = new HashMap<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public CartController() {
        super();
        actionMap.put("addToCart", "/cart.jsp");
        actionMap.put("remove", "/cart.jsp");
        actionMap.put("removeAll", "/cart.jsp");
        actionMap.put("add", "/cart.jsp");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		
		try {
			addToCart(request, response, "addToCart");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(actionMap.get(action)).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String job = request.getParameter("job");
		
		if (job.equals("add")) {
			try {
				addToCart(request, response, "add");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			deleteFromCart(request, response, job);
		}
		
		request.getRequestDispatcher(actionMap.get(job)).forward(request, response);
		//response.sendRedirect(actionMap.get(job));
		
	}
	
	private void addToCart(HttpServletRequest request, HttpServletResponse response, String job) throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		HttpSession sess = request.getSession(true);
		
		if (sess.getAttribute("cart") == null) {
			sess.setAttribute("cart", new Cart());
		}
		
		Product p = getProductInfo(request, response, sess, job);
		Cart c = (Cart)sess.getAttribute("cart");
		
		c.addToCart(p);
		
	}
	
	private void deleteFromCart(HttpServletRequest request, HttpServletResponse response, String job) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(true);
		
		if (sess.getAttribute("cart") != null) {
			
			Cart c = (Cart)sess.getAttribute("cart");
		
			if (job.equals("remove")) {
		
				int id = Integer.parseInt((String)request.getParameter("p_id"));
				c.removeFromCart(id);
				
			}
			
			if (job.equals("removeAll")) {
				c.removeAllFromCart();
			}
			
		}
		
	}
	
	private Product getProductInfo(HttpServletRequest request, HttpServletResponse response, HttpSession sess, String job) throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		Product p;
		String productName, des, src, type, brand;
		int id; double price;
		
		if (job.equals("addToCart")) {
			
			p = (Product)sess.getAttribute("product_info");
			
			id = p.getId();
			productName = p.getName();
			des = p.getDescription();
			price = p.getPrice();
			src = p.getImg_src();
			type = p.getType();
			brand = p.getBrand();
			
		} else {
			
			p = getProductFromId(request, response);
			
			id = p.getId();
			productName = p.getName();
			des = p.getDescription();
			price = p.getPrice();
			src = p.getImg_src();
			type = p.getType();
			brand = p.getBrand();
			
		}
		
		Product toAdd = new Product(id, productName, des, price, src, type, brand, 1);
		
		return toAdd;
		
	}
	
	private Product getProductFromId(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
		
		int id = Integer.parseInt((String)request.getParameter("p_id"));
		
		JDBC_Access conn = new JDBC_Access();
		Connection cnt = conn.getConn();
		
		ListProduct a = new ListProduct(cnt);
		Product rs = a.getProduct(id);
		
		cnt.close();
		
		return rs;
		
	}

}
