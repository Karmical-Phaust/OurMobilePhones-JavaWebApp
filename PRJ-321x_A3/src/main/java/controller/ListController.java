package controller;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import context.JDBC_Access;
import dao.ListProduct;

/**
 * Servlet implementation class ListController
 */

public class ListController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Map<String, String> actionMap = new HashMap<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public ListController() {
        super();
        actionMap.put("info", "/product_info");
        actionMap.put("addToCart", "/cart.jsp");
    	actionMap.put("home", "/our_mobile_phones_homepage");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			processReq(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			processReq(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void processReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		String action = request.getParameter("action");
		
		if (action == null || !actionMap.containsKey(action)) {
			action = "home";
		}
		
		if (action.equals("info")) {
			
			String p_id = request.getParameter("productID");
			
			if (isInteger(p_id)) {
				
				int id = Integer.parseInt(p_id);
				Product p = getProduct(request, response, id);
				
				HttpSession sess = request.getSession(true);
				sess.setAttribute("product_info", p);
				
			} else {
				action = "home";
			}
			
		}
		
		if (action.equals("addToCart")) {
			
			String p_id = request.getParameter("productID");
			
			if (isInteger(p_id)) {
				
				int id = Integer.parseInt(p_id);
				Product p = getProduct(request, response, id);
				
				HttpSession sess = request.getSession(true);
				sess.setAttribute("product_info", p);
				
				
				if (sess.getAttribute("user") != null) {
					request.getRequestDispatcher("/cart_controller").forward(request, response);
					return;
				}
				
			} else {
				action = "home";
			}
			
		}
		
		request.getRequestDispatcher(actionMap.get(action)).forward(request, response);
	}
	
	private Product getProduct(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		JDBC_Access conn = new JDBC_Access();
		Connection cnt = conn.getConn();
		
		ListProduct a = new ListProduct(cnt);
		Product rs = a.getProduct(id);
		
		return rs;
		
	}
	
	private boolean isInteger(String id) {
	    try {
	        Integer.parseInt(id);
	        return true;
	    }
	    catch(Exception e) {
	        return false;
	    }
	}

}
