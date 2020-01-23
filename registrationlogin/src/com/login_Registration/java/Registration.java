 package com.login_Registration.java;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java .sql.Connection;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class Registration implements Servlet{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>Response From Registration</h1>");
		
		 String name=req.getParameter("name");
		 String age=req.getParameter("age");
		 String stream=req.getParameter("stream");
		 String yop=req.getParameter("yop");
		 String contact=req.getParameter("contact");
		 String username=req.getParameter("username");
		 String password=req.getParameter("password");
		int iage=Integer.parseInt(age);
		int iyop=Integer.parseInt(yop);
				 
		 
		pw.print("<br>name :"+" " + name);
		 pw.print("<br>age : " +" "+ age);
		 pw.print("<br>stream : "+" " + stream);
		 pw.print("<br>yop : "+" " + yop);
		 pw.print("<br>contact : "+" " + contact);
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
		// Statement st= con.createStatement();
		 // st.execute("create table shiv (name varchar(50) not null,age number(3) not null,stream varchar(255) not null, yop number(4) not null, contact varchar(10) primary key");
		 //System.out.println("executed");
		 
		 PreparedStatement pst=con.prepareStatement("insert into shiv values(?,?,?,?,?,?,?)");
		 pst.setString(1,name);  
		 pst.setInt(2,iage);
		 pst.setString(3,stream);
		 pst.setInt(4, iyop);
		 pst.setString(5,contact);
		 pst.setString(6, username);
		 pst.setString(7, password);
		 pst.execute();
		 System.out.println("enterd");
		 
		 }
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch(SQLException e){
			 e.printStackTrace();
		 }
		 
		 
		 
		 
	}

}
