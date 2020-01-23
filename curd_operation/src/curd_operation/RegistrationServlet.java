package curd_operation;

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


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		
		
				////jdbc////
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
			PreparedStatement pst=con.prepareStatement("insert into curd(name,email,username,password) values(?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, username);
			pst.setString(4, password);
			pst.execute();
			pw.print("registration successfull");
		} 
		
		
		catch (ClassNotFoundException | SQLException e) {
pw.print("<h3>registraion unsuccessful</h3>");
			e.printStackTrace();
		}
				
	}

}
