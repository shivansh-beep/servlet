package attribute_demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class MyServlet3 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String user=req.getParameter("username");
		String pass= req.getParameter("password");
		String myuser="admin";
		String mypass="1234";
				
		PrintWriter pw=resp.getWriter();
	    ServletContext context=getServletContext();
	
	if(user.equals(myuser)&& pass.equals(mypass)){
		
		context.setAttribute("user", user);
		
pw.print("login successful ms."+ user); 
pw.print("<a href= 'Link1' >link1</a> | <a href= 'Link2' >link2</a>| <a href= 'Link3' >link3</a>");

   

		}		else{
			pw.print("login failed"); 
	}
}
}
