package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
//import java.io.PrintWriter;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//logoutRequest(request, response);
		
		//response.sendRedirect("login.jsp"); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		request.getSession(true).invalidate();
		response.sendRedirect("our_mobile_phones_homepage");
		
	}
	
	/*private void logoutRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.getRequestDispatcher("admin/admin.jsp").include(request, response);
		
	}*/

}
