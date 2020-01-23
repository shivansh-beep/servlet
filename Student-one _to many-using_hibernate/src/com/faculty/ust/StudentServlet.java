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
import org.hibernate.criterion.Restrictions;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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
		pw.print("<head>" + "<meta charset='UTF-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "<meta http-equiv='X-UA-Compatible' content='ie=edge'>" + "<title>Document</title>"
				+ "<link rel='stylesheet' href='bootstrap-4.4.1-dist/css/bootstrap.min.css'>"
				+ "<link rel='stylesheet' href='fontawesome-free-5.12.0-web/css/all.css'>" + "</head>");

		pw.print("<body>" + "<div class='container '>"
				+ "<div class='card shadow bg-white rounded col-md-6 offset-3 mt-5'>" + "<div>");

		pw.print("<form action='AddStudents' method='get' role='form'>" + "<center>" + "<h4>Select Batch</h4>"
				+ "<div class='form-group'>" + "<select name='bname'>"
				+ "<option value='select disables'>select</option>" + "<br>");

		for (BatchDTO batchDTO : bl) {
			batchDTO.getBname();
			pw.print("<option value='"+batchDTO.getBname()+"'>"+batchDTO.getBname()+"</option>");
		}
		pw.print("</select>" + "</div>" + "<br>");
		pw.print("<div class='form-group'>"
				+ " <label>Student Name </label> <input type='text' name='sname'  class='form-control'/>" + "</div>"
				+ "<div class='form-group'>"
				+ "<label>contact </label> <input type='text' name='ct'  class='form-control'/>" + "</div>"
				+ "<div class='form-group'>"
				+ "<label>email</label><input type='text' name='em'  class='form-control'/>" + "</div>"
				+ "<div class='form-group'>"
				+ "<label>percentage*</label> <input type='text' name='pc'  class='form-control'/>" + "</div>"
				+ "<div class='form-group'>"
				+ "<label>YOP*</label> <input type='text' name='yop'  class='form-control'/>" + "</div>"
				+ "<input type='submit' name='submit' class='btn btn-success float-center'/>" + "</center>" + "</form>");

		pw.print("</div>" + "</div>" + "</div>" + "<script src='jquery/jquery-3.4.1.js'></script>"
				+ "<script src='bootstrap-4.4.1-dist/js/bootstrap.min.js'></script>" + "</body>" + "</html>");
		ss.close();
		sf.close();
	}

}
