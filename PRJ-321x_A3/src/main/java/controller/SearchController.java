package controller;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.*;
import model.*;
import context.JDBC_Access;
import java.util.*;

/**
 * Servlet implementation class SearchController
 */

public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		JDBC_Access conn = new JDBC_Access();
		
		try {
			search(request, response, conn.getConn());
			//doSmt(request, response, conn.getConn());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response, Connection cnt) throws ServletException, IOException, SQLException {
		
		ListProduct a = new ListProduct(cnt);
		
		String name = request.getParameter("name");
		
		HttpSession sess = request.getSession(true);
		if (sess.getAttribute("nullMess") != null) {
			sess.setAttribute("nullMess", null);
		}
		
		if (name == null || name == "") {
			
			sess.setAttribute("nullMess", "Không có tên cần tìm");
			
			response.sendRedirect("search_result");
			
		} else {
			
			List<Product> ls = a.search(request.getParameter("name"));
			
			sess.setAttribute("productResult", ls);
			
			response.sendRedirect("search_result");
			
		}
		
	}

}
