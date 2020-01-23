package com.faculty.ust;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Servlet implementation class Fetch
 */
@WebServlet("/Fetch")
public class Fetch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		RequestDispatcher rd;

		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();

		Criteria cr = ss.createCriteria(BatchDTO.class);
		List<BatchDTO> bl = cr.list();
		
		pw.print("<form action='Detail' method='get'>" + "<center>" + "<h4>Select Batch</h4>" + "<div>"
				+ "<select name='bname'>" + "<option value='select disables'>select</option>" + "<br>");
		for (BatchDTO batchDTO : bl) {
			batchDTO.getBname();
			pw.print("<option value='" + batchDTO.getBname() + "'>" + batchDTO.getBname() + "</option>");
		}
		pw.print("</select>" + "</div>" + "<br>");
		pw.print( "<input type='submit' name='submit'/>" + "</center>" + "</form><br>");
	}

		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}


