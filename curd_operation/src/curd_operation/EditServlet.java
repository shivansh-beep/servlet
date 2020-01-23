package curd_operation;

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

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String sid= request.getParameter("id");
		int id=Integer.parseInt(sid);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
			PreparedStatement pst=con.prepareStatement("select * from curd where id=? ");
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				pw.print("<h1 align='center'>update</h1>");
				pw.print("<form action='UpdateServlet' method='post'>"+
"<center>"+
"<h4 align='center'>"+
"<input type='hidden' name='id' value='"+rs.getInt("id")+"'>"+"<br>"+
 "<input type='text' name='username' value='"+rs.getString("username")+"'>"+"<br>"+
"<br><br>"+
" <input type='password' name='password' value='"+rs.getString("password")+"'>"+"<br>"+
"<br><br>"+
 "<input type='text' name='name' value='"+rs.getString("name")+"'>"+"<br>"+
"<br><br>"+
 "<input type='text' name='email' value='"+rs.getString("email")+"'>"+"<br>"+
"<br><br>"+
"<input type='submit' value='update'/>"+
"</center>"+
"</form>"+"</h4");
				
			}
		} 
		
		catch (ClassNotFoundException | SQLException e) {
pw.print("<h3>login unsuccessful</h3>");
			e.printStackTrace();
		}
	}

}
