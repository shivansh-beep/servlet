package com.jsp.servlet_chainig;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/html");
	    PrintWriter pw=response.getWriter();
	     String user=request.getParameter("un");
	     String pass=request.getParameter("pw");
	     RequestDispatcher rd;
	     if(pass.equals("1234")){
	    	 pw.print(" <h1 style='color : green'>login successful</h1>");
	    	rd=request.getRequestDispatcher("index.html");
	    	 rd.forward(request, response);
	    	 
	     }
	     else{
	    	 
	    	 pw.print("<h4 style='color : red'>login failed </h4>");
	    	 
	    	rd = request.getRequestDispatcher("login.html");
	    	 rd.include(request, response);
	     }
	
	}


}
