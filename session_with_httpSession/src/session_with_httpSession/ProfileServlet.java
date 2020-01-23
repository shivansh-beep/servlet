package session_with_httpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		pw.print("<br>");
		HttpSession session=request.getSession(false);
		if(session!=null){
		  pw.print("succsessfully loggedin: "+session.getAttribute("user"));	
		}
		else{
			pw.print("<h3>login first </h3> <br>");
			request.getRequestDispatcher("log.html").include(request, response);
		}
		
	}

}
