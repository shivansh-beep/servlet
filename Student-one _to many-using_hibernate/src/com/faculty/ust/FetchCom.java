package com.faculty.ust;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class FetchCom
 */
@WebServlet("/FetchCom")
public class FetchCom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		RequestDispatcher rd;

		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();

		Criteria cr = ss.createCriteria(CompanyDTO.class);
		List<CompanyDTO> bl = cr.list();

		pw.print(
				"<center><table border='1'><tr><th>ID</th> <th>Name</th> <th>Percentage</th> <th>Vacancies</th> <th>YOP</th> <th>JD</th> <th>ADD</th></center>");
		for (CompanyDTO cd : bl) {

			pw.print("<tr><td>" + cd.getCid() + "</td><td>" + cd.getCname() + "</td>" + "<td>"
					+ cd.getPercentage() + "</td>" + "<td>" + cd.getVacancy()+ "</td>" + "<td>"
					+cd.getYop() + "</td>"+ "<td>"
					+cd.getJobDescription() + "</td>"+"<td><a href='AddStDrive?id="+cd.getCid()+"'>Eligible Student</a>"
					+ "</td>" + "</tr>");

		}
		
		ss.close();
		sf.close();
		rd = request.getRequestDispatcher("index.html");
		rd.include(request, response);

	}

}
