package com.registraion.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html");

			ServletContext context = getServletContext();
			String name=req.getParameter("name");
			String yop= req.getParameter("yop");
			
					
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>log in successful</h1>");

			
			try {
		 		Class.forName("com.mysql.jdbc.Driver");
			 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
			
			PreparedStatement pst=con.prepareStatement("select * from gmail where name=? and yop =?");
			pst.setString(1, name);
			pst.setString(2, yop);
			pst.execute();
			
			ResultSet rs=pst.executeQuery();
			boolean b=rs.next();
			
			if(b){
				pw.print("<br>"+"name"+rs.getString(1)+ "<br>"+"age"+rs.getString(2)+"<br>"+"email"+rs.getString(3)+"<br>"+"yopt"+rs.getString(4));
			    
				context.setAttribute("name", rs.getString(1));
				context.setAttribute("age", rs.getString(2));
				context.setAttribute("email", rs.getString(3));
				context.setAttribute("yop", rs.getString(4));
				
				HttpSession session=req.getSession();
				session.setAttribute("name",name);
				
					pw.print("<br><br>");
				 pw.print("<a href='Filter.html'>click here to search</a> |  <a href='logout'>logout</a>");
			}
			else{
				pw.print("<br>please try again<br>"+"<br>go for register<br>"+"<a href=registration.html>register</a>");
			}
			}
			
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				pw.print("");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}

}
