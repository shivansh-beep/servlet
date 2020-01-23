package project_assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;


@WebServlet("/ORDER")
public class ORDER extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 response.setContentType("text/html");
	      PrintWriter pw = response.getWriter();
	 
	      Connection con = null;
	      ResultSet  rset = null;
	 
	      try {
	         pw.println("<html><head><title>Order Confirmation</title></head><body>");
	         pw.println("<h2>YAEBS - Order Confirmation</h2>");
	 
	         // Retrieve and process request parameters: id(s), cust_name, cust_email, cust_phone
	        String[] ids = request.getParameterValues("id");  // Possibly more than one values
	         String custName = request.getParameter("cust_name");
	      boolean hasCustName = custName != null && ((custName = custName.trim()).length() > 0);
	         String custEmail = request.getParameter("cust_email");
	         boolean hasCustEmail = custEmail != null && ((custEmail = custEmail).length() > 0);
	         String custPhone = request.getParameter("cust_phone");
	        boolean hasCustPhone = custPhone != null && ((custPhone = custPhone).length() > 0);
	       
	         if (ids == null || ids.length == 0) {
	            pw.println("<h3>Please Select a product first!</h3>");
				response.sendRedirect("QueryServlet");
	         } else if (!hasCustName) {
	            pw.println("<h3>Please Enter Your Name!</h3>");
	         } else if (!hasCustEmail || (custEmail.indexOf('@') == -1)) {
	            pw.println("<h3>Please Enter Your e-mail (user@host)!</h3>");
	         } else if (!hasCustPhone || (custPhone.length() != 8)) {
	            pw.println("<h3>Please Enter an 8-digit Phone Number!</h3>");
	         } else {  
	            // Display the name, email and phone (arranged in a table)
	            pw.println("<table border='1'>");
	            pw.println("<tr><td>Customer Name:</td><td>" + custName + "</td></tr>");
	            pw.println("<tr><td>Customer Email:</td><td>" + custEmail + "</td></tr>");
	            pw.println("<tr><td>Customer Phone Number:</td><td>" + custPhone + "</td></tr></table>");
	 
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
	 
	            // Print the product ordered in a table
	           pw.println("<br />");
	            pw.println("<table border='1'>");
	            pw.println("<tr><th>product name</th><th>price per unit</th><th>quantity</th></tr>");
	 
	            float totalPrice = 0f;
	            for (String id : ids) {
	            	  int qtyOrdered = Integer.parseInt(request.getParameter("qty" + id));
	               PreparedStatement pst=con.prepareStatement("SELECT * FROM product WHERE id = " + id);
	               pst.execute();
	               rset = pst.executeQuery();
	               System.out.println(qtyOrdered);
	 
	               // Expect only one row in ResultSet
	               rset.next();
	               int qtyAvailable = rset.getInt("quantity");
	               String productname = rset.getString("product name");
	               float price = rset.getFloat("price per unit");
	 
	            
	               
	               
	        PreparedStatement pro = con.prepareStatement(" UPDATE product SET quantity = quantity -? WHERE id=?");
	        pro.setInt(1, qtyOrdered);
	        pro.setString(2, id);
	        pro.execute();
	         
	              System.out.println("query1");
	         PreparedStatement pst2 = con.prepareStatement ("INSERT INTO records values (?,?,?,?,?)");
	         pst2.setString(1, id);
	         pst2.setInt(2, qtyOrdered);
	         pst2.setString(3,custName ); 
	         pst2.setString(4,custEmail ); 
	         pst2.setString(5,custPhone ); 
	         pst2.execute();
	   System.out.println("query2");
	   
	               pw.println("<tr>");
	               pw.println("<td>" + productname + "</td>");
	               pw.println("<td>" + price + "</td>");
	               pw.println("<td>" + qtyOrdered + "</td></tr>");
	               totalPrice += price * qtyOrdered;
	            }
	 
	            pw.println("<tr><td colspan='4' align='right'>Total Price: ");
	            pw.printf("%.2f</td></tr>", totalPrice);
	            pw.println("</table>");
	 
	            pw.println("<h3>Thank you.</h3>");
	            pw.println("<p><a href='start'>Back to Select Menu</a></p>");
	         
	         pw.println("</body></html>");
	      }
	      }
	      catch (SQLException ex) {
	         pw.println("<h3>Service not available. Please try again later!</h3></body></html>");
	      } 
	      }
}
	   
		
	

