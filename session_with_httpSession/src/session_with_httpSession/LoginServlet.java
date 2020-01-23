package session_with_httpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			String user=request.getParameter("un");
			String pass=request.getParameter("pw");
			if(pass.equals("1234")){
				HttpSession session=request.getSession();
				session.setAttribute("user", user);
				pw.print("Login is Success");
				request.getRequestDispatcher("link.html").include(request, response);
			}else{
				pw.print("Login is failed... try agian...");
				request.getRequestDispatcher("login.html").include(request, response);
			}
		
		
		}

	}

	
	
