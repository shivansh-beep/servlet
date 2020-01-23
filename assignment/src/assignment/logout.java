package assignment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class logout extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		
		ServletContext context=getServletContext();
		String s= (String)context.getAttribute("name");
		if(s==null){
			pw.print("log in first : "+"<a href='login.html'>login</a>");
		}
		else{
			context.removeAttribute("name");
			pw.print("logout successful");
		}
		
		
}
}
