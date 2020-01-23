package project_assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.print("from view servlet");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
			PreparedStatement pst=con.prepareStatement("select * from curd");
			ResultSet rs=pst.executeQuery();
			pw.print("<center><table border='1'><tr><th>ID</th> <th>Name</th> <th>Email</th> <th>Username</th> <th>Password</th> <th>Edit</th> <th>delete</th></center>");
			while(rs.next()){
				pw.print("<tr><td>"+rs.getInt("id")+"</td><td>"+rs.getString("name")+"</td>"+"<td>"+rs.getString("email")+"</td>"+"<td>"+rs.getString("username")+"</td>"+"<td>"+rs.getString("password")+"</td><td><a href='EditServlet?id="+rs.getInt("id")+"'>edit</a>"+"</td><td><a href='DeleteServlet?id="+rs.getInt("id")+"'>delete</a>"+"</tr>");
				
			}
		} 
		
		
		catch (ClassNotFoundException | SQLException e) {
pw.print("<h3> unsuccessful</h3>");
			e.printStackTrace();
		}
	}


}
