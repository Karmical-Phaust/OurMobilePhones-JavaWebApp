package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Accounts;

//import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import context.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		JDBC_Access conn = new JDBC_Access();
		
		try {
			processRequest(request, response, conn.getConn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response, Connection cnt) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		
		try {
			
			//In: form
			String eidIn, pwdIn;
			//form info:
			eidIn = request.getParameter("user_email");
			pwdIn = request.getParameter("password");
			
			//check info:
			
			if (cnt != null) {

				// database submission:
				Accounts_checker acc = new Accounts_checker(cnt);

				// check info:
				if (acc.login(eidIn, pwdIn)) {
					
					Accounts a = acc.getAcc(eidIn);

					request.getSession(true).invalidate();
					HttpSession sess = request.getSession(true);
					sess.setAttribute("user", a.getUser_name());
					sess.setAttribute("user_email", a.getUser_mail());
					sess.setAttribute("phone_number", a.getUser_phone());
					sess.setAttribute("address", a.getUser_address());
					
					if (a.getAccount_role() == 1) {
						sess.setAttribute("role", 1);
						response.sendRedirect("admin_page");
					} else {
						sess.setAttribute("role", 0);
						response.sendRedirect("our_mobile_phones_homepage");
					}
					
				} else {

					request.getSession(true).invalidate();
					HttpSession sess = request.getSession(true);
					sess.setAttribute("error", "There's no such email or password!");

					response.sendRedirect("login_page");
				}

			} else {
				
				request.getSession(true).invalidate();
				HttpSession sess = request.getSession(true);
				sess.setAttribute("error", "Connection to database error!");

				response.sendRedirect("login_page");
				
			}
			
		} catch (Exception ex) {
			out.println(ex);
		}
		
	}

}
