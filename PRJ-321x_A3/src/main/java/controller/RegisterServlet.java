package controller;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Accounts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import context.*;

/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public RegisterServlet() {
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
			addUserRequest(request, response, conn.getConn());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void addUserRequest(HttpServletRequest request, HttpServletResponse response, Connection cnt) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		
		try {
			
			//In: form
			String eidIn, pwdIn, name, address, phone; int role = 0;
			//form info:
			eidIn = request.getParameter("user_email");
			pwdIn = request.getParameter("password");
			name = request.getParameter("username");
			address = request.getParameter("address");
			phone = request.getParameter("phone_num");
			
			
			//check info:
			
			if (cnt != null) {

				// database check:
				Accounts_checker acc = new Accounts_checker(cnt);
				Accounts newAcc = new Accounts(eidIn, pwdIn, role, name, address, phone);

				// check info:
				if (!acc.mailCheck(eidIn)) {

					acc.addAcc(newAcc);
					
					request.getSession(true).invalidate();
					HttpSession sess = request.getSession(true);
					sess.setAttribute("successAlert", "Signed up successfully! Please sign in to check");
					
					response.sendRedirect("login_page");

				} else {

					request.getSession(true).invalidate();
					HttpSession sess = request.getSession(true);
					sess.setAttribute("error", "The email is already being used!");

					response.sendRedirect("register_page");
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
