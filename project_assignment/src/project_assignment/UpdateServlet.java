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

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();	
		String id=request.getParameter("id");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
			PreparedStatement pst=con.prepareStatement("update curd set name=?,email=?,username=?,password=? where id=?");
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, username);
			pst.setString(4, password);
			pst.setString(5, id);
			pst.execute();
			response.sendRedirect("ViewServlet");
		} 
		
		
		catch (ClassNotFoundException | SQLException e) {
pw.print("<h3>update unsuccessful</h3>");
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}

}
