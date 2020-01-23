package project_assignment;


import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 response.setContentType("text/html");
	      PrintWriter pw = response.getWriter();
	      
	      RequestDispatcher rd;
			 RequestDispatcher rd1 ;
	      
				try {	 
			 Cookie[] arr=request.getCookies();
				Cookie ck=arr[0];
	      if((ck!=null)&&(!ck.getValue().equals(""))){
	      	
	      		Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
					PreparedStatement pst=con.prepareStatement("SELECT * FROM product");
					pst.execute();
					ResultSet rs= pst.executeQuery();
					
					pw.print("<center><table border='1'><tr><th>&nbsp;</th> <th>product name</th> <th>price per unit</th>  <th>ammount</th> <th>retailer id</th>  <th>quantity</th></center>");
					while(rs.next()){
						int id = rs.getInt("id");
						 pw.print("<form action='ORDER' method='post'>");
						pw.print("<tr><td><input type='checkbox' name='id' value='" + id + "' /></td><td>"+rs.getString("product name")+
								"</td>"+"<td>"+rs.getFloat("price per unit")+"</td>"+"<td>"
								+rs.getFloat("ammount")+"</td>"+"<td>"+rs.getString("retailer id")+"</td>"
								+"<td><input type='text' size='3' value='1' name='qty" + rs.getInt("id")  + "' /></td>"+"</tr>");
						  pw.println();
					}
			               // Ask for name, email and phone using text fields (arranged in a table)
			               pw.println("<table>");
			               pw.println("<tr><td>Enter your Name:</td>");
			               pw.println("<td><input type='text' name='cust_name' placeholder='UserName' /></td></tr>");
			               pw.println("<tr><td>Enter your Email (user@host):</td>");
			               pw.println("<td><input type='text' name='cust_email' placeholder='Email' /></td></tr>");
			               pw.println("<tr><td>Enter your Phone Number (8-digit):</td>");
			               pw.println("<td><input type='text' name='cust_phone'  placeholder='number'/></td></tr></table><br />");
			               pw.println("<input type='submit' value='ORDER' >");
			 
			               // Submit and reset buttons
			            //   pw.println("<a href='ORDER'>order</a>");
			               pw.println("<input type='reset' value='CLEAR' /></form>");
			 
			               // Hyperlink to go back to search menu
			               pw.println("<p><a href='start'>Back to Select Menu</a></p>");
			            
	      }
	      else{
	    	  pw.print("please login first");
				
				 rd1=request.getRequestDispatcher("login.html");
		    		rd1.include(request, response);   
				rd=request.getRequestDispatcher("welcome.html");
		    	
		    	 rd.include(request, response);
	             }
	      	}
				
	        catch (ClassNotFoundException e) {
	        	 pw.println("<h3>Service not available. Please try again later!</h3></body></html>");
				e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	      	catch(NullPointerException e){
	      	  pw.print("please login first");
				
				 rd1=request.getRequestDispatcher("login.html");
		    		rd1.include(request, response);   
				rd=request.getRequestDispatcher("welcome.html");
		    	
		    	 rd.include(request, response);
	      	}
	      
	 
	        	
	}
}
	        
	        
	      