package com.registraion.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class registration extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
	//	pw.print("<h1> Registration successful</h1>");
		
		 String name=req.getParameter("name");
		 String gender=req.getParameter("gr");
		 String email=req.getParameter("email");
		 String yop=req.getParameter("yop");
		 String pwd=req.getParameter("pw");
		 
		 
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
		 // Statement st= con.createStatement();
		// st.execute("create table shiv (name varchar(50) not null,age number(3) not null,stream varchar(255) not null, yop number(4) not null, contact varchar(10) primary key");
	   // System.out.println("executed");
		 
		 PreparedStatement pst=con.prepareStatement("insert into gmail(name,gender,email,yop,pw) values(?,?,?,?.?)");
		 pst.setString(1,name);  
		 pst.setString(2,gender);
		 pst.setString(3,email);
		 pst.setString(4,yop);
		 pst.setString(5,pwd);
		 pst.execute();
		 	 
		 }
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch(SQLException e){
			 pw.print("email already exist enter valid contact");
			 
			 e.printStackTrace();
		 }
		 
		 
	}


}
