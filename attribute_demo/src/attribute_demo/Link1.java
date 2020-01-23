package attribute_demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Link1")
public class Link1 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		
		ServletContext context=getServletContext();
		String s= (String)context.getAttribute("user");
		
	  if(s==null){
		  pw.print("you logged out please sign in");
	  }
	  else{
		pw.print("login from"+s);
	  }
	}

}
