package life_cycle;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyServlet2 extends GenericServlet {

	@Override
	public void service(ServletRequest arg0, ServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html");
			resp.getWriter().print("hello from servlet 2"); 
			
			ServletContext context=getServletContext();
			 String coinfo=context.getInitParameter("co");
			 resp.getWriter().print("<br><h2>"+coinfo+"</h2>");
		 	
			 ServletConfig config=getServletConfig();
		 String info=config.getInitParameter("k2");
		 resp.getWriter().print("<br><h3>"+info+"</h3>");
}
	}


