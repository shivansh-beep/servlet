package session_with_httpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LgoutServlet
 */
@WebServlet("/LgoutServlet")
public class LgoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		pw.print("<br>");
		HttpSession session=request.getSession(false);
		if(session!=null){
			pw.print("Logout Success"+session.getAttribute("user"));
			session.invalidate();
		}
		else{
			pw.print("<h3>login first </h3> <br>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
	
	}

}
