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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class registration extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		RequestDispatcher rd;
	//	pw.print("<h1> Register here</h1>");
		
		 String name=req.getParameter("name");
		 String DOB=req.getParameter("DOB");
		 String address=req.getParameter("address");
		 String contact=req.getParameter("contact");
		 
		 
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
		 // Statement st= con.createStatement();
		// st.execute("create table shiv (name varchar(50) not null,age number(3) not null,stream varchar(255) not null, yop number(4) not null, contact varchar(10) primary key");
	   // System.out.println("executed");
		 
		 PreparedStatement pst=con.prepareStatement("insert into ecom values(?,?,?,?)");
		 pst.setString(1,name);  
		 pst.setString(2,DOB);
		 pst.setString(3,address);
		 pst.setString(4,contact);
		 pst.execute();
		
		 pw.println("registration successful"+"<br>");
pw.print("name :" + name+"<br>");
pw.print("dob : "+ DOB+"<br>");
pw.print("address : " + address+"<br>");
pw.print("contact : "+contact+"<br>");
		 
		 }
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch(SQLException e){
			 pw.print("contact no. already exist enter valid contact");
			 
			 e.printStackTrace();
		 }
		 
		 
	}


}
