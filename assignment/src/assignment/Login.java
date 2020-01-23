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
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html");

			ServletContext context = getServletContext();
			String name=req.getParameter("name");
			String contact= req.getParameter("contact");
			String myuser=name;
			String mypass=contact;
					
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>log in successful</h1>");

			
			try {
		 		Class.forName("com.mysql.jdbc.Driver");
			 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
			
			PreparedStatement pst=con.prepareStatement("select * from ecom where name=? and contact =? ");
			pst.setString(1, name);
			pst.setString(2, contact);
			pst.execute();
			
			ResultSet rs=pst.executeQuery();
			boolean b=rs.next();
			
			if(b){
				pw.print("<br>"+"name"+rs.getString(1)+ "<br>"+"DOB"+rs.getString(2)+"<br>"+"address"+rs.getString(3)+"<br>"+"contact"+rs.getString(4));
				
				context.setAttribute("name", rs.getString(1));
				context.setAttribute("dob", rs.getString(2));
				context.setAttribute("address", rs.getString(3));
				context.setAttribute("contact", rs.getString(4));
				
					pw.print("<br><br>");
				 pw.print("<a href='logout'>log out</a>");
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
