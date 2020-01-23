package project_assignment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			 RequestDispatcher rd;
			 RequestDispatcher rd1 ;
		try{	Cookie[] arr=request.getCookies();
			Cookie ck=arr[0];
			
			if((ck!=null)&&(!ck.getValue().equals(""))){
			ck=new Cookie("k1","");
			response.addCookie(ck);
			pw.print("<h1>log out successful"+ck.getValue()+"</h1><br><br>");
			 
	    	rd=request.getRequestDispatcher("welcome.html");
	    	
	    	 rd.include(request, response);
			}
			else{
				pw.print("please login first");
				
				 rd1=request.getRequestDispatcher("login.html");
		    		rd1.include(request, response);   
				rd=request.getRequestDispatcher("welcome.html");
		    	
		    	 rd.include(request, response);
			}
		}
		catch(NullPointerException e){
			pw.print("please login");
			  rd1=request.getRequestDispatcher("login.html");
	    		rd1.include(request, response); 
			rd=request.getRequestDispatcher("welcome.html");
	    	
	    	 rd.include(request, response);
			
		}
			
			
			
	}

}
