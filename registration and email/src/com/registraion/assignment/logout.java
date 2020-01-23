package com.registraion.assignment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class logout extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.print("<br>");
		HttpSession session=request.getSession(false);
		if(session!=null){
			pw.print("Logout Success as : "+session.getAttribute("name"));
			session.invalidate();
		}
		else{
			pw.print("<h3>login first </h3> <br>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
	
	}

	}

