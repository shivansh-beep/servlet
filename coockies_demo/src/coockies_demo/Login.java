package coockies_demo;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	    response.setContentType("text/html");
	    PrintWriter pw=response.getWriter();
	     String user=request.getParameter("un");
	     String pass=request.getParameter("pw");
	     RequestDispatcher rd;
	     

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
				PreparedStatement pst=con.prepareStatement("select * from ecom where name=? and contact=?");

				pst.setString(1,user);
				pst.setString(2,pass);
			
				ResultSet rs=pst.executeQuery();
				if(rs.next())
				{
	    	 Cookie ck=new Cookie("k1",user);
	    	 response.addCookie(ck);
	    	 
	    	 pw.print("<h4 style='color : green'>login successful : "+ck.getValue()+ "</h4>"+"<br>");
	 		pw.print("<br>"+"name : "+rs.getString(1)+ "<br>"+"dob : "+rs.getString(2)+ "<br>"+"address : "
	    	 +rs.getString(3)+ "<br>"+"contact:"+rs.getString(4)+ "<br>");
	    	 
	    	rd=request.getRequestDispatcher("index.html");
	    	
	    	 rd.include(request, response);
	    	
	    	 
	     }
				
	     else{
	    	
	    	 pw.print("<h4 style='color : red'>login failed </h4>");
	    	rd = request.getRequestDispatcher("login.html");
	    	 rd.include(request, response);
	     }
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}


}
