package attribute_demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Link3")
public class Link3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		
		ServletContext context=getServletContext();
		String s= (String)context.getAttribute("user");
		pw.print("logout from : "+s);
		context.removeAttribute("user");
	}

}
