package com.login_Registration.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServlet implements  Servlet{

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
		resp.setContentType("text/html");
		String user=req.getParameter("username");
		String pass= req.getParameter("password");
		String myuser="admin";
		String mypass="1234";
				
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>Response From Login</h1>");
//		
//		if(user.equals(myuser)&& pass.equals(mypass)){
//pw.print("login successful");
//
//		}
//		else{
//			pw.print("login failed"); 
		//}
		
		try {
	 		Class.forName("com.mysql.jdbc.Driver");
		 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
		
		PreparedStatement pst=con.prepareStatement("select * from shiv where username=? and password =? ");
		pst.setString(1, user);
		pst.setString(2, pass);
		pst.execute();
		
		ResultSet rs=pst.executeQuery();
		boolean b=rs.next();
		
		if(b){
			pw.print("<br>"+"name"+rs.getString(1)+ "<br>"+"age"+rs.getInt(2)+"<br>"+"stream"+rs.getString(3)+"<br>"+"yop"+rs.getInt(4)+"<br>"+"contact"+rs.getString(5));
		}
		else{
			pw.print("invadid entries");
		}
		}
		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
