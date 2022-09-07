package productsController;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Orders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;

import context.JDBC_Access;
import dao.OrderDAO;

/**
 * Servlet implementation class PayController
 */

public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public PayController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
			processReq(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void processReq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();
		
		HttpSession sess = request.getSession(true);
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		
		Cart c = (Cart)sess.getAttribute("cart");
		if (c == null) {
			response.sendRedirect("our_mobile_phones_homepage");
			return;
		}
		String mail = (String)sess.getAttribute("user_email");
		String phonenum = (String)sess.getAttribute("phone_number");
		String discount = request.getParameter("coupon");
		String address = (String)sess.getAttribute("address");
		
		Orders o = new Orders(mail, phonenum, address, 2, date, discount);
		
		JDBC_Access conn = new JDBC_Access();
		Connection cnt = conn.getConn();
		
		OrderDAO odao = new OrderDAO(cnt);
		
		odao.insertOrder(o, c);
		//odao.showOrder(sess);
		
		out.print("good here 2");
		
		cnt.close();
		sess.removeAttribute("cart");
		request.getRequestDispatcher("/user_orders").forward(request, response);
		
	}

}
