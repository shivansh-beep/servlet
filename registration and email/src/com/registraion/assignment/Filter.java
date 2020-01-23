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

@WebServlet("/Filter")
public class Filter extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.print("from filter servlet");
		String year=request.getParameter("year");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
			PreparedStatement pst=con.prepareStatement("select * from gmail where yop=?");
			pst.setString(1, year);
			ResultSet rs=pst.executeQuery();
			pw.print("<center><table border='1'><tr><th>Name</th> <th>Email</th> <th>yop</th> <th>gender</th> <th>compose mail</th></center>");
			while(rs.next()){
				pw.print("<tr><td>"+rs.getString("name")+"</td><td>"+rs.getString("email")+"</td>"+"<td>"+rs.getString("yop")+"</td>"+"<td>"+rs.getString("gender")+"</td>"+"</td><td><a href='GmailServlet?id="+rs.getInt("id")+"'>send mail</a>");
				
			}
		} 
		
		
		catch (ClassNotFoundException | SQLException e) {
pw.print("<h3> unsuccessful</h3>");
			e.printStackTrace();
		}
	}
			}


