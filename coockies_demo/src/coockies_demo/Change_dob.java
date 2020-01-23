package coockies_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Change_dob
 */
@WebServlet("/Change_dob")
public class Change_dob extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		    PrintWriter pw=response.getWriter();
		     RequestDispatcher rd ;
		     RequestDispatcher rd1 ;

		  Cookie[] arr=request.getCookies();
			Cookie ck=arr[0];
			try{	
				
			     if((ck!=null)&&(!ck.getValue().equals(""))){
			    	String  nDOB=request.getParameter("nDOB");
			    	if(nDOB==""){
			    		pw.print("please enter valid DOB<br>");
			    		
			    		rd=request.getRequestDispatcher("DOB.html");
			    		rd.include(request, response);
			    		
			    	}
			    	else{		    	
			    	
			    	  
			    	  Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
							PreparedStatement pst=con.prepareStatement("UPDATE ecom SET DOB= ? where name=? ");
							pst.setString(1,nDOB);
							pst.setString(2,ck.getValue());
							pst.execute();
			    	 pw.print("<h4>DOB changed into : "+nDOB+"</h4>");
			    	 rd=request.getRequestDispatcher("index.html");
			    	 rd.include(request, response);
			    	} 
			     }
			     else{
			    	 pw.print("<h1>please login first</h1>");
			    	 
			    	 rd=request.getRequestDispatcher("index.html");
			    	rd.include(request, response);
			    	  rd1=request.getRequestDispatcher("login.html");
			    		rd1.include(request, response); 
			     }
			     
				}
				 catch(NullPointerException e){
					   pw.print("<h4 style='color : red'>login failed </h4>");
				    	rd = request.getRequestDispatcher("login.html");
				    	 rd.include(request, response);
				   } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
