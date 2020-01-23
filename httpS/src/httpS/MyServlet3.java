package httpS;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class MyServlet3 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String user=req.getParameter("username");
		String pass= req.getParameter("password");
		String myuser="admin";
		String mypass="1234";
				
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>Response From Login</h1>");
	
	if(user.equals(myuser)&& pass.equals(mypass)){
pw.print("login successful");  
		}		else{
			pw.print("login failed"); 
	}
}
}
