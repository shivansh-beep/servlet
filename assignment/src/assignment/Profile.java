package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/profile")
public class Profile extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		
		
		ServletContext context=getServletContext();
		String name= (String)context.getAttribute("name");
		String dob= (String)context.getAttribute("DOB");
		String address= (String)context.getAttribute("address");
		String contact= (String)context.getAttribute("contact");
		if(name==null){
			pw.print("try again or  log in :"+"<a href='login.html'>login</a>");
		}
		else{
			pw.print("<br>name : "+ name);
			pw.print("<br>name : "+ dob);
			pw.print("<br>name : "+ address);
			pw.print("<br>name : "+ contact);
			
			
			
			pw.print("<a href=logout>log out</a>");
		}
		 
		
	
	  
	}

	}

